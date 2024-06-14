package com.ps.models;

public class Sales {

    private int id;
    private String buyerName;
    private String vin;
    private String contractDate;

    public Sales(int id, String buyerName, String vin, String contractDate) {
        this.id = id;
        this.buyerName = buyerName;
        this.vin = vin;
        this.contractDate = contractDate;
    }

    public int getId() {
        return id;
    }


    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "id=" + id +
                ", buyerName='" + buyerName + '\'' +
                ", vin='" + vin + '\'' +
                ", contractDate='" + contractDate + '\'' +
                '}';
    }
}
