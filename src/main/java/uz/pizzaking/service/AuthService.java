package uz.pizzaking.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.pizzaking.bot.MainBot;
import uz.pizzaking.entity.enums.Languages;
import uz.pizzaking.entity.enums.States;
import uz.pizzaking.entity.User;
import uz.pizzaking.utils.Buttons;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static uz.pizzaking.db.Datasource.*;
import static uz.pizzaking.utils.Util.*;

public class AuthService {
    private final MainBot bot;

    public AuthService(MainBot bot) {
        this.bot = bot;
    }

    public void service(Update update) {
        Long chatId = update.getMessage().getChatId();
        String text = update.hasMessage() ? update.getMessage().getText() : "";

        if (isRegister(chatId)) {
            if (Objects.equals(chatId, ADMIN)) {
                new AdminService(bot).service(update);
            } else {
                new UserService(bot).service(update);
            }
        } else {
            States currentState = state.getOrDefault(chatId, States.LANGUAGE_SELECTION);

            switch (currentState) {
                case LANGUAGE_SELECTION -> {
                    switch (text) {
                        case "/start" -> {
                            bot.sendMessage(chatId, "🍕 Ready to savor Pizza King? First, choose your language!", keyboard(languages));
                        }
                        case Buttons.UZ -> {
                            User user = new User();
                            user.setLanguage(Languages.UZ);
                            users.put(chatId, user);
                            bot.sendMessage(chatId, "Iltimos, telefon raqamingizni yuboring:", createPhoneButton(langToMessage(Languages.UZ)));
                            state.put(chatId, States.PHONE_NUMBER_REQUEST);
                        }
                        case Buttons.EN -> {
                            User user = new User();
                            user.setLanguage(Languages.EN);
                            users.put(chatId, user);
                            bot.sendMessage(chatId, "Please share your phone number:", createPhoneButton(langToMessage(Languages.EN)));
                            state.put(chatId, States.PHONE_NUMBER_REQUEST);
                        }
                        case Buttons.RU -> {
                            User user = new User();
                            user.setLanguage(Languages.RU);
                            users.put(chatId, user);
                            bot.sendMessage(chatId, "Пожалуйста, отправьте ваш номер телефона:", createPhoneButton(langToMessage(Languages.RU)));
                            state.put(chatId, States.PHONE_NUMBER_REQUEST);
                        }
                        default -> bot.sendMessage(chatId, "Please select a language from the options.");
                    }
                }

                case PHONE_NUMBER_REQUEST -> {
                    if (update.getMessage().hasContact()) {

                        var from = update.getMessage().getFrom();

                        String phoneNumber = update.getMessage().getContact().getPhoneNumber();
                        User user = users.get(chatId);
                        user.setPhoneNumber(phoneNumber);
                        user.setName(from.getFirstName());
                        user.setSurname(from.getLastName());
                        user.setUsername(from.getUserName());

                        Languages lang = user.getLanguage();

                        String successMessage = switch (lang) {
                            case UZ -> "Ro‘yxatdan o‘tish muvaffaqiyatli yakunlandi!";
                            case EN -> "Registration completed successfully!";
                            case RU -> "Регистрация успешно завершена!";
                        };
                        bot.sendMessage(chatId, successMessage, keyboard(lang == Languages.UZ ? main_uz : lang == Languages.EN ? main_en : main_ru));
                        state.put(chatId, States.MAIN_MENU);
                    }
                }
            }
        }
    }


    private boolean isRegister(Long chatId) {
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

    private String langToMessage(Languages lang) {
        return switch (lang) {
            case UZ -> "Telefon raqamini yuborish";
            case EN -> "Share phone number";
            case RU -> "Отправить номер телефона";
        };
    }
}