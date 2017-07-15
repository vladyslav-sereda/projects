var webSocket;
var serverUrl = "ws://" + document.location.host
    + "/webSocketHandler";
webSocket = new WebSocket(serverUrl);

webSocket.onmessage = function messageReceived(msg) {
    var data = $.parseJSON(msg.data);
    switch (data.type) {
        case "books": {
            fillResultTable(data.data);
            break;
        }
        case "order": {
            addOrderRow(data.data, data.user);
            break;
        }
        case "notAvailable": {
            bookNotAvailable();
            break;
        }
    }
};
