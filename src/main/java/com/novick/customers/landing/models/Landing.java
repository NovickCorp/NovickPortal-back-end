package com.novick.customers.landing.models;

public class Landing {

    private Header header;
    private MainContent mainContent;
    private Footer footer;

    public Landing(Header header, MainContent mainContent, Footer footer) {
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

    public Footer getFooter() {
        return footer;
    }

}
