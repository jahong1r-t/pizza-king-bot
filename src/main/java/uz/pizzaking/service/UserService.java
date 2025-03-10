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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static uz.pizzaking.db.Datasource.*;
import static uz.pizzaking.entity.enums.Languages.EN;
import static uz.pizzaking.entity.enums.Languages.UZ;
import static uz.pizzaking.utils.Buttons.*;
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

                    case Buttons.MENU_UZ -> {
                        state.put(chatId, States.PRODUCT_MENU);
                        bot.sendMessage(chatId, "Tanlang:", keyboard(product_uz));
                    }
                    case Buttons.MENU_EN -> {
                        state.put(chatId, States.PRODUCT_MENU);
                        bot.sendMessage(chatId, "Select:", keyboard(product_en));
                    }
                    case Buttons.MENU_RU -> {
                        state.put(chatId, States.PRODUCT_MENU);
                        bot.sendMessage(chatId, "Выбирать:", keyboard(product_ru));
                    }
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
            } else if (currentState == States.PRODUCT_MENU) {
                final String message = lang == UZ ? "Tanlang:" : lang == EN ? "Select:" : "Выбирать:";
                switch (text) {
                    case Buttons.PIZZA_UZ -> {
                        bot.sendMessage(chatId, message, keyboard(pizza_sub_uz));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }
                    case Buttons.HOTDOG_UZ -> {
                        bot.sendMessage(chatId, message, keyboard(lang == UZ ? hotdog_sub_uz : hotdog_sub_en));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }
                    case Buttons.BURGER_UZ -> {
                        bot.sendMessage(chatId, message, keyboard(burger_sub_uz));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }
                    case Buttons.SANDWICH_UZ -> {
                        bot.sendMessage(chatId, message, keyboard(sandwich_sub_uz));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }
                    case DRINKS_UZ -> {
                        bot.sendMessage(chatId, message, keyboard(drinks_sub_uz));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }
                    case DESSERT_UZ -> {
                        bot.sendMessage(chatId, message, keyboard(dessert_sub_uz));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }
                    case FRIES_UZ -> {
                        bot.sendMessage(chatId, message, keyboard(fries_sub_uz));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }
                    case Buttons.PIZZA_EN -> {
                        bot.sendMessage(chatId, message, keyboard(pizza_sub_en));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }
                    case Buttons.DESSERT_EN -> {
                        bot.sendMessage(chatId, message, keyboard(dessert_sub_en));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }
                    case Buttons.LAVASH_EN -> {
                        bot.sendMessage(chatId, message, keyboard(lang == UZ ? lavash_sub_uz : lavash_sub_en));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }
                    case Buttons.BURGER_EN -> {
                        bot.sendMessage(chatId, message, keyboard(burger_sub_en));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }
                    case Buttons.SANDWICH_EN -> {
                        bot.sendMessage(chatId, message, keyboard(sandwich_sub_en));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }
                    case Buttons.DRINKS_EN -> {
                        bot.sendMessage(chatId, message, keyboard(drinks_sub_en));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }
                    case FRIES_EN -> {
                        bot.sendMessage(chatId, message, keyboard(fries_sub_en));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }
                    case Buttons.PIZZA_RU -> {
                        bot.sendMessage(chatId, message, keyboard(pizza_sub_ru));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }
                    case Buttons.HOTDOG_RU -> {
                        bot.sendMessage(chatId, message, keyboard(hotdog_sub_ru));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }
                    case Buttons.LAVASH_RU -> {
                        bot.sendMessage(chatId, message, keyboard(lavash_sub_ru));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }

                    case Buttons.BURGER_RU -> {
                        bot.sendMessage(chatId, message, keyboard(burger_sub_ru));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }
                    case Buttons.SANDWICH_RU -> {
                        bot.sendMessage(chatId, message, keyboard(sandwich_sub_ru));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }
                    case DESSERT_RU -> {
                        bot.sendMessage(chatId, message, keyboard(dessert_sub_ru));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }
                    case FRIES_RU -> {
                        bot.sendMessage(chatId, message, keyboard(fries_sub_ru));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }

                    case DRINKS_RU -> {
                        bot.sendMessage(chatId, message, keyboard(drinks_sub_ru));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }
                    case BACK_UZ, BACK_RU, BACK_EN -> {
                        state.put(chatId, States.MAIN_MENU);
                        bot.sendMessage(chatId, lang == UZ ? Texts.welcome_uz : lang == Languages.RU ? Texts.welcome_ru : Texts.welcome_en, keyboard(lang == UZ ? main_uz : lang == Languages.EN ? main_en : main_ru));
                    }
                }
            } else if (currentState == States.PRODUCT_SUB_MENU) {

                switch (text) {
                    case Buttons.PIZZA_CLASSIC_UZ, Buttons.PIZZA_CLASSIC_EN, Buttons.PIZZA_CLASSIC_RU -> {
                        String message = lang == Languages.UZ ? "🍕 Klassik pitsa\nNarxi: 90 000 so'm" : lang == Languages.EN ? "🍕 Classic pizza\nPrice: 90 000 sum" : "🍕 Классическая пицца\nЦена: 90 000 сум.";
                        bot.sendMessage(chatId, message, new File("src/main/resources/pizza_classic.jpg"), productInlineButton(chatId, lang));
                    }
                    case Buttons.PIZZA_PEPPERONI_UZ, Buttons.PIZZA_PEPPERONI_EN, Buttons.PIZZA_PEPPERONI_RU -> {
                        String message = lang == Languages.UZ ? "🍕 Pepperonie pitsa\nNarxi: 120 000 so'm" : lang == Languages.EN ? "🍕 Pepperoni pizza\nPrice: 120 000 sum" : "🍕 Пицца Пепперони\nЦена: 120 000 сум.";
                        bot.sendMessage(chatId, message, new File("src/main/resources/pizza_pepperoni.jpg"), productInlineButton(chatId, lang));
                    }
                    case Buttons.PIZZA_MARGHERITA_UZ, Buttons.PIZZA_MARGHERITA_RU -> {
                        String message = lang == Languages.UZ ? "🍕 Margherita pitsa\nNarxi: 110 000 so'm" : lang == Languages.EN ? "🍕 Margherita pizza\nPrice: 110 000 sum" : "🍕 Пицца Маргарита\nЦена: 110 000 сум.";
                        bot.sendMessage(chatId, message, new File("src/main/resources/pizza_cheese.jpg"), productInlineButton(chatId, lang)); // Margherita uchun pizza_cheese.jpg ishlatildi
                    }

                    case Buttons.BURGER_CHEESE_UZ, Buttons.BURGER_CHEESE_EN, Buttons.BURGER_CHEESE_RU -> {
                        String message = lang == Languages.UZ ? "🍔 Klassik gamburger\nNarxi: 60 000 so'm" : lang == Languages.EN ? "🍔 Cheeseburger\nPrice: 60 000 sum" : "🍔 Чизбургер\nЦена: 60 000 сум.";
                        bot.sendMessage(chatId, message, new File("src/main/resources/burger_classic.jpg"), productInlineButton(chatId, lang)); // Pishloqli uchun burger_classic.jpg
                    }
                    case Buttons.BURGER_CHICKEN_UZ, Buttons.BURGER_CHICKEN_EN, Buttons.BURGER_CHICKEN_RU -> {
                        String message = lang == Languages.UZ ? "🍔 Tovuqli gamburger\nNarxi: 55 000 so'm" : lang == Languages.EN ? "🍔 Chicken Burger\nPrice: 55 000 sum" : "🍔 Куриный бургер\nЦена: 55 000 сум.";
                        bot.sendMessage(chatId, message, new File("src/main/resources/burger_chicken.jpg"), productInlineButton(chatId, lang));
                    }
                    case Buttons.BURGER_DOUBLE_UZ, Buttons.BURGER_DOUBLE_EN, Buttons.BURGER_DOUBLE_RU -> {
                        String message = lang == Languages.UZ ? "🍔 Ikki qavatli gamburger\nNarxi: 80 000 so'm" : lang == Languages.EN ? "🍔 Double Burger\nPrice: 80 000 sum" : "🍔 Двойной бургер\nЦена: 80 000 сум.";
                        bot.sendMessage(chatId, message, new File("src/main/resources/burger_double.jpg"), productInlineButton(chatId, lang));
                    }

                    // Hotdog turlari
                    case Buttons.HOTDOG_CLASSIC_UZ, Buttons.HOTDOG_CLASSIC_EN, Buttons.HOTDOG_CLASSIC_RU -> {
                        String message = lang == Languages.UZ ? "🌭 Klassik hotdog\nNarxi: 30 000 so'm" : lang == Languages.EN ? "🌭 Classic hotdog\nPrice: 30 000 sum" : "🌭 Классический хот-дог\nЦена: 30 000 сум.";
                        bot.sendMessage(chatId, message, new File("src/main/resources/hotdog_classic.jpg"), productInlineButton(chatId, lang));
                    }
                    case Buttons.HOTDOG_CHILI_UZ, Buttons.HOTDOG_CHILI_EN, Buttons.HOTDOG_CHILI_RU -> {
                        String message = lang == Languages.UZ ? "🌭 Chili hotdog\nNarxi: 35 000 so'm" : lang == Languages.EN ? "🌭 Chili Dog\nPrice: 35 000 sum" : "🌭 Чили дог\nЦена: 35 000 сум.";
                        bot.sendMessage(chatId, message, new File("src/main/resources/hotdog_chili.jpg"), productInlineButton(chatId, lang));
                    }
                    case Buttons.HOTDOG_CHEESE_UZ, Buttons.HOTDOG_CHEESE_EN, Buttons.HOTDOG_CHEESE_RU -> {
                        String message = lang == Languages.UZ ? "🌭 Pishloqli hotdog\nNarxi: 40 000 so'm" : lang == Languages.EN ? "🌭 Cheese Dog\nPrice: 40 000 sum" : "🌭 Сырный дог\nЦена: 40 000 сум.";
                        bot.sendMessage(chatId, message, new File("src/main/resources/hotdog_cheese.jpg"), productInlineButton(chatId, lang));
                    }

                    // Lavash turlari
                    case Buttons.LAVASH_MEAT_UZ, Buttons.LAVASH_MEAT_EN, Buttons.LAVASH_MEAT_RU -> {
                        String message = lang == Languages.UZ ? "🥙 Go‘shtli lavash\nNarxi: 45 000 so'm" : lang == Languages.EN ? "🥙 Meat Lavash\nPrice: 45 000 sum" : "🥙 Мясной лаваш\nЦена: 45 000 сум.";
                        bot.sendMessage(chatId, message, new File("src/main/resources/lavash_meat.jpg"), productInlineButton(chatId, lang));
                    }
                    case Buttons.LAVASH_CHICKEN_UZ, Buttons.LAVASH_CHICKEN_EN, Buttons.LAVASH_CHICKEN_RU -> {
                        String message = lang == Languages.UZ ? "🥙 Tovuqli lavash\nNarxi: 40 000 so'm" : lang == Languages.EN ? "🥙 Chicken Lavash\nPrice: 40 000 sum" : "🥙 Куриный лаваш\nЦена: 40 000 сум.";
                        bot.sendMessage(chatId, message, new File("src/main/resources/lavash_chicken.jpg"), productInlineButton(chatId, lang));
                    }
                    case Buttons.LAVASH_CHEESE_UZ, Buttons.LAVASH_CHEESE_EN, Buttons.LAVASH_CHEESE_RU -> {
                        String message = lang == Languages.UZ ? "🥙 Pishloqli lavash\nNarxi: 42 000 so'm" : lang == Languages.EN ? "🥙 Cheese Lavash\nPrice: 42 000 sum" : "🥙 Сырный лаваш\nЦена: 42 000 сум.";
                        bot.sendMessage(chatId, message, new File("src/main/resources/lavash_cheese.jpg"), productInlineButton(chatId, lang));
                    }

                    // Sandwich turlari
                    case Buttons.SANDWICH_CLUB_UZ, Buttons.SANDWICH_CLUB_EN, Buttons.SANDWICH_CLUB_RU -> {
                        String message = lang == Languages.UZ ? "🥪 Klub sendvichi\nNarxi: 50 000 so'm" : lang == Languages.EN ? "🥪 Club Sandwich\nPrice: 50 000 sum" : "🥪 Клубный сэндвич\nЦена: 50 000 сум.";
                        bot.sendMessage(chatId, message, new File("src/main/resources/sandwich_classic.jpg"), productInlineButton(chatId, lang));
                    }
                    case Buttons.SANDWICH_VEGGIE_UZ, Buttons.SANDWICH_VEGGIE_EN, Buttons.SANDWICH_VEGGIE_RU -> {
                        String message = lang == Languages.UZ ? "🥪 Pishloqli sendvich\nNarxi: 45 000 so'm" : lang == Languages.EN ? "🥪 Veggie Sandwich\nPrice: 45 000 sum" : "🥪 Овощной сэндвич\nЦена: 45 000 сум.";
                        bot.sendMessage(chatId, message, new File("src/main/resources/sandwich_cheese.jpg"), productInlineButton(chatId, lang));
                    }

                    // Fries turlari
                    case Buttons.FRIES_PLAIN_UZ, Buttons.FRIES_PLAIN_EN, Buttons.FRIES_PLAIN_RU -> {
                        String message = lang == Languages.UZ ? "🍟 Oddiy kartoshka fri\nNarxi: 20 000 so'm" : lang == Languages.EN ? "🍟 Plain fries\nPrice: 20 000 sum" : "🍟 Обычная картошка фри\nЦена: 20 000 сум.";
                        bot.sendMessage(chatId, message, new File("src/main/resources/fri_classic.jpg"), productInlineButton(chatId, lang));
                    }
                    case Buttons.FRIES_CHEESE_UZ, Buttons.FRIES_CHEESE_EN, Buttons.FRIES_CHEESE_RU -> {
                        String message = lang == Languages.UZ ? "🍟 Pishloqli kartoshka fri\nNarxi: 25 000 so'm" : lang == Languages.EN ? "🍟 Cheesy fries\nPrice: 25 000 sum" : "🍟 Картошка фри с сырным соусом\nЦена: 25 000 сум.";
                        bot.sendMessage(chatId, message, new File("src/main/resources/fri_cheese.jpg"), productInlineButton(chatId, lang));
                    }

                    // Dessert turlari
                    case Buttons.DESSERT_CHEESECAKE_UZ, Buttons.DESSERT_CHEESECAKE_RU -> {
                        String message = lang == Languages.UZ ? "🍮 Cheesecake\nNarxi: 35 000 so'm" : lang == Languages.EN ? "🍮 Cheesecake\nPrice: 35 000 sum" : "🍮 Чизкейк\nЦена: 35 000 сум.";
                        bot.sendMessage(chatId, message, new File("src/main/resources/cake_cheesecake.jpg"), productInlineButton(chatId, lang));
                    }
                    case Buttons.DESSERT_MEDOVIK_UZ, Buttons.DESSERT_MEDOVIK_RU -> {
                        String message = lang == Languages.UZ ? "🍮 Medovik\nNarxi: 30 000 so'm" : lang == Languages.EN ? "🍮 Medovik\nPrice: 30 000 sum" : "🍮 Медовик\nЦена: 30 000 сум.";
                        bot.sendMessage(chatId, message, new File("src/main/resources/cace_medovik.jpg"), productInlineButton(chatId, lang));
                    }
                    case Buttons.DESSERT_NAPALEON_UZ, Buttons.DESSERT_NAPALEON_EN, Buttons.DESSERT_NAPALEON_RU -> {
                        String message = lang == Languages.UZ ? "🍮 Napaleon\nNarxi: 32 000 so'm" : lang == Languages.EN ? "🍮 Napoleon\nPrice: 32 000 sum" : "🍮 Наполеон\nЦена: 32 000 сум.";
                        bot.sendMessage(chatId, message, new File("src/main/resources/cace_napaleon.jpg"), productInlineButton(chatId, lang));
                    }

                    case Buttons.DRINKS_COLA_UZ, Buttons.DRINKS_COLA_RU -> {
                        String message = lang == Languages.UZ ? "🥤 Cola\nNarxi: 10 000 so'm" : lang == Languages.EN ? "🥤 Cola\nPrice: 10 000 sum" : "🥤 Кола\nЦена: 10 000 сум.";
                        bot.sendMessage(chatId, message, new File("src/main/resources/drink_cola.jpg"), productInlineButton(chatId, lang));
                    }
                    case Buttons.DRINKS_SPRITE_UZ, Buttons.DRINKS_SPRITE_RU -> {
                        String message = lang == Languages.UZ ? "🥤 Sprite\nNarxi: 10 000 so'm" : lang == Languages.EN ? "🥤 Sprite\nPrice: 10 000 sum" : "🥤 Спрайт\nЦена: 10 000 сум.";
                        bot.sendMessage(chatId, message, new File("src/main/resources/drink_sprite.jpg"), productInlineButton(chatId, lang));
                    }
                    case Buttons.DRINKS_TEA_UZ, Buttons.DRINKS_TEA_EN, Buttons.DRINKS_TEA_RU -> {
                        String message = lang == Languages.UZ ? "🍵 Choy\nNarxi: 8 000 so'm" : lang == Languages.EN ? "🍵 Tea\nPrice: 8 000 sum" : "🍵 Чай\nЦена: 8 000 сум.";
                        bot.sendMessage(chatId, message, new File("src/main/resources/drink_tea.jpg"), productInlineButton(chatId, lang));
                    }
                    case Buttons.DRINKS_COFFEE_UZ, Buttons.DRINKS_COFFEE_EN, Buttons.DRINKS_COFFEE_RU -> {
                        String message = lang == Languages.UZ ? "☕ Kofe\nNarxi: 12 000 so'm" : lang == Languages.EN ? "☕ Coffee\nPrice: 12 000 sum" : "☕ Кофе\nЦена: 12 000 сум.";
                        bot.sendMessage(chatId, message, new File("src/main/resources/drink_coffe.jpg"), productInlineButton(chatId, lang));
                    }

                    // Orqaga tugmasi
                    case BACK_UZ, BACK_EN, BACK_RU -> {
                        String backMessage = lang == Languages.UZ ? "Asosiy menyuga qaytdik!" : lang == Languages.EN ? "Back to main menu!" : "Вернулись в главное меню!";
                        bot.sendMessage(chatId, backMessage, keyboard(lang == Languages.UZ ? product_uz : lang == Languages.EN ? product_en : product_ru));
                        state.put(chatId, States.PRODUCT_MENU);
                    }
                }
            } else if (currentState == States.WAIT_MESSAGE) {
                String builder = "📨 Yangi xabar keldi!\n" + "👤 Kimdan: @"
                        + (from.getUserName() != null ? from.getUserName() :
                        "Username yo‘q") + "\n" + "📝 Ism: " + from.getFirstName()
                        + "\n💬 Xabar: " + text + "\n";
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

    private ReplyKeyboard productInlineButton(Long chatId, Languages lang) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        InlineKeyboardButton minusButton = new InlineKeyboardButton();
        InlineKeyboardButton plusButton = new InlineKeyboardButton();

        minusButton.setText("-");
        minusButton.setCallbackData(chatId.toString() + "minus");
        plusButton.setText("+");
        plusButton.setCallbackData(chatId.toString() + "plus");

        firstRow.add(minusButton);
        firstRow.add(plusButton);
        rows.add(firstRow);

        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        InlineKeyboardButton addToCartButton = new InlineKeyboardButton();

        addToCartButton.setText(lang == Languages.UZ ? "Savatga qo'shish 🛒" : lang == Languages.EN ? "Add to cart 🛒" : "Добавить в корзину 🛒");
        addToCartButton.setCallbackData(chatId.toString() + "add_to_cart");

        secondRow.add(addToCartButton);
        rows.add(secondRow);

        markup.setKeyboard(rows);
        return markup;
    }

}