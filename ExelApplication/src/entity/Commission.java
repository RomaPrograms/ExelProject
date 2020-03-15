package entity;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Commission {
    private String chairUnivName;
    private int chairYear;
    private SimpleStringProperty fConstantSchoolWork;
    private SimpleStringProperty fConstantMethodicalWork;
    private SimpleStringProperty fConstantIdWork;
    private SimpleStringProperty fConstantSinceWork;
    private SimpleStringProperty fConstantMatBase;
    private SimpleStringProperty fConstantVSandVPVO;
    private SimpleStringProperty fConstantSMR;

    public Commission() {
        this.fConstantSchoolWork = new SimpleStringProperty();
        this.fConstantMethodicalWork
                = new SimpleStringProperty();
        this.fConstantIdWork = new SimpleStringProperty();
        this.fConstantSinceWork = new SimpleStringProperty();
        this.fConstantMatBase = new SimpleStringProperty();
        this.fConstantVSandVPVO = new SimpleStringProperty();
        this.fConstantSMR = new SimpleStringProperty();
    }

    public Commission(String chairUnivName,
                      int chairYear,
                      String fConstantSchoolWork,
                      String fConstantMethodicalWork,
                      String fConstantIdWork,
                      String fConstantSinceWork,
                      String fConstantMatBase,
                      String fConstantVSandVPVO,
                      String fConstantSMP) {
        this.chairUnivName = chairUnivName;
        this.chairYear = chairYear;
        this.fConstantSchoolWork = new SimpleStringProperty(fConstantSchoolWork);
        this.fConstantMethodicalWork
                = new SimpleStringProperty(fConstantMethodicalWork);
        this.fConstantIdWork = new SimpleStringProperty(fConstantIdWork);
        this.fConstantSinceWork = new SimpleStringProperty(fConstantSinceWork);
        this.fConstantMatBase = new SimpleStringProperty(fConstantMatBase);
        this.fConstantVSandVPVO = new SimpleStringProperty(fConstantVSandVPVO);
        this.fConstantSMR = new SimpleStringProperty(fConstantSMP);
    }

    public String getChairUnivName() {
        return chairUnivName;
    }

    public void setChairUnivName(String chairUnivName) {
        this.chairUnivName = chairUnivName;
    }

    public int getChairYear() {
        return chairYear;
    }

    public void setChairYear(int chairYear) {
        this.chairYear = chairYear;
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

    public String getfConstantMethodicalWork() {
        return fConstantMethodicalWork.get();
    }

    public SimpleStringProperty fConstantMethodicalWorkProperty() {
        return fConstantMethodicalWork;
    }

    public void setfConstantMethodicalWork(String fConstantMethodicalWork) {
        this.fConstantMethodicalWork.set(fConstantMethodicalWork);
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
}
