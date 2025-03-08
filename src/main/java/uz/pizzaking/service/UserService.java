package uz.pizzaking.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import uz.pizzaking.bot.MainBot;
import uz.pizzaking.entity.enums.States;
import uz.pizzaking.utils.Buttons;
import uz.pizzaking.utils.Texts;

import java.io.File;

import static uz.pizzaking.db.Datasource.*;


public class UserService {
    private MainBot bot;

    public UserService(MainBot bot) {
        this.bot = bot;
    }

    public void service(Update update) {
        String text = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();

        if (update.hasMessage()) {
            States currentState = state.getOrDefault(chatId, States.MAIN_MENU);

            switch (currentState) {
                case MAIN_MENU -> {
                    switch (text) {
                        case Buttons.ABOUT_UZ -> {
                            bot.sendMessage(chatId, Texts.about_uz, new File("src/main/resources/logo vertical.png"));
                        }
                        case Buttons.ABOUT_EN -> {
                            bot.sendMessage(chatId, Texts.about_en, new File("src/main/resources/logo vertical.png"));
                        }
                        case Buttons.ABOUT_RU -> {
                            bot.sendMessage(chatId, Texts.about_ru, new File("src/main/resources/logo vertical.png"));
                        }
                    }
                }
            }
        }
    }

}
