package com.suc.web.entity;

import javax.persistence.*;
import com.suc.web.entity.common.BaseEntity;

@Entity
@Table(name = "m_dept")
@NamedQueries({ @NamedQuery(name = "MDept.findAll", query = "SELECT m FROM MDept m"),
        @NamedQuery(name = "MDept.findByCode", query = "select o from MDept o" + " where o.code = ?1"),
        @NamedQuery(name = "MDept.findByName", query = "select o from MDept o" + " where o.name = ?1") })
public class MDept extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private String            code;
    private String            name;

    public MDept() {
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MDept [code=" + code + ", name=" + name + ", toString()=" + super.toString() + "]";
    }
}