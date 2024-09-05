package kassandrafalsitta.u2w1d4.services;

import kassandrafalsitta.u2w1d4.entities.FoodAndDrink;
import kassandrafalsitta.u2w1d4.entities.Pizza;
import kassandrafalsitta.u2w1d4.exceptions.NotFoundException;
import kassandrafalsitta.u2w1d4.exceptions.ValidationException;
import kassandrafalsitta.u2w1d4.repositories.FoodAndDrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FoodAndDrinkService {
    @Autowired
    private FoodAndDrinkRepository foodAndDrinkRepository;

    public void saveFoodAndDrink(List<FoodAndDrink> newFoodAndDrinks) {
        newFoodAndDrinks.forEach(newFoodAndDrink -> {
            if (foodAndDrinkRepository.existsByName(newFoodAndDrink.getName()))
                throw new ValidationException("Il cibo o bevanda con nome: " + newFoodAndDrink.getName() + " è già in utilizzo!");
            if (newFoodAndDrink.getName().length() < 2) throw new ValidationException("Nome troppo corto!");
            foodAndDrinkRepository.save(newFoodAndDrink);
            System.out.println("Nuovo cibo o bevanda " + newFoodAndDrink.getName() + " salvato con successo!");
        });

    }

    public List<FoodAndDrink> findAll() {
        return foodAndDrinkRepository.findAll();
    }

    public FoodAndDrink findById(UUID foodAndDrinkId) {
        return foodAndDrinkRepository.findById(foodAndDrinkId).orElseThrow(() -> new NotFoundException(foodAndDrinkId));
    }

    public void findByIdAndDelete(UUID foodAndDrinkId) {
        FoodAndDrink found = this.findById(foodAndDrinkId);
        foodAndDrinkRepository.delete(found);
        System.out.println("Cibo o bevanda con id " + foodAndDrinkId + " cancellato correttamente!");
    }

    public void findByIdAndUpdate(UUID userId, FoodAndDrink updatedFoodAndDrink) {

        FoodAndDrink found = this.findById(userId);
        found.setName(updatedFoodAndDrink.getName());
        found.setCalories(updatedFoodAndDrink.getCalories());
        found.setPrice(updatedFoodAndDrink.getPrice());
        if (found instanceof Pizza & updatedFoodAndDrink instanceof Pizza) {
            ((Pizza) found).setToppings(((Pizza) updatedFoodAndDrink).getToppings());
        } else {
            System.out.println("Errore");
        }
        ;
        foodAndDrinkRepository.save(found);

        System.out.println("Cibo o bevanda con id " + userId + " modificato correttamente!");
    }

    //QUERIES
    public long count() {
        return foodAndDrinkRepository.count();
    }

    //DERIVED QUERIES
    public List<FoodAndDrink> findByName(String name) {
        return foodAndDrinkRepository.findByName(name);
    }

    public List<FoodAndDrink> findByCalories(int calories) {
        return foodAndDrinkRepository.findByCaloriesLessThan(calories);
    }

}
