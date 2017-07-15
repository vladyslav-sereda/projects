function readingRoom() {
    document.getElementById('orderDeadline').disabled = document.getElementById('readingRoomCheck').checked;
}

function openOrderForm(bookId, bookName) {
    $('#orderModalLabel').text('Order: ' + bookName);
    $('#orderModal').modal();
    $('#book-id').val(bookId);
}

function closeOrderModal() {
    $('#orderModal').modal('hide');
    document.getElementById("order-form").reset();

}

function bookNotAvailable() {
    $('#notAvailableModal').modal('show');
    // alert("Sorry, this book is not available right now...")
}

function makeOrder() {
    var userId = $("#user-id").val();
    var bookId = $("#book-id").val();
    var readingRoom = document.getElementById('readingRoomCheck').checked;
    var deadline = $("#orderDeadline").val();


    if (!validDeadline(deadline) && document.getElementById('readingRoomCheck').checked === false) {
        $("#order-form-error").text("Fill up deadline correctly");
    } else if (validDeadline(deadline) || document.getElementById('readingRoomCheck').checked === true) {
        $("#order-form-error").text("");

        webSocket.send(JSON.stringify({
            type: 'order',
            userId: userId,
            bookId: bookId,
            readingRoom: readingRoom,
            deadline: deadline
        }));
        $('#orderModal').modal('hide');
        // $('#notAvailableModal').modal();
    }
}

function validDeadline(date) {
    if (date) {
        return new Date(date) > Date.now();
    }
    return false;
}

function confirmOrder(orderId, bookId, userName, userEmail, deadline) {
    $.ajax({
        url: "Controller",
        cache: false,
        type: 'POST',
        data: {
            command: "confirmOrderStatus",
            orderId: orderId
        },
        success: function () {
            $("#nOrder" + orderId).remove();
            var td_data = "<td>" + orderId + "</td> " +
                "<td>" + bookId + "</td> " +
                "<td>" + userName + "</td> " +
                "<td>" + userEmail + "</td> " +
                "<td>" + deadline + "</td> " +
                "<td> <a class='btn btn-success' onclick='closeOrder(" + orderId + ")'>Close</a></td>";
            $("#confirmedOrdersTable").find("tbody").append("<tr id='cOrder" + orderId + "'>" + td_data + "</tr>");

        }
    });
}

function closeOrder(orderId) {
    $.ajax({
        url: "Controller",
        cache: false,
        type: 'POST',
        data: {
            command: "closeOrder",
            orderId: orderId
        },
        success: function () {
            $("#cOrder" + orderId).remove();
        }
    });
}

function addOrderRow(order, user) {
    var deadline = getDate(order.deadline);
    var td_data = "<td>" + order.id + "</td> " +
        "<td>" + order.bookId + "</td> " +
        "<td>" + user.name + "</td> " +
        "<td>" + user.email + "</td> " +
        "<td>" + deadline + "</td> " +
        "<td> <a class='btn btn-success'" +
        " onclick=\'confirmOrder(" + order.id + ", " + order.bookId + " , \" " + user.name + "\",\" " + user.email + "\"," + deadline + ")\'>Confirm</a></td>";
    $("#newOrdersTable").find("tbody").append("<tr id='nOrder" + order.id + "'>" + td_data + "</tr>");

}