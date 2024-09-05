package kassandrafalsitta.u2w1d4.entities;

import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pizzas")
public class Pizza extends FoodAndDrink {
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "pizza_e_topping",
            joinColumns = @JoinColumn(name = "pizza_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "topping_id", nullable = false)
    )
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
        return "Pizza{"
                + super.toString() +
//                "toppings=" + toppings +
                "} ";
    }
}
