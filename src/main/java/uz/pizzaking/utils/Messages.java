package uz.pizzaking.utils;

import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import uz.pizzaking.entity.enums.Languages;

import java.io.File;
import java.util.Map;

import static uz.pizzaking.db.Datasource.keyboard;
import static uz.pizzaking.db.Datasource.users;
import static uz.pizzaking.entity.enums.Languages.ENGLISH;
import static uz.pizzaking.entity.enums.Languages.UZBEK;
import static uz.pizzaking.utils.Util.*;

public interface Messages {
    File logo_path = new File("src/main/resources/logo vertical.png");
    File call_path = new File("src/main/resources/call center.png");

    static String send_msg_to_all_admin(Languages adminLang) {
        return adminLang == UZBEK ? "📢 Barcha foydalanuvchilarga xabar yuborish uchun matn yoki rasm kiriting:" :
                adminLang == ENGLISH ? "📢 Enter text or photo to send to all users:" :
                        "📢 Введите текст или фото для отправки всем пользователям:";
    }

    static String send_mgs_to_all_success_message(Languages lang) {
        return lang == UZBEK ? "✅ Xabar barchaga muvaffaqiyatli uzatildi!" :
                lang == ENGLISH ? "✅ Message successfully sent to everyone!" :
                        "✅ Сообщение успешно отправлено всем!";
    }

    static String send_mgs_to_user(Languages lang) {
        return lang == UZBEK ? "✅ Xabar muvaffaqiyatli yuborildi!" :
                lang == ENGLISH ? "✅ Message sent successfully" :
                        "✅ Сообщение успешно отправлено!";
    }

    static String welcome_admin(Languages lang) {
        return lang == UZBEK ? "\uD83D\uDC4B Xush kelibsiz, hurmatli admin!" :
                lang == ENGLISH ? "\uD83D\uDC4B Welcome, dear admin!" :
                        "\uD83D\uDC4B Добро пожаловать, уважаемый администратор!";
    }

    static String res_message(Languages lang) {
        return lang == UZBEK ? "Xabaringiz uchun rahmat! \uD83D\uDCE8 Biz uni oldik va tez orada javob beramiz. Pizza King bilan birga bo‘ling!" :
                lang == ENGLISH ? "Thanks for your message! \uD83D\uDCE8 We’ve received it and will get back to you soon. Stay with Pizza King!" :
                        "Спасибо за ваше сообщение! \uD83D\uDCE8 Мы его получили и скоро ответим. Оставайтесь с Pizza King!";
    }

    static String error_msg(Languages lang) {
        return lang == UZBEK ? "Noto‘g‘ri buyruq! Iltimos, menyudan tanlang." :
                lang == ENGLISH ? "Invalid command! Please choose from the menu." :
                        "Неверная команда! Выберите из меню.";
    }

    static String send_msg(Languages lang) {
        return lang == UZBEK ? "Bizga xabar yubormoqchimisiz? \uD83D\uDCE8 Iltimos, savolingiz yoki taklifingizni yozing — tezda javob beramiz!" :
                lang == ENGLISH ? "Want to send us a message? \uD83D\uDCE8 Please write your question or suggestion — we’ll reply soon!" :
                        "Хотите отправить нам сообщение? \uD83D\uDCE8 Напишите ваш вопрос или предложение — мы ответим быстро!";
    }

    static String call_num_msg(Languages lang) {
        return lang == UZBEK ? "Biz bilan bog‘lanmoqchimisiz? Iltimos, quyidagi raqamga qo‘ng‘iroq qiling:\n\n+998 90 123 45 67\n\n Sizni kutamiz!" :
                lang == ENGLISH ? "Want to reach us? Please call us at:\n\n+998 90 123 45 67\n\n We’re waiting for you!" :
                        "Хотите связаться с нами? Звоните по номеру:\n\n+998 90 123 45 67\n\n Ждём вашего звонка!";
    }

