package com.example.demo.graphql;

import com.example.demo.dto.ReviewImageRequest;
import com.example.demo.dto.ReviewImageResponse;
import com.example.demo.service.ReviewImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReviewImageGraphql {

    private final ReviewImageService reviewImageService;

    /*@QueryMapping("getAllReviewImages")
    public List<ReviewImageResponse> getAllReviewImages() {
        return reviewImageService.findAll();
    }
    */

    @QueryMapping("findAllReviewImagesWithPagination")
    public List<ReviewImageResponse> findAllReviewImagesWithPagination(
            @Argument int page,
            @Argument int pageSize) {
        return reviewImageService.findAllWithPagination(page, pageSize);
    }


    /* 
    @QueryMapping("getReviewImageById")
    public ReviewImageResponse getReviewImageById(@Argument Integer id) {
        return reviewImageService.findById(id);
    }
    */

    
    @QueryMapping("findReviewImagesByReview")
    public List<ReviewImageResponse> findReviewImagesByReview(@Argument Integer reviewId) {
        return reviewImageService.findByReviewId(reviewId);
    }

    @MutationMapping("createReviewImage")
    public ReviewImageResponse createReviewImage(
            @Argument Integer reviewId,
            @Argument ReviewImageRequest request) {
        return reviewImageService.create(reviewId, request);
    }

    @MutationMapping("updateReviewImage")
    public ReviewImageResponse updateReviewImage(
            @Argument Integer id,
            @Argument ReviewImageRequest request) {
        return reviewImageService.update(id, request);
    }

    @MutationMapping("deleteReviewImage")
    public String deleteReviewImage(@Argument Integer id) {
        reviewImageService.delete(id);
        return "Review image deleted successfully";
    }
}
