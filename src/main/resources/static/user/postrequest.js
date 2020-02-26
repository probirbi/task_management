function ajaxPost() {

    var formData = {
        id: $("#id").val(),
        firstName: $("#firstName").val(),
        lastName: $("#lastName").val(),
        password: $("#password").val(),
        password: $("#conpassword").val(),
        address: $("#address").val(),
        date: $("#date").val(),
        email: $("#email").val(),
    };

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/user/save",
        data: JSON.stringify(formData),
        dataType: 'json',
        success: function (response) {

            $("#exampleModal").modal("hide");
            var tableNewTr = '<tr id="table-row-' + response.id + '"><td>' + response.id + '</td><td>' + response.firstName + '</td><td>' + response.lastName + '</td><td>' + response.address + '</td>' +
                '            <td>' + response.date + '</td>' +
                '            <td>' + response.email + '</td>' +
                '            <td class="text-center"><a href="/user/edituser/">Edit</a></td>' +
                '            <td class="text-center"><button onclick="deleteData(' + response.id + ')" class="btn btn-danger">Delete</button></td>' +
                '        </tr>';

            $('#tableBody').prepend(tableNewTr);

            $("#firstName").val('');
            $("#lastName").val('');
            $("#password").val('');
            $("#conpassword").val('');
            $("#address").val('');
            $("#date").val('');
            $("#email").val('');
        }
    });
}

/********************************************************/

$(function () {

    $("#fname_error_message").hide();
    $("#lname_error_message").hide();
    $("#pass_error_message").hide();
    $("#conpass_error_message").hide();
    $("#street_error_message").hide();
    $("#date_error_message").hide();
    $("#email_error_message").hide();

    var error_fname = false;
    var error_lname = false;
    var error_pass = false;
    var error_conpass = false;
    var error_street = false;
    var error_date = false;
    var error_email = false;

    $("#firstName").focusout(function () {
        check_fname();
    });
    $("#lastName").focusout(function () {
        check_lname();
    });
    $("#password").focusout(function () {
        check_pass();
    });
    $("#conpassword").focusout(function () {
        check_conpass();
    });
    $("#address").focusout(function () {
        check_street();
    });
    $("#date").focusout(function () {
        check_date();
    });
    $("#email").focusout(function () {
        check_email();
    });

    function check_fname() {
        var pattern = /^[a-zA-Z]*$/;
        var fname = $("#firstName").val();
        if (pattern.test(fname) && fname !== '') {
            $("#fname_error_message").hide();
            $("#firstName").css("border-bottom", "2px solid #34F458");
        } else {
            $("#fname_error_message").html("Should contain only character.");
            $("#fname_error_message").show();
            $("#firstName").css("border-bottom", "2px solid #34F458");
            error_fname = true;
        }
    }

    function check_lname() {
        var pattern = /^[a-zA-Z]*$/;
        var lname = $("#lastName").val();
        if (pattern.test(lname) && lname !== '') {
            $("#lname_error_message").hide();
            $("#lastName").css("border-bottom", "2px solid #34F458");
        } else {
            $("#lname_error_message").html("Should contain only character.");
            $("#lname_error_message").show();
            $("#lastName").css("border-bottom", "2px solid #34F458");
            error_lname = true;
        }
    }

    function check_pass() {
    }

    function check_conpass() {
    }

    function check_street() {
    }

    function check_date() {
    }

    function check_email() {
    }

    $("#registration_form").submit(function () {
        /*if (error_fname === false && error_lname === false && error_pass === false && error_conpass === false && error_street === false && error_date === false && error_email === false) {*/
        if (error_fname === false && error_lname === false){
        alert("Successfully added");
            return true;
        } else {
            alert("Please fill the form correctly.");
            return false;
        }

        ajaxPost();

    });

})