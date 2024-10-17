package com.novick.customers.landing.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "header")
public class Header {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="logoImage")
    private String logoImage;
    @Column(name="logoHomeLink")
    private String logoHomeLink;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(String logoImage) {
        this.logoImage = logoImage;
    }

    public String getLogoHomeLink() {
        return logoHomeLink;
    }

    public void setLogoHomeLink(String logoHomeLink) {
        this.logoHomeLink = logoHomeLink;
    }
}
