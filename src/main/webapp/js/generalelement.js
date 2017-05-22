$(document).ready(function () {
    loadLogin();
});

function loadLogin(){
    $('#nav-bar-login').load("/login/loadLogin.ass");
}