package com.novick.customers.menu;

import com.novick.customers.menu.entities.AgeGroup;
import com.novick.customers.menu.service.AgeGroupService;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AgeGroupController {

    private final AgeGroupService ageGroupService;

    public AgeGroupController(AgeGroupService ageGroupService) {
        this.ageGroupService = ageGroupService;
    }

    @GetMapping("/ages-group")
    public List<AgeGroup> agesGroup() {
        return ageGroupService.findAll();
    }
}
