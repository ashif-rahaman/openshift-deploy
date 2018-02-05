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
        <title>Login</title>
        <link href="resources/stylesheets/genstyle.css" rel="stylesheet" type="text/css"/>
        <link href="resources/stylesheets/library/w3-theme-blue.css" rel="stylesheet" type="text/css"/>
        <link href="resources/stylesheets/library/w3.css" rel="stylesheet" type="text/css"/>
        <script src="resources/script/indexscript.js" type="text/javascript"></script>
    </head>
    <body class="w3-theme-light" style="font-family: 'Lato', sans-serif;">

        <div class="w3-container w3-padding-64">

            <div class="w3-margin-bottom w3-center">
                <img src="resources/images/icons/leave-small.png" alt="logo-small"/><br>
                <label class="w3-xxlarge w3-center"><b>Login to My Forgo</b></label>
            </div>

            <div class="w3-row" style="margin-left: 33%">
                <div class="w3-half w3-large">

                    <!--login form-->

                    <form action="login" method="POST" onsubmit="return loginRequest()">

                        <label class="">Userid ( or Email )</label>
                        <input name="loginid" onblur="idEmailValidator()" class="w3-xlarge w3-input w3-round-large w3-light-gray w3-margin-bottom" type="text" placeholder="e.g., ashif-rahaman">

                        <label>Password</label>
                        <input name="password" onblur="passwordValidator()" class="w3-xlarge w3-input w3-round-large w3-light-gray" type="password" placeholder="e.g., *********">

                        <div class="w3-row w3-margin-top">
                            <input type="submit" value="Login" class="w3-btn w3-theme-l1 w3-round" style="padding-left: 7%; padding-right: 7%;">
                            <a  href="signup" class="w3-half w3-right w3-text-theme" style="margin-top: 3%">Create an account</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
