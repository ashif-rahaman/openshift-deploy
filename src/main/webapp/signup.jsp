<%-- 
    Document   : index
    Created on : Dec 17, 2017, 9:36:05 PM
    Author     : ashif
--%>
<%
    String user = (String) request.getSession().getAttribute("userKey");
    if (user != null) {

        response.sendRedirect("home");
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" type="image/x-icon" href="resources/images/icons/leave16x16.png" />
        <title>Signup</title>
        <link href="resources/stylesheets/genstyle.css" rel="stylesheet" type="text/css"/>
        <link href="resources/stylesheets/library/w3-theme-blue.css" rel="stylesheet" type="text/css"/>
        <link href="resources/stylesheets/library/w3.css" rel="stylesheet" type="text/css"/>
        <script src="resources/script/signupscript.js" type="text/javascript"></script>
    </head>
    <body class="w3-theme-light" style="font-family: 'Lato', sans-serif;">

        <div class="w3-container w3-padding-64">

            <div class="w3-margin-bottom w3-center">

                <label class="w3-xxlarge w3-center"><b>My Forgo Sign Up</b></label>
            </div>

            <div class="w3-row" style="">
                <img class="w3-quarter" src="resources/images/icons/leave_large.png" alt="leave_large" width="30%" style="filter: opacity(50%);"/>

                <div class="w3-half w3-large" style="padding-left: 10%">
                    <form action="signup" method="POST" onsubmit="return signupValidation()">
                        <label class="">First Name</label>
                        <input name="first_name" onkeyup="firstNameValidator(this.value)" class="w3-xlarge w3-input w3-round-large w3-light-gray w3-margin-bottom" style="display: inline" type="text" placeholder="e.g., Ashifur"><span id="first_name_sign"></span><br>

                        <label class="">Last Name</label>
                        <input name="last_name" onkeyup="lastNameValidator(this.value)" class="w3-xlarge w3-input w3-round-large w3-light-gray w3-margin-bottom" style="display: inline" type="text" placeholder="e.g., Rahaman"><span id="last_name_sign"></span><br>

                        <label class="">Username</label><span id="username_msg"></span>
                        <input name="username" onkeyup="userNameValidator(this.value)" class="w3-xlarge w3-input w3-round-large w3-light-gray w3-margin-bottom" style="display: inline" type="text" placeholder="Username to login"><span id="username_sign"></span><br>

                        <label class="">Email</label><span id="email_msg"></span>
                        <input name="email" onkeyup="emailValidator(this.value)" class="w3-xlarge w3-input w3-round-large w3-light-gray w3-margin-bottom" style="display: inline" type="text" placeholder="e.g., email@example.com"><span id="email_sign"></span><br>

                        <label class="">Password</label><span id="password_length_msg"></span>
                        <input name="password" onkeyup="passwordValidator(this.value)" class="w3-xlarge w3-input w3-round-large w3-light-gray w3-margin-bottom" style="display: inline" type="password" placeholder="e.g., *********"><span id="password_sign"></span><br>

                        <label>Repeat Password</label>
                        <input name="repeat_password" onkeyup="repeatPasswordValidator(this.value)" class="w3-xlarge w3-input w3-round-large w3-light-gray" style="display: inline" type="password" placeholder="e.g., *********"><span id="repeat_password_sign"></span><br>

                        <div class="w3-row w3-margin-top">
                            <input type="submit" value="Sign Up" class="w3-btn w3-theme-l1 w3-round" style="padding-left: 7%; padding-right: 7%;">
                            <a  href="index" class="w3-right w3-text-theme" style="margin-top: 3%">Already have an account</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
