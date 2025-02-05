package com.github.blaszczyk.scopeviso.postcodeservice.persistence;

public class Postcodes {

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
}
