<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ page import="java.util.*, com.OnlineTrainingSystem.Model.Student, com.OnlineTrainingSystem.Model.UserCreditionals,com.OnlineTrainingSystem.Model.Course" %>
<jsp:include page="/navbar.jsp" />
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
    <title>Admin Page</title>
</head>
<body class="box-border">
<%
    Map<List<UserCreditionals>, List<Student>> userStudentMap =
        (Map<List<UserCreditionals>, List<Student>>) request.getAttribute("allUsers");

    if (userStudentMap == null || userStudentMap.isEmpty()) {
%>
        <p class="text-center text-gray-500 mt-8">No user data available.</p>
<%
    } else {
%>
        <div class="mx-[5.5rem] mt-[8rem] border-2 w-[7rem] h-10 rounded-md border-green-300 flex justify-center items-center"><p class="text-center font-bold tracking-wide text-xl uppercase">Users</p></div>
<br/>
        <div class="border relative overflow-x-auto sm:rounded-lg mx-[5.5rem] mb-[5rem]">
            <table class="w-full text-sm text-left rtl:text-right text-gray-500">
                <thead class="text-xs uppercase bg-gray-300 text-black">
                    <tr>
                        <th scope="col" class="px-6 py-3">Email</th>
                        <th scope="col" class="px-6 py-3">Name</th>
                        <th scope="col" class="px-6 py-3">Department</th>
                        <th scope="col" class="px-6 py-3">Delete</th>
                        <th scope="col" class="px-6 py-3">Edit</th>
                    </tr>
                </thead>
                <tbody>
                <%
                    for (Map.Entry<List<UserCreditionals>, List<Student>> entry : userStudentMap.entrySet()) {
                        List<UserCreditionals> userCredentialsList = entry.getKey();
                        List<Student> studentList = entry.getValue();

                        for (int i = 0; i < userCredentialsList.size() && i < studentList.size(); i++) {
                            UserCreditionals userCred = userCredentialsList.get(i);
                            Student student = studentList.get(i);
                %>
                            <tr class="bg-slate-50 border-b hover:bg-gray-50">
                                <td class="px-6 py-4"><%= userCred.getUserEmail() %></td>
                                <td class="px-6 py-4"><%= student.getName() %></td>
                                <td class="px-6 py-4"><%= student.getDepartment() %></td>
                                <td class="px-6 py-4">
                                    <a href="deleteUser/<%= student.getId() %>" class="text-red-600">Delete</a>
                                </td>
                                <td class="px-6 py-4">
                                    <a href="getUserDetail/<%= student.getId() %>" class="font-medium text-blue-600 hover:underline">Edit user</a>
                                </td>
                            </tr>

                <%
                        }
                    }

                %>
                </tbody>
            </table>
        </div>
<%
    }
%>
  <%
      List<Course> courses = (List<Course>) request.getAttribute("courses");
      if (courses == null || courses.isEmpty()) {
  %>
          <p class="text-center text-gray-500 mt-8">No courses available.</p>
  <%
      } else {
  %>
  <div class="mx-[5.5rem] mt-[5rem] border-2 w-[7rem] h-10 rounded-md border-green-300 flex justify-center items-center"><p class="text-center font-bold tracking-wide text-xl uppercase">Courses</p></div>
  <br/>
          <div class="border relative overflow-x-auto sm:rounded-lg mx-[5.5rem] mb-[8.5rem]">
              <table class="w-full text-sm text-left rtl:text-right text-gray-500">
                  <thead class="text-xs uppercase bg-gray-300 text-black">
                      <tr>
                          <th scope="col" class="px-6 py-3"> Course Id</th>
                          <th scope="col" class="px-6 py-3"> Course Name</th>
                          <th scope="col" class="px-6 py-3">Course Description</th>
                          <th scope="col" class="px-6 py-3">Delete</th>
                          <th scope="col" class="px-6 py-3">Edit</th>
                      </tr>
                  </thead>
                  <tbody>
                  <%
                      for (Course course : courses) {
                  %>

                              <tr class="bg-slate-50 border-b hover:bg-gray-50">
                               <td class="px-6 py-4"><%= course.getId() %></td>
                                  <td class="px-6 py-4"><%= course.getCourseName() %></td>
                                  <td class="px-6 py-4"><%= course.getCourseDescription() %></td>
                                  <td class="px-6 py-4">
                                      <a href="deleteCourse/<%= course.getId() %>" class="text-red-600">Delete</a>
                                  </td>
                                  <td class="px-6 py-4">
                                      <a href="getCourseDetail/<%= course.getId() %>" class="font-medium text-blue-600 hover:underline">Edit user</a>
                                  </td>
                              </tr>

                <%
                    }
                %>
                  </tbody>
              </table>
          </div>
  <%
      }
  %>
  </div>
</div>
</body>
</html>
