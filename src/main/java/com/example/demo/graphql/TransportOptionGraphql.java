package com.example.demo.graphql;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.demo.dto.TransportOptionRequest;
import com.example.demo.dto.TransportOptionResponse;
import com.example.demo.service.TransportOptionService;

import jakarta.validation.Valid;

@Controller
public class TransportOptionGraphql {

    @Autowired
    private TransportOptionService service;

    /* 
    @QueryMapping
    public TransportOptionResponse transportOptionById(@Argument Integer id) {
        return service.findById(id);
    }

    @QueryMapping
    public List<TransportOptionResponse> allTransportOptions() {
        return service.findAll();
    }

    */
    
    @QueryMapping
    public List<TransportOptionResponse> findAllTransportOptionsWithPaginated(@Argument int page, @Argument int pageSize) {
        return service.findAllWithPagination(page, pageSize);
    }

    @QueryMapping
    public List<TransportOptionResponse> findTransportOptionsByType(@Argument String type) {
        return service.findByType(type);
    }

    @MutationMapping
    public TransportOptionResponse createTransportOption(
            @Argument Integer placeId,
            @Valid @Argument TransportOptionRequest input) {
        return service.create(placeId, input);
    }

    @MutationMapping
    public TransportOptionResponse updateTransportOption(
            @Argument Integer id,
            @Valid @Argument TransportOptionRequest input) {
        return service.update(id, input);
    }

    /* 
    @MutationMapping
    public String deleteTransportOption(@Argument Integer id) {
        service.delete(id);
        return "TransportOption deleted successfully";
    }
    */
}
