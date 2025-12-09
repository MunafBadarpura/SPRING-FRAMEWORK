package com.munaf.projects.A30_HELP_DESK_AI.controller;

import com.munaf.projects.A30_HELP_DESK_AI.service.AiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }


    @GetMapping("/chat/{convoId}")
    public String chat(@PathVariable ("convoId") String convoId, @RequestParam("query") String userQuery) {
        return aiService.chat(convoId, userQuery);
    }


}
