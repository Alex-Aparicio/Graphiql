package com.example.demo.graphql;

import com.example.demo.dto.LegendRequest;
import com.example.demo.dto.LegendResponse;
import com.example.demo.service.LegendService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LegendGraphql {

    private final LegendService legendService;

    /*@QueryMapping
    public LegendResponse legendById(@Argument Integer id) {
        return legendService.findById(id);
    }

    @QueryMapping
    public List<LegendResponse> allLegends() {
        return legendService.findAll();
    }
    */

    @QueryMapping
    public List<LegendResponse> findAllLegendsWithPagination(@Argument int page, @Argument int pageSize) {
        return legendService.findAllWithPagination(page, pageSize);
    }

    @MutationMapping
    public LegendResponse createLegend(@Argument Integer placeId, @Argument LegendRequest input) {
        return legendService.create(placeId, input);
    }

    /*@MutationMapping
    public LegendResponse updateLegend(@Argument Integer id, @Argument LegendRequest input) {
        return legendService.update(id, input);
    }

    @MutationMapping
    public String deleteLegend(@Argument Integer id) {
        legendService.findById(id); // para lanzar error si no existe
        return "Legend deleted successfully";
    }
        */
}
