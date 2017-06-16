package by.restaurant.service;

import by.restaurant.model.Feedback;

import java.util.List;

/**
 * Created by Pavel on 15.06.2017.
 */
public interface FeedbackService {

    void save(Feedback feedback);

    List<Feedback> getAll();

    void delete(Feedback feedback);
}
