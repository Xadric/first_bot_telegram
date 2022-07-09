package org.example;

import org.telegram.telegrambots.bots.*;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.stickers.Sticker;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class MySuperBot extends TelegramLongPollingBot{
    List list = new ArrayList();
    int f =0;
    public void onUpdateReceived(Update update){
        var message = update.getMessage().getText();
        var chatId = update.getMessage().getChatId();
        Random random = new Random();

        try {



            if (f==1){
                f=0;
                list.add(message);
                sendMassage(chatId,"added");
                sendMassage(chatId,"I can add position in the list (/add)\n I can delete position from the list(/delete) \n I can view your list (/viewList)");

            } else if (f == 2) {
                f=0;
                list.remove(Integer.parseInt(message)-1);
                sendMassage(chatId,"deleted");
                sendMassage(chatId,"I can add position in the list (/add)\n I can delete position from the list(/delete) \n I can view your list (/viewList)");

            } else if (message.equals("Hello")){
                sendPhoto(chatId,"hello.png");
                sendMassage(chatId, "I can send a photo (/sendPhoto) \n I can create a shopping list (/shoppingList)");
            } else if (message.equals("/sendPhoto")) {
                sendPhoto(chatId,random.nextInt(4)+".jpg");
            } else if (message.equals("/shoppingList")) {

                sendMassage(chatId,"I can add position in the list (/add)\n I can delete position from the list(/delete) \n I can view your list (/viewList)");
            } else if (message.equals("/add")) {
                sendMassage(chatId,"name");
                f=1;
            } else if (message.equals("/delete")) {
                sendMassage(chatId,"index");
                f=2;
            } else if (message.equals("/viewList")) {
                for (int i = 0; i < list.size(); i++) {
                    sendMassage(chatId,(i+1) +")"+ list.get(i));
                }
                sendMassage(chatId,"I can add position in the list (/add)\n I can delete position from the list(/delete) \n I can view your list (/viewList)");

            } else{
                sendMassage(chatId, "??????????");
            }
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

    void sendPhoto(long chatId , String name) throws Exception{
        var photo = getClass().getClassLoader().getResourceAsStream(name);

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