package com.Triple_HomeWork.Triple_HomeWork.domain.repository;

import com.Triple_HomeWork.Triple_HomeWork.domain.review.entity.ReviewLog;
import com.Triple_HomeWork.Triple_HomeWork.domain.review.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewLogTest {

    @Autowired
    ReviewRepository repository;

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
}
