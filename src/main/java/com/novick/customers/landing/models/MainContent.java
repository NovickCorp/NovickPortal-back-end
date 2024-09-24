package com.novick.customers.landing.models;

import com.novick.customers.landing.entities.News;
import com.novick.customers.landing.entities.PromoSpace;

import java.util.List;

public final class MainContent {

    private final PromoSpace promoSpace;
    private final List<News> news;

    public MainContent(List<PromoSpace> promoSpace, List<News> news) {
        this.promoSpace = promoSpace.get(0);
        this.news = news;
    }

    public PromoSpace getPromoSpace() {
        return promoSpace;
    }

    public List<News> getNews() {
        return news;
    }
}
