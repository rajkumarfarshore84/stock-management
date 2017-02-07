/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author node
 */
@Entity
@Table(name = "purchase_master")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchaseMaster.findAll", query = "SELECT p FROM PurchaseMaster p")
    , @NamedQuery(name = "PurchaseMaster.findById", query = "SELECT p FROM PurchaseMaster p WHERE p.id = :id")
    , @NamedQuery(name = "PurchaseMaster.findByPurchasedDate", query = "SELECT p FROM PurchaseMaster p WHERE p.purchasedDate = :purchasedDate")
    , @NamedQuery(name = "PurchaseMaster.findByBaseSubtotal", query = "SELECT p FROM PurchaseMaster p WHERE p.baseSubtotal = :baseSubtotal")
    , @NamedQuery(name = "PurchaseMaster.findByBaseTaxAmount", query = "SELECT p FROM PurchaseMaster p WHERE p.baseTaxAmount = :baseTaxAmount")
    , @NamedQuery(name = "PurchaseMaster.findByBaseShippingAmount", query = "SELECT p FROM PurchaseMaster p WHERE p.baseShippingAmount = :baseShippingAmount")
    , @NamedQuery(name = "PurchaseMaster.findByBaseDiscount", query = "SELECT p FROM PurchaseMaster p WHERE p.baseDiscount = :baseDiscount")
    , @NamedQuery(name = "PurchaseMaster.findByBaseTotal", query = "SELECT p FROM PurchaseMaster p WHERE p.baseTotal = :baseTotal")
    , @NamedQuery(name = "PurchaseMaster.findByCreatedAt", query = "SELECT p FROM PurchaseMaster p WHERE p.createdAt = :createdAt")
    , @NamedQuery(name = "PurchaseMaster.findByUpdatedAt", query = "SELECT p FROM PurchaseMaster p WHERE p.updatedAt = :updatedAt")})
public class PurchaseMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "purchased_date")
    @Temporal(TemporalType.DATE)
    private Date purchasedDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "base_subtotal")
    private BigDecimal baseSubtotal;
    @Column(name = "base_tax_amount")
    private BigDecimal baseTaxAmount;
    @Column(name = "base_shipping_amount")
    private BigDecimal baseShippingAmount;
    @Column(name = "base_discount")
    private BigDecimal baseDiscount;
    @Column(name = "base_total")
    private BigDecimal baseTotal;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Size(max = 45)
    @Column(name = "updated_at")
    private String updatedAt;
    @JoinColumn(name = "vendor_id", referencedColumnName = "vendor_id")
    @ManyToOne
    private Vendors vendorId;
    
    @JoinColumn(name = "order_against_id", referencedColumnName = "entity_id")
    @ManyToOne(optional = false)
    private OrderMaster orderAgainstId;
    
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchaseId")
    private List<PurchasedItems> purchasedItemsList;
    
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "purchaseId")
    private Set<PurchasedItems> purchasedItemsListSave;

    public PurchaseMaster() {
    }

    public PurchaseMaster(Integer id) {
        this.id = id;
    }

    public PurchaseMaster(Integer id, BigDecimal baseSubtotal) {
        this.id = id;
        this.baseSubtotal = baseSubtotal;
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

    public BigDecimal getBaseSubtotal() {
        return baseSubtotal;
    }

    public void setBaseSubtotal(BigDecimal baseSubtotal) {
        this.baseSubtotal = baseSubtotal;
    }

    public BigDecimal getBaseTaxAmount() {
        return baseTaxAmount;
    }

    public void setBaseTaxAmount(BigDecimal baseTaxAmount) {
        this.baseTaxAmount = baseTaxAmount;
    }

    public BigDecimal getBaseShippingAmount() {
        return baseShippingAmount;
    }

    public void setBaseShippingAmount(BigDecimal baseShippingAmount) {
        this.baseShippingAmount = baseShippingAmount;
    }

    public BigDecimal getBaseDiscount() {
        return baseDiscount;
    }

    public void setBaseDiscount(BigDecimal baseDiscount) {
        this.baseDiscount = baseDiscount;
    }

    public BigDecimal getBaseTotal() {
        return baseTotal;
    }

    public void setBaseTotal(BigDecimal baseTotal) {
        this.baseTotal = baseTotal;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Vendors getVendorId() {
        return vendorId;
    }

    public void setVendorId(Vendors vendorId) {
        this.vendorId = vendorId;
    }

    @XmlTransient
    public OrderMaster getOrderAgainstId() {
        return orderAgainstId;
    }

    public void setOrderAgainstId(OrderMaster orderAgainstId) {
        this.orderAgainstId = orderAgainstId;
    }

    @XmlTransient
    public List<PurchasedItems> getPurchasedItemsList() {
        return purchasedItemsList;
    }

    public void setPurchasedItemsList(List<PurchasedItems> purchasedItemsList) {
        this.purchasedItemsList = purchasedItemsList;
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
        if (!(object instanceof PurchaseMaster)) {
            return false;
        }
        PurchaseMaster other = (PurchaseMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rajtech.stockwebservice.model.PurchaseMaster[ id=" + id + " ]";
    }
    
    public void addItem(PurchasedItems item){
        if(this.purchasedItemsListSave == null){
            this.purchasedItemsListSave = new HashSet<PurchasedItems>();
        }
        item.setPurchaseId(this);
        item.setProductId(item.getProductId());
        this.purchasedItemsListSave.add(item);
    }

    public Set<PurchasedItems> getPurchasedItemsListSave() {
        return purchasedItemsListSave;
    }

    public void setPurchasedItemsListSave(Set<PurchasedItems> purchasedItemsListSave) {
        this.purchasedItemsListSave = purchasedItemsListSave;
    }
    
}
