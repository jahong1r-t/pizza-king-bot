package uz.pizzaking.db;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.pizzaking.entity.User;
import uz.pizzaking.entity.enums.Languages;
import uz.pizzaking.entity.enums.States;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Datasource {
    public static final Long ADMIN = 5699941692L;

    public static Map<Long, States> state = new HashMap<>();

    public static Map<Long, User> users = new HashMap<>();

    static {
        users.put(ADMIN, new User(ADMIN, "turayev_j", "998975881554", "Jahongir", null, Languages.UZBEK));
    }

    public static Languages adminLanguage = users.get(ADMIN).getLanguage();

    public static ReplyKeyboardMarkup keyboard(String[][] buttons) {
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
