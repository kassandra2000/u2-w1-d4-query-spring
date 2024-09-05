package kassandrafalsitta.u2w1d1.entities;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class Drink extends FoodAndDrink{
    public Drink(String name, double price, int calories) {
        super(name, price, calories);
    }




}
