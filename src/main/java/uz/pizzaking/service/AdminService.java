package uz.pizzaking.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import uz.pizzaking.bot.MainBot;
import uz.pizzaking.entity.enums.States;
import uz.pizzaking.utils.Buttons;

import static uz.pizzaking.db.Datasource.*;

public class AdminService {
    private final MainBot bot;

    public AdminService(MainBot bot) {
        this.bot = bot;
    }

    public void service(Update update) {
        String text = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();

        States currentState = state.getOrDefault(chatId, States.MAIN_MENU);

        if (update.hasMessage()) {
            switch (currentState) {
                case MAIN_ADMIN -> {
                    switch (text) {
                        case Buttons.USERS_UZ -> {

                        }
                        case Buttons.STATISTICS_UZ -> {

                        }
                        case Buttons.MESSAGE_FOR_ALL_UZ -> {

                        }
                        case Buttons.SETTINGS_ADMIN_UZ -> {

                        }
                    }
                }
            }
        }
    }


}
