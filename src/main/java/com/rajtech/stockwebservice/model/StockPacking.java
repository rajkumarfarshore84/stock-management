/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rajkumar Ramasamy
 */
@Entity
@Table(name = "stock_packing")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StockPacking.findAll", query = "SELECT s FROM StockPacking s")
    , @NamedQuery(name = "StockPacking.findById", query = "SELECT s FROM StockPacking s WHERE s.id = :id")
    , @NamedQuery(name = "StockPacking.findByValue", query = "SELECT s FROM StockPacking s WHERE s.value = :value")})
public class StockPacking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "value")
    private Integer value;
    @Lob
    @Column(name = "barcode")
    private byte[] barcode;
    @JoinColumn(name = "manufactured_stocks_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private ManufacturedStocks manufacturedStocksId;
    @JoinColumn(name = "packages_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private PackagesSize packagesId;

    public StockPacking() {
    }

    public StockPacking(Integer id) {
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

    public byte[] getBarcode() {
        return barcode;
    }

    public void setBarcode(byte[] barcode) {
        this.barcode = barcode;
    }

    public ManufacturedStocks getManufacturedStocksId() {
        return manufacturedStocksId;
    }

    public void setManufacturedStocksId(ManufacturedStocks manufacturedStocksId) {
        this.manufacturedStocksId = manufacturedStocksId;
    }

    public PackagesSize getPackagesId() {
        return packagesId;
    }

    public void setPackagesId(PackagesSize packagesId) {
        this.packagesId = packagesId;
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
        if (!(object instanceof StockPacking)) {
            return false;
        }
        StockPacking other = (StockPacking) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rajtech.stockwebservice.model.StockPacking[ id=" + id + " ]";
    }
    
}
