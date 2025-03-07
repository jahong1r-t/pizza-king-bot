package uz.pizzaking.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pizzaking.service.AuthService;

import java.io.File;

import static uz.pizzaking.utils.Bot.BOT_TOKEN;
import static uz.pizzaking.utils.Bot.BOT_USERNAME;

public class MainBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        new AuthService(this).service(update);
    }


    public void sendMessage(Long chatId, String message) {
        try {
            execute(SendMessage.builder().chatId(chatId).text(message).build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessageWithKeyboard(Long chatId, String message, ReplyKeyboard replyKeyboard) {
        try {
            execute(SendMessage.builder().chatId(chatId).replyMarkup(replyKeyboard).text(message).build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendPhoto(Long chatId, String path, String caption) {
        try {
            execute(SendPhoto.builder().chatId(chatId).photo(new InputFile(new File(path))).caption(caption).build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}
