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

    loadPage($root);
    loadLogin();
}

function loadPage($page){
    $('body').load($page);
    updateHistory($page);
}

function disableSendForm(event) {
    event.preventDefault();
}

$(document).ajaxError(function(event, request, settings) {
    console.log(settings.url);
    let $response = request.responseText;
    const logger = $("#log");
    logger.html($response);
});

function backPage() {
    history.back();
    location.reload();
}