    static String about_msg(Languages lang) {
        return lang == UZBEK ? "Pizza King — bu nafaqat mazali pizzalar, balki sizning har bir buyurtmangizda o‘zingizni alohida his qilishingiz uchun yaratilgan xizmatdir. Bizning maqsadimiz — eng yangi ingredientlardan tayyorlangan, issiq va sifatli pizzalarni eshigingizgacha tez yetkazib berish. Pizza King 2010-yilda tashkil topganidan beri mijozlarimizni lazzatlanishning haqiqiy qirollari sifatida ko‘rishni o‘z oldiga maqsad qilib qo‘ygan. Har bir pizza — bu siz uchun maxsus tayyorlangan taom, har bir yetkazib berish — sizga bo‘lgan e’tiborimizning bir qismidir. Biz bilan birga pizza dunyosida o‘z o‘rningizni toping!" :
                lang == ENGLISH ? "Pizza King is more than just delicious pizzas — it’s a service designed to make you feel special with every order. Our mission is to deliver hot, high-quality pizzas made from the freshest ingredients right to your door, and to do it fast. Since starting in 2010, Pizza King has been dedicated to treating our customers like true royalty in the world of pizza. Every pizza is crafted with care just for you, and every delivery is a reflection of our commitment to your satisfaction. Join us and discover your place in the kingdom of pizza!" :
                        "Pizza King — это не просто вкусные пиццы, а сервис, созданный для того, чтобы вы чувствовали себя особенными с каждым заказом. Наша цель — быстро доставлять горячую и качественную пиццу, приготовленную из самых свежих ингредиентов, прямо к вашей двери. С момента основания в 2010 году Pizza King стремится видеть в своих клиентах настоящих королей мира пиццы. Каждая пицца — это блюдо, приготовленное специально для вас, а каждая доставка — часть нашего внимания к вам. Присоединяйтесь к нам и найдите своё место в королевстве пиццы!";
    }

    static ReplyKeyboard main_keyboard(Languages lang) {
        return lang == UZBEK ? keyboard(Util.main_uz) :
                lang == ENGLISH ? keyboard(Util.main_en) :
                        keyboard(Util.main_ru);
    }

    static ReplyKeyboard settings_keyboard(Languages lang) {
        return lang == UZBEK ? keyboard(settings_uz) :
                lang == ENGLISH ? keyboard(settings_en) :
                        keyboard(settings_ru);
    }

    static ReplyKeyboard main_keyboard_admin(Languages lang) {
        return lang == UZBEK ? keyboard(Util.main_admin_uz) :
                lang == ENGLISH ? keyboard(Util.main_admin_en) :
                        keyboard(Util.main_admin_ru);
    }

    static String welcome_msg(Languages lang) {
        return lang == UZBEK ? "Yana xush kelibsiz! \uD83C\uDF55 Pizza King sizni kutmoqda — nima buyurasiz?" :
                lang == ENGLISH ? "Welcome back! \uD83C\uDF55 Pizza King awaits — what’s your order?" :
                        "С возвращением! \uD83C\uDF55 Pizza King ждёт — что закажете?";
    }

    static String select_msg(Languages lang) {
        return lang == UZBEK ? "Tanlang:" :
                lang == ENGLISH ? "Select:" :
                        "Выбирать:";
    }

    static ReplyKeyboard pizza_sub_menu(Languages lang) {
        return lang == UZBEK ? keyboard(Util.pizza_sub_uz) :
                lang == ENGLISH ? keyboard(Util.pizza_sub_en) :
                        keyboard(Util.pizza_sub_ru);
    }

    static ReplyKeyboard hotdog_sub_menu(Languages lang) {
        return lang == UZBEK ? keyboard(Util.hotdog_sub_uz) :
                lang == ENGLISH ? keyboard(Util.hotdog_sub_en) :
                        keyboard(Util.hotdog_sub_ru);
    }

    static ReplyKeyboard lavash_sub_menu(Languages lang) {
        return lang == UZBEK ? keyboard(Util.lavash_sub_uz) :
                lang == ENGLISH ? keyboard(Util.lavash_sub_en) :
                        keyboard(Util.lavash_sub_ru);
    }

