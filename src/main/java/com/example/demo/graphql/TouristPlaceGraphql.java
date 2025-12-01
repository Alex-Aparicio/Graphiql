package com.example.demo.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.demo.dto.TouristPlaceRequest;
import com.example.demo.dto.TouristPlaceResponse;
import com.example.demo.service.TouristPlaceService;

import jakarta.validation.Valid;

@Controller
public class TouristPlaceGraphql {

    @Autowired
    
    private TouristPlaceService service;

    // 🔍 Consultas (Query)
    /* 
    @QueryMapping
    public List<TouristPlaceResponse> findAllTouristPlaces() {
        return service.findAll();
    }

    @QueryMapping
    public TouristPlaceResponse findTouristPlaceById(@Argument Integer id) {
        return service.findById(id);
    }

    */
    @QueryMapping
    public List<TouristPlaceResponse> findTouristPlacesByNameLike(@Argument String name) {
        return service.findByNameLike(name);
    }

    /* 
    @QueryMapping
    public List<TouristPlaceResponse> findTouristPlacesByMunicipalityId(@Argument Integer municipalityId) {
        return service.findByMunicipalityId(municipalityId);
    }
     */

    @QueryMapping
    public List<TouristPlaceResponse> findAllTouristPlacesWithPagination(@Argument int page, @Argument int pageSize) {
        return service.findAllWithPagination(page, pageSize);
    }

    // ✏️ Mutaciones (Mutation)
    @MutationMapping
    public TouristPlaceResponse createTouristPlace(
            @Argument Integer municipalityId,
            @Argument Integer addressId,
            @Valid @Argument TouristPlaceRequest request) {
        return service.create(municipalityId, addressId, request);
    }

    @MutationMapping
    public TouristPlaceResponse updateTouristPlace(
            @Argument Integer id,
            @Argument Integer municipalityId,
            @Argument Integer addressId,
            @Valid @Argument TouristPlaceRequest request) {
        return service.update(id, municipalityId, addressId, request);
    }
}
