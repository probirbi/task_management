
function deleteData(id) {
    var alert = confirm("Are you want to delete this item ?");
    if (alert) {
        $.ajax({
            type: "GET",
            url: "/user/deletetask/" + id,
            cache: false,
            dataType: 'text',
            success: function (response) {
                if(response == 'success') {
                    $('#table-row-' + taskId).remove();

                    $('<span style="color:green">Successfully deleted</span>').insertBefore('#getResultDiv');
                }
            }
        });

    }

}
