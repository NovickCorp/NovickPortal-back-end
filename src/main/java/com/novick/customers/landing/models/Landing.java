package com.novick.customers.landing.models;

import java.util.Objects;

public record Landing(Header header, MainContent dashboard, FooterData footer, MenuPlanning menuPlanning, MenuBuilder menuBuilder) {

    public Landing {
        Objects.requireNonNull(header);
        Objects.requireNonNull(dashboard);
        Objects.requireNonNull(footer);
        Objects.requireNonNull(menuPlanning);
        Objects.requireNonNull(menuBuilder);
    }
}