    static ReplyKeyboard burger_sub_menu(Languages lang) {
        return lang == UZBEK ? keyboard(Util.burger_sub_uz) :
                lang == ENGLISH ? keyboard(Util.burger_sub_en) :
                        keyboard(Util.burger_sub_ru);
    }

    static ReplyKeyboard sandwich_sub_menu(Languages lang) {
        return lang == UZBEK ? keyboard(Util.sandwich_sub_uz) :
                lang == ENGLISH ? keyboard(Util.sandwich_sub_en) :
                        keyboard(Util.sandwich_sub_ru);
    }

    static ReplyKeyboard drinks_sub_menu(Languages lang) {
        return lang == UZBEK ? keyboard(Util.drinks_sub_uz) :
                lang == ENGLISH ? keyboard(Util.drinks_sub_en) :
                        keyboard(Util.drinks_sub_ru);
    }

    static ReplyKeyboard dessert_sub_menu(Languages lang) {
        return lang == UZBEK ? keyboard(Util.dessert_sub_uz) :
                lang == ENGLISH ? keyboard(Util.dessert_sub_en) :
                        keyboard(Util.dessert_sub_ru);
    }

    static ReplyKeyboard fries_sub_menu(Languages lang) {
        return lang == UZBEK ? keyboard(Util.fries_sub_uz) :
                lang == ENGLISH ? keyboard(Util.fries_sub_en) :
                        keyboard(Util.fries_sub_ru);
    }

    static String pizza_classic_details(Languages lang) {
        return lang == UZBEK ? "🍕 Klassik pitsa\nNarxi: 90 000 so'm" :
                lang == ENGLISH ? "🍕 Classic pizza\nPrice: 90 000 sum" :
                        "🍕 Классическая пицца\nЦена: 90 000 сум.";

    }

    static String pizza_pepperoni_details(Languages lang) {
        return lang == UZBEK ? "🍕 Pepperonie pitsa\nNarxi: 120 000 so'm" :
                lang == ENGLISH ? "🍕 Pepperoni pizza\nPrice: 120 000 sum" :
                        "🍕 Пицца Пепперони\nЦена: 120 000 сум.";
    }

    static String pizza_margherita_details(Languages lang) {
        return lang == UZBEK ? "🍕 Margherita pitsa\nNarxi: 110 000 so'm" :
                lang == ENGLISH ? "🍕 Margherita pizza\nPrice: 110 000 sum" :
                        "🍕 Пицца Маргарита\nЦена: 110 000 сум.";

    }

    static String burger_cheese_details(Languages lang) {
        return lang == UZBEK ? "🍔 Klassik gamburger\nNarxi: 60 000 so'm" :
                lang == ENGLISH ? "🍔 Cheeseburger\nPrice: 60 000 sum" :
                        "🍔 Чизбургер\nЦена: 60 000 сум.";
    }

    static String burger_chicken_details(Languages lang) {
        return lang == UZBEK ? "🍔 Tovuqli gamburger\nNarxi: 55 000 so'm" :
                lang == ENGLISH ? "🍔 Chicken Burger\nPrice: 55 000 sum" :
                        "🍔 Куриный бургер\nЦена: 55 000 сум.";

    }

    static String burger_double_details(Languages lang) {
        return lang == UZBEK ? "🍔 Ikki qavatli gamburger\nNarxi: 80 000 so'm" :
                lang == ENGLISH ? "🍔 Double Burger\nPrice: 80 000 sum" :
                        "🍔 Двойной бургер\nЦена: 80 000 сум.";
    }

    static String hotdog_classic_details(Languages lang) {
        return lang == UZBEK ? "🌭 Klassik hotdog\nNarxi: 30 000 so'm" :
                lang == ENGLISH ? "🌭 Classic hotdog\nPrice: 30 000 sum" :
                        "🌭 Классический хот-дог\nЦена: 30 000 сум.";
    }

