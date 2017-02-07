/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author node
 */
@Entity
@Table(name = "order_items")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderItems.findAll", query = "SELECT o FROM OrderItems o")
    , @NamedQuery(name = "OrderItems.findByItemId", query = "SELECT o FROM OrderItems o WHERE o.itemId = :itemId")
    , @NamedQuery(name = "OrderItems.findByQty", query = "SELECT o FROM OrderItems o WHERE o.qty = :qty")
    , @NamedQuery(name = "OrderItems.findByPrice", query = "SELECT o FROM OrderItems o WHERE o.price = :price")
    , @NamedQuery(name = "OrderItems.findByWeight", query = "SELECT o FROM OrderItems o WHERE o.weight = :weight")
    , @NamedQuery(name = "OrderItems.findByWeightIn", query = "SELECT o FROM OrderItems o WHERE o.weightIn = :weightIn")})
public class OrderItems implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    private BigDecimal qty;
    @Basic(optional = false)
    @NotNull
    private BigDecimal price;
    @Basic(optional = false)
    @NotNull
    private BigDecimal weight;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "item_id")
    private Integer itemId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "weight_in")
    private String weightIn;
    @JoinColumn(name = "order_id", referencedColumnName = "entity_id")
    @ManyToOne(optional = false)
    private OrderMaster orderId;
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Products productId;

    public OrderItems() {
    }

    public OrderItems(Integer itemId) {
        this.itemId = itemId;
    }

    public OrderItems(Integer itemId,  BigDecimal qty, BigDecimal price, BigDecimal weight, String weightIn) {
        this.itemId = itemId;
        this.qty = qty;
        this.price = price;
        this.weight = weight;
        this.weightIn = weightIn;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }


    public String getWeightIn() {
        return weightIn;
    }

    public void setWeightIn(String weightIn) {
        this.weightIn = weightIn;
    }

    public OrderMaster getOrderId() {
        return orderId;
    }

    public void setOrderId(OrderMaster orderId) {
        this.orderId = orderId;
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
        if (!(object instanceof OrderItems)) {
            return false;
        }
        OrderItems other = (OrderItems) object;
        if ((this.itemId == null && other.itemId != null) || (this.itemId != null && !this.itemId.equals(other.itemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rajtech.stockwebservice.model.OrderItems[ itemId=" + itemId + " ]";
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
    
}
