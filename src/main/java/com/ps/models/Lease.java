package com.ps.models;

public class Lease {

    private int id;
    private String lesseeName;
    private String vin;

    public Lease(int id, String vin, String lesseeName) {
        this.vin = vin;
        this.lesseeName = lesseeName;
        this.id = id;

    }

    public int getId() {
        return id;
    }



    public String getLesseeName() {
        return lesseeName;
    }

    public void setLesseeName(String lesseeName) {
        this.lesseeName = lesseeName;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Override
    public String toString() {
        return "Lease{" +
                "id=" + id +
                ", lesseeName='" + lesseeName + '\'' +
                ", vin='" + vin + '\'' +
                '}';
    }
}
