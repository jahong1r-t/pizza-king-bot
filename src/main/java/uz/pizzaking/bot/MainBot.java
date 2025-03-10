package uz.pizzaking.bot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pizzaking.service.AuthService;

import java.io.File;
import java.util.List;

import static uz.pizzaking.utils.Bot.BOT_TOKEN;
import static uz.pizzaking.utils.Bot.BOT_USERNAME;

public class MainBot extends TelegramLongPollingBot {

    @SneakyThrows
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

    public void sendMessage(Long chatId, String caption, File path) {
        try {
            execute(SendPhoto.builder().chatId(chatId).photo(new InputFile(path)).caption(caption).build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(Long chatId, String message, ReplyKeyboard replyKeyboard) {
        try {
            execute(SendMessage.builder().chatId(chatId).replyMarkup(replyKeyboard).text(message).build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(Long chatId, String caption, File path, ReplyKeyboard replyKeyboard) {
        try {
            execute(SendPhoto.builder().chatId(chatId).photo(new InputFile(path)).caption(caption).replyMarkup(replyKeyboard).build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendPhoto(Long chatId, String caption, List<PhotoSize> photoSizes) {
        try {
            if (!photoSizes.isEmpty()) {
                PhotoSize photo = photoSizes.stream()
                        .max((p1, p2) -> Long.compare(p1.getFileSize() != null ? p1.getFileSize() : 0,
                                p2.getFileSize() != null ? p2.getFileSize() : 0))
                        .orElse(photoSizes.get(0));

                execute(SendPhoto.builder()
                        .chatId(chatId)
                        .photo(new InputFile(photo.getFileId()))
                        .caption(caption)
                        .build());
            }
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