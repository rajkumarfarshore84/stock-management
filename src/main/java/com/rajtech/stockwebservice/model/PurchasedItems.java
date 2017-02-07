/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author node
 */
@Entity
@Table(name = "purchased_items")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchasedItems.findAll", query = "SELECT p FROM PurchasedItems p")
    , @NamedQuery(name = "PurchasedItems.findByItemId", query = "SELECT p FROM PurchasedItems p WHERE p.itemId = :itemId")
    , @NamedQuery(name = "PurchasedItems.findByBaseAmount", query = "SELECT p FROM PurchasedItems p WHERE p.baseAmount = :baseAmount")
    , @NamedQuery(name = "PurchasedItems.findByPruchasedQty", query = "SELECT p FROM PurchasedItems p WHERE p.pruchasedQty = :pruchasedQty")
    , @NamedQuery(name = "PurchasedItems.findByBatchId", query = "SELECT p FROM PurchasedItems p WHERE p.batchId = :batchId")
    , @NamedQuery(name = "PurchasedItems.findByBaseTaxAmount", query = "SELECT p FROM PurchasedItems p WHERE p.baseTaxAmount = :baseTaxAmount")
    , @NamedQuery(name = "PurchasedItems.findByBaseDiscount", query = "SELECT p FROM PurchasedItems p WHERE p.baseDiscount = :baseDiscount")
    , @NamedQuery(name = "PurchasedItems.findByCreatedAt", query = "SELECT p FROM PurchasedItems p WHERE p.createdAt = :createdAt")
    , @NamedQuery(name = "PurchasedItems.findByUpdatedAt", query = "SELECT p FROM PurchasedItems p WHERE p.updatedAt = :updatedAt")})
public class PurchasedItems implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "row_total")
    private BigDecimal rowTotal;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "qty_in")
    private String qtyIn;

    @Column(name = "pruchased_qty")
    private BigDecimal pruchasedQty;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "item_id")
    private Integer itemId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "base_amount")
    private BigDecimal baseAmount;
    @Size(max = 45)
    @Column(name = "batch_id")
    private String batchId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "base_tax_amount")
    private BigDecimal baseTaxAmount = BigDecimal.valueOf(0);
    @Basic(optional = false)
    @NotNull
    @Column(name = "base_discount")
    private BigDecimal baseDiscount = BigDecimal.valueOf(0);
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "purchase_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PurchaseMaster purchaseId;
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Products productId;

    public PurchasedItems() {
    }

    public PurchasedItems(Integer itemId) {
        this.itemId = itemId;
    }

    public PurchasedItems(Integer itemId, BigDecimal baseAmount, BigDecimal baseTaxAmount, BigDecimal baseDiscount) {
        this.itemId = itemId;
        this.baseAmount = baseAmount;
        this.baseTaxAmount = baseTaxAmount;
        this.baseDiscount = baseDiscount;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(BigDecimal baseAmount) {
        this.baseAmount = baseAmount;
    }

    public BigDecimal getPruchasedQty() {
        return pruchasedQty;
    }

    public void setPruchasedQty(BigDecimal pruchasedQty) {
        this.pruchasedQty = pruchasedQty;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public BigDecimal getBaseTaxAmount() {
        return baseTaxAmount;
    }

    public void setBaseTaxAmount(BigDecimal baseTaxAmount) {
        this.baseTaxAmount = baseTaxAmount;
    }

    public BigDecimal getBaseDiscount() {
        return baseDiscount;
    }

    public void setBaseDiscount(BigDecimal baseDiscount) {
        this.baseDiscount = baseDiscount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public PurchaseMaster getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(PurchaseMaster purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Products getProductId() {
        return productId;
    }

    public void setProductId(Products productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemId != null ? itemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchasedItems)) {
            return false;
        }
        PurchasedItems other = (PurchasedItems) object;
        if ((this.itemId == null && other.itemId != null) || (this.itemId != null && !this.itemId.equals(other.itemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rajtech.stockwebservice.model.PurchasedItems[ itemId=" + itemId + " ]";
    }

    public String getQtyIn() {
        return qtyIn;
    }

    public void setQtyIn(String qtyIn) {
        this.qtyIn = qtyIn;
    }

    public BigDecimal getRowTotal() {
        return rowTotal;
    }

    public void setRowTotal(BigDecimal rowTotal) {
        this.rowTotal = rowTotal;
    }
    
}
