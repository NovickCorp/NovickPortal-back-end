package com.novick.customers.landing.models;

import com.novick.customers.landing.entities.Header;

public final class HeaderLinks {

    private final long id;
    private final String url;
    private final String text;
    private final String icon;
    private final int priority;
    private final String dummyUrl;

    public HeaderLinks(com.novick.customers.landing.entities.HeaderLinks headerLinks) {
        this.id = headerLinks.getId();
        this.url = headerLinks.getUrl();
        this.text = headerLinks.getText();
        this.icon = headerLinks.getIcon();
        this.priority = headerLinks.getPriority();
        this.dummyUrl = headerLinks.getDummyUrl();
    }

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getText() {
        return text;
    }

    public String getIcon() {
        return icon;
    }

    public int getPriority() {
        return priority;
    }

    public String getDummyUrl() {
        return dummyUrl;
    }
}
