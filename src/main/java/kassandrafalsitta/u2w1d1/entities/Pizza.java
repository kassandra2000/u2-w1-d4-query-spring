package kassandrafalsitta.u2w1d1.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Pizza extends FoodAndDrink {
    private List<Topping> toppings;

    //costruttore
    public Pizza(String name, double price, int calories) {
        super(name, price, calories);
        this.toppings = new ArrayList<>();
    }

    //metodi
    @Override
    public double getPrice() {
        double price = toppings.stream().mapToDouble(Topping::getPrice).sum();
        return price + super.getPrice();
    }

    public int getCalories() {
        int calories = toppings.stream().mapToInt(Topping::getCalories).sum();
        return calories + super.getCalories();
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    //To string
    @Override
    public String toString() {
        return "Pizza{" +
                "toppings=" + toppings +
                '}';
    }
}
