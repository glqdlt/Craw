package com.glqdlt.controller;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class ChatController {

	@MessageMapping("/chat")
	@SendTo("/push/chat")
	public Message chatManager(Message message) throws Exception {

		return message;
	}

}
