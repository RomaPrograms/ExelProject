package entity;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person {
    private SimpleIntegerProperty pId;
    private SimpleStringProperty pPath;
    private SimpleStringProperty pChair;
    private SimpleStringProperty pRank;
    private SimpleStringProperty pName;
    private SimpleStringProperty pCategory;
    private SimpleDoubleProperty pRate;
    private SimpleDoubleProperty pRateQual;

    public Person(String pPath, String pChair, String pRank, String pName,
                  String pCategory, double pRate, double pRateQual) {
        //this.pId = new SimpleIntegerProperty(pId);
        this.pPath = new SimpleStringProperty(pPath);
        this.pChair = new SimpleStringProperty(pChair);
        this.pRank = new SimpleStringProperty(pRank);
        this.pName = new SimpleStringProperty(pName);
        this.pCategory = new SimpleStringProperty(pCategory);
        this.pRate = new SimpleDoubleProperty(pRate);
        this.pRateQual = new SimpleDoubleProperty(pRateQual);
    }

    public double getpRateQual() {
        return pRateQual.get();
    }

    public SimpleDoubleProperty pRateQualProperty() {
        return pRateQual;
    }

    public void setpRateQual(double pRateQual) {
        this.pRateQual.set(pRateQual);
    }

    public int getpId() {
        return pId.get();
    }

    public SimpleIntegerProperty pIdProperty() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId.set(pId);
    }

    public String getpPath() {
        return pPath.get();
    }

    public SimpleStringProperty pPathProperty() {
        return pPath;
    }

    public void setpPath(String pPath) {
        this.pPath.set(pPath);
    }

    public String getpChair() {
        return pChair.get();
    }

    public SimpleStringProperty pChairProperty() {
        return pChair;
    }

    public void setpChair(String pChair) {
        this.pChair.set(pChair);
    }

    public String getpRank() {
        return pRank.get();
    }

    public SimpleStringProperty pRankProperty() {
        return pRank;
    }

    public void setpRank(String pRank) {
        this.pRank.set(pRank);
    }

    public String getpName() {
        return pName.get();
    }

    public SimpleStringProperty pNameProperty() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName.set(pName);
    }

    public String getpCategory() {
        return pCategory.get();
    }

    public SimpleStringProperty pCategoryProperty() {
        return pCategory;
    }

    public void setpCategory(String pCategory) {
        this.pCategory.set(pCategory);
    }

    public double getpRate() {
        return pRate.get();
    }

    public SimpleDoubleProperty pRateProperty() {
        return pRate;
    }

    public void setpRate(double pRate) {
        this.pRate.set(pRate);
    }
}
