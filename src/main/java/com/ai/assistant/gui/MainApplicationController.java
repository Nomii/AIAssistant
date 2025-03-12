package com.ai.assistant.gui;

import com.ai.assistant.util.ContextUtil;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import org.springframework.ai.chat.client.ChatClient;

import java.net.URL;
import java.util.ResourceBundle;

public class MainApplicationController implements Initializable {
    @FXML
    public TextArea txtAreaAiResponse;
    @FXML
    public TextArea txtAreaInput;
    @FXML
    public ProgressBar progressBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupGui();
    }

    private void setupGui() {
    }

    /**
     *
     * This method:
     * 1. Gets the text from the input text area
     * 2. Validates that the input is not empty
     * 3. Retrieves the ChatClient from the application context, ChatClient is not a spring bean that's why cannot inject it directly into this class
     * 4. Sends the user's input to the AI and gets the response
     *
     * @param actionEvent The action event triggered by the send button
     */
    @FXML
    public void btnSendActionPerformed(ActionEvent actionEvent) {
        var llmInput = txtAreaInput.getText();
        if(llmInput == null || llmInput.isEmpty()) {
            return;
        }
        progressBar.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
        txtAreaAiResponse.setText("");
        var chatClient = ContextUtil.getApplicationContext().getBean(ChatClient.class);
        // To let response be processed token by token, we need to use stream() so to improve performance
        // It will process each word as token and print it out
        chatClient.prompt()
                .user(llmInput)
                .stream()
                .content()
                .doOnComplete(() -> Platform.runLater(() -> progressBar.setProgress(0)))
                .subscribe(token ->{
                    Platform.runLater(()->txtAreaAiResponse.appendText(token));
                });
    }
}