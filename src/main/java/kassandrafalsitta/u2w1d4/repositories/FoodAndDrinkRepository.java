package kassandrafalsitta.u2w1d4.repositories;

import kassandrafalsitta.u2w1d4.entities.FoodAndDrink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FoodAndDrinkRepository extends JpaRepository<FoodAndDrink, UUID> {
    boolean existsByName(String name);

    //DERIVED QUERIES
    List<FoodAndDrink> findByName(String name);
    List<FoodAndDrink> findByCaloriesLessThan(int calories);



}
