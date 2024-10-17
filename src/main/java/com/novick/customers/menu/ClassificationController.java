package com.novick.customers.menu;

import com.novick.customers.menu.entities.Classification;
import com.novick.customers.menu.service.ClassificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClassificationController {

    private final ClassificationService classificationService;

    public ClassificationController(ClassificationService classificationService) {
        this.classificationService = classificationService;
    }

    @GetMapping("classifications")
    public List<Classification> classifications() {
        return classificationService.findAll();
    }
}
