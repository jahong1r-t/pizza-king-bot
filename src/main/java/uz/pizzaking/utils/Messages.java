package uz.pizzaking.utils;

import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import uz.pizzaking.entity.Product;
import uz.pizzaking.entity.enums.Languages;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static uz.pizzaking.db.Datasource.*;
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

    static String getProductDetails(String productName, Languages lang) {
        for (Product product : products) {
            if (product.getNameUz().equals(productName) || product.getNameEn().equals(productName) || product.getNameRu().equals(productName)) {
                return lang == Languages.UZBEK ? product.getNameUz() + "\nNarxi: " + product.getPrice() + " so'm" :
                        lang == Languages.ENGLISH ? product.getNameEn() + "\nPrice: " + product.getPrice() + " sum" :
                                product.getNameRu() + "\nЦена: " + product.getPrice() + " сум";
            }
        }
        return "Mahsulot topilmadi";
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

    static String product_add_success(Languages lang) {
        return lang == Languages.UZBEK ? "Mahsulot savatga qo‘shildi 🛒" :
                lang == Languages.ENGLISH ? "Product added to cart 🛒" :
                        "Товар добавлен в корзину 🛒";
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

    static String getBasketDetails(Long chatId, Languages lang) {
        uz.pizzaking.entity.User user = users.get(chatId);
        if (user == null || user.getBasket() == null || user.getBasket().getProducts().isEmpty()) {
            return lang == Languages.UZBEK ? "Sizning savatingiz bo‘sh 🛒" :
                    lang == Languages.ENGLISH ? "Your cart is empty 🛒" :
                            "Ваша корзина пуста 🛒";
        }

        List<Product> basketProducts = user.getBasket().getProducts();
        StringBuilder basketDetails = new StringBuilder();
        int totalPrice = 0;

        basketDetails.append(lang == Languages.UZBEK ? "📋 Sizning savatingiz:\n\n" :
                lang == Languages.ENGLISH ? "📋 Your cart:\n\n" :
                        "📋 Ваша корзина:\n\n");

        Map<Product, Integer> productCount = new HashMap<>();
        for (Product product : basketProducts) {
            productCount.put(product, productCount.getOrDefault(product, 0) + 1);
        }

        int index = 1;
        for (Map.Entry<Product, Integer> entry : productCount.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            String name = lang == Languages.UZBEK ? product.getNameUz() :
                    lang == Languages.ENGLISH ? product.getNameEn() :
                            product.getNameRu();
            int price = product.getPrice();
            int subtotal = price * quantity;

            basketDetails.append(String.format("%d. %s - %d x %,d = %,d %s\n",
                    index++, name, quantity, price, subtotal,
                    lang == Languages.UZBEK ? "so‘m" :
                            lang == Languages.ENGLISH ? "sum" :
                                    "сум"));
            totalPrice += subtotal;
        }

        basketDetails.append("\n");
        basketDetails.append(lang == Languages.UZBEK ? String.format("Jami: %,d so‘m", totalPrice) :
                lang == Languages.ENGLISH ? String.format("Total: %,d sum", totalPrice) :
                        String.format("Итого: %,d сум", totalPrice));

        return basketDetails.toString();
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
