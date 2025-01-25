<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ page import="java.util.*" %>
<%@ page import="com.OnlineTrainingSystem.Model.Course" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<jsp:include page="/navbar.jsp" />


<!doctype html>
<script src="https://cdn.tailwindcss.com"></script>
<html>
<body class="box-border">

<div class="my-[8.5rem] mx-4 md:mx-20">
    <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
        <%
            List<Course> courses = (List<Course>) request.getAttribute("courseList");
            if (courses != null && !courses.isEmpty()) {
                for (Course course : courses) {
        %>
        <div class="relative flex flex-col bg-white shadow-sm border border-slate-200 rounded-lg p-6">
            <div class="flex items-center mb-4">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="h-6 w-6 text-slate-600">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M12 21a9.004 9.004 0 0 0 8.716-6.747M12 21a9.004 9.004 0 0 1-8.716-6.747M12 21c2.485 0 4.5-4.03 4.5-9S14.485 3 12 3m0 18c-2.485 0-4.5-4.03-4.5-9S9.515 3 12 3m0 0a8.997 8.997 0 0 1 7.843 4.582M12 3a8.997 8.997 0 0 0-7.843 4.582m15.686 0A11.953 11.953 0 0 1 12 10.5c-2.998 0-5.74-1.1-7.843-2.918m15.686 0A8.959 8.959 0 0 1 21 12c0 .778-.099 1.533-.284 2.253m0 0A17.919 17.919 0 0 1 12 16.5c-3.162 0-6.133-.815-8.716-2.247m0 0A9.015 9.015 0 0 1 3 12c0-1.605.42-3.113 1.157-4.418" />
                </svg>
                <h5 class="ml-3 text-slate-800 text-xl font-semibold">
                    <%= course.getCourseName() %>
                </h5>
            </div>
            <p class="block text-slate-600 leading-normal font-light mb-4">
                <%= course.getCourseDescription() %>
            </p>
            <div>
                     <%
                     String userEmail = (String) session.getAttribute("userEmail");
                     String courseName = URLEncoder.encode(course.getCourseName(), "UTF-8");
                      %>
                <a href="course/<%= courseName %>/<%= userEmail %>/" class="text-slate-800 font-semibold text-sm hover:underline flex items-center" onclick="this.style.pointerEvents='none';">
                    Enroll
                    <svg xmlns="http://www.w3.org/2000/svg" class="ml-2 h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M14 5l7 7m0 0l-7 7m7-7H3" />
                    </svg>
                </a>
            </div>
        </div>
        <%
                }
            } else {
        %>
        <p>No courses available.</p>
        <% } %>
    </div>
</div>
<jsp:include page="/footer.jsp" />
</body>
</html>

