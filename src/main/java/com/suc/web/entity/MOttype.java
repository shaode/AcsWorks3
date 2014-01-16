package com.suc.web.entity;

import javax.persistence.*;
import com.suc.web.entity.common.BaseEntity;
import java.math.BigDecimal;

@Entity
@Table(name = "m_ottype")
@NamedQueries({ @NamedQuery(name = "MOttype.findAll", query = "SELECT m FROM MOttype m"),
        @NamedQuery(name = "MOttype.findByName", query = "select o from MOttype o" + " where o.name = ?1"),
        @NamedQuery(name = "MOttype.findAllNames", query = "select distinct o.name from MOttype o" + " order by o.name") })
public class MOttype extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private String            comments;
    private BigDecimal        factor;
    private String            name;

    public MOttype() {
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public BigDecimal getFactor() {
        return this.factor;
    }

    public void setFactor(BigDecimal factor) {
        this.factor = factor;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MOttype [comments=" + comments + ", factor=" + factor + ", name=" + name + ", toString()=" + super.toString() + "]";
    }
}