package kassandrafalsitta.u2w1d4.runners;


import kassandrafalsitta.u2w1d4.entities.*;
import kassandrafalsitta.u2w1d4.enums.StateOrder;
import kassandrafalsitta.u2w1d4.exceptions.NotFoundException;
import kassandrafalsitta.u2w1d4.exceptions.ValidationException;
import kassandrafalsitta.u2w1d4.services.FoodAndDrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.UUID;
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
    @Qualifier("getMargheritaPizzaXl")
    @Autowired
    Pizza margheritaXl;
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
    //topping
    @Qualifier("Mozzarella")
    @Autowired
    Topping Mozzarella;
    @Qualifier("Tomato")
    @Autowired
    Topping Tomato;
    @Qualifier("Ham")
    @Autowired
    Topping Ham;
    @Qualifier("Pineapple")
    @Autowired
    Topping Pineapple;
    @Qualifier("Salami")
    @Autowired
    Topping Salami;
    @Qualifier("Onion")
    @Autowired
    Topping Onion;


    @Autowired
    private FoodAndDrinkService foodAndDrinkService;


    @Override
    public void run(String... args) throws Exception {

        System.out.println(getOrder(table).printOrderDetails());
        System.out.println(getOrder1(table).printOrderDetails());
        System.out.println(getOrder2(table).printOrderDetails());

        printPizzas();
        printPizzasXL();
        printToppings();
        printDrinks();

        //save
//        saveFoodAndDrink(List.of(Mozzarella,Tomato,Salami,Pineapple,Onion,Ham,margherita, hawaiian, salami, margheritaXl, salamiXl, hawaiianXl, water, cocaCola, lemonade, wine));
        System.out.println("\n--------------------------------------------");
        //findAll
        try {
            System.out.println(foodAndDrinkService.findAll());
        } catch (NotFoundException ex) {
            System.out.println("Non è stato trovato nessun cibo o bevanda ");
        }
        System.out.println("\n--------------------------------------------");
        //findById
        try {
            System.out.println(foodAndDrinkService.findById(UUID.fromString("428fd419-4bfa-4e5e-9002-1a2b746a9a56")));
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        }
//        System.out.println("\n--------------------------------------------");
        //delete
//        try {
//            foodAndDrinkService.findByIdAndDelete(UUID.fromString("1963fd75-7ec2-45c6-8750-4522227c313a"));
//        } catch (ValidationException ex) {
//            System.out.println(ex.getMessage());
//        }


        System.out.println("\n--------------------------------------------");
        //update
        try {
            foodAndDrinkService.findByIdAndUpdate(UUID.fromString("428fd419-4bfa-4e5e-9002-1a2b746a9a56"), foodAndDrinkService.findById(UUID.fromString("b1cf1beb-26a2-45bb-a793-2582a7002bfa")));
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }





//        System.out.println(salami);
//        System.out.println(salami.getToppings());
        System.out.println("\n--------------------------------------------");
        //count
        System.out.println("ci sono: " + foodAndDrinkService.count() + " cibi e bevande");

        System.out.println("\n--------------------------------------------");
        //find
        System.out.println(foodAndDrinkService.findByName("ananas"));
        System.out.println("\n--------------------------------------------");
        System.out.println(foodAndDrinkService.findByCalories(800)+"\n");



    }

    public void saveFoodAndDrink(List<FoodAndDrink> foodAndDrink) {
        try {
            foodAndDrinkService.saveFoodAndDrink(foodAndDrink);
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        }
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

    public void printPizzas() {
        System.out.println("\n");
        System.out.println("Pizzas                                                        Calories            Price");
        menu.getPizze().forEach(pizza -> {
            String toppings = pizza.getToppings().stream()
                    .map(FoodAndDrink::getName)
                    .collect(Collectors.joining(", "));
            System.out.printf("%-10s %-45s %10d %18.2f\n", pizza.getName(), "(" + toppings + ")", pizza.getCalories(), pizza.getPrice());
        });
    }

    public void printPizzasXL() {
        System.out.println("\n");
        System.out.println("Pizzas XL                                                     Calories            Price");

        menu.getPizzeXl().forEach(pizza -> {
            String toppings = pizza.getToppings().stream()
                    .map(FoodAndDrink::getName)
                    .collect(Collectors.joining(", "));
            System.out.printf("%-13s %-45s %8d %18.2f\n", pizza.getName(), "(" + toppings + ")", pizza.getCalories(), pizza.getPrice());
        });
    }

    public void printToppings() {
        System.out.println("\nToppings                                                      Calories            Price");
        menu.getToppings().forEach(topping ->
                System.out.printf("%-20s %46d %18.2f\n", topping.getName(), topping.getCalories(), topping.getPrice())
        );
    }

    public void printDrinks() {
        System.out.println("\nDrinks                                                       Calories             Price");

        menu.getDrinks().forEach(drink ->
                System.out.printf("%-20s %46d %18.2f\n", drink.getName(), drink.getCalories(), drink.getPrice())
        );
    }

}
