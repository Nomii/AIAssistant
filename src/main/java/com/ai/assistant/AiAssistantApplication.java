package com.ai.assistant;

import com.ai.assistant.gui.MainApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class AiAssistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiAssistantApplication.class, args);
    }
    // Once spring boot finishes application startup, this event will be called
    @EventListener(ApplicationReadyEvent.class)
    public void startGuiApp() {
        MainApplication.launchApplication();
    }


}
