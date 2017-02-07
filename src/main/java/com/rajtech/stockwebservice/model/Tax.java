/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rajkumar Ramasamy
 */
@Entity
@Table(name = "tax", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"states_id", "type"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tax.findAll", query = "SELECT t FROM Tax t")
    , @NamedQuery(name = "Tax.findById", query = "SELECT t FROM Tax t WHERE t.id = :id")
    , @NamedQuery(name = "Tax.findByValue", query = "SELECT t FROM Tax t WHERE t.value = :value")
    , @NamedQuery(name = "Tax.findByType", query = "SELECT t FROM Tax t WHERE t.type = :type")})
public class Tax implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "value")
    private Integer value;
    @Size(max = 45)
    @Column(name = "type", length = 45)
    private String type;
    @JoinColumn(name = "states_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private States statesId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taxId")
    private List<CompanyMargin> companyMarginList;

    public Tax() {
    }

    public Tax(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public States getStatesId() {
        return statesId;
    }

    public void setStatesId(States statesId) {
        this.statesId = statesId;
    }

    @XmlTransient
    public List<CompanyMargin> getCompanyMarginList() {
        return companyMarginList;
    }

    public void setCompanyMarginList(List<CompanyMargin> companyMarginList) {
        this.companyMarginList = companyMarginList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tax)) {
            return false;
        }
        Tax other = (Tax) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rajtech.stockwebservice.model.Tax[ id=" + id + " ]";
    }
    
}
