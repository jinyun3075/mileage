package com.Triple_HomeWork.Triple_HomeWork.domain.review.repository;

import com.Triple_HomeWork.Triple_HomeWork.domain.review.entity.ReviewLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<ReviewLog, String> {
    List<ReviewLog> findByPlaceIdAndUserId(String place_id,String user_id);
}