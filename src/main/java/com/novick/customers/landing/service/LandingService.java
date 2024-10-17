package com.novick.customers.landing.service;

import com.novick.customers.landing.models.*;
import com.novick.customers.landing.repositories.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    @Cacheable("landing")
    public Landing landing() {
        var headerEntity = this.headerRepository.findAll();
         if (headerEntity.isEmpty()) {
             throw new IllegalStateException("No header found");
         }

        var headerLinks = this.headerLinksRepository.findAll().stream().map(HeaderLinks::new).toList();
        var header = new Header(headerEntity.get(0).getLogoImage(), headerEntity.get(0).getLogoHomeLink(), headerLinks);

        var promoSpace = this.promoSpaceRepository.findAll().stream().toList();
        var newsEntity = this.newsRepository.findAll().stream().toList();

        var mainContent = new MainContent(promoSpace, newsEntity);

        var footerLinks = footerLinksRepository.findAll().stream().toList();
        var footer = footerRepository.findAll().stream().toList().get(0);
        var address = new Address(footer.getAddress(), footer.getCity(), footer.getState(), footer.getZipCode(), footer.getPhoneNumber());

        var copyrightLinks = copyrightLinksRepository.findAll().stream().toList();
        var terms = new Link(copyrightLinks.get(0).getText(), copyrightLinks.get(0).getUrl());
        var tutorial = new Link(copyrightLinks.get(1).getText(), copyrightLinks.get(1).getUrl());

        var footerData = new FooterData(footerLinks, terms, tutorial, footer.getCopyright(), footer.getLogoImage(), address);

        var menuLinks = List.of(
                new IconLink("/menu-planning/library", "Menu Library", "menu-library", "Select a menu from the predefined list, and customize according to your needs"),
                new IconLink("/menu-planning/build", "Build a New Menu", "menu-new", "Create a menu completely from scratch"),
                new IconLink("/menu-planning/my-menus", "My Menus", "menu-list", "A list of all the menus you have created")
        );

        var menuPlanning = new MenuPlanning("Let's Plan our Menu", menuLinks);

        var items = List.of(
                new TitleDescription("meal-patterns", "Choose Meal Patterns", "Meal patterns and age groups"),
                new TitleDescription("create-recipes", "Create Your Recipes", "Create your own recipe for every meal pattern"),
                new TitleDescription("menu-planner", "Menu Planner", "Plan your menu for every week"),
                new TitleDescription("date-menu", "Date and Menu", "Choose a date and name your menu")
        );
        var steps = new Section("Steps", items);
        var menuBuilder = new MenuBuilder("Build a Menu", steps);

        return new Landing(header, mainContent, footerData, menuPlanning, menuBuilder);
    }
}
