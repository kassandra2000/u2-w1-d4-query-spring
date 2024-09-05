package kassandrafalsitta.u2w1d1.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
public abstract class FoodAndDrink  {
    private String name;
    private double price;
    private int calories;

    //To string
    @Override
    public String toString() {
        return "FoodAndDrink{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", calories=" + calories +
                '}';
    }


}
