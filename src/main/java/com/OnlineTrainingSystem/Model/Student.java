package com.OnlineTrainingSystem.Model;

import java.util.List;

public class Student {
    private int id;
    private String name;
    private String department;
    private List<Course> studentEnrolledCourseList;

    public List<Course> getStudentEnrolledCourseList() {
        return studentEnrolledCourseList;
    }

    public void setStudentEnrolledCourseList(List<Course> studentEnrolledCourseList) {
        this.studentEnrolledCourseList = studentEnrolledCourseList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
