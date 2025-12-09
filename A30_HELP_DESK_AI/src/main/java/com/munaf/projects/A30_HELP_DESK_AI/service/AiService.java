package com.munaf.projects.A30_HELP_DESK_AI.service;

import com.munaf.projects.A30_HELP_DESK_AI.tool.TicketDatabaseTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    private final ChatClient vertexChatClient;
    private final TicketDatabaseTool ticketDatabaseTool;

    @Value("classpath:ticket.st")
    private Resource ticketSystemPrompt;


    public AiService(@Qualifier("vertexChatClient") ChatClient vertexChatClient, TicketDatabaseTool ticketDatabaseTool) {
        this.vertexChatClient = vertexChatClient;
        this.ticketDatabaseTool = ticketDatabaseTool;
    }

    public String chat(String convoId, String userQuery) {
        return vertexChatClient
                .prompt()
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, convoId))
                .tools(ticketDatabaseTool)
                .user(userQuery)
                .system(sp -> sp.text(ticketSystemPrompt))
                .call()
                .content();
    }
}
