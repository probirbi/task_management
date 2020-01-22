
function deleteData(userId) {
    var alert = confirm("Are you want to delete this item ?");
    if (alert) {
        $.ajax({
            type: "GET",
            url: "/user/deleteuser/" + userId,
            cache: false,
            dataType: 'text',
            success: function (response) {
                if(response == 'success') {
                    $('#table-row-' + userId).remove();

                    $('<span style="color:green">Successfully deleted</span>').insertBefore('#getResultDiv');
                }
            }
        });

    }

}
