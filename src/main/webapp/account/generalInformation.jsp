<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>

<jsp:useBean id="generalUserInformation" scope="request"
             type="main.com.bsuir.autoservice.dto.UserGeneralInformationDTO"/>
<jsp:useBean id="haveNewNotification" scope="request" type="java.lang.Boolean"/>

<div class="row" id="general-information">
    <form id="general-information-form" class="form-horizontal">

        <p id="info"></p>

        <div class="form-group">
            <label for="form-email">Email address: </label>
            <p class="form-control-static" id="form-email">${generalUserInformation.userMail}</p>
        </div>

        <div class="form-group">
            <label for="change-password">Password</label>
            <button type="button" class="btn btn-primary" id="change-password">Change password</button>
        </div>

        <div class="form-group">
            <label for="form-tel">Telephone: </label>
            <input type="tel" class="form-control" id="form-tel" placeholder="Enter phone" name="phone"
                   disabled="disabled"
                   value="${generalUserInformation.userPhone}"/>
        </div>

        <div class="form-group">
            <label for="form-name">Name: </label>
            <input type="text" class="form-control" id="form-name" placeholder="Enter name" name="name"
                   disabled="disabled"
                   value="${generalUserInformation.userName}"/>
        </div>

        <div class="form-group">
            <label for="form-last-name">Last Name: </label>
            <input type="text" class="form-control" id="form-last-name" placeholder="Enter last name" name="lastName" disabled="disabled"
                   value="${generalUserInformation.userLastName}"/>
        </div>

        <div class="row">
            <div class="col-md-offset-8 col-md-2">
                <button type="submit" class="btn btn-success btn-block hidden" id="form-save">Save</button>
                <button type="submit" class="btn btn-warning btn-block" id="form-edit">Edit</button>
            </div>
        </div>
    </form>

    <script>
        $(document).ready(function () {

            const $formBtnEdit = $("#form-edit");
            const $formBtnSave = $("#form-save");

            function switchMode() {
                $("#info").html("");
                switchMode.$isEdit = !(switchMode.$isEdit);

                $formBtnSave.toggleClass('hidden');
                $formBtnEdit.toggleClass('hidden');

                const $formEl = [$("#form-name"), $("#form-last-name"), $("#form-tel")];

                $.each($formEl, function (i, value) {
                    if (switchMode.$isEdit) {
                        value.removeAttr("disabled");
                    } else {
                        value.attr("disabled", "disabled");
                    }
                });
            }

            switchMode.$isEdit = false;

            $formBtnEdit.click(function () {
                switchMode();
                return false;
            });

            var $general_form = $("#general-information-form");

            $general_form.submit(function (event) {
                disableSendForm(event);
                $.post("/account/updateUser.ass", $general_form.serialize(), function (data) {
                    if (data.isUpdateUser[0] === true){
                        switchMode();
                        $("#info").html("Update compete");
                    }else {
                        alert("Error updating");
                    }
                });
            });

            $("#change-password").click(function () {
                const $restorePass = "/account/restorePass.ass";

                $('body').load($restorePass);
                updateHistory($restorePass);
                return false;
            });
        });
    </script>
</div>