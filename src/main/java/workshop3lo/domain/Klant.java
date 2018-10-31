/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop3lo.domain;

import java.io.Serializable;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "klant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Klant.findAll", query = "SELECT k FROM Klant k")
    , @NamedQuery(name = "Klant.findById", query = "SELECT k FROM Klant k WHERE k.id = :id")
    , @NamedQuery(name = "Klant.findByVoornaam", query = "SELECT k FROM Klant k WHERE k.voornaam = :voornaam")
    , @NamedQuery(name = "Klant.findByTussenvoegsel", query = "SELECT k FROM Klant k WHERE k.tussenvoegsel = :tussenvoegsel")
    , @NamedQuery(name = "Klant.findByAchternaam", query = "SELECT k FROM Klant k WHERE k.achternaam = :achternaam")})
public class Klant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @JsonbProperty("id ")
    private Integer id;
    @Size(max = 45)
    @Column(name = "voornaam")
    @JsonbProperty("voornaam ")
    private String voornaam;
    @Size(max = 45)
    @Column(name = "tussenvoegsel")
    @JsonbProperty("tussenvoegsel ")
    private String tussenvoegsel;
    @Size(max = 45)
    @Column(name = "achternaam")
    @JsonbProperty("achternaam ")
    private String achternaam;
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonbProperty("accountId ")
    private Account accountId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "klantidKlant")
    private Collection<Bestelling> bestellingCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "klantidKlant")
    private Collection<Adres> adresCollection;

    public Klant() {
    }

    public Klant(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }

    @JsonbTransient
    @XmlTransient
    public Collection<Bestelling> getBestellingCollection() {
        return bestellingCollection;
    }

    public void setBestellingCollection(Collection<Bestelling> bestellingCollection) {
        this.bestellingCollection = bestellingCollection;
    }

    @JsonbTransient
    @XmlTransient
    public Collection<Adres> getAdresCollection() {
        return adresCollection;
    }

    public void setAdresCollection(Collection<Adres> adresCollection) {
        this.adresCollection = adresCollection;
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
        if (!(object instanceof Klant)) {
            return false;
        }
        Klant other = (Klant) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "workshop3lo.domain.Klant[ id=" + id + " ]";
    }

}
