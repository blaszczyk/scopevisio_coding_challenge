package com.github.blaszczyk.scopeviso.postcodeservice.persistence;

import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Table(name = "postcodes")
public class PostcodeEntity {

    private String iso_3166_1_alpha_2;

    private String iso_3166_1_alpha_2_region_code;

    private String region1;

    private String region2;

    private String region3;

    private String region4;

    private String postleitzahl;

    private String ort;

    private String area1;

    private String area2;

    private float latitude;

    private float longitude;

    private String zeitzone;

    private String utc;

    private boolean sommerzeit;

    private String active;

    public String getIso_3166_1_alpha_2() {
        return iso_3166_1_alpha_2;
    }

    public String getIso_3166_1_alpha_2_region_code() {
        return iso_3166_1_alpha_2_region_code;
    }

    public String getRegion1() {
        return region1;
    }

    public String getRegion2() {
        return region2;
    }

    public String getRegion3() {
        return region3;
    }

    public String getRegion4() {
        return region4;
    }

    public String getPostleitzahl() {
        return postleitzahl;
    }

    public String getOrt() {
        return ort;
    }

    public String getArea1() {
        return area1;
    }

    public String getArea2() {
        return area2;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getZeitzone() {
        return zeitzone;
    }

    public String getUtc() {
        return utc;
    }

    public boolean isSommerzeit() {
        return sommerzeit;
    }

    public String getActive() {
        return active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostcodeEntity that = (PostcodeEntity) o;
        return Float.compare(latitude, that.latitude) == 0 && Float.compare(longitude, that.longitude) == 0 && sommerzeit == that.sommerzeit && Objects.equals(iso_3166_1_alpha_2, that.iso_3166_1_alpha_2) && Objects.equals(iso_3166_1_alpha_2_region_code, that.iso_3166_1_alpha_2_region_code) && Objects.equals(region1, that.region1) && Objects.equals(region2, that.region2) && Objects.equals(region3, that.region3) && Objects.equals(region4, that.region4) && Objects.equals(postleitzahl, that.postleitzahl) && Objects.equals(ort, that.ort) && Objects.equals(area1, that.area1) && Objects.equals(area2, that.area2) && Objects.equals(zeitzone, that.zeitzone) && Objects.equals(utc, that.utc) && Objects.equals(active, that.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iso_3166_1_alpha_2, iso_3166_1_alpha_2_region_code, region1, region2, region3, region4, postleitzahl, ort, area1, area2, latitude, longitude, zeitzone, utc, sommerzeit, active);
    }

    @Override
    public String toString() {
        return "Postcodes{" +
                "iso_3166_1_alpha_2='" + iso_3166_1_alpha_2 + '\'' +
                ", iso_3166_1_alpha_2_region_code='" + iso_3166_1_alpha_2_region_code + '\'' +
                ", region1='" + region1 + '\'' +
                ", region2='" + region2 + '\'' +
                ", region3='" + region3 + '\'' +
                ", region4='" + region4 + '\'' +
                ", postleitzahl='" + postleitzahl + '\'' +
                ", ort='" + ort + '\'' +
                ", area1='" + area1 + '\'' +
                ", area2='" + area2 + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", zeitzone='" + zeitzone + '\'' +
                ", utc='" + utc + '\'' +
                ", sommerzeit=" + sommerzeit +
                ", active='" + active + '\'' +
                '}';
    }
}
