package com.example.demo.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.demo.dto.ServicesRequest;
import com.example.demo.dto.ServicesResponse;
import com.example.demo.service.ServicesService;

import jakarta.validation.Valid;

@Controller
public class ServicesGraphql {

    @Autowired
    private ServicesService service;

    /* 
    @QueryMapping
    public ServicesResponse serviceById(@Argument Integer id) {
        return service.findById(id);
    }
    */

    @QueryMapping
    public List<ServicesResponse> findAllServicesWithPagination(@Argument int page, @Argument int pageSize) {
        return service.findAllWithPagination(page, pageSize);
    }

    @QueryMapping
    public List<ServicesResponse> findServicesByName(@Argument String name) {
        return service.findByNameLike(name);
    }

    @QueryMapping
    public List<ServicesResponse> findServicesByType(@Argument String type) {
        return service.findByType(type);
    }

    @QueryMapping
    public List<ServicesResponse> findServicesByPriceRange(@Argument String range) {
        return service.findByPriceRange(range);
    }

    @QueryMapping
    public List<ServicesResponse> findServicesByTouristPlaceName(@Argument String placeName) {
        return service.findByTouristPlaceName(placeName);
    }

    @MutationMapping
    public ServicesResponse createService(
            @Argument Integer placeId,
            @Argument Integer addressId,
            @Valid @Argument ServicesRequest input) {
        return service.create(placeId, addressId, input);
    }

    @MutationMapping
    public ServicesResponse updateService(
            @Argument Integer id,
            @Argument Integer placeId,
            @Argument Integer addressId,
            @Valid @Argument ServicesRequest input) {
        return service.update(id, placeId, addressId, input);
    }

    /* 
    @MutationMapping
    public String deleteService(@Argument Integer id) {
        service.delete(id);
        return "Service deleted successfully";
    }
    */
}
