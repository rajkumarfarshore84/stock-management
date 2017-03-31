/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rajkumar Ramasamy
 */
@Entity
@Table(name = "company_margin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompanyMargin.findAll", query = "SELECT c FROM CompanyMargin c")
    , @NamedQuery(name = "CompanyMargin.findById", query = "SELECT c FROM CompanyMargin c WHERE c.id = :id")
    , @NamedQuery(name = "CompanyMargin.findByLabor", query = "SELECT c FROM CompanyMargin c WHERE c.labor = :labor")
    , @NamedQuery(name = "CompanyMargin.findByTransport", query = "SELECT c FROM CompanyMargin c WHERE c.transport = :transport")
    , @NamedQuery(name = "CompanyMargin.findByEb", query = "SELECT c FROM CompanyMargin c WHERE c.eb = :eb")
    , @NamedQuery(name = "CompanyMargin.findByMargin", query = "SELECT c FROM CompanyMargin c WHERE c.margin = :margin")
    , @NamedQuery(name = "CompanyMargin.findByRmetrialPrice", query = "SELECT c FROM CompanyMargin c WHERE c.rmetrialPrice = :rmetrialPrice")
    , @NamedQuery(name = "CompanyMargin.findByMetrialWeight", query = "SELECT c FROM CompanyMargin c WHERE c.metrialWeight = :metrialWeight")
    , @NamedQuery(name = "CompanyMargin.findByTaxAmount", query = "SELECT c FROM CompanyMargin c WHERE c.taxAmount = :taxAmount")
    , @NamedQuery(name = "CompanyMargin.findByCreatedAt", query = "SELECT c FROM CompanyMargin c WHERE c.createdAt = :createdAt")})
public class CompanyMargin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "labor", precision = 22)
    private Double labor;
    @Column(name = "transport", precision = 22)
    private Double transport;
    @Column(name = "eb", precision = 22)
    private Double eb;
    @Column(name = "margin", precision = 22)
    private Double margin;
    @Column(name = "rmetrial_price", precision = 22)
    private Double rmetrialPrice;
    @Column(name = "metrial_weight")
    private Integer metrialWeight;
    @Column(name = "tax_amount", precision = 22)
    private Double taxAmount;
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt = new Date();
    @JoinColumn(name = "tax_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Tax taxId;
    @JoinColumn(name = "manuf_id", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    private ManufacturedStocks manufId;

    public CompanyMargin() {
    }

    public CompanyMargin(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLabor() {
        return labor;
    }

    public void setLabor(Double labor) {
        this.labor = labor;
    }

    public Double getTransport() {
        return transport;
    }

    public void setTransport(Double transport) {
        this.transport = transport;
    }

    public Double getEb() {
        return eb;
    }

    public void setEb(Double eb) {
        this.eb = eb;
    }

    public Double getMargin() {
        return margin;
    }

    public void setMargin(Double margin) {
        this.margin = margin;
    }

    public Double getRmetrialPrice() {
        return rmetrialPrice;
    }

    public void setRmetrialPrice(Double rmetrialPrice) {
        this.rmetrialPrice = rmetrialPrice;
    }

    public Integer getMetrialWeight() {
        return metrialWeight;
    }

    public void setMetrialWeight(Integer metrialWeight) {
        this.metrialWeight = metrialWeight;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Tax getTaxId() {
        return taxId;
    }

    public void setTaxId(Tax taxId) {
        this.taxId = taxId;
    }

    public ManufacturedStocks getManufId() {
        return manufId;
    }

    public void setManufId(ManufacturedStocks manufId) {
        this.manufId = manufId;
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
        if (!(object instanceof CompanyMargin)) {
            return false;
        }
        CompanyMargin other = (CompanyMargin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rajtech.stockwebservice.model.CompanyMargin[ id=" + id + " ]";
    }
    
}
