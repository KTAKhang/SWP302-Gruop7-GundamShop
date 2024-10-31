package gruop7.gundamshop.service;

import org.springframework.stereotype.Service;

import gruop7.gundamshop.repository.FeedbackRepository;
import gruop7.gundamshop.repository.UserRepository;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;

    }

}
