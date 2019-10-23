package me.codeminions.bean.db;

import com.google.gson.annotations.Expose;

public class SnackInfo {         //对应表t_snack_info

    @Expose
    private int infoID;
    @Expose
    private String salt;
    @Expose
    private String sugar;
    @Expose
    private String carbon_water;
    @Expose
    private String heat_quantity;
    @Expose
    private String protein;
    @Expose
    private String fat;

    @Expose
    private String dietary_fiber;

    @Expose
    private String Na;

    @Expose
    private String vitaminD;

    @Expose
    private String Ca;


    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getCarbon_water() {
        return carbon_water;
    }

    public void setCarbon_water(String carbon_water) {
        this.carbon_water = carbon_water;
    }

    public String getHeat_quantity() {
        return heat_quantity;
    }

    public void setHeat_quantity(String heat_quantity) {
        this.heat_quantity = heat_quantity;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public int getInfoID() {
        return infoID;
    }

    public void setInfoID(int infoID) {
        this.infoID = infoID;
    }

    public String getDietary_fiber() {
        return dietary_fiber;
    }

    public void setDietary_fiber(String dietary_fiber) {
        this.dietary_fiber = dietary_fiber;
    }

    public String getNa() {
        return Na;
    }

    public void setNa(String na) {
        Na = na;
    }

    public String getVitaminD() {
        return vitaminD;
    }

    public void setVitaminD(String vitaminD) {
        this.vitaminD = vitaminD;
    }

    public String getCa() {
        return Ca;
    }

    public void setCa(String ca) {
        Ca = ca;
    }
}
