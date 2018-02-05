var showPendingMenu = function (id) {


    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {

//        console.log('ready state = ' + this.readyState)
//        console.log('status = ' + this.status)

        if (this.readyState == 4 && this.status == 200) {

            var pendingMenuField = document.getElementById('pending_menu');
            var userJSON = JSON.parse(this.responseText);
            if (userJSON.wrong_param === 'false' && userJSON.superuser === 'true') {

//                console.log('Menu should be visible');
                pendingMenuField.style.visibility = 'visible';
            } else {

//                console.log('Menu should be hidden');
                pendingMenuField.style.visibility = 'hidden';
            }
        }
    };
    var param = "username=" + id;
    xhttp.open("GET", "superuser?" + param, true);
    xhttp.send();
};