package entity;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Faculty {
    private SimpleStringProperty fName;
    private SimpleIntegerProperty fYear;
    private SimpleDoubleProperty fSchoolWork;
    private SimpleDoubleProperty fConstantSchoolWork;
    private SimpleDoubleProperty fMethodicalWork;
    private SimpleDoubleProperty fConstantMethodicalWork;
    private SimpleDoubleProperty fConstantIdWork;
    private SimpleDoubleProperty fSinceWork;
    private SimpleDoubleProperty fConstantSinceWork;
    private SimpleDoubleProperty fMatBase;
    private SimpleDoubleProperty fConstantMatBase;
    private SimpleDoubleProperty fConstantVSandVPVO;
    private SimpleDoubleProperty fConstantSMR;
    private SimpleDoubleProperty fRate;

    public Faculty() {
        this.fName = new SimpleStringProperty();
        this.fYear = new SimpleIntegerProperty();
        this.fSchoolWork = new SimpleDoubleProperty();
        this.fConstantSchoolWork = new SimpleDoubleProperty();
        this.fMethodicalWork = new SimpleDoubleProperty();
        this.fConstantMethodicalWork = new SimpleDoubleProperty();
        this.fConstantIdWork = new SimpleDoubleProperty();
        this.fSinceWork = new SimpleDoubleProperty();
        this.fConstantSinceWork = new SimpleDoubleProperty();
        this.fMatBase = new SimpleDoubleProperty();
        this.fConstantMatBase = new SimpleDoubleProperty();
        this.fConstantVSandVPVO = new SimpleDoubleProperty();
        this.fConstantSMR = new SimpleDoubleProperty();
        this.fRate = new SimpleDoubleProperty();
    }

    public Faculty(String fName, double fSchoolWork, double fMethodicalWork,
                   double fIdWork, double fSinceWork, double fMatBase,
                   double fVSandVPVO, double fSMR, double fRate) {
        this.fName = new SimpleStringProperty(fName);
        this.fSchoolWork = new SimpleDoubleProperty(fSchoolWork);
        this.fMethodicalWork = new SimpleDoubleProperty(fMethodicalWork);
        this.fSinceWork = new SimpleDoubleProperty(fSinceWork);
        this.fMatBase = new SimpleDoubleProperty(fMatBase);
        this.fRate = new SimpleDoubleProperty(fRate);
    }

    public double getfConstantSchoolWork() {
        return fConstantSchoolWork.get();
    }

    public SimpleDoubleProperty fConstantSchoolWorkProperty() {
        return fConstantSchoolWork;
    }

    public void setfConstantSchoolWork(double fConstantSchoolWork) {
        this.fConstantSchoolWork.set(fConstantSchoolWork);
    }

    public double getfConstantMethodicalWork() {
        return fConstantMethodicalWork.get();
    }

    public SimpleDoubleProperty fConstantMethodicalWorkProperty() {
        return fConstantMethodicalWork;
    }

    public void setfConstantMethodicalWork(double fConstantMethodicalWork) {
        this.fConstantMethodicalWork.set(fConstantMethodicalWork);
    }

    public double getfConstantIdWork() {
        return fConstantIdWork.get();
    }

    public SimpleDoubleProperty fConstantIdWorkProperty() {
        return fConstantIdWork;
    }

    public void setfConstantIdWork(double fConstantIdWork) {
        this.fConstantIdWork.set(fConstantIdWork);
    }

    public double getfConstantSinceWork() {
        return fConstantSinceWork.get();
    }

    public SimpleDoubleProperty fConstantSinceWorkProperty() {
        return fConstantSinceWork;
    }

    public void setfConstantSinceWork(double fConstantSinceWork) {
        this.fConstantSinceWork.set(fConstantSinceWork);
    }

    public double getfConstantMatBase() {
        return fConstantMatBase.get();
    }

    public SimpleDoubleProperty fConstantMatBaseProperty() {
        return fConstantMatBase;
    }

    public void setfConstantMatBase(double fConstantMatBase) {
        this.fConstantMatBase.set(fConstantMatBase);
    }

    public double getfConstantVSandVPVO() {
        return fConstantVSandVPVO.get();
    }

    public SimpleDoubleProperty fConstantVSandVPVOProperty() {
        return fConstantVSandVPVO;
    }

    public void setfConstantVSandVPVO(double fConstantVSandVPVO) {
        this.fConstantVSandVPVO.set(fConstantVSandVPVO);
    }

    public double getfConstantSMR() {
        return fConstantSMR.get();
    }

    public SimpleDoubleProperty fConstantSMRProperty() {
        return fConstantSMR;
    }

    public void setfConstantSMR(double fConstantSMR) {
        this.fConstantSMR.set(fConstantSMR);
    }

    public int getfYear() {
        return fYear.get();
    }

    public SimpleIntegerProperty fYearProperty() {
        return fYear;
    }

    public void setfYear(int fYear) {
        this.fYear.set(fYear);
    }

    public String getfName() {
        return fName.get();
    }

    public SimpleStringProperty fNameProperty() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName.set(fName);
    }

    public double getfSchoolWork() {
        return fSchoolWork.get();
    }

    public SimpleDoubleProperty fSchoolWorkProperty() {
        return fSchoolWork;
    }

    public void setfSchoolWork(double fSchoolWork) {
        this.fSchoolWork.set(fSchoolWork);
    }

    public double getfMethodicalWork() {
        return fMethodicalWork.get();
    }

    public SimpleDoubleProperty fMethodicalWorkProperty() {
        return fMethodicalWork;
    }

    public void setfMethodicalWork(double fMethodicalWork) {
        this.fMethodicalWork.set(fMethodicalWork);
    }

    public double getfSinceWork() {
        return fSinceWork.get();
    }

    public SimpleDoubleProperty fSinceWorkProperty() {
        return fSinceWork;
    }

    public void setfSinceWork(double fSinceWork) {
        this.fSinceWork.set(fSinceWork);
    }

    public double getfMatBase() {
        return fMatBase.get();
    }

    public SimpleDoubleProperty fMatBaseProperty() {
        return fMatBase;
    }

    public void setfMatBase(double fMatBase) {
        this.fMatBase.set(fMatBase);
    }

    public double getfRate() {
        return fRate.get();
    }

    public SimpleDoubleProperty fRateProperty() {
        return fRate;
    }

    public void setfRate(double fRate) {
        this.fRate.set(fRate);
    }
}
