package org.kesler.mfc.routeforms.server.domain;


import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Настройки приложения
 */
@Entity
public class ApplicationOptions extends AbstractEntity {

    @Column(length = 127)
    private String orgName;

    @Column
    private Integer winterToSummerMonth;

    @Column
    private Integer winterToSummerDay;

    @Column
    private Integer summerToWinterMonth;

    @Column
    private Integer summerToWinterDay;

    @Column(length = 100000)
    private byte[] carForm;

    @Column(length = 100000)
    private byte[] truckForm;

    @Column(length = 100000)
    private byte[] branchReportForm;

    public String getOrgName() { return orgName; }
    public void setOrgName(String orgName) { this.orgName = orgName; }

    public Integer getWinterToSummerMonth() { return winterToSummerMonth; }
    public void setWinterToSummerMonth(Integer winterToSummerMonth) { this.winterToSummerMonth = winterToSummerMonth; }

    public Integer getWinterToSummerDay() { return winterToSummerDay; }
    public void setWinterToSummerDay(Integer winterToSummerDay) { this.winterToSummerDay = winterToSummerDay; }

    public Integer getSummerToWinterMonth() { return summerToWinterMonth; }
    public void setSummerToWinterMonth(Integer summerToWinterMonth) { this.summerToWinterMonth = summerToWinterMonth; }

    public Integer getSummerToWinterDay() { return summerToWinterDay; }
    public void setSummerToWinterDay(Integer summerToWinterDay) { this.summerToWinterDay = summerToWinterDay; }

    public byte[] getCarForm() { return carForm; }
    public void setCarForm(byte[] carForm) { this.carForm = carForm; }

    public byte[] getTruckForm() { return truckForm; }
    public void setTruckForm(byte[] truckForm) { this.truckForm = truckForm; }

    public byte[] getBranchReportForm() { return branchReportForm; }
    public void setBranchReportForm(byte[] branchReportForm) { this.branchReportForm = branchReportForm; }
}
