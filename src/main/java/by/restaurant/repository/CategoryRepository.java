package by.restaurant.repository;

import by.restaurant.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Pavel on 21.04.2017.
 */

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long>{

    List<Category> findAll();
}
