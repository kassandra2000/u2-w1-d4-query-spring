package kassandrafalsitta.u2w1d1.config;

import kassandrafalsitta.u2w1d1.entities.*;
import kassandrafalsitta.u2w1d1.enums.StateTable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import java.util.List;
import java.util.Random;

@Configuration
@PropertySource("application.properties")
public class FoodAndDrinkConfig {
    //Topping
    @Bean(name = "Mozzarella")
    public Topping getMozzarella() {
        return new Topping("mozzarella", 0.50, 140);
    }

    @Bean(name = "Tomato")
    public Topping getTomato() {
        return new Topping("pomodoro", 0.50, 19);
    }

    @Bean(name = "Ham")
    public Topping getHam() {
        return new Topping("prosciutto", 1.50, 145);
    }

    @Bean(name = "Pineapple")
    public Topping getPineapple() {
        return new Topping("ananas", 1.00, 50);
    }

    @Bean(name = "Salami")
    public Topping getSalami() {
        return new Topping("salame", 1.50, 290);
    }
    @Bean(name = "Onion")
    public Topping getOnion() {
        return new Topping("cipolla", 1.50, 290);
    }

    //Pizze
    @Bean
    public Pizza getMargheritaPizza() {
        Pizza margherita = new Pizza("Margherita", 5.00, 700);
        margherita.addTopping(getMozzarella());
        margherita.addTopping(getTomato());
        return margherita;
    }

    @Bean
    public Pizza getHawaiianPizza() {
        Pizza hawaiian = new Pizza("Hawaiian", 6.00, 700);
        hawaiian.addTopping(getMozzarella());
        hawaiian.addTopping(getTomato());
        hawaiian.addTopping(getHam());
        hawaiian.addTopping(getPineapple());
        return hawaiian;
    }

    @Bean
    public Pizza getSalamiPizza() {
        Pizza salami = new Pizza("Salami", 6.00, 700);
        salami.addTopping(getMozzarella());
        salami.addTopping(getTomato());
        salami.addTopping(getSalami());
        return salami;
    }

    //Pizze XL
    @Bean
    public Pizza getMargheritaPizzaXl() {
        Pizza margherita = new Pizza("Margherita XL", 8.00, 1400);
        margherita.addTopping(getMozzarella());
        margherita.addTopping(getTomato());
        return margherita;
    }

    @Bean
    public Pizza getHawaiianPizzaXl() {
        Pizza hawaiian = new Pizza("Hawaiian Xl", 11.00, 1400);
        hawaiian.addTopping(getMozzarella());
        hawaiian.addTopping(getTomato());
        hawaiian.addTopping(getHam());
        hawaiian.addTopping(getPineapple());
        return hawaiian;
    }

    @Bean
    public Pizza getSalamiPizzaXl() {
        Pizza salami = new Pizza("Salami Xl", 11.00, 1400);
        salami.addTopping(getMozzarella());
        salami.addTopping(getTomato());
        salami.addTopping(getSalami());
        return salami;
    }

    //Bevande
    @Bean
    public Drink getCocaCola() {
        return new Drink("Coca-Cola (0.33l)", 4.00, 38);
    }

    @Bean
    public Drink getLemonade() {
        return new Drink("Limonata (0.33l)", 3.50, 128);
    }

    @Bean
    public Drink getWater() {
        return new Drink("Acqua (0.55l)", 2.50, 0);
    }

    @Bean
    public Drink getWine() {
        return new Drink("Vino (0.75l)", 7.49, 607);
    }

    //Menù
    @Bean(name = "Menu")
    public Menu getMenu() {
        return new Menu(List.of(getMargheritaPizza(),getHawaiianPizza(),getSalamiPizza()),List.of(getMargheritaPizzaXl(),getHawaiianPizzaXl(),getSalamiPizzaXl()),List.of(getMozzarella(),getTomato(),getOnion(),getSalami(),getPineapple(),getHam()),List.of(getCocaCola(),getWater(),getLemonade(), getWine()));
    }

    //tavoli
    @Bean
    public Random getRandom() {
        return new Random();
    }

    @Bean
    @Scope("prototype")
    public int getNumMaxClients() {
        return getRandom().nextInt(4, 30);
    }

    @Bean
    @Scope("prototype")
    public StateTable getStateTable() {
        StateTable[] stateTableList = StateTable.values();
        return stateTableList[getRandom().nextInt(stateTableList.length)];
    }

    @Bean
    @Scope("prototype")
    public Table getTable(int numMaxClients, StateTable stateTable) {
        return new Table(numMaxClients, stateTable);
    }

    //Ordini
    @Bean
    public String getCoverChange(@Value("${Order.coverCharge}") String coverCharge){
        return coverCharge;

    }



}
