package com.example.demo.graphql;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.demo.dto.FavoritePlaceRequest;
import com.example.demo.dto.FavoritePlaceResponse;
import com.example.demo.service.FavoritePlaceService;

import jakarta.validation.Valid;

@Controller
public class FavoritePlaceGraphql {

    @Autowired
    private FavoritePlaceService service;

    
    @QueryMapping
    public FavoritePlaceResponse favoritePlaceById(@Argument Integer id) {
        return service.findById(id);
    }

    @QueryMapping
    public List<FavoritePlaceResponse> favoritesByUserId(@Argument Integer userId) {
        return service.findByUserId(userId);
    }
    

    @QueryMapping
    public Boolean isFavorite(@Argument Integer userId, @Argument Integer placeId) {
        return service.isFavorite(userId, placeId);
    }

    @MutationMapping
    public FavoritePlaceResponse createFavoritePlace(@Valid @Argument FavoritePlaceRequest input) {
        return service.create(input);
    }

    @MutationMapping
    public String deleteFavoritePlace(@Argument Integer id) {
        service.delete(id);
        return "Favorite place removed successfully";
    }
}
