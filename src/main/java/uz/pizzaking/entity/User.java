package uz.pizzaking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Location;
import uz.pizzaking.entity.enums.Languages;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private Long chatId;
    private String username;
    private String phoneNumber;
    private String name;
    private String surname;
    private Basket basket;
    private Languages language;
    private Location location;
}
