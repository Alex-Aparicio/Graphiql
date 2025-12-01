package com.example.demo.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.demo.dto.StoryRequest;
import com.example.demo.dto.StoryResponse;
import com.example.demo.service.StoryService;

import jakarta.validation.Valid;

@Controller
public class StoryGraphql {

    @Autowired
    private StoryService service;

    /* 
    @QueryMapping
    public StoryResponse storyById(@Argument Integer id) {
        return service.findById(id);
    }

    @QueryMapping
    public List<StoryResponse> allStories() {
        return service.findAll();
    }
    */

    @QueryMapping
    public List<StoryResponse> findAllStoriesWithPagination(@Argument int page, @Argument int pageSize) {
        return service.findAllWithPagination(page, pageSize);
    }

    @MutationMapping
    public StoryResponse createStory(@Argument Integer placeId, @Valid @Argument StoryRequest input) {
        return service.create(placeId, input);
    }

    /*@MutationMapping
    public StoryResponse updateStory(@Argument Integer id, @Valid @Argument StoryRequest input) {
        return service.update(id, input);
    }
    

    @MutationMapping
    public String deleteStory(@Argument Integer id) {
        service.findById(id); 
        return "Story deleted successfully";
    }
    */
}
