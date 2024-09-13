package com.novick.customers.landing.models;

import java.util.List;

public class Header {

    private String logoImage;
    private String logoHomeLink;
    private List<HeaderLinks> headerLinks;

    public Header(String logoImage, String logoHomeLink, List<HeaderLinks> headerLinks) {
        this.logoImage = logoImage;
        this.logoHomeLink = logoHomeLink;
        this.headerLinks = headerLinks;
    }

    public String getLogoImage() {
        return logoImage;
    }

    public String getLogoHomeLink() {
        return logoHomeLink;
    }

    public List<HeaderLinks> getHeaderLinks() {
        return headerLinks;
    }
}
