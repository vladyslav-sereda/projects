function searchBookByName() {
    webSocket.send(JSON.stringify({
        type: 'search',
        name: $("#search").val()
    }));
}

function searchBookByAuthor() {
    webSocket.send(JSON.stringify({
        type: 'search',
        author: $("#search").val()
    }));
}


function fillResultTable(books) {
    $("#foundBooks").find("tbody").find("tr").remove();
    books.forEach(function (item, i, books) {
        var td_data = "<td>" + item.name + "</td> " +
            "<td>" + item.author + "</td> " +
            "<td>" + item.publisher + "</td> " +
            "<td>" + getDate(item.publicationDate) + "</td>" +
            "<td><button class='btn btn-primary' type='button' onclick='openOrderForm(" + item.id + ")'>order</button></td>";
        $("#foundBooks").find("tbody").append("<tr>" + td_data + "</tr>");
    });
}

function getDate(date) {
    var d = new Date(date.year, date.month - 1, date.day);
    date = d.format("yyyy-mm-dd");
    return date;
}