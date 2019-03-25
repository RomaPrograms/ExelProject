package entity;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Faculty {
    private SimpleStringProperty fName;
    private SimpleDoubleProperty fStudyWork;
    private SimpleDoubleProperty fMethodicalWork;
    private SimpleDoubleProperty fIdWork;
    private SimpleDoubleProperty fSinceWork;
    private SimpleDoubleProperty fMatBase;
    private SimpleDoubleProperty fVSANDVPVO;
    private SimpleDoubleProperty fSMR;
    private SimpleDoubleProperty fRate;

    public Faculty(String fName, double fStudyWork, double fMethodicalWork,
                   double fIdWork, double fSinceWork, double fMatBase,
                   double fVSANDVPVO, double fSMR, double fRate) {
        this.fName = new SimpleStringProperty(fName);
        this.fStudyWork = new SimpleDoubleProperty(fStudyWork);
        this.fMethodicalWork = new SimpleDoubleProperty(fMethodicalWork);
        this.fIdWork = new SimpleDoubleProperty(fIdWork);
        this.fSinceWork = new SimpleDoubleProperty(fSinceWork);
        this.fMatBase = new SimpleDoubleProperty(fMatBase);
        this.fVSANDVPVO = new SimpleDoubleProperty(fVSANDVPVO);
        this.fSMR = new SimpleDoubleProperty(fSMR);
        this.fRate = new SimpleDoubleProperty(fRate);
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

    public double getfStudyWork() {
        return fStudyWork.get();
    }

    public SimpleDoubleProperty fStudyWorkProperty() {
        return fStudyWork;
    }

    public void setfStudyWork(double fStudyWork) {
        this.fStudyWork.set(fStudyWork);
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

    public double getfIdWork() {
        return fIdWork.get();
    }

    public SimpleDoubleProperty fIdWorkProperty() {
        return fIdWork;
    }

    public void setfIdWork(double fIdWork) {
        this.fIdWork.set(fIdWork);
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

    public double getfVSANDVPVO() {
        return fVSANDVPVO.get();
    }

    public SimpleDoubleProperty fVSANDVPVOProperty() {
        return fVSANDVPVO;
    }

    public void setfVSANDVPVO(double fVSANDVPVO) {
        this.fVSANDVPVO.set(fVSANDVPVO);
    }

    public double getfSMR() {
        return fSMR.get();
    }

    public SimpleDoubleProperty fSMRProperty() {
        return fSMR;
    }

    public void setfSMR(double fSMR) {
        this.fSMR.set(fSMR);
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
