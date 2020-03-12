function changeStatus(taskId, obj) {
    let data = {
        id: taskId,
        status: $(obj).val()
    };

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/user/task/changestatus",
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (success) {
            alert("Your Success Message here." +success);
        }
    });
}