    static String hotdog_chili_details(Languages lang) {
        return lang == UZBEK ? "🌭 Chili hotdog\nNarxi: 35 000 so'm" :
                lang == ENGLISH ? "🌭 Chili Dog\nPrice: 35 000 sum" :
                        "🌭 Чили дог\nЦена: 35 000 сум.";
    }

    static String hotdog_cheese_details(Languages lang) {
        return lang == UZBEK ? "🌭 Pishloqli hotdog\nNarxi: 40 000 so'm" :
                lang == ENGLISH ? "🌭 Cheese Dog\nPrice: 40 000 sum" :
                        "🌭 Сырный дог\nЦена: 40 000 сум.";
    }

    static String lavash_meat_details(Languages lang) {
        return lang == UZBEK ? "🥙 Go‘shtli lavash\nNarxi: 45 000 so'm" :
                lang == ENGLISH ? "🥙 Meat Lavash\nPrice: 45 000 sum" :
                        "🥙 Мясной лаваш\nЦена: 45 000 сум.";
    }

    static String lavash_chicken_details(Languages lang) {
        return lang == UZBEK ? "🥙 Tovuqli lavash\nNarxi: 40 000 so'm" :
                lang == ENGLISH ? "🥙 Chicken Lavash\nPrice: 40 000 sum" :
                        "🥙 Куриный лаваш\nЦена: 40 000 сум.";
    }

    static String lavash_cheese_details(Languages lang) {
        return lang == UZBEK ? "🥙 Pishloqli lavash\nNarxi: 42 000 so'm" :
                lang == ENGLISH ? "🥙 Cheese Lavash\nPrice: 42 000 sum" :
                        "🥙 Сырный лаваш\nЦена: 42 000 сум.";
    }

    static String sandwich_club_details(Languages lang) {
        return lang == UZBEK ? "🥪 Klub sendvichi\nNarxi: 50 000 so'm" :
                lang == ENGLISH ? "🥪 Club Sandwich\nPrice: 50 000 sum" :
                        "🥪 Клубный сэндвич\nЦена: 50 000 сум.";
    }

    static String sandwich_veggie_details(Languages lang) {
        return lang == UZBEK ? "🥪 Pishloqli sendvich\nNarxi: 45 000 so'm" :
                lang == ENGLISH ? "🥪 Veggie Sandwich\nPrice: 45 000 sum" :
                        "🥪 Овощной сэндвич\nЦена: 45 000 сум.";
    }

    static String fries_plain_details(Languages lang) {
        return lang == UZBEK ? "🍟 Oddiy kartoshka fri\nNarxi: 20 000 so'm" :
                lang == ENGLISH ? "🍟 Plain fries\nPrice: 20 000 sum" :
                        "🍟 Обычная картошка фри\nЦена: 20 000 сум.";
    }

    static String fries_cheese_details(Languages lang) {
        return lang == UZBEK ? "🍟 Pishloqli kartoshka fri\nNarxi: 25 000 so'm" :
                lang == ENGLISH ? "🍟 Cheesy fries\nPrice: 25 000 sum" :
                        "🍟 Картошка фри с сырным соусом\nЦена: 25 000 сум.";
    }

    static String dessert_cheesecake_details(Languages lang) {
        return lang == UZBEK ? "🍮 Cheesecake\nNarxi: 35 000 so'm" :
                lang == ENGLISH ? "🍮 Cheesecake\nPrice: 35 000 sum" :
                        "🍮 Чизкейк\nЦена: 35 000 сум.";
    }

    static String dessert_medovik_details(Languages lang) {
        return lang == UZBEK ? "🍮 Medovik\nNarxi: 30 000 so'm" :
                lang == ENGLISH ? "🍮 Medovik\nPrice: 30 000 sum" :
                        "🍮 Медовик\nЦена: 30 000 сум.";
    }

    static String dessert_napoleon_details(Languages lang) {
        return lang == UZBEK ? "🍮 Napaleon\nNarxi: 32 000 so'm" :
                lang == ENGLISH ? "🍮 Napoleon\nPrice: 32 000 sum" :
                        "🍮 Наполеон\nЦена: 32 000 сум.";
    }

