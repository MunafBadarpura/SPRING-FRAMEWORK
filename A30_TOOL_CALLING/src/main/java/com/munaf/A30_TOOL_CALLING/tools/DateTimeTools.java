package com.munaf.A30_TOOL_CALLING.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateTimeTools {

    // informational tool
    @Tool(name = "get_current_time", description = "This is the tool for getting current date and time.")
    public String getCurrentTime() {
        System.out.println("getCurrentTime tool called");
        return Instant.now().toString();
    }


    // action tool
    @Tool(name = "set_alarm", description = "This is the tool for setting an alarm.")
    public void setAlarm(@ToolParam(description = "time in ISO-8601 format") String time) {
        System.out.println("alarm value: " + time);
        LocalDateTime dateTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
        System.out.println("Alarm set for: " + dateTime);
    }

}
