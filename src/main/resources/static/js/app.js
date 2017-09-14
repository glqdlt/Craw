var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#dataTable").html("");
    $("#chatRoom").html("");
}

function connect() {
    var socket = new SockJS('/webSocks');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/push/newData', function (newCrawData) {
            showData(JSON.parse(newCrawData.body));
        });
        stompClient.subscribe('/push/chat', function (chat) {
        	showDataChat(JSON.parse(chat.body).message);
        });
    });
    
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMsg() {
    stompClient.send("/app/chat", {}, JSON.stringify({'message': $("#message").val()}));
}

function showData(newCrawData) {
    $("#dataTable").append("<tr><td>" + newCrawData + "</td></tr>");
}
function showDataChat(message) {
	$("#chatRoom").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
	connect();
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMsg(); });
});