package org.kesler.mfc.routeforms.server.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Водитель
 */
@Entity
public class Driver extends AbstractEntity {

    @Column(name = "FIOFull")
    protected String fioFull;
    @Column(name = "FIOShort")
    protected String fioShort;
    @Column(name = "DriverLicense", length = 31)
    protected String driverLicense;
    @Column(name = "DriverCategory", length = 31)
    protected String driverCategory;
    @Column(name = "TabelNum", length = 31)
    protected String tabelNum;
    @ManyToOne
    @JoinColumn(name = "BranchID")
    protected Branch branch;
    @Column(name = "Active")
    protected Boolean active = true;


    public String getFioFull() { return fioFull; }
    public void setFioFull(String fioFull) { this.fioFull = fioFull; }

    public String getFioShort() { return fioShort; }
    public void setFioShort(String fioShort) { this.fioShort = fioShort; }

    public String getDriverLicense() { return driverLicense; }
    public void setDriverLicense(String driverLicense) { this.driverLicense = driverLicense; }

    public String getDriverCategory() { return driverCategory; }
    public void setDriverCategory(String driverCategory) { this.driverCategory = driverCategory; }

    public String getTabelNum() { return tabelNum; }
    public void setTabelNum(String tabelNum) { this.tabelNum = tabelNum; }

    public Branch getBranch() { return branch; }
    public void setBranch(Branch branch) { this.branch = branch; }

    public Boolean isActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    @Override
    public String toString() {
        return fioShort;
    }
}
