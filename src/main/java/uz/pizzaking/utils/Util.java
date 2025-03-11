package uz.pizzaking.utils;

import static uz.pizzaking.utils.Buttons.*;

public interface Util {
    /// ALL languages
    String[][] languages = {
            {UZ, EN, RU},
    };

    /// ///User panel keyboard
    String[][] main_uz = {
            {MENU_UZ},
            {MY_ORDERS_UZ},
            {BASKET_UZ, CALL_UZ},
            {SEND_MESSAGE_UZ, SETTINGS_UZ},
            {ABOUT_UZ}
    };

    String[][] main_en = {
            {MENU_EN},
            {MY_ORDERS_EN},
            {BASKET_EN, CALL_EN},
            {SEND_MESSAGE_EN, SETTINGS_EN},
            {ABOUT_EN}
    };

    String[][] main_ru = {
            {MENU_RU},
            {MY_ORDERS_RU},
            {BASKET_RU, CALL_RU},
            {SEND_MESSAGE_RU, SETTINGS_RU},
            {ABOUT_RU}
    };

    ///Admin panel buttons
    String[][] main_admin_uz = {
            {USERS_UZ, STATISTICS_UZ},
            {MESSAGE_FOR_ALL_UZ},
            {SETTINGS_ADMIN_UZ}
    };
    String[][] main_admin_en = {
            {USERS_EN, STATISTICS_EN},
            {MESSAGE_FOR_ALL_EN},
            {SETTINGS_ADMIN_EN}
    };
    String[][] main_admin_ru = {
            {USERS_RU, STATISTICS_RU},
            {MESSAGE_FOR_ALL_RU},
            {SETTINGS_ADMIN_RU}
    };

    ///Products keyboards
    String[][] product_uz = {
            {PIZZA_UZ, HOTDOG_UZ, LAVASH_UZ},
            {BURGER_UZ, SANDWICH_UZ, FRIES_UZ},
            {DESSERT_UZ, DRINKS_UZ},
            {BACK_UZ}
    };
    String[][] product_en = {
            {PIZZA_EN, HOTDOG_EN, LAVASH_EN},
            {BURGER_EN, SANDWICH_EN, FRIES_EN},
            {DESSERT_EN, DRINKS_EN},
            {BACK_EN}
    };
    String[][] product_ru = {
            {PIZZA_RU, HOTDOG_RU, LAVASH_RU},
            {BURGER_RU, SANDWICH_RU, FRIES_RU},
            {DESSERT_RU, DRINKS_RU},
            {BACK_RU}
    };

    /// Product sub menu uz
    String[][] pizza_sub_uz = {
            {PIZZA_CLASSIC_UZ, PIZZA_PEPPERONI_UZ},
            {PIZZA_MARGHERITA_UZ},
            {BACK_UZ}
    };

    String[][] hotdog_sub_uz = {
            {HOTDOG_CLASSIC_UZ, HOTDOG_CHILI_UZ},
            {HOTDOG_CHEESE_UZ},
            {BACK_UZ}
    };

    String[][] lavash_sub_uz = {
            {LAVASH_MEAT_UZ, LAVASH_CHICKEN_UZ},
            {LAVASH_CHEESE_UZ},
            {BACK_UZ}
    };

    String[][] burger_sub_uz = {
            {BURGER_CHEESE_UZ, BURGER_CHICKEN_UZ},
            {BURGER_DOUBLE_UZ},
            {BACK_UZ}
    };

    String[][] sandwich_sub_uz = {
            {SANDWICH_CLUB_UZ, SANDWICH_VEGGIE_UZ},
            {BACK_UZ}
    };

    String[][] dessert_sub_uz = {
            {DESSERT_CHEESECAKE_UZ, DESSERT_MEDOVIK_UZ},
            {DESSERT_NAPALEON_UZ}, {BACK_UZ}
    };

    String[][] fries_sub_uz = {
            {FRIES_PLAIN_UZ, FRIES_CHEESE_UZ},
            {BACK_UZ}
    };

