var websocket;
var serverUrl = "ws://" + document.location.host
    + "/Photostudio4/webSocketHandler";
websocket = new WebSocket(serverUrl);

websocket.onmessage = function messageReceived(msg) {
    var data = $.parseJSON(msg.data);
    switch (data.type) {
        case "reservation": {
            addReserveRow(data.data);
        }
        case "workday": {
            addWorkday(data.data);
        }
    }
};
