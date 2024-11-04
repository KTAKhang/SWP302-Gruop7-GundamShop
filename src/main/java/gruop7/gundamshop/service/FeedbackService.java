package gruop7.gundamshop.service;

import gruop7.gundamshop.domain.ProductReview;
import gruop7.gundamshop.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    // Lấy tất cả feedback của một người dùng dựa trên userId
    public List<ProductReview> getFeedbackByUserId(Long userId) {
        return feedbackRepository.findByUserId(userId);
    }

    // Lấy feedback theo feedbackId
    public ProductReview getFeedbackById(Long feedbackId) {
        return feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new IllegalArgumentException("Feedback không tồn tại."));
    }

    // Cập nhật trạng thái hiển thị của feedback
    @Transactional
    public void updateFeedbackVisibility(Long feedbackId, String visible) {
        ProductReview feedback = getFeedbackById(feedbackId); // Sử dụng getFeedbackById
        feedback.setVisible(visible);
        feedbackRepository.save(feedback);
    }
}
