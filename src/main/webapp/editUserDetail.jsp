<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ page import="java.util.*, com.OnlineTrainingSystem.Model.Student, com.OnlineTrainingSystem.Model.UserCreditionals" %>
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
          Map<UserCreditionals,Student> userStudentMap =
              (Map<UserCreditionals,Student>) session.getAttribute("userDetails");

          if (userStudentMap == null || userStudentMap.isEmpty()) {
      %>
              <p class="text-center text-gray-500 mt-8">No user data available.</p>
      <%
          } else {
      %>
        <h1 class="pb-3 text-center text-[2.4rem] font-extrabold">Edit User Details</h1>
        <%
         for (Map.Entry<UserCreditionals,Student> entry : userStudentMap.entrySet()) {
             UserCreditionals userCredentialsList = entry.getKey();
             Student studentList = entry.getValue();
         %>
        <form action="/ServeletProject/updateUserdetail" method="post">
        <div class="rounded-md bg-white p-3">
        <div class="relative bg-inherit">
        <input value="<%=studentList.getName()%>" type="text" id="username" name="username" class="focus:border-rose-[#14b8a6] peer h-12 w-80 rounded-md bg-transparent px-2 text-gray-500 placeholder-transparent ring-1 ring-gray-400 focus:outline-none focus:ring-[#14b8a6]" placeholder="*Username" required/>
        <label for="username" class="absolute -top-3 left-4 mx-1 cursor-text rounded-lg bg-inherit px-1 text-sm text-gray-500 transition-all peer-placeholder-shown:top-3 peer-placeholder-shown:text-base peer-placeholder-shown:text-gray-500 peer-focus:-top-3 peer-focus:text-sm peer-focus:text-[#14b8a6]">*Username</label></div>
        </div>
        <div class="rounded-md bg-white p-3">
                  <div class="relative bg-inherit">
                  <input value="<%=studentList.getDepartment()%>" type="text" id="userdepartment" name="userdepartment" class="focus:border-rose-[#14b8a6] peer h-12 w-80 rounded-md bg-transparent px-2 text-gray-500 placeholder-transparent ring-1 ring-gray-400 focus:outline-none focus:ring-[#14b8a6]" placeholder="*Department" required/>
                  <label for="userdepartment" class="absolute -top-3 left-4 mx-1 cursor-text rounded-lg bg-inherit px-1 text-sm text-gray-500 transition-all peer-placeholder-shown:top-3 peer-placeholder-shown:text-base peer-placeholder-shown:text-gray-500 peer-focus:-top-3 peer-focus:text-sm peer-focus:text-[#14b8a6]">*Department</label></div>
                </div>
        <div class="rounded-md bg-white p-3">
          <div class="relative bg-inherit">
          <input value="<%=userCredentialsList.getUserEmail()%>" type="email" id="useremail" name="useremail" class="focus:border-rose-[#14b8a6] peer h-12 w-80 rounded-md bg-transparent px-2 text-gray-500 placeholder-transparent ring-1 ring-gray-400 focus:outline-none focus:ring-[#14b8a6]" placeholder="*Enter email address" required/>
          <label for="useremail" class="absolute -top-3 left-4 mx-1 cursor-text rounded-lg bg-inherit px-1 text-sm text-gray-500 transition-all peer-placeholder-shown:top-3 peer-placeholder-shown:text-base peer-placeholder-shown:text-gray-500 peer-focus:-top-3 peer-focus:text-sm peer-focus:text-[#14b8a6]">*Enter email address</label></div>
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
        }
        %>
      </div>
    </div>
  </body>
</html>


