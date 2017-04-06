$(function() {

	var socket = null;

	$("#connectBtn").on("click", function() {
		socket = new WebSocket("ws://localhost:8080/day04/chat/general/"
				+ $("#name").val());
		socket.onopen = function() {
			$("#chats").val("Connected to chat server");
		}

		socket.onclose = function() {
			$("#chats").val("Connection closed\n" + $("#chats").val());
		}

		socket.onmessage = function(evt) {
			$("#chats").val(evt.data + "\n" + $("#chats").val());
		}
	})

	$("#sendBtn").on("click", function() {
		var msg = $("#msg").val();
		socket.send(msg);
		$("#msg").val("");
	});

});