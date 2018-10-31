/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop3lo.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author LIZ
 */
@Entity
@Table(name = "artikel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artikel.findAll", query = "SELECT a FROM Artikel a")
    , @NamedQuery(name = "Artikel.findById", query = "SELECT a FROM Artikel a WHERE a.id = :id")
    , @NamedQuery(name = "Artikel.findByNaam", query = "SELECT a FROM Artikel a WHERE a.naam = :naam")
    , @NamedQuery(name = "Artikel.findByPrijs", query = "SELECT a FROM Artikel a WHERE a.prijs = :prijs")
    , @NamedQuery(name = "Artikel.findByVoorraad", query = "SELECT a FROM Artikel a WHERE a.voorraad = :voorraad")})
public class Artikel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @JsonbProperty("id ")
    private Integer id;
    @Size(max = 45)
    @Column(name = "naam")
    @JsonbProperty("naam ")
    private String naam;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prijs")
        @JsonbProperty("prijs ")
    private BigDecimal prijs;
    @Column(name = "voorraad")
    @JsonbProperty("voorraad ")
    private Integer voorraad;
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "artikelidArtikel")
    private Collection<Bestelregel> bestelregelCollection;

    public Artikel() {
    }

    public Artikel(Integer id) {
        this.id = id;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }


    public BigDecimal getPrijs() {
        return prijs;
    }

    public void setPrijs(BigDecimal prijs) {
        this.prijs = prijs;
    }

    
    public Integer getVoorraad() {
        return voorraad;
    }

    public void setVoorraad(Integer voorraad) {
        this.voorraad = voorraad;
    }

    @JsonbTransient
    @XmlTransient
    public Collection<Bestelregel> getBestelregelCollection() {
        return bestelregelCollection;
    }

    public void setBestelregelCollection(Collection<Bestelregel> bestelregelCollection) {
        this.bestelregelCollection = bestelregelCollection;
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
        if (!(object instanceof Artikel)) {
            return false;
        }
        Artikel other = (Artikel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "workshop3lo.domain.Artikel[ id=" + id + " ]";
    }

}
