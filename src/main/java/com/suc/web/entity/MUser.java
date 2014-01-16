package com.suc.web.entity;

import javax.persistence.*;
import com.suc.web.entity.common.BaseEntity;

@Entity
@Table(name = "m_user")
@NamedQueries({ @NamedQuery(name = "MUser.findAll", query = "SELECT m FROM MUser m"),
        @NamedQuery(name = "MUser.findByName", query = "select o from MUser o" + " where o.name = ?1"),
        @NamedQuery(name = "MUser.findByMail", query = "select o from MUser o" + " where o.mail = ?1"),
        @NamedQuery(name = "MUser.findByNamePassword", query = "select o from MUser o" + " where o.name = ?1 and o.password=?2") })
public class MUser extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private String            comments;
    private String            mail;
    private String            name;
    private String            password;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dept_id")
    private MDept             mDept;

    public MUser() {
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MDept getMDept() {
        return this.mDept;
    }

    public void setMDept(MDept mDept) {
        this.mDept = mDept;
    }

    @Override
    public String toString() {
        return "MUser [comments=" + comments + ", mail=" + mail + ", name=" + name + ", password=" + password + ", mDept=" + mDept + ", toString()=" + super.toString() + "]";
    }
}