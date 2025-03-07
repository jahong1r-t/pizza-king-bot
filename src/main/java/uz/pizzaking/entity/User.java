package uz.pizzaking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String chatId;
    private String username;
    private String phoneNumber;
    private String name;
    private String surname;
}
