package entity;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Faculty {
    private SimpleStringProperty fName;
    private SimpleIntegerProperty fYear;
    private SimpleDoubleProperty fSchoolWork;
    private SimpleStringProperty fConstantSchoolWork;
    private SimpleDoubleProperty fMethodicalWork;
    private SimpleStringProperty fConstantMethodicalWork;
    private SimpleStringProperty fConstantIdWork;
    private SimpleDoubleProperty fSinceWork;
    private SimpleStringProperty fConstantSinceWork;
    private SimpleDoubleProperty fMatBase;
    private SimpleStringProperty fConstantMatBase;
    private SimpleStringProperty fConstantVSandVPVO;
    private SimpleStringProperty fConstantSMR;
    private SimpleDoubleProperty fRate;

    public Faculty() {
        this.fName = new SimpleStringProperty();
        this.fYear = new SimpleIntegerProperty();
        this.fSchoolWork = new SimpleDoubleProperty();
        this.fConstantSchoolWork = new SimpleStringProperty();
        this.fMethodicalWork = new SimpleDoubleProperty();
        this.fConstantMethodicalWork = new SimpleStringProperty();
        this.fConstantIdWork = new SimpleStringProperty();
        this.fSinceWork = new SimpleDoubleProperty();
        this.fConstantSinceWork = new SimpleStringProperty();
        this.fMatBase = new SimpleDoubleProperty();
        this.fConstantMatBase = new SimpleStringProperty();
        this.fConstantVSandVPVO = new SimpleStringProperty();
        this.fConstantSMR = new SimpleStringProperty();
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

    public String getfConstantSchoolWork() {
        return fConstantSchoolWork.get();
    }

    public SimpleStringProperty fConstantSchoolWorkProperty() {
        return fConstantSchoolWork;
    }

    public void setfConstantSchoolWork(String fConstantSchoolWork) {
        this.fConstantSchoolWork.set(fConstantSchoolWork);
    }

    public String getfConstantIdWork() {
        return fConstantIdWork.get();
    }

    public SimpleStringProperty fConstantIdWorkProperty() {
        return fConstantIdWork;
    }

    public void setfConstantIdWork(String fConstantIdWork) {
        this.fConstantIdWork.set(fConstantIdWork);
    }

    public String getfConstantMethodicalWork() {
        return fConstantMethodicalWork.get();
    }

    public SimpleStringProperty fConstantMethodicalWorkProperty() {
        return fConstantMethodicalWork;
    }

    public void setfConstantMethodicalWork(String fConstantMethodicalWork) {
        this.fConstantMethodicalWork.set(fConstantMethodicalWork);
    }

    public String getfConstantSinceWork() {
        return fConstantSinceWork.get();
    }

    public SimpleStringProperty fConstantSinceWorkProperty() {
        return fConstantSinceWork;
    }

    public void setfConstantSinceWork(String fConstantSinceWork) {
        this.fConstantSinceWork.set(fConstantSinceWork);
    }

    public String getfConstantMatBase() {
        return fConstantMatBase.get();
    }

    public SimpleStringProperty fConstantMatBaseProperty() {
        return fConstantMatBase;
    }

    public void setfConstantMatBase(String fConstantMatBase) {
        this.fConstantMatBase.set(fConstantMatBase);
    }

    public String getfConstantVSandVPVO() {
        return fConstantVSandVPVO.get();
    }

    public SimpleStringProperty fConstantVSandVPVOProperty() {
        return fConstantVSandVPVO;
    }

    public void setfConstantVSandVPVO(String fConstantVSandVPVO) {
        this.fConstantVSandVPVO.set(fConstantVSandVPVO);
    }

    public String getfConstantSMR() {
        return fConstantSMR.get();
    }

    public SimpleStringProperty fConstantSMRProperty() {
        return fConstantSMR;
    }

    public void setfConstantSMR(String fConstantSMR) {
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
