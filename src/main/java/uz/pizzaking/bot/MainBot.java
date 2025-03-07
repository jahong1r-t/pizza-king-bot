package uz.pizzaking.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.pizzaking.utils.Bot;

import static uz.pizzaking.utils.Bot.BOT_TOKEN;
import static uz.pizzaking.utils.Bot.BOT_USERNAME;

public class MainBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {

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
