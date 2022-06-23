package com.Triple_HomeWork.Triple_HomeWork.service;

import com.Triple_HomeWork.Triple_HomeWork.domain.review.dto.ReviewEventDto;
import com.Triple_HomeWork.Triple_HomeWork.domain.review.entity.ReviewEvent;

public interface ReviewEventService {
    String reviewService(ReviewEventDto dto) throws Exception;

    Long myMileage(String user_id);

    default ReviewEvent dtoToEntity(ReviewEventDto dto) {
        return ReviewEvent.builder()
                .type(dto.getType())
                .photo(dto.getAttachedPhotoIds())
                .content(dto.getContent())
                .action(dto.getAction())
                .reviewId(dto.getReviewId())
                .placeId(dto.getPlaceId())
                .userId(dto.getUserId())
                .bonusCheck(dto.getBonusCheck())
                .mileage(dto.getMileage())
                .build();
    }
}
