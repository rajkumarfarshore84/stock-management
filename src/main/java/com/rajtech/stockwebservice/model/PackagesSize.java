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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rajkumar Ramasamy
 */
@Entity
@Table(name = "packages_size")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PackagesSize.findAll", query = "SELECT p FROM PackagesSize p")
    , @NamedQuery(name = "PackagesSize.findById", query = "SELECT p FROM PackagesSize p WHERE p.id = :id")
    , @NamedQuery(name = "PackagesSize.findByName", query = "SELECT p FROM PackagesSize p WHERE p.name = :name")})
public class PackagesSize implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 45)
    @Column(name = "name", length = 45)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "packagesSizeId")
    private List<PackItem> packItemList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "packagesId")
    private List<StockPacking> stockPackingList;

    public PackagesSize() {
    }

    public PackagesSize(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<PackItem> getPackItemList() {
        return packItemList;
    }

    public void setPackItemList(List<PackItem> packItemList) {
        this.packItemList = packItemList;
    }

    @XmlTransient
    public List<StockPacking> getStockPackingList() {
        return stockPackingList;
    }

    public void setStockPackingList(List<StockPacking> stockPackingList) {
        this.stockPackingList = stockPackingList;
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
        if (!(object instanceof PackagesSize)) {
            return false;
        }
        PackagesSize other = (PackagesSize) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rajtech.stockwebservice.model.PackagesSize[ id=" + id + " ]";
    }
    
}
