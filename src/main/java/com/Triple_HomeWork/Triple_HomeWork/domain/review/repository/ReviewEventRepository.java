package com.Triple_HomeWork.Triple_HomeWork.domain.review.repository;

import com.Triple_HomeWork.Triple_HomeWork.domain.review.entity.ReviewEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewEventRepository extends JpaRepository<ReviewEvent, Long> {
    List<ReviewEvent> findByPlaceIdAndUserId(String place_id, String user_id);

    List<ReviewEvent> findByBonusCheckAndPlaceId(Boolean check, String place_id);

    List<ReviewEvent> findByReviewIdAndBonusCheck(String review_id, Boolean check);

    List<ReviewEvent> findByReviewId(String review_id);

    List<ReviewEvent> findByUserId(String user_id);

}