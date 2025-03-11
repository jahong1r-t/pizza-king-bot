package uz.pizzaking.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pizzaking.entity.Product;
import uz.pizzaking.entity.enums.Languages;
import uz.pizzaking.entity.enums.States;
import uz.pizzaking.service.AdminService;
import uz.pizzaking.service.AuthService;
import uz.pizzaking.utils.Messages;

import java.io.File;
import java.util.List;

import static uz.pizzaking.db.Datasource.*;
import static uz.pizzaking.db.Datasource.ADMIN;
import static uz.pizzaking.utils.Bot.BOT_TOKEN;
import static uz.pizzaking.utils.Bot.BOT_USERNAME;
import static uz.pizzaking.utils.Messages.response_to_user;

public class MainBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            new AuthService(this).service(update);
        } else if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().startsWith("id")) {
                responseToUser(update.getCallbackQuery().getData());
            } else if (update.getCallbackQuery().getData().startsWith("addCart")) {
                addToCart(update.getCallbackQuery().getData());
            }

        }
    }

    private void addToCart(String data) {

        String[] parts = data.replace("addCart", "").split("_");

        Long chatId = Long.parseLong(parts[0]);
        String productName = parts[1];

        Product product = products.stream()
                .filter(p -> p.getNameEn().equals(productName))
                .findFirst()
                .orElse(null);
        if (product != null && users.get(chatId) != null) {
            users.get(chatId).getBasket().getProducts().add(product);
        }

        Languages language = users.get(chatId).getLanguage();

        sendMessage(chatId, Messages.product_add_success(language));
    }


    private void responseToUser(String data) {
        AdminService.replyId = Long.parseLong(data.substring(data.indexOf("id") + 2, data.indexOf("msgId")));
        AdminService.messageId = Integer.parseInt(data.substring(data.indexOf("msgId") + 5));
        sendMessage(ADMIN, response_to_user(adminLanguage, Long.parseLong(AdminService.replyId.toString())));
        state.put(ADMIN, States.RESPONSE_TO_USER);
    }

    public void sendMessage(Long chatId, String message) {
        try {
            execute(SendMessage.builder().chatId(chatId).text(message).build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(Long chatId, String message, Integer messageId) {
        try {
            execute(SendMessage.builder().chatId(chatId).replyToMessageId(messageId).text(message).build());
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