    String[][] drinks_sub_uz = {
            {DRINKS_COLA_UZ, DRINKS_SPRITE_UZ},
            {DRINKS_TEA_UZ, DRINKS_COFFEE_UZ},
            {BACK_UZ}
    };

    /// Product sub menu en
    String[][] pizza_sub_en = {
            {PIZZA_CLASSIC_EN, PIZZA_PEPPERONI_EN},
            {PIZZA_MARGHERITA_EN},
            {BACK_EN}
    };

    String[][] dessert_sub_en = {
            {DESSERT_CHEESECAKE_EN, DESSERT_MEDOVIK_EN},
            {DESSERT_NAPALEON_EN}, {BACK_EN}
    };

    String[][] hotdog_sub_en = {
            {HOTDOG_CLASSIC_EN, HOTDOG_CHILI_EN},
            {HOTDOG_CHEESE_EN},
            {BACK_EN}
    };

    String[][] lavash_sub_en = {
            {LAVASH_MEAT_EN, LAVASH_CHICKEN_EN},
            {LAVASH_CHEESE_EN},
            {BACK_EN}
    };

    String[][] burger_sub_en = {
            {BURGER_CHEESE_EN, BURGER_CHICKEN_EN},
            {BURGER_DOUBLE_EN},
            {BACK_EN}

    };

    String[][] sandwich_sub_en = {
            {SANDWICH_CLUB_EN, SANDWICH_VEGGIE_EN},
            {BACK_EN}
    };

    String[][] fries_sub_en = {
            {FRIES_PLAIN_EN, FRIES_CHEESE_EN},
            {BACK_EN}
    };

    String[][] drinks_sub_en = {
            {DRINKS_COLA_EN, DRINKS_SPRITE_EN},
            {DRINKS_TEA_EN, DRINKS_COFFEE_EN},
            {BACK_EN}
    };

    /// Product sub menu ru
    String[][] pizza_sub_ru = {
            {PIZZA_CLASSIC_RU, PIZZA_PEPPERONI_RU},
            {PIZZA_MARGHERITA_RU},
            {BACK_RU}
    };

    String[][] hotdog_sub_ru = {
            {HOTDOG_CLASSIC_RU, HOTDOG_CHILI_RU},
            {HOTDOG_CHEESE_RU},
            {BACK_RU}
    };

    String[][] lavash_sub_ru = {
            {LAVASH_MEAT_RU, LAVASH_CHICKEN_RU},
            {LAVASH_CHEESE_RU},
            {BACK_RU}
    };

    String[][] burger_sub_ru = {
            {BURGER_CHEESE_RU, BURGER_CHICKEN_RU},
            {BURGER_DOUBLE_RU},
            {BACK_RU}
    };

    String[][] sandwich_sub_ru = {
            {SANDWICH_CLUB_RU, SANDWICH_VEGGIE_RU},
            {BACK_RU}
    };

    String[][] dessert_sub_ru = {
            {DESSERT_CHEESECAKE_RU, DESSERT_MEDOVIK_RU},
            {DESSERT_NAPALEON_RU}, {BACK_RU}
    };

    String[][] drinks_sub_ru = {
            {DRINKS_COLA_RU, DRINKS_SPRITE_RU},
            {DRINKS_TEA_RU, DRINKS_COFFEE_RU},
            {BACK_RU}
    };
    String[][] fries_sub_ru = {
            {FRIES_PLAIN_RU, FRIES_CHEESE_RU},
            {BACK_RU}
    };

    String[][] settings_uz = {
            {CHANGE_LANG_UZ, CHANGE_NUMBER_UZ},
            {BACK_UZ}
    };
    String[][] settings_en = {
            {CHANGE_LANG_EN, CHANGE_NUMBER_EN},
            {BACK_EN}
    };
    String[][] settings_ru = {
            {CHANGE_LANG_RU, CHANGE_NUMBER_RU},
            {BACK_RU}
    };
}