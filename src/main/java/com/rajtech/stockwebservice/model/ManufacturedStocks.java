/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rajkumar Ramasamy
 */
@Entity
@Table(name = "manufactured_stocks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ManufacturedStocks.findAll", query = "SELECT m FROM ManufacturedStocks m")
    , @NamedQuery(name = "ManufacturedStocks.findById", query = "SELECT m FROM ManufacturedStocks m WHERE m.id = :id")
    , @NamedQuery(name = "ManufacturedStocks.findByQty", query = "SELECT m FROM ManufacturedStocks m WHERE m.qty = :qty")
    , @NamedQuery(name = "ManufacturedStocks.findByWeightIn", query = "SELECT m FROM ManufacturedStocks m WHERE m.weightIn = :weightIn")
    , @NamedQuery(name = "ManufacturedStocks.findByBatchNo", query = "SELECT m FROM ManufacturedStocks m WHERE m.batchNo = :batchNo")
    , @NamedQuery(name = "ManufacturedStocks.findByRetailerMargin", query = "SELECT m FROM ManufacturedStocks m WHERE m.retailerMargin = :retailerMargin")
    , @NamedQuery(name = "ManufacturedStocks.findByMrp", query = "SELECT m FROM ManufacturedStocks m WHERE m.mrp = :mrp")
    , @NamedQuery(name = "ManufacturedStocks.findByCreatedAt", query = "SELECT m FROM ManufacturedStocks m WHERE m.createdAt = :createdAt")
    , @NamedQuery(name = "ManufacturedStocks.findByUpdatedAt", query = "SELECT m FROM ManufacturedStocks m WHERE m.updatedAt = :updatedAt")})
public class ManufacturedStocks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qty", nullable = false)
    private long qty;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "weight_in", nullable = false, length = 10)
    private String weightIn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "batch_no", nullable = false, length = 50)
    private String batchNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "retailer_margin", nullable = false)
    private int retailerMargin;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "mrp", nullable = false, precision = 12, scale = 4)
    private BigDecimal mrp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date updatedAt;
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Products productId;
    @JoinColumn(name = "sales_item_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private SalesItem salesItemId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manufacturedStocksId")
    private List<StockPacking> stockPackingList;
    @OneToMany(mappedBy = "manufId")
    private List<CompanyMargin> companyMarginList;

    public ManufacturedStocks() {
    }

    public ManufacturedStocks(Integer id) {
        this.id = id;
    }

    public ManufacturedStocks(Integer id, long qty, String weightIn, String batchNo, int retailerMargin, BigDecimal mrp, Date createdAt, Date updatedAt) {
        this.id = id;
        this.qty = qty;
        this.weightIn = weightIn;
        this.batchNo = batchNo;
        this.retailerMargin = retailerMargin;
        this.mrp = mrp;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getQty() {
        return qty;
    }

    public void setQty(long qty) {
        this.qty = qty;
    }

    public String getWeightIn() {
        return weightIn;
    }

    public void setWeightIn(String weightIn) {
        this.weightIn = weightIn;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public int getRetailerMargin() {
        return retailerMargin;
    }

    public void setRetailerMargin(int retailerMargin) {
        this.retailerMargin = retailerMargin;
    }

    public BigDecimal getMrp() {
        return mrp;
    }

    public void setMrp(BigDecimal mrp) {
        this.mrp = mrp;
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

    public Products getProductId() {
        return productId;
    }

    public void setProductId(Products productId) {
        this.productId = productId;
    }

    public SalesItem getSalesItemId() {
        return salesItemId;
    }

    public void setSalesItemId(SalesItem salesItemId) {
        this.salesItemId = salesItemId;
    }

    @XmlTransient
    public List<StockPacking> getStockPackingList() {
        return stockPackingList;
    }

    public void setStockPackingList(List<StockPacking> stockPackingList) {
        this.stockPackingList = stockPackingList;
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
        if (!(object instanceof ManufacturedStocks)) {
            return false;
        }
        ManufacturedStocks other = (ManufacturedStocks) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rajtech.stockwebservice.model.ManufacturedStocks[ id=" + id + " ]";
    }
    
}
