function ajaxPost() {

    var image = new FormData($("#profilepicture")[0]);

    var formData = {
        id: $("#id").val(),
        firstName: $("#firstName").val(),
        lastName: $("#lastName").val(),
        password: $("#password").val(),
        password: $("#conpassword").val(),
        address: $("#address").val(),
        date: $("#date").val(),
        email: $("#email").val(),
        file: $("#image").val()
    };

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/user/save",
        data: new FormData($("#registration_form")[0]),
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function (response) {

            $("#exampleModal").modal("hide");
            var tableNewTr = '<tr id="table-row-' + response.id + '"><td>' + response.id + '</td><td>' + response.firstName + '</td><td>' + response.lastName + '</td><td>' + response.address + '</td>' +
                '            <td>' + response.date + '</td>' +
                '            <td>' + response.email + '</td>' +
                '            <td>' + response.image + '</td>' +
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

function readSingleURL(input, previewId) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#' + previewId).attr('src', e.target.result);
        };
        reader.readAsDataURL(input.files[0]);
    }
}