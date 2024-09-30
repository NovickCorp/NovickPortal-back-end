package com.novick.customers.landing;

import com.novick.customers.landing.models.Landing;
import com.novick.customers.landing.service.LandingService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LandingController {

    private final LandingService landingService;

    public LandingController(LandingService landingService) {
        this.landingService = landingService;
    }

    @GetMapping("/landing")
    public Landing landing() {
        return landingService.landing();
    }
}
