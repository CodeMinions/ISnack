package me.codeminions.bean.db;

public class SnackInfo {         //对应表t_snack_info

    private int infoID;
    private int snack_id;
    private String salt;
    private String sugar;
    private String carbon_water;
    private String heat_quantity;
    private String protein;
    private String fat;


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

    public int getSnack_id() {
        return snack_id;
    }

    public void setSnack_id(int snack_id) {
        this.snack_id = snack_id;
    }
}
