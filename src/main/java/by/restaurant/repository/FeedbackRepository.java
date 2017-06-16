package by.restaurant.repository;

import by.restaurant.model.Feedback;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Pavel on 15.06.2017.
 */
@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Long> {

    List<Feedback> findAll();
}
