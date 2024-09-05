package kassandrafalsitta.u2w1d1.entities;

import kassandrafalsitta.u2w1d1.enums.StateOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Getter
@Setter
public class Order {
    private Table table;
    private List<FoodAndDrink> foodAndDrinks;
    private int numbOrder = 0;
    private StateOrder state;
    private int numbSeats;
    private double acquisitionTime;
    private double amount;
    private String coverCharge;

    //costruttore
    public Order(Table table, StateOrder state, int numbSeats, double acquisitionTime, String coverCharge) {
        Random r = new Random();
        this.table = table;
        this.foodAndDrinks = new ArrayList<>();
        this.numbOrder = r.nextInt(1, 99999);
        this.state = state;
        this.numbSeats = numbSeats;
        this.acquisitionTime = acquisitionTime;
        this.coverCharge = coverCharge;
    }

    //metodi
    public double getCoverCharge() {
        return this.numbSeats * Double.parseDouble(this.coverCharge);
    }

    public double getAmount() {
        double amount = this.foodAndDrinks.stream().mapToDouble(FoodAndDrink::getPrice).sum();
        setAmount(amount);
        return amount + getCoverCharge();
    }
    public double getPartialAmount() {
        double amount = this.foodAndDrinks.stream().mapToDouble(FoodAndDrink::getPrice).sum();
        setAmount(amount);
        return amount;
    }

    public String printOrderDetails() {
        StringBuilder details = new StringBuilder();
        details.append("\n--------------------------------------------------------\n")
                .append("Ordine numero: ").append(numbOrder).append("\n")
                .append("Stato dell'ordine: ").append(state).append("\n")
                .append("Numero di posti: ").append(numbSeats).append("\n")
                .append("Ora di acquisizione: ").append(acquisitionTime).append("\n")
                .append("Costo Coperto a persona: ").append(coverCharge).append("€\n")
                .append("Totale ordine senza coperto: ").append(getPartialAmount()).append("€\n")
                .append("Costo coperto totale: ").append(getCoverCharge()).append("€\n")
                .append("Spesa totale: ").append(String.format("%.2f", getAmount())).append("€\n")
                .append("\nDettagli della tavola:\n")
                .append("Numero Tavolo: ").append(table.getTableNum()).append("\n")
                .append("Numero massimo clienti: ").append(table.getNumMaxClients()).append("\n")
                .append("Stato del tavolo: ").append(table.getState()).append("\n\n")

                .append("Cibo e bevande ordinati:\n");

        for (FoodAndDrink item : foodAndDrinks) {

            if (item instanceof Pizza pizza) {
                String toppings = pizza.getToppings().stream()
                        .map(FoodAndDrink::getName)
                        .collect(Collectors.joining(", "));
                details.append("-Pizza: ").append(pizza.getName()).append("\n")
                        .append(" Topping: ").append(toppings).append("\n")
                        .append(" Prezzo: ").append(String.format("%.2f", pizza.getPrice())).append("€\n")
                        .append(" Calorie: ").append(pizza.getCalories()).append("\n");
            } else {
                details.append("-Bevanda: ").append(item.getName()).append("\n")
                        .append(" Prezzo: ").append(String.format("%.2f", item.getPrice())).append("€\n")
                        .append(" Calorie: ").append(item.getCalories()).append("\n");
            }
        }


        return details.toString();
    }


    //to string
    @Override
    public String toString() {
        return "Order{" +
                "table=" + table +
                ", foodAndDrinks=" + foodAndDrinks +
                ", numbOrder=" + numbOrder +
                ", state=" + state +
                ", numbSeats=" + numbSeats +
                ", acquisitionTime=" + acquisitionTime +
                ", amount=" + amount +
                '}';
    }
}
