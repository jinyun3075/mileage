package com.Triple_HomeWork.Triple_HomeWork.service.impl;

import com.Triple_HomeWork.Triple_HomeWork.domain.review.dto.ReviewEventDto;
import com.Triple_HomeWork.Triple_HomeWork.domain.review.entity.ReviewEvent;
import com.Triple_HomeWork.Triple_HomeWork.domain.review.repository.ReviewEventRepository;
import com.Triple_HomeWork.Triple_HomeWork.service.ReviewEventService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class ReviewEventServiceImpl implements ReviewEventService {

    private final ReviewEventRepository reviewRepository;

    @Override
    public String reviewService(ReviewEventDto dto) throws Exception{
        switch (dto.getAction()){
            case "ADD":
            case "MOD": reviewInsertAndUpdate(dto);
            return "입력완료";
            case "DELETE": reviewDelete(dto);
            return "입력완료";
            default: throw new Exception("Action을 확인해주세요");
        }
    }

    @Override
    public Long myMileage(String user_id) {
        List<ReviewEvent> list = reviewRepository.findByUserId(user_id);
        Long result = 0l;
        for(ReviewEvent entity: list) {
            result += entity.getMileage();
        }
        return result;
    }

    private void reviewInsertAndUpdate(ReviewEventDto dto) throws Exception{

        List<ReviewEvent> list = reviewRepository.findByPlaceIdAndUserId(dto.getPlaceId(),dto.getUserId());
        Long mileage = 0l;
        for(ReviewEvent entity: list) {
            mileage += entity.getMileage();
        }
        if(dto.getAction().equals("ADD")&&mileage != 0) {
            throw new Exception("리뷰는 1개 이상 작성 할 수 없습니다.");
        }

        dto = mileageCheck(dto);

        if(dto.getAction().equals("MOD")) {
            if(overlapCheck(dto.getReviewId())){
                dto.setMileage(dto.getMileage()-mileage+1);
            }else {
                dto.setMileage(dto.getMileage()-mileage);
            }
        }
        reviewRepository.save(dtoToEntity(dto));
    }

    private void reviewDelete(ReviewEventDto dto) {
        List<ReviewEvent> list = reviewRepository.findByReviewId(dto.getReviewId());
        Long mileage = 0l;
        for(ReviewEvent entity: list) {
            mileage += entity.getMileage();
        }

        List<ReviewEvent> bonusCheck = reviewRepository.findByReviewIdAndBonusCheck(dto.getReviewId(),true);
        for(ReviewEvent entity: bonusCheck) {
            entity.setBonusCheck();
            reviewRepository.save(entity);
        }

        dto.setMileage(-mileage);
        dto.setBonusCheck(false);
        reviewRepository.save(dtoToEntity(dto));
    }

    private ReviewEventDto mileageCheck(ReviewEventDto dto) {
        Long mileage = 0l;
        Boolean bonus = false;
        if(dto.getContent().length()>0) {
            mileage++;
        }
        if(dto.getAttachedPhotoIds().size()>0) {
            mileage++;
        }
        List<ReviewEvent> bonusCheck = reviewRepository.findByBonusCheckAndPlaceId(true,dto.getPlaceId());
        if(bonusCheck.size()==0) {
            mileage++;
            bonus = true;
        }
        dto.setMileage(mileage);
        dto.setBonusCheck(bonus);
        return dto;
    }

    private boolean overlapCheck(String review_id) {
        List<ReviewEvent> bonusCheck = reviewRepository.findByReviewIdAndBonusCheck(review_id,true);
        for(ReviewEvent entity: bonusCheck) {
            return true;
        }
        return false;
    }

}
