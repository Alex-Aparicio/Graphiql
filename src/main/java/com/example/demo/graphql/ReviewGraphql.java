package com.example.demo.graphql;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.demo.dto.ReviewRequest;
import com.example.demo.dto.ReviewResponse;
import com.example.demo.service.ReviewService;

import jakarta.validation.Valid;

@Controller
public class ReviewGraphql {

    @Autowired
    private ReviewService service;

    /*
     * @QueryMapping
     * public ReviewResponse reviewById(@Argument Integer id) {
     * return service.findById(id);
     * }
     */
    @QueryMapping
    public List<ReviewResponse> findAllReviews() {
        return service.findAll();
    }


    @QueryMapping
    public List<ReviewResponse> findAllReviewsWithPagination(
            @Argument int page,
            @Argument int pageSize) {
        return service.findAllWithPagination(page, pageSize);
    }

    /*
     * @QueryMapping
     * public List<ReviewResponse> reviewsByPlaceId(
     * 
     * @Argument Integer placeId,
     * 
     * @Argument int page,
     * 
     * @Argument int pageSize) {
     * return service.findByPlaceId(placeId, page, pageSize);
     * }
     * 
     * @QueryMapping
     * public List<ReviewResponse> reviewsByUserId(
     * 
     * @Argument Integer userId,
     * 
     * @Argument int page,
     * 
     * @Argument int pageSize) {
     * return service.findByUserId(userId, page, pageSize);
     * }
     */

    @MutationMapping
    public ReviewResponse createReview(
            @Argument Integer userId,
            @Argument Integer placeId,
            @Valid @Argument ReviewRequest input) {
        return service.create(userId, placeId, input);
    }

    @MutationMapping
    public ReviewResponse updateReview(
            @Argument Integer id,
            @Valid @Argument ReviewRequest input) {
        return service.update(id, input);
    }

    @MutationMapping
    public String deleteReview(@Argument Integer id) {
        service.delete(id);
        return "Review deleted successfully";
    }
}
