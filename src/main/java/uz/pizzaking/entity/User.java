package uz.pizzaking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private Languages language;
}
