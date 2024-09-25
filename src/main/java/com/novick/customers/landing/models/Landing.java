package com.novick.customers.landing.models;

public class Landing {

    private Header header;
    private MainContent dashboard;
    private FooterData footer;

    public Landing(Header header, MainContent dashboard, FooterData footer) {
        this.header = header;
        this.dashboard = dashboard;
        this.footer = footer;
    }

    public Header getHeader() {
        return header;
    }

    public MainContent getDashboard() {
        return dashboard;
    }

    public FooterData getFooter() {
        return footer;
    }

    @Override
    public String toString() {
        return "Landing{" +
                "header=" + header +
                ", dashboard=" + dashboard +
                ", footer=" + footer +
                '}';
    }
}