    static String drinks_cola_details(Languages lang) {
        return lang == UZBEK ? "🥤 Cola\nNarxi: 10 000 so'm" :
                lang == ENGLISH ? "🥤 Cola\nPrice: 10 000 sum" :
                        "🥤 Кола\nЦена: 10 000 сум.";
    }

    static String drinks_sprite_details(Languages lang) {
        return lang == UZBEK ? "🥤 Sprite\nNarxi: 10 000 so'm" :
                lang == ENGLISH ? "🥤 Sprite\nPrice: 10 000 sum" :
                        "🥤 Спрайт\nЦена: 10 000 сум.";
    }

    static String drinks_tea_details(Languages lang) {
        return lang == UZBEK ? "🍵 Choy\nNarxi: 8 000 so'm" :
                lang == ENGLISH ? "🍵 Tea\nPrice: 8 000 sum" :
                        "🍵 Чай\nЦена: 8 000 сум.";
    }

    static String drinks_coffee_details(Languages lang) {
        return lang == UZBEK ? "☕ Kofe\nNarxi: 12 000 so'm" :
                lang == ENGLISH ? "☕ Coffee\nPrice: 12 000 sum" :
                        "☕ Кофе\nЦена: 12 000 сум.";
    }

    static String back_msg(Languages lang) {
        return lang == UZBEK ? "Asosiy menyuga qaytdik!" :
                lang == ENGLISH ? "Back to main menu!" :
                        "Вернулись в главное меню!";
    }

    static ReplyKeyboard product_menu(Languages lang) {
        return keyboard(lang == UZBEK ? product_uz : lang == ENGLISH ? product_en : product_ru);
    }

    static String to_cart(Languages lang) {
        return lang == UZBEK ? "Savatga qo'shish 🛒" :
                lang == ENGLISH ? "Add to cart 🛒" :
                        "Добавить в корзину 🛒";
    }

    static String register_success_msg(Languages lang) {
        return lang == UZBEK ? "Ro‘yxatdan o‘tish muvaffaqiyatli yakunlandi!" :
                lang == ENGLISH ? "Registration completed successfully!" :
                        "Регистрация успешно завершена!";
    }

    static String phone_number_error(Languages lang) {
        return lang == UZBEK ? "Iltimos, telefon raqamingizni tugma orqali yuboring!" :
                lang == ENGLISH ? "Please share your phone number using the button!" :
                        "Пожалуйста, отправьте номер телефона через кнопку!";
    }

    static String phone_num_button(Languages lang) {
        return lang == UZBEK ? "Telefon raqamini yuborish" :
                lang == ENGLISH ? "Share phone number" :
                        "Отправить номер телефона";
    }

    static String phone_num_req(Languages lang) {
        return lang == UZBEK ? "Iltimos, telefon raqamingizni yuboring:" :
                lang == ENGLISH ? "Please share your phone number:" :
                        "Пожалуйста, отправьте ваш номер телефона:";

    }

    static String settings_menu_text(Languages lang) {
        return lang == UZBEK ? "\uD83D\uDCCB | Sozlamalar bo'limiga xush kelibsiz!" :
                lang == ENGLISH ? "📋 | Welcome to the settings section!" :
                        "📋 | Добро пожаловать в раздел настроек!";
    }

    static String change_lang_msg(Languages lang) {
        return lang == UZBEK ? "🌍 Kerakli tilni tanlang" :
                lang == ENGLISH ? "🌍 Select the desired language" :
                        "🌍 Выберите нужный язык";
    }

    static String change_lang_success_msg(Languages lang) {
        return lang == UZBEK ? "✅ Til muvaffaqiyatli o'zgartirildi" :
                lang == ENGLISH ? "✅ Language successfully changed" :
                        "✅ Язык успешно изменен";
    }

