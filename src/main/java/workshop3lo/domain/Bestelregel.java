/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop3lo.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LIZ
 */
@Entity
@Table(name = "bestelregel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bestelregel.findAll", query = "SELECT b FROM Bestelregel b")
    , @NamedQuery(name = "Bestelregel.findById", query = "SELECT b FROM Bestelregel b WHERE b.id = :id")
    , @NamedQuery(name = "Bestelregel.findByAantal", query = "SELECT b FROM Bestelregel b WHERE b.aantal = :aantal")
    , @NamedQuery(name = "Bestelregel.findByPrijs", query = "SELECT b FROM Bestelregel b WHERE b.prijs = :prijs")})
public class Bestelregel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @JsonbProperty("id")
    private Integer id;
    @Column(name = "aantal")
    @JsonbProperty("aantal")
    private Integer aantal;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prijs")
    @JsonbProperty("prijs")
    private BigDecimal prijs;
    @JoinColumn(name = "Artikel_id", referencedColumnName = "id")
    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JsonbProperty("artikelidArtikel")
    private Artikel artikelidArtikel;
    @JoinColumn(name = "bestelling_id", referencedColumnName = "id")
    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JsonbProperty("bestellingidBestelling")
    private Bestelling bestellingidBestelling;

    public Bestelregel() {
    }

    public Bestelregel(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAantal() {
        return aantal;
    }

    public void setAantal(Integer aantal) {
        this.aantal = aantal;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public void setPrijs(BigDecimal prijs) {
        this.prijs = prijs;
    }

    public Artikel getArtikelidArtikel() {
        return artikelidArtikel;
    }

    public void setArtikelidArtikel(Artikel artikelidArtikel) {
        this.artikelidArtikel = artikelidArtikel;
    }

    
    public Bestelling getBestellingidBestelling() {
        return bestellingidBestelling;
    }

    public void setBestellingidBestelling(Bestelling bestellingidBestelling) {
        this.bestellingidBestelling = bestellingidBestelling;
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
        if (!(object instanceof Bestelregel)) {
            return false;
        }
        Bestelregel other = (Bestelregel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "workshop3lo.domain.Bestelregel[ id=" + id + " ]";
    }

}
