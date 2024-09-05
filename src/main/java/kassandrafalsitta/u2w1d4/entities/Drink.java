package kassandrafalsitta.u2w1d4.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "drinks")
public class Drink extends FoodAndDrink{

    public Drink(String name, double price, int calories) {
        super(name, price, calories);
    }




}
