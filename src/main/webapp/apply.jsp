<%-- 
    Document   : apply
    Created on : Dec 30, 2017, 1:20:55 PM
    Author     : ashif
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
    String user = (String) request.getSession().getAttribute("userKey");
    if (user == null) {

        response.sendRedirect("index");
        return;
    }

    String applicant_name = (String) request.getSession().getAttribute("Applicant_Name");
    String boss_name = (String) request.getSession().getAttribute("Super_Name");

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();

%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <head>
        <link rel="shortcut icon" type="image/x-icon" href="resources/images/icons/leave16x16.png" />
        <title>Apply</title>
        <link href="resources/stylesheets/genstyle.css" rel="stylesheet" type="text/css"/>
        <link href="resources/stylesheets/library/w3-theme-blue.css" rel="stylesheet" type="text/css"/>
        <link href="resources/stylesheets/library/w3.css" rel="stylesheet" type="text/css"/>
        <script src="resources/script/applicationscript.js" type="text/javascript"></script>
        <script src="resources/script/manubarscript.js" type="text/javascript"></script>
    </head>
</head>
<body onload="showPendingMenu('<%= user%>')">
    <div class="navbar container w3-theme-l1 w3-row">
        <img src="resources/images/icons/leave-small.png" class="w3-center" height="100%" alt="leave-small"/>
        <div class="navbar-menu">
            <a href="" style="text-decoration: none;">
                <span class="menu-item w3-hover-text-black" onclick="">Apply</span>
            </a>
            <a href="" style="text-decoration: none;">
                <span class="menu-item w3-hover-text-black" onclick="">My Application</span>
            </a>
            <a id="pending_menu" onclick="" href="" style="text-decoration: none; visibility: hidden" class="w3-hover-text-black">
                <span class="menu-item">Pending Application</span>
                <span id="notification" class="w3-badge w3-large w3-red w3-hover-green">6</span>
            </a>
            <a href="logout" style="text-decoration: none;">
                <span class="menu-item w3-hover-text-red w3-text-dark-gray">Logout</span>
            </a>
        </div>
    </div>
    <div class="w3-row w3-border">

        <!--left side-->
        <div class="w3-container w3-col w3-padding-24" style="width: 20%">
            <img class="w3-center" src="resources/images/dummy/dummy_man.png" alt="dummy_man" width="70%" style="display: block"/>
            <span id="name" style="display: block; padding: 20px 0px 0px 30px"><%= applicant_name%></span>
        </div>

        <!--right side-->
        <div class="w3-container w3-rest w3-card-4">
            <form method="POST" action="applyinsert" onsubmit="return applicationInsertValidator()">
                <input id="apply_to" type="text" class="apply-input w3-large w3-input" <% if (boss_name != null) {%>placeholder="To : <%= boss_name%>"<% }%> disabled>

                <input id="apply_subject" onblur="subjectValidator(this.value)" name="apply_subject" type="text" class="apply-input w3-input w3-xxlarge" placeholder="Subject">

                <textarea id="apply_body" onblur="bodyValidator(this.value)" name="apply_body" class="apply-input w3-input" placeholder="Write your application"></textarea>
                <!--<input id="leave_days" onblur="leaveDaysValidator(this.value)" class="apply-input w3-input" type="number" min="1" placeholder="Leave days">-->

                <span class="apply-input">Start Date:</span>
                <input id="start_date" class="apply-input w3-input" name="start_date" type="date" min="<%= df.format(date)%>">

                <span class="apply-input">End Date:</span>
                <input id="end_date" class="apply-input w3-input" name="end_date" type="date" min="<%= df.format(date)%>">

                <input type="submit" class="w3-button w3-theme-dark w3-hover-green w3-hover-text-white input-button w3-large" value="Apply">
            </form>
        </div>
    </div>
</body>
</html>

<%
    applicant_name = null;
    boss_name = null;
%>
