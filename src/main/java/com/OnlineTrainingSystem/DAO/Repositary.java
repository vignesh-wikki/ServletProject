package com.OnlineTrainingSystem.DAO;

import com.OnlineTrainingSystem.Model.Course;
import com.OnlineTrainingSystem.Model.Student;
import com.OnlineTrainingSystem.Model.UserCreditionals;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.http.HttpServlet;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repositary extends HttpServlet {
    private static final String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    private static final String name = "system";
    private static final String password = "tiger";
    private UserCreditionals user;
    private Student newStudent;
    private Course newCourse;
    private static HikariDataSource connection;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(name);
        config.setPassword(password);
        config.setDriverClassName("oracle.jdbc.OracleDriver");
        config.setMaximumPoolSize(10);
        connection = new HikariDataSource(config);
        System.out.println("Database connection pool established.");
    }

    public Connection getConnection() throws SQLException {
        return connection.getConnection();
    }

    public void setNewCourse(Course newCourse) {
        this.newCourse = newCourse;
    }

    public void setNewStudent(Student newStudent) {
        this.newStudent = newStudent;
    }

    public UserCreditionals getUser() {
        return user;
    }

    public void setUser(UserCreditionals user) {
        this.user = user;
    }

    public String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    public boolean requestLogging() throws SQLException, ClassNotFoundException {
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("select userName,userPassword from student where userEmail=?");) {
            statement.setString(1, user.getUserEmail());
            ResultSet records = statement.executeQuery();
            if (records.next()) {
                String storedPassword = records.getNString("userPassword");
                if (checkPassword(user.getUserPassword(), storedPassword)) {
                    System.out.println("Logined successfully");
                    return true;
                }
            } else System.out.println("Error happened");
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean requestRegistering() {
        int row = 0;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("insert into student (userName,userDepartment,userEmail,userPassword) values (?,?,?,?)");) {
            statement.setString(1, newStudent.getName());
            statement.setString(2, newStudent.getDepartment());
            statement.setString(3, user.getUserEmail());
            statement.setString(4, hashPassword(user.getUserPassword()));
            row = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (row > 0) {
                System.out.println("Successfully Registered");
                return true;
            } else System.out.println("Student not Registered");
        }
        return false;
    }

    public boolean requestCourseRegister() {
        int row = 0;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("insert into course (courseName,Description) values (?,?)")) {
            statement.setString(1, newCourse.getCourseName());
            statement.setString(2, newCourse.getCourseDescription());
            row = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (row > 0) {
                System.out.println("Successfully Course Registered");
                return true;
            } else {
                System.out.println("Course not Registered");
            }
        }
        return false;
    }

    public List<Course> getAllCources() throws ClassNotFoundException {
        List<Course> list = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("select * from course");) {
            ResultSet records = statement.executeQuery();
            while (records.next()) {
                newCourse = new Course();
                newCourse.setCourseName(records.getNString("coursename"));
                newCourse.setCourseDescription(records.getNString("description"));
                newCourse.setId(records.getInt("id"));
                list.add(newCourse);
            }
            System.out.println("Successfully courses retrieved");
        } catch (Exception e) {
            System.out.println("Course not retrived");
            System.out.println(e);
        }
        return list;
    }

    public boolean isRegistered(String userEmail, String courseName) {
        boolean isRegistered = false;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT s.id AS student_id, c.id AS course_id FROM Student s JOIN Student_Course sc ON s.id = sc.student_id JOIN Course c ON c.id = sc.course_id WHERE c.courseName = ? AND s.userEmail = ?")) {
            statement.setString(1, courseName);
            statement.setString(2, userEmail);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) isRegistered = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isRegistered;
    }

    public boolean registerStudentCourse(String courseName, String userEmail) {
        boolean isUserAlreadyRegisteredThisCourse = isRegistered(userEmail, courseName);
        if (isUserAlreadyRegisteredThisCourse) return false;
        int studentId = 0;
        int courseId = 0;
        int row = -1;
        boolean isCourseAlreadyRegistered;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT s.id AS student_id, c.id AS course_id FROM Student s JOIN Course c ON c.courseName = ? WHERE s.userEmail = ?")) {
            statement.setString(1, courseName);
            statement.setString(2, userEmail);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                studentId = result.getInt("student_id");
                courseId = result.getInt("course_id");
            }

            PreparedStatement statement0 = connection.prepareStatement("select id from student_course where course_id=? and student_id=?");
            statement0.setInt(1, courseId);
            statement0.setInt(2, studentId);
            isCourseAlreadyRegistered = statement0.execute();

            if (isCourseAlreadyRegistered) {
                PreparedStatement statement1 = connection.prepareStatement("insert into student_course (student_id,course_id) values (?,?)");
                statement1.setInt(1, studentId);
                statement1.setInt(2, courseId);
                row = statement1.executeUpdate();
                System.out.println(row + " rows number");
                if (row < 0) System.out.println("Something went wrong can't registered");
            } else return false;

        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    public Map<String, List<Course>> getUserRegisteredCourse(String userEmail) throws ClassNotFoundException {
        Map<String, List<Course>> registerCourseData = new HashMap<>();
        List<Course> list = new ArrayList<>();
        String userName = null;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT s.userName, c.courseName, c.Description FROM Student s JOIN Student_Course sc ON s.id = sc.student_id JOIN Course c ON c.id = sc.course_id WHERE s.userEmail = ?")) {
            statement.setString(1, userEmail);
            ResultSet records = statement.executeQuery();
            if (records.next()) {
                userName = records.getNString("userName");
                System.out.println(userName);
                newCourse = new Course();
                newCourse.setCourseName(records.getNString("courseName"));
                newCourse.setCourseDescription(records.getNString("Description"));
                list.add(newCourse);
            }
            while (records.next()) {
                newCourse = new Course();
                newCourse.setCourseName(records.getNString("courseName"));
                newCourse.setCourseDescription(records.getNString("Description"));
                list.add(newCourse);
            }
            System.out.println(list);
            registerCourseData.put(userName, list);

            System.out.println("Successfully registered courses retrived");

        } catch (Exception e) {
            System.out.println("Not courses retrived");
            System.out.println(e);
        }
        return registerCourseData;
    }

    public Map<List<UserCreditionals>, List<Student>> getAllUsers() {
        Map<List<UserCreditionals>, List<Student>> map = new HashMap<>();
        List<Student> studentList = new ArrayList<>();
        List<UserCreditionals> userList = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("select * from student"); ResultSet records = statement.executeQuery()) {
            while (records.next()) {
                newStudent = new Student();
                user = new UserCreditionals();
                newStudent.setName(records.getNString("username"));
                newStudent.setDepartment(records.getNString("userdepartment"));
                newStudent.setId(records.getInt("id"));
                user.setUserEmail(records.getString("useremail"));
                userList.add(user);
                studentList.add(newStudent);
            }
            map.put(userList, studentList);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return map;
    }

    public Map<UserCreditionals, Student> getEditUserDetail(int userId) {
        Map<UserCreditionals, Student> map = new HashMap<>();

        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("select * from student where id=?");) {
            statement.setInt(1, userId);
            ResultSet records = statement.executeQuery();
            while (records.next()) {
                newStudent = new Student();
                user = new UserCreditionals();
                newStudent.setName(records.getNString("username"));
                newStudent.setDepartment(records.getNString("userdepartment"));
                newStudent.setId(records.getInt("id"));
                user.setUserEmail(records.getString("useremail"));
                map.put(user, newStudent);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return map;
    }

    public boolean updateUser(String username, String department, String email, int id) {
        int row = 0;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("update student set username=?,userdepartment=?,useremail=? where id = ?")) {
            statement.setString(1, username);
            statement.setString(2, department);
            statement.setString(3, email);
            statement.setInt(4, id);
            row = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        if (row > 0) return true;
        return false;
    }

    public boolean deleteUser(int id) {
        int row = 0;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("delete from student where id=?")) {
            statement.setInt(1, id);
            row = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        if (row > 0) return true;
        return false;
    }

    public boolean deleteCourse(int id) {
        int row = 0;
        System.out.println(id);
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("delete from course where id=?")) {
            statement.setInt(1, id);
            row = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        if (row > 0) return true;
        return false;
    }

    public boolean updateCourse(int courseId, String courseName, String courseDescription) {
        int row = 0;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("update course set coursename=?,description=? where id = ?")) {
            statement.setString(1, courseName);
            statement.setString(2, courseDescription);
            statement.setInt(3, courseId);
            row = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        if (row > 0) return true;
        return false;
    }

    public Course getCourseDetail(int courseId) {

        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("select * from course where id=?");) {
            statement.setInt(1, courseId);
            ResultSet records = statement.executeQuery();
            while (records.next()) {
                newCourse = new Course();
                newCourse.setCourseName(records.getNString("coursename"));
                newCourse.setCourseDescription(records.getNString("description"));
                newCourse.setId(records.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return newCourse;
    }

    @Override
    public void destroy() {
        super.destroy();
        if (connection != null) {
            connection.close();
            System.out.println("Database connection pool closed.");
        }
    }
}


