package uz.pizzaking.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.pizzaking.bot.MainBot;
import uz.pizzaking.entity.enums.Languages;
import uz.pizzaking.entity.enums.States;
import uz.pizzaking.utils.Buttons;
import uz.pizzaking.utils.Texts;

import java.util.ArrayList;
import java.util.List;

import static uz.pizzaking.db.Datasource.*;
import static uz.pizzaking.entity.enums.Languages.EN;
import static uz.pizzaking.entity.enums.Languages.UZ;
import static uz.pizzaking.utils.Util.*;

public class UserService {
    private final MainBot bot;

    public UserService(MainBot bot) {
        this.bot = bot;
    }

    public void service(Update update) {
        String text = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        User from = update.getMessage().getFrom();
        Languages lang = users.get(chatId).getLanguage();

        if (update.hasMessage()) {
            state.putIfAbsent(chatId, States.MAIN_MENU);
            States currentState = state.get(chatId);

            if (currentState == States.MAIN_MENU) {
                switch (text) {
                    case "/start" ->
                            bot.sendMessage(chatId, lang == UZ ? Texts.welcome_uz : lang == Languages.EN ? Texts.welcome_en : Texts.welcome_ru, keyboard(lang == UZ ? main_uz : lang == Languages.EN ? main_en : main_ru));
                    case Buttons.ABOUT_UZ -> bot.sendMessage(chatId, Texts.about_uz, Texts.logo_path);
                    case Buttons.ABOUT_EN -> bot.sendMessage(chatId, Texts.about_en, Texts.logo_path);
                    case Buttons.ABOUT_RU -> bot.sendMessage(chatId, Texts.about_ru, Texts.logo_path);
                    case Buttons.CALL_UZ -> bot.sendMessage(chatId, Texts.call_uz, Texts.call_path);
                    case Buttons.CALL_EN -> bot.sendMessage(chatId, Texts.call_en, Texts.call_path);
                    case Buttons.CALL_RU -> bot.sendMessage(chatId, Texts.call_ru, Texts.call_path);
                    case Buttons.SEND_MESSAGE_UZ -> {
                        bot.sendMessage(chatId, Texts.send_msg_uz);
                        state.put(chatId, States.WAIT_MESSAGE);
                    }
                    case Buttons.SEND_MESSAGE_EN -> {
                        bot.sendMessage(chatId, Texts.send_msg_en);
                        state.put(chatId, States.WAIT_MESSAGE);
                    }
                    case Buttons.SEND_MESSAGE_RU -> {
                        bot.sendMessage(chatId, Texts.send_msg_ru);
                        state.put(chatId, States.WAIT_MESSAGE);
                    }
                    default -> {
                        bot.sendMessage(chatId, lang == UZ ? Texts.user_error_uz : lang == Languages.EN ? Texts.user_error_en : Texts.user_error_ru);
                    }
                }
            } else if (currentState == States.WAIT_MESSAGE) {
                String builder = "📨 Yangi xabar keldi!\n" +
                        "👤 Kimdan: @" + (from.getUserName() != null ? from.getUserName() : "Username yo‘q") + "\n" +
                        "📝 Ism: " + from.getFirstName() +
                        "💬 Xabar: " + text + "\n";

                bot.sendMessage(ADMIN, builder, getInlineKeyboard(chatId, lang));
                bot.sendMessage(chatId, lang == UZ ? Texts.res_message_uz : lang == EN ? Texts.res_message_en : Texts.res_message_ru);
                state.remove(chatId);
            }
        }
    }


    private ReplyKeyboard getInlineKeyboard(Long chatId, Languages lang) {

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();


        List<List<InlineKeyboardButton>> listButtons = new ArrayList<>();

        List<InlineKeyboardButton> buttons = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText(lang == UZ ? "Javob berish" : lang == EN ? "Reply" : "Отвечать");
        button1.setCallbackData(chatId.toString());
        buttons.add(button1);
        listButtons.add(buttons);

        markup.setKeyboard(listButtons);
        return markup;
    }

}