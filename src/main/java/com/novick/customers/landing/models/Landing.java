package com.novick.customers.landing.models;

public class Landing {

    private Header header;
    private MainContent mainContent;
    private FooterData footer;

    public Landing(Header header, MainContent mainContent, FooterData footer) {
        this.header = header;
        this.mainContent = mainContent;
        this.footer = footer;
    }

    public Header getHeader() {
        return header;
    }

    public MainContent getMainContent() {
        return mainContent;
    }

    public FooterData getFooter() {
        return footer;
    }

}
