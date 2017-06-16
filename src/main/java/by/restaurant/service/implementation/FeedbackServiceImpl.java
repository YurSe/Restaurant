package by.restaurant.service.implementation;

import by.restaurant.model.Feedback;
import by.restaurant.repository.FeedbackRepository;
import by.restaurant.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Pavel on 15.06.2017.
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public void save(Feedback feedback) {
        feedbackRepository.save(feedback);
    }
}
