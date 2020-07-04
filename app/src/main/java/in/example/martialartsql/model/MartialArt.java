package in.example.martialartsql.model;

import androidx.annotation.NonNull;

public class MartialArt {

    private String martialArtName;
    private double martialArtPrice;
    private String martialArtColor;

    private int martialArtID;

    public MartialArt(int id, String name, double price, String color) {
        setMartialArtID(id);
        setMartialArtColor(color);
        setMartialArtName(name);
        setMartialArtPrice(price);
    }

    public String getMartialArtName() {
        return martialArtName;
    }

    public void setMartialArtName(String martialArtName) {
        this.martialArtName = martialArtName;
    }

    public double getMartialArtPrice() {
        return martialArtPrice;
    }

    public void setMartialArtPrice(double martialArtPrice) {
        this.martialArtPrice = martialArtPrice;
    }

    public String getMartialArtColor() {
        return martialArtColor;
    }

    public void setMartialArtColor(String martialArtColor) {
        this.martialArtColor = martialArtColor;
    }

    public int getMartialArtID() {
        return martialArtID;
    }

    public void setMartialArtID(int martialArtID) {
        this.martialArtID = martialArtID;
    }

    @NonNull
    @Override
    public String toString() {
        return getMartialArtID() + " " + getMartialArtName() + " " + getMartialArtPrice() + " " + getMartialArtColor();
    }
}
