package kassandrafalsitta.u2w1d4.entities;

import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "foods_and_drinks")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_foods_and_drinks")
public abstract class FoodAndDrink {
    @GeneratedValue
    @Id
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String name;
    private double price;
    private int calories;

    //costruttore
    public FoodAndDrink(String name, double price, int calories) {
        this.name = name;
        this.price = price;
        this.calories = calories;
    }

    //To string
    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", price=" + price +
                ", calories=" + calories ;
    }


}
