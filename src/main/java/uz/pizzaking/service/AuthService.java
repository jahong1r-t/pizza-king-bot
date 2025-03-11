package uz.pizzaking.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.pizzaking.bot.MainBot;
import uz.pizzaking.entity.Basket;
import uz.pizzaking.entity.User;
import uz.pizzaking.entity.enums.Languages;
import uz.pizzaking.entity.enums.States;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static uz.pizzaking.db.Datasource.*;
import static uz.pizzaking.utils.Buttons.*;
import static uz.pizzaking.utils.Util.*;
import static uz.pizzaking.utils.Messages.*;

public class AuthService {
    private final MainBot bot;

    public AuthService(MainBot bot) {
        this.bot = bot;
    }

    public void service(Update update) {
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();


        if (Objects.equals(chatId, ADMIN)) {
            new AdminService(bot).service(update);
        } else if (isRegister(chatId) && !Objects.equals(chatId, ADMIN)) {
            new UserService(bot).service(update);
        } else {
            States currentState = state.getOrDefault(chatId, States.LANGUAGE_SELECTION);
            switch (currentState) {
                case LANGUAGE_SELECTION -> {
                    switch (text) {
                        case "/start" -> {
                            bot.sendMessage(chatId, "Welcome to Pizza King. Please select the desired language.", keyboard(languages));
                            state.put(chatId, States.LANGUAGE_SELECTION);
                        }
                        case UZ, EN, RU -> {
                            User user = new User(chatId, null, null, null, null, new Basket(chatId, new ArrayList<>()), null, null);
                            Languages lang = text.equals(UZ) ? Languages.UZBEK : text.equals(EN) ? Languages.ENGLISH : Languages.RUSSIAN;
                            user.setLanguage(lang);
                            users.put(chatId, user);

                            bot.sendMessage(chatId, phone_num_req(lang), createPhoneButton(phone_num_button(lang)));
                            state.put(chatId, States.PHONE_NUMBER_REQUEST);
                        }
                        default ->
                                bot.sendMessage(chatId, "Please select a language from the options.", keyboard(languages));
                    }
                }
                case PHONE_NUMBER_REQUEST -> {
                    if (update.getMessage().hasContact()) {
                        var from = update.getMessage().getFrom();
                        String phoneNumber = update.getMessage().getContact().getPhoneNumber();

                        User user = users.get(chatId);
                        user.setPhoneNumber(phoneNumber.startsWith("+") ? phoneNumber : "+" + phoneNumber);
                        user.setUsername(from.getUserName());
                        user.setName(from.getFirstName());
                        user.setSurname(from.getLastName());

                        Languages lang = user.getLanguage();

                        bot.sendMessage(chatId, register_success_msg(lang), main_keyboard(lang));
                        state.remove(chatId);
                    } else {
                        bot.sendMessage(chatId, phone_number_error(users.get(chatId).getLanguage()));
                    }
                }
            }
        }

    }

    public boolean isRegister(Long chatId) {
        User user = users.get(chatId);
        return user != null && user.getPhoneNumber() != null;
    }

    private ReplyKeyboardMarkup createPhoneButton(String buttonText) {
        KeyboardRow row = new KeyboardRow();
        KeyboardButton phoneButton = new KeyboardButton(buttonText);
        phoneButton.setRequestContact(true);
        row.add(phoneButton);

        List<KeyboardRow> rows = new ArrayList<>();
        rows.add(row);

        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(true);
        keyboard.setKeyboard(rows);
        return keyboard;
    }
}