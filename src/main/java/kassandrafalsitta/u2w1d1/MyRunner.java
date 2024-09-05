package kassandrafalsitta.u2w1d1;

import kassandrafalsitta.u2w1d1.entities.*;
import kassandrafalsitta.u2w1d1.enums.StateOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class MyRunner implements CommandLineRunner {
    @Autowired
    Menu menu;
    @Autowired
    Random random;
    @Autowired
    Table table;
    @Autowired
    String coverCharge;
    @Qualifier("getMargheritaPizza")
    @Autowired
    Pizza margherita;
    @Qualifier("getHawaiianPizza")
    @Autowired
    Pizza hawaiian;
    @Qualifier("getSalamiPizza")
    @Autowired
    Pizza salami;
    @Qualifier("getHawaiianPizzaXl")
    @Autowired
    Pizza hawaiianXl;
    @Qualifier("getSalamiPizzaXl")
    @Autowired
    Pizza salamiXl;
    @Qualifier("getLemonade")
    @Autowired
    Drink lemonade;
    @Qualifier("getWine")
    @Autowired
    Drink wine;
    @Qualifier("getWater")
    @Autowired
    Drink water;
    @Qualifier("getCocaCola")
    @Autowired
    Drink cocaCola;



    @Override
    public void run(String... args) throws Exception {

        System.out.println(getOrder(table).printOrderDetails());
        System.out.println(getOrder1(table).printOrderDetails());
        System.out.println(getOrder2(table).printOrderDetails());

        printPizzas();
        printPizzasXL();
        printToppings();
        printDrinks();

    }

    public Order getOrder(Table table) {
        Order order = new Order(table, StateOrder.IN_CORSO, table.getNumMaxClients() > 10 ? table.getNumMaxClients() - random.nextInt(2, 6) : table.getNumMaxClients() - random.nextInt(1, 3), 19.30, coverCharge);
        order.getFoodAndDrinks().addAll(List.of(lemonade, wine, water, margherita, hawaiian, salami));
        return order;
    }

    public Order getOrder1(Table table) {
        Order order = new Order(table, StateOrder.IN_CORSO, table.getNumMaxClients() > 10 ? table.getNumMaxClients() - random.nextInt(2, 6) : table.getNumMaxClients() - random.nextInt(1, 3), 19.30, coverCharge);
        order.getFoodAndDrinks().addAll(List.of(cocaCola, hawaiianXl, water, margherita, salami));
        return order;
    }

    public Order getOrder2(Table table) {
        Order order = new Order(table, StateOrder.IN_CORSO, table.getNumMaxClients() > 10 ? table.getNumMaxClients() - random.nextInt(2, 6) : table.getNumMaxClients() - random.nextInt(1, 3), 19.30, coverCharge);
        order.getFoodAndDrinks().addAll(List.of(wine, hawaiianXl, salamiXl));
        return order;
    }
    public  void printPizzas() {
        System.out.println("\n");
        System.out.println("Pizzas                                                        Calories            Price");
        menu.getPizze().forEach(pizza -> {
            String toppings = pizza.getToppings().stream()
                    .map(FoodAndDrink::getName)
                    .collect(Collectors.joining(", "));
            System.out.printf("%-10s %-45s %10d %18.2f\n", pizza.getName(), "(" + toppings + ")", pizza.getCalories(), pizza.getPrice());
        });
    }
    public  void printPizzasXL() {
        System.out.println("\n");
        System.out.println("Pizzas XL                                                     Calories            Price");

        menu.getPizzeXl().forEach(pizza -> {
            String toppings = pizza.getToppings().stream()
                    .map(FoodAndDrink::getName)
                    .collect(Collectors.joining(", "));
            System.out.printf("%-13s %-45s %8d %18.2f\n",pizza.getName(),"(" +toppings+")", pizza.getCalories(), pizza.getPrice());
        });
    }
    public  void printToppings() {
        System.out.println("\nToppings                                                      Calories            Price");
        menu.getToppings().forEach(topping ->
                System.out.printf("%-20s %46d %18.2f\n", topping.getName(), topping.getCalories(), topping.getPrice())
        );
    }
    public  void printDrinks() {
        System.out.println("\nDrinks                                                       Calories             Price");

        menu.getDrinks().forEach(drink ->
                System.out.printf("%-20s %46d %18.2f\n", drink.getName(), drink.getCalories(), drink.getPrice())
        );
    }

}
