package bank.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee {
    public StringProperty EMPNO;
    public StringProperty FIRSTNME;
    public StringProperty DININIT;
    public StringProperty LASTNAME;
    public String EEMPNO;
    public String EFIRSTNME;
    public String EDININIT;
    public String ELASTNAME;

     public Employee(){
         EMPNO = null;
         FIRSTNME = null;
         DININIT = null;
         LASTNAME = null;
     }

     public Employee(String EMPNO,String FIRSTNME,String DININIT,String LASTNAME){
         this.EMPNO =  new SimpleStringProperty(EMPNO);
         this.FIRSTNME = new SimpleStringProperty(FIRSTNME);
         this.DININIT = new SimpleStringProperty(DININIT);
         this.LASTNAME = new SimpleStringProperty(LASTNAME);
         EEMPNO = EMPNO;
         EFIRSTNME = FIRSTNME;
         EDININIT = DININIT;
         ELASTNAME = LASTNAME;
     }

    public String getEMPNO() {
        return EEMPNO;
    }

    public void setEMPNO(String EMPNO) {
        this.EMPNO = new SimpleStringProperty(EMPNO);
        EEMPNO = EMPNO;
    }

    public StringProperty EMPNOProperty(){
         return EMPNO;
    }

    public java.lang.String getFIRSTNME() {
        return EFIRSTNME;
    }

    public void setFIRSTNME(String FIRSTNME) {
        this.FIRSTNME = new SimpleStringProperty(FIRSTNME);
        EFIRSTNME = FIRSTNME;
    }

    public StringProperty FIRSTNMEProperty(){
        return FIRSTNME;
    }

    public String getDININIT() {
        return EDININIT;
    }

    public void setDININIT(String DININIT) {
        this.DININIT = new SimpleStringProperty(DININIT);
        EDININIT = DININIT;
    }
    public StringProperty DININITProperty(){
        return DININIT;
    }

    public String getLASTNAME() {
        return ELASTNAME;
    }

    public void setLASTNAME(String LASTNAME) {
        this.LASTNAME = new SimpleStringProperty(LASTNAME);
        ELASTNAME = LASTNAME;
    }

    public StringProperty LASTNAMEProperty(){
        return LASTNAME;
    }
}
