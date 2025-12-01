package com.example.demo.graphql;

import com.example.demo.dto.MunicipalityRequest;
import com.example.demo.dto.MunicipalityResponse;
import com.example.demo.service.MunicipalityService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MunicipalityGraphql {

    private final MunicipalityService municipalityService;

    /*@QueryMapping("getAllMunicipalities")
    public List<MunicipalityResponse> getAllMunicipalities() {
        return municipalityService.findAll();
    }
    */
    
    @QueryMapping("findAllMunicipalitiesWithPagination")
    public List<MunicipalityResponse> findAllMunicipalitiesWithPagination(
            @Argument int page,
            @Argument int pageSize) {
        return municipalityService.findAllWithPagination(page, pageSize);
    }

    /* 
    @QueryMapping("getMunicipalityById")
    public MunicipalityResponse getMunicipalityById(@Argument Integer id) {
        return municipalityService.findById(id);
    }
    */

    /* 
    @QueryMapping("getMunicipalitiesByStateId")
    public List<MunicipalityResponse> getMunicipalitiesByStateId(@Argument Integer stateId) {
        return municipalityService.findByStateId(stateId);
    }
    */

    @QueryMapping("findMunicipalitiesByName")
    public List<MunicipalityResponse> findMunicipalitiesByName(@Argument String name) {
        return municipalityService.findByNameLike(name);
    }

    @MutationMapping("createMunicipality")
    public MunicipalityResponse createMunicipality(
            @Argument Integer stateId,
            @Argument MunicipalityRequest request) {
        return municipalityService.create(stateId, request);
    }

    @MutationMapping("updateMunicipality")
    public MunicipalityResponse updateMunicipality(
            @Argument Integer id,
            @Argument Integer stateId,
            @Argument MunicipalityRequest request) {
        return municipalityService.update(id, stateId, request);
    }
}

