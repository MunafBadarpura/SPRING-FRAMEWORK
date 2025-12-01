package com.munaf.A30_TOOL_CALLING.controllers;

import com.munaf.A30_TOOL_CALLING.tools.DateTimeTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ToolCallingController {


    private final ChatClient vertexChatClint;

    public ToolCallingController(@Qualifier("vertexChatClint") ChatClient vertexChatClint) {
        this.vertexChatClint = vertexChatClint;
    }


    @GetMapping()
    public String chat(@RequestParam(value = "q") String userQuery) {
        return vertexChatClint
                .prompt()
                .user(userQuery)
                .system("Use tools if needed. else respond with general knowledge.")
                .tools(new DateTimeTools())
                .call()
                .content();
    }

}
