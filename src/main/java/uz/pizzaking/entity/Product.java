package uz.pizzaking.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {
    private UUID id = UUID.randomUUID();
    private String nameUz;
    private String nameEn;
    private String nameRu;
    private Integer price;
}
