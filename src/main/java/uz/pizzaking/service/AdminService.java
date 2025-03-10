package uz.pizzaking.service;

import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.pizzaking.bot.MainBot;
import uz.pizzaking.entity.enums.Languages;
import uz.pizzaking.entity.enums.States;
import uz.pizzaking.utils.Buttons;
import uz.pizzaking.utils.Texts;

import java.util.List;

import static uz.pizzaking.db.Datasource.*;
import static uz.pizzaking.entity.enums.Languages.UZ;
import static uz.pizzaking.utils.Util.*;

public class AdminService {
    private final MainBot bot;

    public AdminService(MainBot bot) {
        this.bot = bot;
    }

    public void service(Update update) {
        String text = update.getMessage().getText();
        Languages lang = users.get(ADMIN).getLanguage();
        Long chatId = update.getMessage().getChatId();

        state.putIfAbsent(ADMIN, States.MAIN_ADMIN);

        States currentState = state.get(ADMIN);

        if (update.hasMessage()) {
            if (currentState == States.MAIN_ADMIN) {
                switch (text) {
                    case "/start" ->
                            bot.sendMessage(ADMIN, lang == UZ ? Texts.welcome_admin_uz : lang == Languages.EN ? Texts.welcome_admin_en : Texts.welcome_admin_ru, keyboard(lang == UZ ? main_admin_uz : lang == Languages.EN ? main_admin_en : main_admin_ru));

                    case Buttons.USERS_UZ, Buttons.USERS_RU, Buttons.USERS_EN -> {
                        StringBuilder sb = new StringBuilder();
                        Languages adminLang = users.get(ADMIN).getLanguage();

                        sb.append(adminLang == Languages.UZ ? "👥 Foydalanuvchilar ro‘yxati:\n\n" :
                                adminLang == Languages.EN ? "👥 List of Users:\n\n" :
                                        "👥 Список пользователей:\n\n");

                        users.forEach((u, user) -> {
                            String username = user.getUsername() != null ? "@" + user.getUsername() : adminLang == Languages.UZ ? "Username yo‘q" :
                                    adminLang == Languages.EN ? "No username" :
                                            "Нет имени пользователя";
                            String phone = user.getPhoneNumber() != null ? user.getPhoneNumber() : adminLang == Languages.UZ ? "Telefon yo‘q" :
                                    adminLang == Languages.EN ? "No phone" :
                                            "Нет телефона";
                            String userInfo = adminLang == Languages.UZ ?
                                    "👤 Ism: " + user.getName() + "\n" +
                                            "📝 Username: " + username + "\n" +
                                            "📞 Telefon: " + phone + "\n" +
                                            "🌐 Til: " + user.getLanguage() + "\n\n" :
                                    adminLang == Languages.EN ?
                                            "👤 Name: " + user.getName() + "\n" +
                                                    "📝 Username: " + username + "\n" +
                                                    "📞 Phone: " + phone + "\n" +
                                                    "🌐 Language: " + user.getLanguage() + "\n\n" :
                                            "👤 Имя: " + user.getName() + "\n" +
                                                    "📝 Имя пользователя: " + username + "\n" +
                                                    "📞 Телефон: " + phone + "\n" +
                                                    "🌐 Язык: " + user.getLanguage() + "\n\n";
                            sb.append(userInfo);
                        });

                        if (sb.isEmpty()) {
                            sb.append(adminLang == Languages.UZ ? "❌ Hozircha foydalanuvchilar yo‘q." :
                                    adminLang == Languages.EN ? "❌ No users yet." :
                                            "❌ Пока нет пользователей.");
                        }

                        bot.sendMessage(ADMIN, sb.toString());
                    }
                    case Buttons.STATISTICS_UZ -> {

                    }
                    case Buttons.MESSAGE_FOR_ALL_UZ, Buttons.MESSAGE_FOR_ALL_EN, Buttons.MESSAGE_FOR_ALL_RU -> {
                        Languages adminLang = users.get(ADMIN).getLanguage();
                        String message = adminLang == Languages.UZ ? "📢 Barcha foydalanuvchilarga xabar yuborish uchun matn yoki rasm kiriting:" :
                                adminLang == Languages.EN ? "📢 Enter text or photo to send to all users:" :
                                        "📢 Введите текст или фото для отправки всем пользователям:";
                        bot.sendMessage(chatId, message);
                        state.put(chatId, States.SEND_MESSAGE_TO_ALL);
                    }
                    case Buttons.SETTINGS_ADMIN_UZ -> {

                    }
                }
            } else if (currentState == States.SEND_MESSAGE_TO_ALL) {
                if (update.getMessage().hasPhoto()) {
                    List<PhotoSize> photo = update.getMessage().getPhoto();
                    String caption = update.getMessage().getCaption() != null ? update.getMessage().getCaption() : "";

                    users.forEach((userChatId, user) -> {
                        if (!userChatId.equals(chatId)) {
                            bot.sendPhoto(userChatId, caption, photo);
                        }
                    });
                } else {
                    users.forEach((userChatId, user) -> {
                        if (!userChatId.equals(chatId)) {
                            bot.sendMessage(userChatId, text);
                        }
                    });
                }

                String successMessage = lang == Languages.UZ ? "✅ Xabar barchaga muvaffaqiyatli uzatildi!" :
                        lang == Languages.EN ? "✅ Message successfully sent to everyone!" :
                                "✅ Сообщение успешно отправлено всем!";
                bot.sendMessage(chatId, successMessage);
                state.remove(chatId);
            }
        }
    }
}