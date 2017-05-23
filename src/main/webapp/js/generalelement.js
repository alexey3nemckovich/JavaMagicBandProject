$(document).ready(function () {
    loadLogin();
});

function loadLogin(){
    $('#nav-bar-login').load("/login/loadLogin.ass");
}

function updateHistory($url, $data="") {
    history.pushState($data, document.title, $url);
}

function loadRoot(){
    const $root = "/";

    $('body').load($root);
    loadLogin();
    updateHistory($root);
}