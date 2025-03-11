package uz.pizzaking.service;

import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.pizzaking.bot.MainBot;
import uz.pizzaking.entity.enums.Languages;
import uz.pizzaking.entity.enums.States;

import java.util.List;

import static uz.pizzaking.db.Datasource.*;
import static uz.pizzaking.utils.Buttons.*;
import static uz.pizzaking.utils.Messages.*;
import static uz.pizzaking.utils.Util.languages;


public class AdminService {
    private final MainBot bot;
    public static Long replyId = null;
    public static Integer messageId = null;

    public AdminService(MainBot bot) {
        this.bot = bot;
    }

    @SneakyThrows
    public void service(Update update) {
        String text = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();

        state.putIfAbsent(ADMIN, States.MAIN_ADMIN);
        States currentState = state.get(ADMIN);

        if (update.hasMessage()) {
            if (currentState == States.MAIN_ADMIN) {
                switch (text) {
                    case "/start" ->
                            bot.sendMessage(ADMIN, welcome_admin(adminLanguage), main_keyboard_admin(adminLanguage));
                    case USERS_UZ, USERS_RU, USERS_EN ->
                            bot.sendMessage(ADMIN, getUserListMessage(users, adminLanguage));
                    case STATISTICS_UZ -> bot.sendMessage(ADMIN, "Coming soon!");
                    case MESSAGE_FOR_ALL_UZ, MESSAGE_FOR_ALL_EN, MESSAGE_FOR_ALL_RU -> {
                        bot.sendMessage(chatId, send_msg_to_all_admin(adminLanguage));
                        state.put(chatId, States.SEND_MESSAGE_TO_ALL);
                    }
                    case SETTINGS_EN, SETTINGS_RU, SETTINGS_UZ -> {
                        bot.sendMessage(chatId, settings_menu_text(adminLanguage), settings_keyboard(adminLanguage));
                        state.put(chatId, States.SETTINGS);
                    }
                }
            } else if (currentState == States.SETTINGS) {
                switch (text) {
                    case CHANGE_LANG_UZ, CHANGE_LANG_EN, CHANGE_LANG_RU ->
                            bot.sendMessage(chatId, change_lang_msg(adminLanguage), keyboard(languages));
                    case UZ, EN, RU -> {
                        Languages newLang = text.equals(UZ) ? Languages.UZBEK : text.equals(EN) ? Languages.ENGLISH : Languages.RUSSIAN;
                        users.get(chatId).setLanguage(newLang);

                        Languages language = users.get(chatId).getLanguage();

                        bot.sendMessage(chatId, change_lang_success_msg(language), main_keyboard(language));
                        state.put(chatId, States.MAIN_MENU);
                    }
                    case BACK_UZ, BACK_RU, BACK_EN -> {
                        bot.sendMessage(chatId, welcome_msg(adminLanguage), main_keyboard(adminLanguage));
                        state.put(chatId, States.MAIN_MENU);
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
                Languages lang = users.get(replyId).getLanguage();

                bot.sendMessage(replyId, admin_response_to_user(lang, text), messageId);
                state.remove(ADMIN);
                bot.sendMessage(ADMIN, send_mgs_to_user(adminLanguage));
            }

        }

    }
}