package com.Triple_HomeWork.Triple_HomeWork.service;

import com.Triple_HomeWork.Triple_HomeWork.domain.review.dto.ReviewEventDto;
import com.Triple_HomeWork.Triple_HomeWork.domain.review.entity.ReviewLog;

public interface ReviewService {
    void reviewService(ReviewEventDto dto);

    default ReviewLog dtoToEntity(ReviewEventDto dto) {
        return ReviewLog.builder()
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
