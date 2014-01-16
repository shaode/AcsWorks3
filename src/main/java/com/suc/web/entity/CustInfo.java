package com.suc.web.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CustInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "cust_project", nullable = false, length = 30)
    private String            custProject;
    @Column(name = "cust_name", length = 100)
    private String            custName;
    @Column(name = "cust_contactor", length = 100)
    private String            custContactor;
    @Column(name = "cust_info", length = 100)
    private String            custInfo;

    public CustInfo() {
    }

    public CustInfo(String custProject, String custName, String custContactor, String custInfo) {
        this.custProject = custProject;
        this.custName = custName;
        this.custContactor = custContactor;
        this.custInfo = custInfo;
    }

    public String getCustContactor() {
        return this.custContactor;
    }

    public void setCustContactor(String custContactor) {
        this.custContactor = custContactor;
    }

    public String getCustInfo() {
        return this.custInfo;
    }

    public void setCustInfo(String custInfo) {
        this.custInfo = custInfo;
    }

    public String getCustName() {
        return this.custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustProject() {
        return this.custProject;
    }

    public void setCustProject(String custProject) {
        this.custProject = custProject;
    }

    @Override
    public String toString() {
        return "CustInfo:" + this.hashCode() + " [custProject=" + custProject + ", custName=" + custName + ", custContactor=" + custContactor + ", custInfo=" + custInfo + "]";
    }
}
