<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ page import="java.util.*,com.OnlineTrainingSystem.Model.Course" %>
<!doctype html>
<script src="https://cdn.tailwindcss.com"></script>
<script>
   document.addEventListener('DOMContentLoaded', () => {
           document.getElementById('closeButton').addEventListener('click', () => {
               window.location.href = "/ServeletProject/admin"; // Ensure this path is correct
           });
       });
</script>
<html>
  <body class="font-sans">
    <div class="flex h-1 justify-center">
    <img src="https://uxwing.com/wp-content/themes/uxwing/download/brands-and-social-media/spring-boot-icon.png" alt="logo" class="h-[3rem] w-[3rem] mt-10" />
        </div>
    <div class="grid h-screen place-items-center">
      <div>
      <%
          Course course = (Course) session.getAttribute("CourseDetails");

          if (course == null) {
      %>
              <p class="text-center text-gray-500 mt-8">No course data available.</p>
      <%
          } else {
      %>
        <h1 class="pb-3 text-center text-[2.4rem] font-extrabold">Edit User Details</h1>

        <form action="/ServeletProject/editCourse" method="post">
        <div class="rounded-md bg-white p-3">
        <div hidden class="relative bg-inherit">
        <input  value="<%=course.getId()%>" type="number" id="courseid" name="courseid" class="focus:border-rose-[#14b8a6] peer h-12 w-80 rounded-md bg-transparent px-2 text-gray-500 placeholder-transparent ring-1 ring-gray-400 focus:outline-none focus:ring-[#14b8a6]" placeholder="*courseid" required/>
        <label for="courseid" class="absolute -top-3 left-4 mx-1 cursor-text rounded-lg bg-inherit px-1 text-sm text-gray-500 transition-all peer-placeholder-shown:top-3 peer-placeholder-shown:text-base peer-placeholder-shown:text-gray-500 peer-focus:-top-3 peer-focus:text-sm peer-focus:text-[#14b8a6]">*courseid</label></div>
        </div>
        <div class="rounded-md bg-white p-3">
        <div class="relative bg-inherit">
        <input value="<%=course.getCourseName()%>" type="text" id="coursename" name="coursename" class="focus:border-rose-[#14b8a6] peer h-12 w-80 rounded-md bg-transparent px-2 text-gray-500 placeholder-transparent ring-1 ring-gray-400 focus:outline-none focus:ring-[#14b8a6]" placeholder="*coursename" required/>
        <label for="coursename" class="absolute -top-3 left-4 mx-1 cursor-text rounded-lg bg-inherit px-1 text-sm text-gray-500 transition-all peer-placeholder-shown:top-3 peer-placeholder-shown:text-base peer-placeholder-shown:text-gray-500 peer-focus:-top-3 peer-focus:text-sm peer-focus:text-[#14b8a6]">*coursename</label></div>
        </div>
        <div class="rounded-md bg-white p-3">
        <div class="relative bg-inherit">
        <input value="<%=course.getCourseDescription()%>" type="text" id="coursedescription" name="coursedescription" class="focus:border-rose-[#14b8a6] peer h-12 w-80 rounded-md bg-transparent px-2 text-gray-500 placeholder-transparent ring-1 ring-gray-400 focus:outline-none focus:ring-[#14b8a6]" placeholder="*coursedescription" required/>
        <label for="coursedescription" class="absolute -top-3 left-4 mx-1 cursor-text rounded-lg bg-inherit px-1 text-sm text-gray-500 transition-all peer-placeholder-shown:top-3 peer-placeholder-shown:text-base peer-placeholder-shown:text-gray-500 peer-focus:-top-3 peer-focus:text-sm peer-focus:text-[#14b8a6]">*Description</label></div>
        </div>

      <div class="text-[#dc2626] text-base ps-14">
          ${errorMessage}
      </div>
        <div class="">
          <div class="m-3 flex h-12 w-80 cursor-pointer justify-center rounded-md bg-[#10b981] text-lg font-medium tracking-wider text-white">
                      <button type="submit">Save</button>
          </div>

        </div>

        </form>
         <div class="m-3 flex h-12 w-80 cursor-pointer justify-center rounded-md text-black bg-slate-50 border text-lg font-medium tracking-wider">
         <button id="closeButton" type="buttom" onclick="close()">Close</button>
         </div>
        <%
        }
        %>
      </div>
    </div>
  </body>
</html>


