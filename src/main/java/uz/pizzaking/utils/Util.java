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


}
