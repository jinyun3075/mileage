package com.Triple_HomeWork.Triple_HomeWork.controller;

import com.Triple_HomeWork.Triple_HomeWork.domain.review.dto.ReviewEventDto;
import com.Triple_HomeWork.Triple_HomeWork.service.ReviewEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewEventController {

    private final ReviewEventService service;

    @PostMapping("/events")
    public ResponseEntity<String> reviewEvent(@RequestBody ReviewEventDto dto) throws Exception{
        return new ResponseEntity<>(service.reviewService(dto), HttpStatus.OK);
    }

    @GetMapping("/mileage/{user_id}")
    public ResponseEntity<Long> myMileage(@PathVariable String user_id) {
        return new ResponseEntity<>(service.myMileage(user_id),HttpStatus.OK);
    }
}
