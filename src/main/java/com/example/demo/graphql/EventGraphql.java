package com.example.demo.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.demo.dto.EventRequest;
import com.example.demo.dto.EventResponse;
import com.example.demo.service.EventService;

import jakarta.validation.Valid;

@Controller
public class EventGraphql {

    @Autowired
    private EventService service;

    /* 
    @QueryMapping
    public EventResponse findEventById(@Argument Integer id) {
        return service.findById(id);
    }
    */

    @QueryMapping
    public List<EventResponse> findAllEventsWithPagination(
            @Argument int page,
            @Argument int pageSize) {
        return service.findAllWithPagination(page, pageSize);
    }

    @MutationMapping
    public EventResponse createEvent(
            @Argument Integer placeId,
            @Valid @Argument EventRequest request) {
        return service.create(placeId, request);
    }

    @MutationMapping
    public EventResponse updateEvent(
            @Argument Integer id,
            @Valid @Argument EventRequest request) {
        return service.update(id, request);
    }

    /*@MutationMapping
    public String deleteEvent(@Argument Integer id) {
        service.delete(id);
        return "Event deleted successfully";
    }
    */
}
