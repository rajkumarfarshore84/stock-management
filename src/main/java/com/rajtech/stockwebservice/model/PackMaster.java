/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rajkumar Ramasamy
 */
@Entity
@Table(name = "pack_master")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PackMaster.findAll", query = "SELECT p FROM PackMaster p")
    , @NamedQuery(name = "PackMaster.findById", query = "SELECT p FROM PackMaster p WHERE p.id = :id")
    , @NamedQuery(name = "PackMaster.findByPurchasedDate", query = "SELECT p FROM PackMaster p WHERE p.purchasedDate = :purchasedDate")
    , @NamedQuery(name = "PackMaster.findByTax", query = "SELECT p FROM PackMaster p WHERE p.tax = :tax")
    , @NamedQuery(name = "PackMaster.findByGrandTotal", query = "SELECT p FROM PackMaster p WHERE p.grandTotal = :grandTotal")
    , @NamedQuery(name = "PackMaster.findByBalance", query = "SELECT p FROM PackMaster p WHERE p.balance = :balance")})
public class PackMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "purchased_date")
    @Temporal(TemporalType.DATE)
    private Date purchasedDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "tax", precision = 22)
    private Double tax;
    @Column(name = "grand_total", precision = 22)
    private Double grandTotal;
    @Column(name = "balance", precision = 22)
    private Double balance;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "packMasterId")
    private List<PackItem> packItemList;
    @JoinColumn(name = "vendor_id", referencedColumnName = "vendor_id", nullable = false)
    @ManyToOne(optional = false)
    private Vendors vendorId;

    public PackMaster() {
    }

    public PackMaster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(Date purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @XmlTransient
    public List<PackItem> getPackItemList() {
        return packItemList;
    }

    public void setPackItemList(List<PackItem> packItemList) {
        this.packItemList = packItemList;
    }

    public Vendors getVendorId() {
        return vendorId;
    }

    public void setVendorId(Vendors vendorId) {
        this.vendorId = vendorId;
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
        if (!(object instanceof PackMaster)) {
            return false;
        }
        PackMaster other = (PackMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rajtech.stockwebservice.model.PackMaster[ id=" + id + " ]";
    }
    
}
