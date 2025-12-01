package com.example.demo.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.demo.dto.StateRequest;
import com.example.demo.dto.StateResponse;
import com.example.demo.service.StateService;

import jakarta.validation.Valid;

@Controller
public class StateGraphql { 

    @Autowired
    private StateService service;

    /* 
    @QueryMapping
    public List<StateResponse> findAll() {
        return service.findAll();
    }
    */

    @QueryMapping("findAllStatesWithPagination")
    public List<StateResponse> findAllStatesWithPagination(
            @Argument int page,
            @Argument int pageSize) {
        return service.findAllWithPagination(page, pageSize);
    }

    /* 
    @QueryMapping
    public StateResponse findById(@Argument Integer id) {
        return service.findById(id);
    }
    */

    @QueryMapping
    public List<StateResponse> findByNameLike(@Argument String name) {
        return service.findByNameLike(name);
    }

    @MutationMapping
    public StateResponse create(@Valid @Argument StateRequest request) {
        return service.create(request);
    }

    /* 
    @MutationMapping
    public StateResponse update(@Argument Integer id, @Valid @Argument StateRequest request) {
        return service.update(id, request);
    }
    */
}
