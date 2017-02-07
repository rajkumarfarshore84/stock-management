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
 * @author node
 */
@Entity
@Table(name = "vendors")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vendors.findAll", query = "SELECT v FROM Vendors v")
    , @NamedQuery(name = "Vendors.findByVendorId", query = "SELECT v FROM Vendors v WHERE v.vendorId = :vendorId")
    , @NamedQuery(name = "Vendors.findByName", query = "SELECT v FROM Vendors v WHERE v.name = :name")
    , @NamedQuery(name = "Vendors.findByAddress1", query = "SELECT v FROM Vendors v WHERE v.address1 = :address1")
    , @NamedQuery(name = "Vendors.findByAddress2", query = "SELECT v FROM Vendors v WHERE v.address2 = :address2")
    , @NamedQuery(name = "Vendors.findByEmail", query = "SELECT v FROM Vendors v WHERE v.email = :email")
    , @NamedQuery(name = "Vendors.findByPhone", query = "SELECT v FROM Vendors v WHERE v.phone = :phone")
    , @NamedQuery(name = "Vendors.findByCreatedAt", query = "SELECT v FROM Vendors v WHERE v.createdAt = :createdAt")
    , @NamedQuery(name = "Vendors.findByUpdatedAt", query = "SELECT v FROM Vendors v WHERE v.updatedAt = :updatedAt")
    , @NamedQuery(name = "Vendors.findByOpenBalance", query = "SELECT v FROM Vendors v WHERE v.openBalance = :openBalance")})
public class Vendors implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendorId")
    private List<PackMaster> packMasterList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vendor_id")
    private Integer vendorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "address_1")
    private String address1;
    @Size(max = 150)
    @Column(name = "address_2")
    private String address2;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "phone")
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "open_balance")
    private BigDecimal openBalance = new BigDecimal(0);
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendorId")
    private List<OrderMaster> orderMasterList;
    @OneToMany(mappedBy = "vendorId")
    private List<PurchaseMaster> purchaseMasterList;

    public Vendors() {
    }

    public Vendors(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Vendors(Integer vendorId, String name, String address1, String phone, Date createdAt, BigDecimal openBalance) {
        this.vendorId = vendorId;
        this.name = name;
        this.address1 = address1;
        this.phone = phone;
        this.createdAt = createdAt;
        this.openBalance = openBalance;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public BigDecimal getOpenBalance() {
        return openBalance;
    }

    public void setOpenBalance(BigDecimal openBalance) {
        this.openBalance = openBalance;
    }

    @XmlTransient
    public List<OrderMaster> getOrderMasterList() {
        return orderMasterList;
    }

    public void setOrderMasterList(List<OrderMaster> orderMasterList) {
        this.orderMasterList = orderMasterList;
    }

    @XmlTransient
    public List<PurchaseMaster> getPurchaseMasterList() {
        return purchaseMasterList;
    }

    public void setPurchaseMasterList(List<PurchaseMaster> purchaseMasterList) {
        this.purchaseMasterList = purchaseMasterList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vendorId != null ? vendorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vendors)) {
            return false;
        }
        Vendors other = (Vendors) object;
        if ((this.vendorId == null && other.vendorId != null) || (this.vendorId != null && !this.vendorId.equals(other.vendorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rajtech.stockwebservice.model.Vendors[ vendorId=" + vendorId + " ]";
    }

    @XmlTransient
    public List<PackMaster> getPackMasterList() {
        return packMasterList;
    }

    public void setPackMasterList(List<PackMaster> packMasterList) {
        this.packMasterList = packMasterList;
    }
    
}
