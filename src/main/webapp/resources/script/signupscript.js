var first_name = false;
var last_name = false;
var username = false;
var email = false;
var password = false;
var repeat_password = false;
/**
 * 
 * @param {type} firstName
 * @returns {undefined}
 */
var firstNameValidator = function (firstName) {

    //modifying the first name field
    firstName = firstName.trim();
    var firstName_field = document.getElementsByName('first_name')[0];
    var sign = document.getElementById('first_name_sign');
    if (firstName.length != 0) {

        first_name = true;
        firstName_field.style.borderColor = '#4ef442';
        sign.innerHTML = ' &#x2714;';
        sign.style.color = '#4ef442';
    } else {

        first_name = false;
        firstName_field.style.borderColor = '#fc0000';
        sign.innerHTML = ' &#x2716;';
        sign.style.color = '#fc0000';
    }
};
/**
 * 
 * @param {type} str
 * @returns {undefined}
 */
var lastNameValidator = function (lastName) {

    lastName = lastName.trim();
    var sign = document.getElementById('last_name_sign');
    var lastName_field = document.getElementsByName('last_name')[0];
    if (lastName.length != 0) {

        last_name = true;
        lastName_field.style.borderColor = "#4ef442";
        sign.innerHTML = ' &#x2714;';
        sign.style.color = '#4ef442';
    } else {

        last_name = false;
        lastName_field.style.borderColor = "#fc0000";
        sign.innerHTML = ' &#x2716;';
        sign.style.color = '#fc0000';
    }
};
/**
 * 
 * @param {type} str
 * @returns {undefined}
 */
var userNameValidator = function (usernameValue) {

    var usernameField = document.getElementsByName('username')[0];
    var sign = document.getElementById('username_sign');
    var usernameMsg = document.getElementById('username_msg');
    if (usernameValue.indexOf('@') !== -1 ||
            usernameValue.indexOf('$') !== -1 ||
            usernameValue.indexOf('%') !== -1 ||
            usernameValue.indexOf('&') !== -1 ||
            usernameValue.indexOf('*') !== -1 ||
            usernameValue.indexOf('!') !== -1 ||
            usernameValue.indexOf('#') !== -1 ||
            usernameValue.indexOf('^') !== -1 ||
            usernameValue.indexOf('(') !== -1 ||
            usernameValue.indexOf(')') !== -1 ||
            usernameValue.indexOf(' ') !== -1) {

        username = false;
        usernameField.style.borderColor = '#fc0000';
        sign.innerHTML = ' &#x2716;';
        sign.style.color = '#fc0000';
        usernameMsg.innerHTML = ' (cannot contains special character \'@\', \'$\', \'%\', or space)';
        usernameMsg.style.color = '#fc0000';
        usernameMsg.style.visibility = 'visible';
    } else if (usernameValue.trim().length < 5) {

        usernameMsg.innerHTML = ' (at least 5 character long)';
        usernameMsg.style.color = '#fc0000';
        usernameMsg.style.visibility = 'visible';
        usernameField.style.borderColor = "#fc0000";
        sign.innerHTML = ' &#x2716;';
        sign.style.color = '#fc0000';
        username = false;
    } else {

        usernameValue = usernameValue.trim();
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {

                var result = JSON.parse(this.responseText);
                if (result.wrong_param == 'false' && result.user == 'false') {

                    usernameMsg.style.visibility = 'hidden';
                    usernameField.style.borderColor = "#4ef442";
                    sign.innerHTML = ' &#x2714;';
                    sign.style.color = '#4ef442';
                    username = true;
                } else {

                    usernameMsg.innerHTML = ' User already exist';
                    usernameMsg.style.color = '#fc0000';
                    usernameMsg.style.visibility = 'visible';
                    usernameField.style.borderColor = "#fc0000";
                    sign.innerHTML = ' &#x2716;';
                    sign.style.color = '#fc0000';
                    username = false;
                }
            }
        };
        var param = "username=" + usernameValue;
        xhttp.open("GET", "getuser?" + param, true);
        xhttp.send();
    }

    usernameField.value = usernameField.value.trim();
};
/**
 * 
 * @param {type} str
 * @returns {undefined}
 */
