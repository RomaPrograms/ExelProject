package entity;

import javafx.beans.property.SimpleDoubleProperty;

public class FacultyConstants {
    private SimpleDoubleProperty fConstantStudyWork;
    private SimpleDoubleProperty fConstantMethodicalWork;
    private SimpleDoubleProperty fConstantSinceWork;
    private SimpleDoubleProperty fConstantMatBase;

    public FacultyConstants(double fConstantStudyWork,
                            double fConstantMethodicalWork,
                            double fConstantSinceWork,
                            double fConstantMatBase) {
        this.fConstantStudyWork = new SimpleDoubleProperty(fConstantStudyWork);
        this.fConstantMethodicalWork
                = new SimpleDoubleProperty(fConstantMethodicalWork);
        this.fConstantSinceWork = new SimpleDoubleProperty(fConstantSinceWork);
        this.fConstantMatBase = new SimpleDoubleProperty(fConstantMatBase);
    }

    public double getfConstantStudyWork() {
        return fConstantStudyWork.get();
    }

    public SimpleDoubleProperty fConstantStudyWorkProperty() {
        return fConstantStudyWork;
    }

    public void setfConstantStudyWork(double fConstantStudyWork) {
        this.fConstantStudyWork.set(fConstantStudyWork);
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
