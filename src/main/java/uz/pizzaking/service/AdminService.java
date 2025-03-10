package uz.pizzaking.service;

import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.pizzaking.bot.MainBot;
import uz.pizzaking.entity.enums.States;

import java.util.List;

import static uz.pizzaking.db.Datasource.*;
import static uz.pizzaking.utils.Buttons.*;
import static uz.pizzaking.utils.Messages.*;


public class AdminService {
    private final MainBot bot;
    String replyId = "";


    public AdminService(MainBot bot) {
        this.bot = bot;
    }

    @SneakyThrows
    public void service(Update update) {
        String text = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        System.err.println(update.hasCallbackQuery() ? update.getCallbackQuery().getData():"no data admin");


        state.putIfAbsent(ADMIN, States.MAIN_ADMIN);
        States currentState = state.get(ADMIN);

        if (update.hasMessage()) {
            if (currentState == States.MAIN_ADMIN) {
                switch (text) {
                    case "/start" ->
                            bot.sendMessage(ADMIN, welcome_admin(adminLanguage), main_keyboard_admin(adminLanguage));
                    case USERS_UZ, USERS_RU, USERS_EN ->
                            bot.sendMessage(ADMIN, getUserListMessage(users, adminLanguage));
                    case STATISTICS_UZ -> {

                    }
                    case MESSAGE_FOR_ALL_UZ, MESSAGE_FOR_ALL_EN, MESSAGE_FOR_ALL_RU -> {
                        bot.sendMessage(chatId, send_msg_to_all_admin(adminLanguage));
                        state.put(chatId, States.SEND_MESSAGE_TO_ALL);
                    }
                    case SETTINGS_ADMIN_UZ -> {
                    }
                }
            } else if (currentState == States.SEND_MESSAGE_TO_ALL) {
                if (update.getMessage().hasPhoto()) {
                    List<PhotoSize> photo = update.getMessage().getPhoto();
                    String caption = update.getMessage().getCaption() != null ? update.getMessage().getCaption() : "";

                    users.forEach((userChatId, user) -> {
                        if (!userChatId.equals(chatId)) {
                            bot.sendPhoto(userChatId, caption, photo);
                        }
                    });
                } else {
                    users.forEach((userChatId, user) -> {
                        if (!userChatId.equals(chatId)) {
                            bot.sendMessage(userChatId, text);
                        }
                    });
                }

                bot.sendMessage(chatId, send_mgs_to_all_success_message(adminLanguage));
                state.remove(ADMIN);
            } else if (currentState == States.RESPONSE_TO_USER) {
                bot.sendMessage(Long.parseLong(replyId), text);
                state.remove(ADMIN);
                bot.sendMessage(ADMIN, send_mgs_to_user(adminLanguage));
            }

        } else if (update.hasCallbackQuery()) {
            replyId = update.getCallbackQuery().getData();

            bot.sendMessage(ADMIN, response_to_user(adminLanguage, Long.parseLong(replyId)));

            state.put(ADMIN, States.RESPONSE_TO_USER);
        }
    }
}