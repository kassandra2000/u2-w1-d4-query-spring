package kassandrafalsitta.u2w1d1.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class Topping extends FoodAndDrink {
    public Topping(String name, double price, int calories) {
        super(name, price, calories);
    }

}
