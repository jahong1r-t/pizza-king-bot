package uz.pizzaking.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import uz.pizzaking.bot.MainBot;
import uz.pizzaking.utils.Util;

import static uz.pizzaking.db.Datasource.*;

public class AdminService {
    private final MainBot bot;

    public AdminService(MainBot bot) {
        this.bot = bot;
    }

    public void service(Update update) {
        String text = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();


        switch (text) {
            case "/start" -> {
                bot.sendMessage(chatId, "Assalomu alaykum", keyboard(Util.main_admin_uz));
            }
        }
    }


}