    static String response_to_user(Languages lang, Long chatId) {
        uz.pizzaking.entity.User user = users.get(chatId);

        String userNameOrPhone = user.getUsername() != null ? "@" + user.getUsername() : user.getPhoneNumber();

        return lang == UZBEK ? "👤 " + userNameOrPhone + " ga javob yozing:" :
                lang == ENGLISH ? "👤 Reply to " + userNameOrPhone :
                        "👤 Ответьте " + userNameOrPhone;
    }

    static String admin_response_to_user(Languages lang, String text) {
        return lang == UZBEK ? "📨 Adminlar javob berdi!\n\n" +
                "💬 Xabar: " + text + "\n" :
                lang == ENGLISH ? "📨 The admins have responded!\n💬 Message: " + text + "\n" :
                        "📨 Администраторы ответили!\n💬 Сообщение: " + text + "\n";
    }


    static String msg_to_admin(Languages language, User from, String phoneNumber, String text) {
        return language == UZBEK ? "📨 Yangi xabar keldi!\n"
                + "👤 Kimdan: @" + (from.getUserName() != null ? from.getUserName() : "Username yo‘q") + "\n"
                + "📝 Ism: " + from.getFirstName() + "\n"
                + "📞 Raqam: " + phoneNumber + "\n"
                + "💬 Xabar: " + text + "\n" :
                language == ENGLISH ? "📨 New message received!\n"
                        + "👤 From: @" + (from.getUserName() != null ? from.getUserName() : "No username") + "\n"
                        + "📝 Name: " + from.getFirstName() + "\n"
                        + "📞 Phone: " + phoneNumber + "\n"
                        + "💬 Message: " + text + "\n" :
                        "📨 Пришло новое сообщение!\n"
                                + "👤 От кого: @" + (from.getUserName() != null ? from.getUserName() : "Нет имени пользователя") + "\n"
                                + "📝 Имя: " + from.getFirstName() + "\n"
                                + "📞 Номер: " + phoneNumber + "\n"
                                + "💬 Сообщение: " + text + "\n";
    }


    static String getUserInfo(uz.pizzaking.entity.User user, Languages adminLang) {
        String username = user.getUsername() != null ? "@" + user.getUsername() :
                adminLang == UZBEK ? "Username yo‘q" :
                        adminLang == ENGLISH ? "No username" :
                                "Нет имени пользователя";

        String phone = user.getPhoneNumber() != null ? user.getPhoneNumber() :
                adminLang == UZBEK ? "Telefon yo‘q" :
                        adminLang == ENGLISH ? "No phone" :
                                "Нет телефона";

        return adminLang == UZBEK ?
                "👤 Ism: " + user.getName() + "\n" +
                        "📝 Username: " + username + "\n" +
                        "📞 Telefon: " + phone + "\n" +
                        "🌐 Til: " + user.getLanguage() + "\n\n" :
                adminLang == ENGLISH ?
                        "👤 Name: " + user.getName() + "\n" +
                                "📝 Username: " + username + "\n" +
                                "📞 Phone: " + phone + "\n" +
                                "🌐 Language: " + user.getLanguage() + "\n\n" :
                        "👤 Имя: " + user.getName() + "\n" +
                                "📝 Имя пользователя: " + username + "\n" +
                                "📞 Телефон: " + phone + "\n" +
                                "🌐 Язык: " + user.getLanguage() + "\n\n";
    }

    static String getUserListMessage(Map<Long, uz.pizzaking.entity.User> users, Languages adminLang) {
        StringBuilder sb = new StringBuilder();

        users.forEach((u, user) -> {
            String userInfo = getUserInfo(user, adminLang);
            sb.append(userInfo);
        });

        if (sb.isEmpty() || sb.toString().equals(adminLang == UZBEK ? "👥 Foydalanuvchilar ro‘yxati:\n\n" :
                adminLang == ENGLISH ? "👥 List of Users:\n\n" :
                        "👥 Список пользователей:\n\n")) {
            sb.setLength(0);
            sb.append(adminLang == UZBEK ? "❌ Hozircha foydalanuvchilar yo‘q." :
                    adminLang == ENGLISH ? "❌ No users yet." :
                            "❌ Пока нет пользователей.");
        }

        return sb.toString();
    }
}