var emailValidator = function (emailValue) {

    var emailField = document.getElementsByName('email')[0];
    var sign = document.getElementById('email_sign');
    var emailMsg = document.getElementById('email_msg');


    if (emailValue !== '' && emailValue.indexOf('@') !== -1) {

        var filter = /^(\w([.]\w)*)+@[a-zA-Z_]+?([.][a-zA-Z]{2,3}){1,3}$/;
        if (filter.test(emailValue)) {

            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {

                    var result = JSON.parse(this.responseText);
                    if (result.wrong_param === 'false' && result.email === 'false') {

                        emailField.style.borderColor = '#4ef442';
                        sign.innerHTML = ' &#x2714;';
                        sign.style.color = '#4ef442';

                        emailMsg.style.visibility = 'hidden';

                        email = true;
                    } else {

                        emailMsg.innerHTML = ' Email already exist';
                        emailMsg.style.color = '#fc0000';
                        emailMsg.style.visibility = 'visible';

                        emailField.style.borderColor = '#fc0000';
                        sign.innerHTML = ' &#x2716;';
                        sign.style.color = '#fc0000';

                        email = false;
                    }
                }
            };

            var param = "email=" + emailValue.trim();
            xhttp.open("GET", "getemail?" + param, true);
            xhttp.send();
        } else {

            emailMsg.style.visibility = 'hidden';
            emailField.style.borderColor = '#fc0000';
            sign.innerHTML = ' &#x2716;';
            sign.style.color = '#fc0000';

            email = false;
        }
    } else {

        emailMsg.style.visibility = 'hidden';
        emailField.style.borderColor = '#fc0000';
        sign.innerHTML = ' &#x2716;';
        sign.style.color = '#fc0000';

        email = false;
    }
};
/**
 * 
 * @param {type} str
 * @returns {undefined}
 */
var passwordValidator = function (passwordValue) {

    var passwordLengthMsg = document.getElementById('password_length_msg');
    var passwordField = document.getElementsByName('password')[0];
    var sign = document.getElementById('password_sign');
    if (passwordValue.length < 8) {

        password = false;
        passwordField.style.borderColor = '#fc0000';
        sign.innerHTML = ' &#x2716;';
        sign.style.color = '#fc0000';
        passwordLengthMsg.innerHTML = ' (at least 8 character long)';
        passwordLengthMsg.style.color = '#fc0000';
        passwordLengthMsg.style.visibility = 'visible';
    } else {

        password = true;
        passwordField.style.borderColor = '#4ef442';
        sign.innerHTML = ' &#x2714;';
        sign.style.color = '#4ef442';
        passwordLengthMsg.style.visibility = 'hidden';
    }

    var repeatPasswordValue = document.getElementsByName('repeat_password')[0].value;
    repeatPasswordValidator(repeatPasswordValue);
};
/**
 * 
 * @param {type} str
 * @returns {undefined}
 */
var repeatPasswordValidator = function (repeatPasswordValue) {

    var repeatPasswordField = document.getElementsByName('repeat_password')[0];
    var sign = document.getElementById('repeat_password_sign');
    var passwordField = document.getElementsByName('password')[0];
    if (repeatPasswordValue.length != 0 && repeatPasswordValue == passwordField.value) {

        repeat_password = true;
        repeatPasswordField.style.borderColor = '#4ef442';
        sign.innerHTML = ' &#x2714;';
        sign.style.color = '#4ef442';
        return;
    }


    repeat_password = false;
    repeatPasswordField.style.borderColor = '#fc0000';
    sign.innerHTML = ' &#x2716;';
    sign.style.color = '#fc0000';
};
/**
 * 
 * @returns {Boolean}
 */
var signupValidation = function () {

    if (first_name && last_name && username && email && password && repeat_password) {

        return true;
    }

    return false;
};