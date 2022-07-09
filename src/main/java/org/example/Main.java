package org.example;

import org.telegram.telegrambots.bots.*;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Random;

class MySuperBot extends TelegramLongPollingBot{
    public void onUpdateReceived(Update update){
        var massage = update.getMessage().getText();
        var chatId = update.getMessage().getChatId();
        System.out.println(massage);
        try {
            if massage.equals()
            sendMassage(chatId,"Hello");
            sendPhoto(chatId);
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

    void sendPhoto(long chatId) throws Exception{
        var name = new Random().nextInt(2);
        var photo = getClass().getClassLoader().getResourceAsStream(name+".jpg");

        var message = new SendPhoto();
        message.setChatId(String.valueOf(chatId));
        message.setPhoto(new InputFile(photo, "photo"));
        execute(message);
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