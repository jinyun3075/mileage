package com.Triple_HomeWork.Triple_HomeWork.domain.review.repository;

import com.Triple_HomeWork.Triple_HomeWork.domain.review.entity.ReviewLog;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ReviewLogRepository extends JpaRepository<ReviewLog, Long> {
    List<ReviewLog> findByPlaceIdAndUserId(String place_id,String user_id);

    List<ReviewLog> findByBonusCheckAndPlaceId(Boolean check, String place_id);

    List<ReviewLog> findByReviewIdAndBonusCheck(String review_id, Boolean check);

    List<ReviewLog> findByReviewId(String review_id);

}