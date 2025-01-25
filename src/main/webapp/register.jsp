<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<script src="https://cdn.tailwindcss.com"></script>
<html>
  <body class="no-scrollbar overflow-y-scroll font-sans">
    <div class="flex h-2 justify-center pt-3">
    <img src="https://uxwing.com/wp-content/themes/uxwing/download/brands-and-social-media/spring-boot-icon.png" alt="logo" class="h-[3rem] w-[3rem] mt-10" />
    </div>
    <div class="grid h-screen place-items-center">
    <form action="/ServeletProject/register" method="post">
      <div>
        <h1 class="pb-3 text-center text-[2.4rem] font-extrabold">Welcome back</h1>
        <div class="rounded-md bg-white p-3">
          <div class="relative bg-inherit">
          <input type="text" id="username" name="username" class="focus:border-rose-[#14b8a6] peer h-12 w-80 rounded-md bg-transparent px-2 text-gray-500 placeholder-transparent ring-1 ring-gray-400 focus:outline-none focus:ring-[#14b8a6]" placeholder="*username" required />
          <label for="username" class="absolute -top-3 left-4 mx-1 cursor-text rounded-lg bg-inherit px-1 text-sm text-gray-500 transition-all peer-placeholder-shown:top-3 peer-placeholder-shown:text-base peer-placeholder-shown:text-gray-500 peer-focus:-top-3 peer-focus:text-sm peer-focus:text-[#14b8a6]">*Username</label></div>
        </div>
        <div class="rounded-md bg-white p-3">
          <div class="relative bg-inherit">
          <input type="text" id="userdepartment" name="userdepartment" class="focus:border-rose-[#14b8a6] peer h-12 w-80 rounded-md bg-transparent px-2 text-gray-500 placeholder-transparent ring-1 ring-gray-400 focus:outline-none focus:ring-[#14b8a6]" placeholder="*userdepartment" required/>
          <label for="userdepartment" class="absolute -top-3 left-4 mx-1 cursor-text rounded-lg bg-inherit px-1 text-sm text-gray-500 transition-all peer-placeholder-shown:top-3 peer-placeholder-shown:text-base peer-placeholder-shown:text-gray-500 peer-focus:-top-3 peer-focus:text-sm peer-focus:text-[#14b8a6]">*Department</label></div>
        </div>
        <div class="rounded-md bg-white p-3">
          <div class="relative bg-inherit">
          <input type="email" id="useremail" name="useremail" class="focus:border-rose-[#14b8a6] peer h-12 w-80 rounded-md bg-transparent px-2 text-gray-500 placeholder-transparent ring-1 ring-gray-400 focus:outline-none focus:ring-[#14b8a6]" placeholder="*Enter useremail" required/>
          <label for="useremail" class="absolute -top-3 left-4 mx-1 cursor-text rounded-lg bg-inherit px-1 text-sm text-gray-500 transition-all peer-placeholder-shown:top-3 peer-placeholder-shown:text-base peer-placeholder-shown:text-gray-500 peer-focus:-top-3 peer-focus:text-sm peer-focus:text-[#14b8a6]">*Enter email</label>
          </div>
        </div>
        <div class="rounded-md bg-white p-3">
          <div class="relative bg-inherit">
          <input type="password" id="userpassword" name="userpassword" class="focus:border-rose-[#14b8a6] peer h-12 w-80 rounded-md bg-transparent px-2 text-gray-500 placeholder-transparent ring-1 ring-gray-400 focus:outline-none focus:ring-[#14b8a6]" placeholder="*Enter password" required/>
          <label for="userpassword" class="absolute -top-3 left-4 mx-1 cursor-text rounded-lg bg-inherit px-1 text-sm text-gray-500 transition-all peer-placeholder-shown:top-3 peer-placeholder-shown:text-base peer-placeholder-shown:text-gray-500 peer-focus:-top-3 peer-focus:text-sm peer-focus:text-[#14b8a6]">*Enter password</label>
          </div>
        </div>
        <div class="text-[#dc2626] text-base ps-14">
                  ${errorMessage}
              </div>
        <div class="">
          <div class="m-3 flex h-12 w-80 cursor-pointer justify-center rounded-md bg-[#10b981] text-lg font-medium tracking-wider text-white">
            <button type="submit">Submit</button>
          </div>
          <p class="ms-14">You have an account?<a href="login.jsp" class="cursor-pointer text-[#10b981]"> Login here</a></p>
        </div>
      </div>
      </form>
    </div>
  </body>
</html>




