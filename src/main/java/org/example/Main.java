package org.example;

import org.telegram.telegrambots.bots.*;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

class MySuperBot extends TelegramLongPollingBot{
    public void onUpdateReceived(Update update){
        var massage = update.getMessage().getText();
        var chatId = update.getMessage().getChatId();
        System.out.println(massage);
        try {
            sendMassage(chatId,"Hello");
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    void sendMassage(long chatId, String text) throws Exception{
        var massege = new SendMessage();
        massege.setChatId(String.valueOf(chatId));
        massege.setText(text);
        execute(massege);
    }

    public String getBotUsername(){
        return "@first673_bot";
    }

    public String getBotToken(){

        return "5235910116:AAFvDub_hSxBOuSk3LNEdY484ZjzVQ_vE0Q";
    }


}

public class Main {
    public static void main(String[] args) throws Exception {
        var bots = new TelegramBotsApi(DefaultBotSession.class);
        bots.registerBot(new MySuperBot());
    }
}