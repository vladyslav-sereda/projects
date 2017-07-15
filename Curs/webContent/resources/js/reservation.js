$(document).ready(function () {
    $("#reserv-form").on("change", function () {
        var startTime = $("#from-time").val();
        var endTime = $("#to-time").val();

        if (validReserveTime( startTime, endTime)) {
            $("#new-reserv-error").text("");
        } else {
            $("#new-reserv-error").text("Fill up time-fields correctly");
        }
    });

});




var dayId;
function openReserveForm(dayIdbtn) {
    $(".reservation-btn").slideUp("slow", function() {
    });

    $("#reservation-form").fadeIn("slow", function () {
    });
    dayId = dayIdbtn;
}

function getTime(time) {
    var d = new Date(time.time.getHours());
    return d.format("HH:00");
}

function closeReserveForm() {
    $("#reservation-form").fadeOut("slow", function () {
        $(".reservation-btn").slideDown("slow", function() {
        });
        document.forms.reservation.reset();
    });
}

function addReserve(userId) {
    var startTime = $("#from-time").val();
    var endTime = $("#to-time").val();

    $.ajax({
        url : "Controller",
        cache : false,
        type : 'POST',
        data : {
            command : "reserve",
            type: 'reservation',
            dayId: dayId,
            userId: userId,
            startTime: startTime,
            endTime: endTime
        },
        statusCode : {
            200 : function() {
                location.reload();
                closeReserveForm();
            },
            423 : function() {
                $("#new-reserve-error").text("Reservation contains time of another reservation");
            },
            400 : function() {
                $("#new-reserve-error").text("Time or date is not valid");
            },
            500 : function() {
                $("#new-reserve-error").text("Oops some errors occured on server");
            }
        }
    });

    /*if (validReserveTime(startTime, endTime) ) {
        $("#new-reserve-error").text("");

        websocket.send(JSON.stringify({
            type: 'reservation',
            dayId: dayId,
            userId: userId,
            startTime: startTime,
            endTime: endTime
        }));

        closeReserveForm()
    } else {
        $("#new-reserve-error").text("Fill up time-fields correctly");
    }*/
}

websocket.onerror = function (evt) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
};

function validReserveTime(from, to) {
    if (from && to) {
        return to > new from;
    }
    return false;
}


function addReserveRow(reserve) {
    var td_startTime = "<td>" + getTime(reserve.startTime) + "</td>";
    var td_endTime = "<td>" + getTime(reserve.endTime) + "</td>";
    var td_price = "<td>" + reserve.price + "</td>";
    $("#newReservationList" +reserve.dayId).append("<tr id='reserve" + reserve.id + "'>" + td_userId + td_startTime + td_endTime + td_price + "</tr>");
}