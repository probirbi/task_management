function deleteData(type, id) {
    var alert = confirm("Are you want to delete this item ?");
    if (alert) {
        var host = "";
        var id = $("#id").val();

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "/user/deleteuser/" + id,
            cache: false,
            data: JSON.stringify(id),
            dataType: 'json',
            success: function (response) {

                //console.log(response);

                //response.reload(true)

                //$('#tableBody');
            }
        });

    }
}