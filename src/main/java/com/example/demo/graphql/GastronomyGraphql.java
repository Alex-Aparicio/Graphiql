package com.example.demo.graphql;

import com.example.demo.dto.GastronomyRequest;
import com.example.demo.dto.GastronomyResponse;
import com.example.demo.service.GastronomyService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GastronomyGraphql {

    private final GastronomyService gastronomyService;

    /* @QueryMapping
    public GastronomyResponse gastronomyById(@Argument Integer id) {
        return gastronomyService.findById(id);
    }

    @QueryMapping
    public List<GastronomyResponse> allGastronomies() {
        return gastronomyService.findAll();
    }

    
        */

    @QueryMapping
    public List<GastronomyResponse> findAllGastronomiesWithPagination(@Argument int page, @Argument int pageSize) {
        return gastronomyService.findAllWithPagination(page, pageSize);
    }

    @MutationMapping
    public GastronomyResponse createGastronomy(@Argument Integer placeId, @Argument GastronomyRequest input) {
        return gastronomyService.create(placeId, input);
    }

    @MutationMapping
    public GastronomyResponse updateGastronomy(@Argument Integer id, @Argument GastronomyRequest input) {
        return gastronomyService.update(id, input);
    }

    /* 
    @MutationMapping
    public String deleteGastronomy(@Argument Integer id) {
        gastronomyService.delete(id);
        return "Dish deleted successfully";
    }
        */
}
