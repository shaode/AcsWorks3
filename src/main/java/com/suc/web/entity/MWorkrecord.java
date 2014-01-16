package com.suc.web.entity;

import javax.persistence.*;
import com.suc.web.entity.common.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "m_workrecord")
@NamedQueries({ @NamedQuery(name = "MWorkrecord.findAll", query = "SELECT m FROM MWorkrecord m"),
        @NamedQuery(name = "MWorkrecord.findByDate", query = "select o from MWorkrecord o" + " where o.workDate >= ?1 and o.workDate<=?2"),
        @NamedQuery(name = "MWorkrecord.findByCustname", query = "select o from MWorkrecord o" + " where o.custInfo.custName = ?1"),
        @NamedQuery(name = "MWorkrecord.findByCustproject", query = "select o from MWorkrecord o" + " where o.custInfo.custProject = ?1") })
public class MWorkrecord extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @Column(length = 255)
    private String            comments;
    @Embedded
    private CustInfo          custInfo;
    @Column(name = "ot_hours", precision = 10, scale = 2)
    private BigDecimal        otHours;
    @Column(name = "total_hours", precision = 10, scale = 2)
    private BigDecimal        totalHours;
    @Column(name = "work_content", length = 255)
    private String            workContent;
    @Temporal(TemporalType.DATE)
    @Column(name = "work_date", nullable = false)
    private Date              workDate;
    @Column(name = "work_end")
    private String              workEnd;
    @Column(name = "work_hours", precision = 10, scale = 2)
    private BigDecimal        workHours;
    @Column(name = "work_start")
    private String              workStart;
    @ManyToOne
    @JoinColumn(name = "ottype_id")
    private MOttype           mOttype;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private MUser             mUser;

    public MWorkrecord() {
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public CustInfo getCustInfo() {
        return custInfo;
    }

    public void setCustInfo(CustInfo custInfo) {
        this.custInfo = custInfo;
    }

    public BigDecimal getOtHours() {
        return this.otHours;
    }

    public void setOtHours(BigDecimal otHours) {
        this.otHours = otHours;
    }

    public BigDecimal getTotalHours() {
        return this.totalHours;
    }

    public void setTotalHours(BigDecimal totalHours) {
        this.totalHours = totalHours;
    }

    public String getWorkContent() {
        return this.workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    // @JsonSerialize(using = CustomDateSerializer.class)
    public Date getWorkDate() {
        return this.workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public String getWorkEnd() {
        return this.workEnd;
    }

    public void setWorkEnd(String workEnd) {
        this.workEnd = workEnd;
    }

    public BigDecimal getWorkHours() {
        return this.workHours;
    }

    public void setWorkHours(BigDecimal workHours) {
        this.workHours = workHours;
    }

    public String getWorkStart() {
        return this.workStart;
    }

    public void setWorkStart(String workStart) {
        this.workStart = workStart;
    }

    public MOttype getMOttype() {
        return this.mOttype;
    }

    public void setMOttype(MOttype mOttype) {
        this.mOttype = mOttype;
    }

    public MUser getMUser() {
        return this.mUser;
    }

    public void setMUser(MUser mUser) {
        this.mUser = mUser;
    }

    @Override
    public String toString() {
        return "MWorkrecord [comments=" + comments + ", custInfo=" + custInfo + ", otHours=" + otHours + ", totalHours=" + totalHours + ", workContent=" + workContent + ", workDate=" + workDate + ", workEnd=" + workEnd + ", workHours=" + workHours + ", workStart=" + workStart + ", mOttype=" + mOttype + ", mUser=" + mUser + ", toString()=" + super.toString() + "]";
    }
}