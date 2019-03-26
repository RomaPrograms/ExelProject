package entity;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Chair {
    private SimpleStringProperty chUnivName;
    private SimpleStringProperty chName;
    private SimpleDoubleProperty chNpp;
    private SimpleDoubleProperty chStudyWork;
    private SimpleDoubleProperty chMethodicalWork;
    private SimpleDoubleProperty chSinceWork;
    private SimpleDoubleProperty chMatBase;
    private SimpleDoubleProperty chIdWork;
    private SimpleDoubleProperty chSecurity;
    private SimpleDoubleProperty chRate;
    private SimpleDoubleProperty chRateQual;

    public Chair(String chUnivName, String chName, double chNpp,
                 double chStudyWork, double chMethodicalWork,
                 double chSinceWork, double chMatBase, double chIdWork,
                 double chSecurity, double chRate, double chRateQual) {
        this.chUnivName = new SimpleStringProperty(chUnivName);
        this.chName = new SimpleStringProperty(chName);
        this.chNpp = new SimpleDoubleProperty(chNpp);
        this.chStudyWork = new SimpleDoubleProperty(chStudyWork);
        this.chMethodicalWork = new SimpleDoubleProperty(chMethodicalWork);
        this.chSinceWork = new SimpleDoubleProperty(chSinceWork);
        this.chMatBase = new SimpleDoubleProperty(chMatBase);
        this.chIdWork = new SimpleDoubleProperty(chIdWork);
        this.chSecurity = new SimpleDoubleProperty(chSecurity);
        this.chRate = new SimpleDoubleProperty(chRate);
        this.chRateQual = new SimpleDoubleProperty(chRateQual);
    }

    public double getChRateQual() {
        return chRateQual.get();
    }

    public SimpleDoubleProperty chRateQualProperty() {
        return chRateQual;
    }

    public void setChRateQual(double chRateQual) {
        this.chRateQual.set(chRateQual);
    }

    public double getChIdWork() {
        return chIdWork.get();
    }

    public SimpleDoubleProperty chIdWorkProperty() {
        return chIdWork;
    }

    public void setChIdWork(double chIdWork) {
        this.chIdWork.set(chIdWork);
    }

    public double getChSecurity() {
        return chSecurity.get();
    }

    public SimpleDoubleProperty chSecurityProperty() {
        return chSecurity;
    }

    public void setChSecurity(double chSecurity) {
        this.chSecurity.set(chSecurity);
    }

    public String getChUnivName() {
        return chUnivName.get();
    }

    public SimpleStringProperty chUnivNameProperty() {
        return chUnivName;
    }

    public void setChUnivName(String chUnivName) {
        this.chUnivName.set(chUnivName);
    }

    public String getChName() {
        return chName.get();
    }

    public SimpleStringProperty chNameProperty() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName.set(chName);
    }

    public double getChNpp() {
        return chNpp.get();
    }

    public SimpleDoubleProperty chNppProperty() {
        return chNpp;
    }

    public void setChNpp(double chNpp) {
        this.chNpp.set(chNpp);
    }

    public double getChStudyWork() {
        return chStudyWork.get();
    }

    public SimpleDoubleProperty chStudyWorkProperty() {
        return chStudyWork;
    }

    public void setChStudyWork(double chStudyWork) {
        this.chStudyWork.set(chStudyWork);
    }

    public double getChMethodicalWork() {
        return chMethodicalWork.get();
    }

    public SimpleDoubleProperty chMethodicalWorkProperty() {
        return chMethodicalWork;
    }

    public void setChMethodicalWork(double chMethodicalWork) {
        this.chMethodicalWork.set(chMethodicalWork);
    }

    public double getChSinceWork() {
        return chSinceWork.get();
    }

    public SimpleDoubleProperty chSinceWorkProperty() {
        return chSinceWork;
    }

    public void setChSinceWork(double chSinceWork) {
        this.chSinceWork.set(chSinceWork);
    }

    public double getChMatBase() {
        return chMatBase.get();
    }

    public SimpleDoubleProperty chMatBaseProperty() {
        return chMatBase;
    }

    public void setChMatBase(double chMatBase) {
        this.chMatBase.set(chMatBase);
    }

    public double getChRate() {
        return chRate.get();
    }

    public SimpleDoubleProperty chRateProperty() {
        return chRate;
    }

    public void setChRate(double chRate) {
        this.chRate.set(chRate);
    }
}
