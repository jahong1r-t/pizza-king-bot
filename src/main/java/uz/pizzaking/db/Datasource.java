package uz.pizzaking.db;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.pizzaking.entity.Basket;
import uz.pizzaking.entity.Product;
import uz.pizzaking.entity.User;
import uz.pizzaking.entity.enums.Languages;
import uz.pizzaking.entity.enums.States;

import java.util.*;

public class Datasource {
    public static final Long ADMIN = 5699941692L;

    public static Map<Long, States> state = new HashMap<>();

    public static Map<Long, User> users = new HashMap<>();

    public static final List<Product> products = new ArrayList<>();

    static {
        users.put(ADMIN, new User(ADMIN, "turayev_j", "+998975881554", "Jahongir", null, new Basket(ADMIN, new ArrayList<>()), Languages.UZBEK, null));
    }


    static {
        products.add(new Product(UUID.randomUUID(), "🍕 Klassik pitsa", "🍕 Classic pizza", "🍕 Классическая пицца", 90000));
        products.add(new Product(UUID.randomUUID(), "🍕 Pepperonie pitsa", "🍕 Pepperoni pizza", "🍕 Пицца Пепперони", 120000));
        products.add(new Product(UUID.randomUUID(), "🍕 Margherita pitsa", "🍕 Margherita pizza", "🍕 Пицца Маргарита", 110000));
        products.add(new Product(UUID.randomUUID(), "🍔 Klassik gamburger", "🍔 Cheeseburger", "🍔 Чизбургер", 60000));
        products.add(new Product(UUID.randomUUID(), "🍔 Tovuqli gamburger", "🍔 Chicken Burger", "🍔 Куриный бургер", 55000));
        products.add(new Product(UUID.randomUUID(), "🍔 Ikki qavatli gamburger", "🍔 Double Burger", "🍔 Двойной бургер", 80000));
        products.add(new Product(UUID.randomUUID(), "🌭 Klassik hotdog", "🌭 Classic hotdog", "🌭 Классический хот-дог", 30000));
        products.add(new Product(UUID.randomUUID(), "🌭 Chili hotdog", "🌭 Chili Dog", "🌭 Чили дог", 35000));
        products.add(new Product(UUID.randomUUID(), "🌭 Pishloqli hotdog", "🌭 Cheese Dog", "🌭 Сырный дог", 40000));
        products.add(new Product(UUID.randomUUID(), "🥙 Go‘shtli lavash", "🥙 Meat Lavash", "🥙 Мясной лаваш", 45000));
        products.add(new Product(UUID.randomUUID(), "🥙 Tovuqli lavash", "🥙 Chicken Lavash", "🥙 Куриный лаваш", 40000));
        products.add(new Product(UUID.randomUUID(), "🥙 Pishloqli lavash", "🥙 Cheese Lavash", "🥙 Сырный лаваш", 42000));
        products.add(new Product(UUID.randomUUID(), "🥪 Klub sendvichi", "🥪 Club Sandwich", "🥪 Клубный сэндвич", 50000));
        products.add(new Product(UUID.randomUUID(), "🥪 Pishloqli sendvich", "🥪 Veggie Sandwich", "🥪 Овощной сэндвич", 45000));
        products.add(new Product(UUID.randomUUID(), "🍟 Oddiy kartoshka fri", "🍟 Plain fries", "🍟 Обычная картошка фри", 20000));
        products.add(new Product(UUID.randomUUID(), "🍟 Pishloqli kartoshka fri", "🍟 Cheesy fries", "🍟 Картошка фри с сырным соусом", 25000));
        products.add(new Product(UUID.randomUUID(), "🍮 Cheesecake", "🍮 Cheesecake", "🍮 Чизкейк", 35000));
        products.add(new Product(UUID.randomUUID(), "🍮 Medovik", "🍮 Medovik", "🍮 Медовик", 30000));
        products.add(new Product(UUID.randomUUID(), "🍮 Napaleon", "🍮 Napoleon", "🍮 Наполеон", 32000));
        products.add(new Product(UUID.randomUUID(), "🥤 Cola", "🥤 Cola", "🥤 Кола", 10000));
        products.add(new Product(UUID.randomUUID(), "🥤 Sprite", "🥤 Sprite", "🥤 Спрайт", 10000));
        products.add(new Product(UUID.randomUUID(), "🍵 Choy", "🍵 Tea", "🍵 Чай", 8000));
        products.add(new Product(UUID.randomUUID(), "☕ Kofe", "☕ Coffee", "☕ Кофе", 12000));
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
