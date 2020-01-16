function ajaxPost() {

    var formData = {
        id : $("#id").val(),
        firstName : $("#firstName").val(),
        lastName : $("#lastName").val(),
        password : $("#password").val(),
        password : $("#conpassword").val(),
        address : $("#address").val(),
        date : $("#date").val(),
        email : $("#email").val(),
    };

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/user/save",
        data: JSON.stringify(formData),
        dataType: 'json',
        success: function (result) {

        }
    });
}