package com.Triple_HomeWork.Triple_HomeWork.domain.repository;

import com.Triple_HomeWork.Triple_HomeWork.domain.review.dto.ReviewEventDto;
import com.Triple_HomeWork.Triple_HomeWork.domain.review.entity.ReviewLog;
import com.Triple_HomeWork.Triple_HomeWork.domain.review.repository.ReviewLogRepository;
import com.Triple_HomeWork.Triple_HomeWork.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewLogTest {

    @Autowired
    ReviewLogRepository repository;

    @Test
    public void 작성_테스트() {
        IntStream.rangeClosed(1,10).forEach(i-> {
            List<String> list = new ArrayList<>();
            list.add("image"+i);
            list.add("image"+i+1);
            list.add("image"+i+1+1);
            ReviewLog reviewLog = ReviewLog.builder()
                    .reviewId(UUID.randomUUID().toString())
                    .placeId(UUID.randomUUID().toString())
                    .userId(UUID.randomUUID().toString())
                    .action("ADD")
                    .content("Hi~"+i)
                    .photo(list)
                    .type("REVIEW")
                    .build();
            repository.save(reviewLog);
        });
    }

    @Test
    public void 중복_테스트() throws Exception{
        List<ReviewLog> entity = repository.findByPlaceIdAndUserId("1609eb85-4538-4c4b-84e1-6371ea8566c8","b14a2111-caaf-4936-92ce-90f5d8020b90");
        if(entity.size()==0){
            System.out.println("없음");
            throw new Exception("d");
        }
        System.out.println("있음");
        System.out.println(entity.get(0));
    }

    @Autowired
    ReviewService reviewService;

    @Test
    public void Insert_테스트() {
        List<String> list = new ArrayList<>();
        ReviewEventDto dto = new ReviewEventDto();
        dto.setAction("ADD");
        dto.setPlaceId("f6b0c994-3993-4eda-9976-242592ec639c");
//        dto.setReviewId("b5430982-2314-41aa-8870-0fde517eeb1f");
        dto.setUserId("eeae005c-4ebb-42c6-ba52-6c55b48fb47a");
//        dto.setPlaceId(UUID.randomUUID().toString());
        dto.setReviewId(UUID.randomUUID().toString());
//        dto.setUserId(UUID.randomUUID().toString());
        dto.setContent("Hi");
        dto.setAttachedPhotoIds(list);
        dto.setType("REVIEW");

        reviewService.reviewService(dto);
    }

    @Test
    public void Mod_테스트() {
        List<String> list = new ArrayList<>();
        list.add("image");
        ReviewEventDto dto = new ReviewEventDto();
        dto.setAction("MOD");
//        dto.setReviewId(UUID.randomUUID().toString());
//        dto.setPlaceId("f6b0c994-3993-4eda-9976-242592ec639c");
        dto.setReviewId("e93131f6-a430-4f9c-a80e-8ba8f5677341");
        dto.setPlaceId("f6b0c994-3993-4eda-9976-242592ec639c");
//        dto.setUserId(UUID.randomUUID().toString());
        dto.setUserId("2648705a-2622-4ff4-9eab-757a12047f69");
        dto.setContent("Hi수정2");
        dto.setAttachedPhotoIds(list);
        dto.setType("REVIEW");

        reviewService.reviewService(dto);
    }

    @Test
    public void Delete_테스트() {
        List<String> list = new ArrayList<>();
        list.add("image");
        ReviewEventDto dto = new ReviewEventDto();
        dto.setAction("DELETE");
//        dto.setReviewId(UUID.randomUUID().toString());
//        dto.setUserId(UUID.randomUUID().toString());
//        dto.setPlaceId(UUID.randomUUID().toString());
        dto.setPlaceId("f6b0c994-3993-4eda-9976-242592ec639c");
        dto.setReviewId("b5430982-2314-41aa-8870-0fde517eeb1f");
        dto.setUserId("eeae005c-4ebb-42c6-ba52-6c55b48fb47a");
        dto.setContent("삭제");
        dto.setAttachedPhotoIds(list);
        dto.setType("REVIEW");

        reviewService.reviewService(dto);
    }
}
