package uz.pizzaking.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.pizzaking.bot.MainBot;

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
        String text = update.getMessage().getText();

        if (update.hasMessage()) {
            switch (text) {
                case "/start" -> {
                    if (isRegister(chatId) && !Objects.equals(chatId, ADMIN)) {
                        new UserService().service(update);
                    } else if (Objects.equals(chatId, ADMIN)) {
                        new AdminService().service();
                    } else {
                        bot.sendMessageWithKeyboard(chatId, "Welcome to Pizza King. Please select the desired language.", keyboard(languages));
                    }
                }
                default -> {
                    bot.sendMessage(chatId, "I don't understand !");
                }
            }
        }
    }

    private boolean isRegister(Long chatId) {
        return users.get(chatId) != null;
    }

    private ReplyKeyboardMarkup keyboard(String[][] buttons) {
        List<KeyboardRow> rows = new ArrayList<>();

        for (String[] button : buttons) {
            KeyboardRow keyboardRow = new KeyboardRow();
            for (String s : button) {
                keyboardRow.add(s);
            }
            rows.add(keyboardRow);
        }
        ReplyKeyboardMarkup reply = new ReplyKeyboardMarkup();
        reply.setResizeKeyboard(true);
        reply.setKeyboard(rows);

        return reply;
    }
}