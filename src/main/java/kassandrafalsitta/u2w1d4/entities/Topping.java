package kassandrafalsitta.u2w1d4.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "toppings")
@NoArgsConstructor
@Getter
@Setter
public class Topping extends FoodAndDrink {

    @ManyToMany(mappedBy = "toppings")
    private List<Pizza> pizzaList;

    public Topping(String name, double price, int calories) {
        super(name, price, calories);
    }

    //to string
    @Override
    public String toString() {
        return "Topping{" + super.toString()
                 +
                "} " ;
    }
}
