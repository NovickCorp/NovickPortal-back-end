package com.novick.customers.landing.service;

import com.novick.customers.landing.models.Header;
import com.novick.customers.landing.models.HeaderLinks;
import com.novick.customers.landing.models.Landing;
import com.novick.customers.landing.repositories.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LandingService {

    private HeaderRepository headerRepository;
    private HeaderLinksRepository headerLinksRepository;
    private NewsInterface newsRepository;
    private FooterRepository footerRepository;
    private FooterLinksRepository footerLinksRepository;
    private PromoSpaceRepository promoSpaceRepository;
    private CopyrightLinksRepository copyrightLinksRepository;

    public LandingService(HeaderRepository headerRepository, HeaderLinksRepository headerLinksRepository, NewsInterface newsRepository, FooterRepository footerRepository, FooterLinksRepository footerLinksRepository, PromoSpaceRepository promoSpaceRepository, CopyrightLinksRepository copyrightLinksRepository) {
        this.headerRepository = headerRepository;
        this.headerLinksRepository = headerLinksRepository;
        this.newsRepository = newsRepository;
        this.footerRepository = footerRepository;
        this.footerLinksRepository = footerLinksRepository;
        this.promoSpaceRepository = promoSpaceRepository;
        this.copyrightLinksRepository = copyrightLinksRepository;
    }

    public Landing landing() {
        var headerEntity = this.headerRepository.findAll();
         if (headerEntity.isEmpty()) {
             throw new IllegalStateException("No header found");
         }

        var headerLinks = this.headerLinksRepository.findAll().stream().map(HeaderLinks::new).toList();
        var header = new Header(headerEntity.get(0).getLogoImage(), headerEntity.get(0).getLogoHomeLink(), headerLinks);

        return new Landing(header, null, null);
    }
}
