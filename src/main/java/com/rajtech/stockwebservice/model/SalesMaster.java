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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rajkumar Ramasamy
 */
@Entity
@Table(name = "sales_master")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalesMaster.findAll", query = "SELECT s FROM SalesMaster s")
    , @NamedQuery(name = "SalesMaster.findById", query = "SELECT s FROM SalesMaster s WHERE s.id = :id")
    , @NamedQuery(name = "SalesMaster.findBySalesDate", query = "SELECT s FROM SalesMaster s WHERE s.salesDate = :salesDate")
    , @NamedQuery(name = "SalesMaster.findByTax", query = "SELECT s FROM SalesMaster s WHERE s.tax = :tax")
    , @NamedQuery(name = "SalesMaster.findByDiscount", query = "SELECT s FROM SalesMaster s WHERE s.discount = :discount")
    , @NamedQuery(name = "SalesMaster.findByTotal", query = "SELECT s FROM SalesMaster s WHERE s.total = :total")
    , @NamedQuery(name = "SalesMaster.findByPaymentType", query = "SELECT s FROM SalesMaster s WHERE s.paymentType = :paymentType")
    , @NamedQuery(name = "SalesMaster.findByBalance", query = "SELECT s FROM SalesMaster s WHERE s.balance = :balance")})
public class SalesMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "sales_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date salesDate;
    @Column(name = "tax")
    private Integer tax;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "discount", precision = 22)
    private Double discount;
    @Column(name = "total", precision = 22)
    private Double total;
    @Size(max = 45)
    @Column(name = "payment_type", length = 45)
    private String paymentType;
    @Column(name = "balance", precision = 22)
    private Double balance;
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Customer customerId;
    @JoinColumn(name = "order_placed_by_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Users orderPlacedById;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salesId")
    private List<SalesItem> salesItemList;

    public SalesMaster() {
    }

    public SalesMaster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(Date salesDate) {
        this.salesDate = salesDate;
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public Users getOrderPlacedById() {
        return orderPlacedById;
    }

    public void setOrderPlacedById(Users orderPlacedById) {
        this.orderPlacedById = orderPlacedById;
    }

    @XmlTransient
    public List<SalesItem> getSalesItemList() {
        return salesItemList;
    }

    public void setSalesItemList(List<SalesItem> salesItemList) {
        this.salesItemList = salesItemList;
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
        if (!(object instanceof SalesMaster)) {
            return false;
        }
        SalesMaster other = (SalesMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rajtech.stockwebservice.model.SalesMaster[ id=" + id + " ]";
    }
    
}
