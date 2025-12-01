package com.example.demo.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.demo.dto.ServiceReviewRequest;
import com.example.demo.dto.ServiceReviewResponse;
import com.example.demo.service.ServiceReviewService;

import jakarta.validation.Valid;

@Controller
public class ServiceReviewGraphql {

    @Autowired
    private ServiceReviewService service;

    /* 
    @QueryMapping
    public ServiceReviewResponse serviceReviewById(@Argument Integer id) {
        return service.findById(id);
    }
    
    @QueryMapping
    public List<ServiceReviewResponse> allServiceReviews() {
        return service.findAll();
    }

    */

    @QueryMapping
    public List<ServiceReviewResponse> findAllServiceReviewsWithPagination(@Argument int page, @Argument int pageSize) {
        return service.findAllWithPagination(page, pageSize);
    }

    @QueryMapping
    public List<ServiceReviewResponse> serviceReviewsByRating(@Argument Integer rating) {
        return service.findByRating(rating);
    }

    @QueryMapping
    public List<ServiceReviewResponse> searchServiceReviews(@Argument String keyword) {
        return service.searchByComment(keyword);
    }

    @MutationMapping
    public ServiceReviewResponse createServiceReview(
            @Argument Integer serviceId,
            @Argument Integer userId,
            @Valid @Argument ServiceReviewRequest input) {
        return service.create(serviceId, userId, input);
    }

    @MutationMapping
    public ServiceReviewResponse updateServiceReview(
            @Argument Integer id,
            @Valid @Argument ServiceReviewRequest input) {
        return service.update(id, input);
    }

    @MutationMapping
    public String deleteServiceReview(@Argument Integer id) {
        service.delete(id);
        return "Service review deleted successfully";
    }
}
