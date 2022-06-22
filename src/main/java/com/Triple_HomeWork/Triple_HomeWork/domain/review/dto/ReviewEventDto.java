package com.Triple_HomeWork.Triple_HomeWork.domain.review.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReviewEventDto {
    private String reviewId;
    private String type;
    private String action;
    private String userId;
    private String placeId;
    private String content;
    private List<String> attachedPhotoIds;
    private Long mileage;
    private Boolean bonusCheck;
}
