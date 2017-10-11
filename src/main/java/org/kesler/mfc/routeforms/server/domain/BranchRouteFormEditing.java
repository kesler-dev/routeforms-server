package org.kesler.mfc.routeforms.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class BranchRouteFormEditing extends AbstractEntity {

    @Column
    private String employeeFio;
    @Column
    private LocalDateTime dateTime;

    public String getEmployeeFio() { return employeeFio; }
    public void setEmployeeFio(String employeeFio) { this.employeeFio = employeeFio; }

    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }


}
