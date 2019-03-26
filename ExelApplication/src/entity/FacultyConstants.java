package entity;

import javafx.beans.property.SimpleDoubleProperty;

public class FacultyConstants {
    private SimpleDoubleProperty fConstantSchoolWork;
    private SimpleDoubleProperty fConstantMethodicalWork;
    private SimpleDoubleProperty fConstantIdWork;
    private SimpleDoubleProperty fConstantSinceWork;
    private SimpleDoubleProperty fConstantMatBase;
    private SimpleDoubleProperty fConstantVSandVPVO;
    private SimpleDoubleProperty fConstantSMR;

    public FacultyConstants(double fConstantSchoolWork,
                            double fConstantMethodicalWork,
                            double fConstantIdWork,
                            double fConstantSinceWork,
                            double fConstantMatBase,
                            double fConstantVSandVPVO,
                            double fConstantSMP) {
        this.fConstantSchoolWork = new SimpleDoubleProperty(fConstantSchoolWork);
        this.fConstantMethodicalWork
                = new SimpleDoubleProperty(fConstantMethodicalWork);
        this.fConstantIdWork = new SimpleDoubleProperty(fConstantIdWork);
        this.fConstantSinceWork = new SimpleDoubleProperty(fConstantSinceWork);
        this.fConstantMatBase = new SimpleDoubleProperty(fConstantMatBase);
        this.fConstantVSandVPVO = new SimpleDoubleProperty(fConstantVSandVPVO);
        this.fConstantSMR = new SimpleDoubleProperty(fConstantSMP);
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

    public double getfConstantIdWork() {
        return fConstantIdWork.get();
    }

    public SimpleDoubleProperty fConstantIdWorkProperty() {
        return fConstantIdWork;
    }

    public void setfConstantIdWork(double fConstantIdWork) {
        this.fConstantIdWork.set(fConstantIdWork);
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

    public double getfConstantMethodicalWork() {
        return fConstantMethodicalWork.get();
    }

    public SimpleDoubleProperty fConstantMethodicalWorkProperty() {
        return fConstantMethodicalWork;
    }

    public void setfConstantMethodicalWork(double fConstantMethodicalWork) {
        this.fConstantMethodicalWork.set(fConstantMethodicalWork);
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
}
