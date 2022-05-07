package com.example.controller;

import com.example.model.ReviewModel;
import com.example.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    @GetMapping("/{id}")
    public ReviewModel getById(@PathVariable Long id) {
        return reviewService.getById(id);
    }

    @PostMapping
    public ReviewModel createUserRole(@RequestBody ReviewModel reviewModel) {
        return reviewService.create(reviewModel);
    }

    @PutMapping
    public ReviewModel updateUserRole(@RequestBody ReviewModel reviewModel) {
        return reviewService.update(reviewModel);
    }

    @GetMapping
    public List<ReviewModel> getAll() {
        return reviewService.getAll();
    }

    @DeleteMapping("/{id}")
    public ReviewModel deleteById(@PathVariable Long id) {
        return reviewService.deleteById(id);
    }
}
