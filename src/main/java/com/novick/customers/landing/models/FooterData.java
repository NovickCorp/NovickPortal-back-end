package com.novick.customers.landing.models;

import com.novick.customers.landing.entities.FooterLinks;

import java.util.List;

public final class FooterData {

    private final List<FooterLinks> footerLinks;
    private final String copyright;
    private final String logoImage;
    private final Link termsAndPrivacyLink;
    private final Link loginTutorialLink;
    private final Address address;

    public FooterData(List<FooterLinks> footerLinks, Link termsAndPrivacyLink, Link loginTutorialLink,String copyright, String logoImage, Address address) {
        this.footerLinks = footerLinks;
        this.termsAndPrivacyLink = termsAndPrivacyLink;
        this.loginTutorialLink = loginTutorialLink;
        this.copyright = copyright;
        this.logoImage = logoImage;
        this.address = address;
    }

    public List<FooterLinks> getFooterLinks() {
        return footerLinks;
    }

    public Link getTermsAndPrivacyLink() {
        return termsAndPrivacyLink;
    }

    public Link getLoginTutorialLink() {
        return loginTutorialLink;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getLogoImage() {
        return logoImage;
    }

    public Address getAddress() {
        return address;
    }
}
