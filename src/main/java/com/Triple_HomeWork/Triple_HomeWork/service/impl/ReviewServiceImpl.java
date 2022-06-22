package com.Triple_HomeWork.Triple_HomeWork.service.impl;

import com.Triple_HomeWork.Triple_HomeWork.domain.review.dto.ReviewEventDto;
import com.Triple_HomeWork.Triple_HomeWork.domain.review.entity.ReviewLog;
import com.Triple_HomeWork.Triple_HomeWork.domain.review.repository.ReviewLogRepository;
import com.Triple_HomeWork.Triple_HomeWork.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewLogRepository reviewRepository;

    @Override
    public void reviewService(ReviewEventDto dto) {
        try{
            if(dto.getAction().equals("DELETE")) {
                reviewDelete(dto);
            }else {
                reviewInsertAndUpdate(dto);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void reviewInsertAndUpdate(ReviewEventDto dto) throws Exception{

        List<ReviewLog> list = reviewRepository.findByPlaceIdAndUserId(dto.getPlaceId(),dto.getUserId());
        Long mileage = 0l;
        for(ReviewLog entity: list) {
            mileage += entity.getMileage();
        }
        if(dto.getAction().equals("ADD")&&mileage != 0) {
            throw new Exception("리뷰는 1개 이상 작성 할 수 없습니다.");
        }

        dto = mileageCheck(dto);

        if(dto.getAction().equals("MOD")) {
            dto.setMileage(dto.getMileage()-mileage);
        }
        reviewRepository.save(dtoToEntity(dto));
    }

    private void reviewDelete(ReviewEventDto dto) {
        List<ReviewLog> list = reviewRepository.findByReviewId(dto.getReviewId());
        Long mileage = 0l;
        for(ReviewLog entity: list) {
            mileage += entity.getMileage();
        }

        List<ReviewLog> bonusCheck = reviewRepository.findByReviewIdAndBonusCheck(dto.getReviewId(),true);
        for(ReviewLog entity: bonusCheck) {
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
        List<ReviewLog> bonusCheck = reviewRepository.findByBonusCheckAndPlaceId(true,dto.getPlaceId());
        if(bonusCheck.size()==0) {
            mileage++;
            bonus = true;
        }
        dto.setMileage(mileage);
        dto.setBonusCheck(bonus);
        return dto;
    }

}
