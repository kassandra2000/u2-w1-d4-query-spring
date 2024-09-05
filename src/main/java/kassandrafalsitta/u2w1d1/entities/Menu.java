package kassandrafalsitta.u2w1d1.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Menu {
   private List<Pizza> pizze;
    private List<Pizza> pizzeXl;
   private List<Topping> toppings;
   private List<Drink> drinks;


    //to string
    @Override
    public String toString() {
        return "Menu{" +
                "pizze=" + pizze +
                ", pizzeXl=" + pizzeXl +
                ", toppings=" + toppings +
                ", drinks=" + drinks +
                '}';
    }
}
