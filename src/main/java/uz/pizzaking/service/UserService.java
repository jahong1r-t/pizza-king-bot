package uz.pizzaking.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.pizzaking.bot.MainBot;
import uz.pizzaking.entity.enums.Languages;
import uz.pizzaking.entity.enums.States;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static uz.pizzaking.db.Datasource.*;
import static uz.pizzaking.entity.enums.Languages.*;
import static uz.pizzaking.utils.Buttons.*;
import static uz.pizzaking.utils.Messages.*;
import static uz.pizzaking.utils.Util.languages;

public class UserService {
    private final MainBot bot;

    public UserService(MainBot bot) {
        this.bot = bot;
    }

    public void service(Update update) {
        String text = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        Integer messageId = update.getMessage().getMessageId();
        User from = update.getMessage().getFrom();
        Languages lang = users.get(chatId).getLanguage();

        if (update.hasMessage()) {
            state.putIfAbsent(chatId, States.MAIN_MENU);
            States currentState = state.get(chatId);

            if (currentState == States.MAIN_MENU) {
                switch (text) {
                    case "/start" -> bot.sendMessage(chatId, welcome_msg(lang), main_keyboard(lang));
                    case MENU_UZ, MENU_EN, MENU_RU -> {
                        bot.sendMessage(chatId, select_msg(lang), product_menu(lang));
                        state.put(chatId, States.PRODUCT_MENU);
                    }
                    case ABOUT_UZ, ABOUT_EN, ABOUT_RU -> bot.sendMessage(chatId, about_msg(lang), logo_path);
                    case CALL_UZ, CALL_EN, CALL_RU -> bot.sendMessage(chatId, call_num_msg(lang), call_path);
                    case SETTINGS_UZ, SETTINGS_EN, SETTINGS_RU -> {
                        bot.sendMessage(chatId, settings_menu_text(lang), settings_keyboard(lang));
                        state.put(chatId, States.SETTINGS);
                    }
                    case SEND_MESSAGE_UZ, SEND_MESSAGE_EN, SEND_MESSAGE_RU -> {
                        bot.sendMessage(chatId, send_msg(lang));
                        state.put(chatId, States.WAIT_MESSAGE);
                    }
                    default -> bot.sendMessage(chatId, error_msg(lang));
                }


            } else if (currentState == States.SETTINGS) {
                switch (text) {
                    case CHANGE_LANG_UZ, CHANGE_LANG_EN, CHANGE_LANG_RU -> {
                        bot.sendMessage(chatId, change_lang_msg(lang), keyboard(languages));
                    }
                    case UZ, EN, RU -> {
                        Languages newLang = text.equals(UZ) ? Languages.UZBEK : text.equals(EN) ? Languages.ENGLISH : Languages.RUSSIAN;
                        users.get(chatId).setLanguage(newLang);

                        Languages language = users.get(chatId).getLanguage();

                        bot.sendMessage(chatId, change_lang_success_msg(language), main_keyboard(language));
                        state.put(chatId, States.MAIN_MENU);
                    }
                    case BACK_UZ, BACK_RU, BACK_EN -> {
                        bot.sendMessage(chatId, welcome_msg(lang), main_keyboard(lang));
                        state.put(chatId, States.MAIN_MENU);
                    }
                }
            } else if (currentState == States.PRODUCT_MENU) {
                switch (text) {
                    case PIZZA_UZ, PIZZA_RU, PIZZA_EN -> {
                        bot.sendMessage(chatId, select_msg(lang), pizza_sub_menu(lang));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }
                    case HOTDOG_UZ, HOTDOG_RU -> {
                        bot.sendMessage(chatId, select_msg(lang), hotdog_sub_menu(lang));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }
                    case LAVASH_UZ, LAVASH_RU -> {
                        bot.sendMessage(chatId, select_msg(lang), lavash_sub_menu(lang));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }
                    case BURGER_UZ, BURGER_EN, BURGER_RU -> {
                        bot.sendMessage(chatId, select_msg(lang), burger_sub_menu(lang));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }
                    case SANDWICH_UZ, SANDWICH_EN, SANDWICH_RU -> {
                        bot.sendMessage(chatId, select_msg(lang), sandwich_sub_menu(lang));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }
                    case DRINKS_UZ, DRINKS_EN, DRINKS_RU -> {
                        bot.sendMessage(chatId, select_msg(lang), drinks_sub_menu(lang));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }
                    case DESSERT_UZ, DESSERT_EN, DESSERT_RU -> {
                        bot.sendMessage(chatId, select_msg(lang), dessert_sub_menu(lang));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }
                    case FRIES_UZ, FRIES_EN, FRIES_RU -> {
                        bot.sendMessage(chatId, select_msg(lang), fries_sub_menu(lang));
                        state.put(chatId, States.PRODUCT_SUB_MENU);
                    }
                    case BACK_UZ, BACK_RU, BACK_EN -> {
                        bot.sendMessage(chatId, welcome_msg(lang), main_keyboard(lang));
                        state.put(chatId, States.MAIN_MENU);
                    }
                }
            } else if (currentState == States.PRODUCT_SUB_MENU) {
                switch (text) {
                    case PIZZA_CLASSIC_UZ, PIZZA_CLASSIC_EN, PIZZA_CLASSIC_RU ->
                            bot.sendMessage(chatId, pizza_classic_details(lang), new File("src/main/resources/pizza_classic.jpg"), productInlineButton(chatId, lang));
                    case PIZZA_PEPPERONI_UZ, PIZZA_PEPPERONI_EN, PIZZA_PEPPERONI_RU ->
                            bot.sendMessage(chatId, pizza_pepperoni_details(lang), new File("src/main/resources/pizza_pepperoni.jpg"), productInlineButton(chatId, lang));
                    case PIZZA_MARGHERITA_UZ, PIZZA_MARGHERITA_RU ->
                            bot.sendMessage(chatId, pizza_margherita_details(lang), new File("src/main/resources/pizza_cheese.jpg"), productInlineButton(chatId, lang));
                    case BURGER_CHEESE_UZ, BURGER_CHEESE_EN, BURGER_CHEESE_RU ->
                            bot.sendMessage(chatId, burger_cheese_details(lang), new File("src/main/resources/burger_classic.jpg"), productInlineButton(chatId, lang));
                    case BURGER_CHICKEN_UZ, BURGER_CHICKEN_EN, BURGER_CHICKEN_RU ->
                            bot.sendMessage(chatId, burger_chicken_details(lang), new File("src/main/resources/burger_chicken.jpg"), productInlineButton(chatId, lang));
                    case BURGER_DOUBLE_UZ, BURGER_DOUBLE_EN, BURGER_DOUBLE_RU ->
                            bot.sendMessage(chatId, burger_double_details(lang), new File("src/main/resources/burger_double.jpg"), productInlineButton(chatId, lang));
                    case HOTDOG_CLASSIC_UZ, HOTDOG_CLASSIC_EN, HOTDOG_CLASSIC_RU ->
                            bot.sendMessage(chatId, hotdog_classic_details(lang), new File("src/main/resources/hotdog_classic.jpg"), productInlineButton(chatId, lang));
                    case HOTDOG_CHILI_UZ, HOTDOG_CHILI_EN, HOTDOG_CHILI_RU ->
                            bot.sendMessage(chatId, hotdog_chili_details(lang), new File("src/main/resources/hotdog_chili.jpg"), productInlineButton(chatId, lang));
                    case HOTDOG_CHEESE_UZ, HOTDOG_CHEESE_EN, HOTDOG_CHEESE_RU ->
                            bot.sendMessage(chatId, hotdog_cheese_details(lang), new File("src/main/resources/hotdog_cheese.jpg"), productInlineButton(chatId, lang));
                    case LAVASH_MEAT_UZ, LAVASH_MEAT_EN, LAVASH_MEAT_RU ->
                            bot.sendMessage(chatId, lavash_meat_details(lang), new File("src/main/resources/lavash_meat.jpg"), productInlineButton(chatId, lang));
                    case LAVASH_CHICKEN_UZ, LAVASH_CHICKEN_EN, LAVASH_CHICKEN_RU ->
                            bot.sendMessage(chatId, lavash_chicken_details(lang), new File("src/main/resources/lavash_chicken.jpg"), productInlineButton(chatId, lang));
                    case LAVASH_CHEESE_UZ, LAVASH_CHEESE_EN, LAVASH_CHEESE_RU ->
                            bot.sendMessage(chatId, lavash_cheese_details(lang), new File("src/main/resources/lavash_cheese.jpg"), productInlineButton(chatId, lang));
                    case SANDWICH_CLUB_UZ, SANDWICH_CLUB_EN, SANDWICH_CLUB_RU ->
                            bot.sendMessage(chatId, sandwich_club_details(lang), new File("src/main/resources/sandwich_classic.jpg"), productInlineButton(chatId, lang));
                    case SANDWICH_VEGGIE_UZ, SANDWICH_VEGGIE_EN, SANDWICH_VEGGIE_RU ->
                            bot.sendMessage(chatId, sandwich_veggie_details(lang), new File("src/main/resources/sandwich_cheese.jpg"), productInlineButton(chatId, lang));
                    case FRIES_PLAIN_UZ, FRIES_PLAIN_EN, FRIES_PLAIN_RU ->
                            bot.sendMessage(chatId, fries_plain_details(lang), new File("src/main/resources/fri_classic.jpg"), productInlineButton(chatId, lang));
                    case FRIES_CHEESE_UZ, FRIES_CHEESE_EN, FRIES_CHEESE_RU ->
                            bot.sendMessage(chatId, fries_cheese_details(lang), new File("src/main/resources/fri_cheese.jpg"), productInlineButton(chatId, lang));
                    case DESSERT_CHEESECAKE_UZ, DESSERT_CHEESECAKE_RU ->
                            bot.sendMessage(chatId, dessert_cheesecake_details(lang), new File("src/main/resources/cake_cheesecake.jpg"), productInlineButton(chatId, lang));
                    case DESSERT_MEDOVIK_UZ, DESSERT_MEDOVIK_RU ->
                            bot.sendMessage(chatId, dessert_medovik_details(lang), new File("src/main/resources/cake_medovik.jpg"), productInlineButton(chatId, lang));
                    case DESSERT_NAPALEON_UZ, DESSERT_NAPALEON_EN, DESSERT_NAPALEON_RU ->
                            bot.sendMessage(chatId, dessert_napoleon_details(lang), new File("src/main/resources/cake_napaleon.jpg"), productInlineButton(chatId, lang));
                    case DRINKS_COLA_UZ, DRINKS_COLA_RU ->
                            bot.sendMessage(chatId, drinks_cola_details(lang), new File("src/main/resources/drink_cola.jpg"), productInlineButton(chatId, lang));
                    case DRINKS_SPRITE_UZ, DRINKS_SPRITE_RU ->
                            bot.sendMessage(chatId, drinks_sprite_details(lang), new File("src/main/resources/drink_sprite.jpg"), productInlineButton(chatId, lang));
                    case DRINKS_TEA_UZ, DRINKS_TEA_EN, DRINKS_TEA_RU ->
                            bot.sendMessage(chatId, drinks_tea_details(lang), new File("src/main/resources/drink_tea.jpg"), productInlineButton(chatId, lang));
                    case DRINKS_COFFEE_UZ, DRINKS_COFFEE_EN, DRINKS_COFFEE_RU ->
                            bot.sendMessage(chatId, drinks_coffee_details(lang), new File("src/main/resources/drink_coffee.jpg"), productInlineButton(chatId, lang));
                    case BACK_UZ, BACK_EN, BACK_RU -> {
                        bot.sendMessage(chatId, back_msg(lang), product_menu(lang));
                        state.put(chatId, States.PRODUCT_MENU);
                    }
                }
            } else if (currentState == States.WAIT_MESSAGE) {
                String phoneNumber = users.get(chatId).getPhoneNumber();
                Languages language = users.get(ADMIN).getLanguage();

                    bot.sendMessage(ADMIN, msg_to_admin(language, from, phoneNumber, text), getInlineKeyboard(chatId, "id" + chatId + "msgId" + messageId));
                bot.sendMessage(chatId, res_message(lang));
                state.remove(chatId);
            }
        }
    }

    private ReplyKeyboard getInlineKeyboard(Long chatId, String data) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> listButtons = new ArrayList<>();

        List<InlineKeyboardButton> buttons = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText(adminLanguage == UZBEK ? "Javob berish" : adminLanguage == ENGLISH ? "Reply" : "Отвечать");
        button1.setCallbackData(data);
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
        plusButton.setCallbackData(chatId + "plus");

        firstRow.add(minusButton);
        firstRow.add(plusButton);
        rows.add(firstRow);

        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        InlineKeyboardButton addToCartButton = new InlineKeyboardButton();

        addToCartButton.setText(to_cart(lang));
        addToCartButton.setCallbackData(chatId + "add_to_cart");

        secondRow.add(addToCartButton);
        rows.add(secondRow);

        markup.setKeyboard(rows);
        return markup;
    }
}