/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop3lo.domain;

import java.io.Serializable;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LIZ
 */
@Entity
@Table(name = "adres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adres.findAll", query = "SELECT a FROM Adres a")
    , @NamedQuery(name = "Adres.findById", query = "SELECT a FROM Adres a WHERE a.id = :id")
    , @NamedQuery(name = "Adres.findByStraatnaam", query = "SELECT a FROM Adres a WHERE a.straatnaam = :straatnaam")
    , @NamedQuery(name = "Adres.findByHuisnummer", query = "SELECT a FROM Adres a WHERE a.huisnummer = :huisnummer")
    , @NamedQuery(name = "Adres.findByToevoeging", query = "SELECT a FROM Adres a WHERE a.toevoeging = :toevoeging")
    , @NamedQuery(name = "Adres.findByPostcode", query = "SELECT a FROM Adres a WHERE a.postcode = :postcode")
    , @NamedQuery(name = "Adres.findByWoonplaats", query = "SELECT a FROM Adres a WHERE a.woonplaats = :woonplaats")
   /* , @NamedQuery(name = "Adres.findByAdrestype", query = "SELECT a FROM Adres a WHERE a.adrestype = :adrestype")*/})
public class Adres implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum AdresType {
        POSTADRES, FACTUURADRES, BEZORGADRES;

        public static AdresType toAdresType(String adrestype) {
            if (adrestype.equals("postadres") || adrestype.equals("POSTADRES")) {
                return AdresType.POSTADRES;
            } else if (adrestype.equals("bezorgadres") || adrestype.equals("BEZORGADRES")) {
                return AdresType.BEZORGADRES;
            } else if (adrestype.equals("factuuradres") || adrestype.equals("FACTUURADRES")) {
                return AdresType.FACTUURADRES;
            } else {
                return null;
            }
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "straatnaam")
    private String straatnaam;
    @Size(max = 45)
    @Column(name = "huisnummer")
    private String huisnummer;
    @Size(max = 45)
    @Column(name = "toevoeging")
    private String toevoeging;
    @Size(max = 45)
    @Column(name = "postcode")
    private String postcode;
    @Size(max = 45)
    @Column(name = "woonplaats")
    private String woonplaats;
//    @Enumerated(EnumType.STRING)
//    @Size(max = 15)
//    @Column(name = "Adrestype")
//    private AdresType adrestype;
    @JoinColumn(name = "Klant_idKlant", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Klant klantidKlant;

    public Adres() {
    }

    public Adres(Integer id) {
        this.id = id;
    }

    @JsonbProperty("id ")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonbProperty("straatnaam")
    public String getStraatnaam() {
        return straatnaam;
    }

    public void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }

    @JsonbProperty("huisnummer")
    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    @JsonbProperty("toevoeging")
    public String getToevoeging() {
        return toevoeging;
    }

    public void setToevoeging(String toevoeging) {
        this.toevoeging = toevoeging;
    }

    @JsonbProperty("postcode")
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @JsonbProperty("woonplaats")
    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

//    @XmlTransient
//    @JsonbTransient
//    public AdresType getAdrestype() {
//        return adrestype;
//    }
//
//    public void setAdrestype(AdresType adrestype) {
//        this.adrestype = adrestype;
//    }

    @JsonbProperty("klantidKlant")
    public Klant getKlantidKlant() {
        return klantidKlant;
    }

    public void setKlantidKlant(Klant klantidKlant) {
        this.klantidKlant = klantidKlant;
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
        if (!(object instanceof Adres)) {
            return false;
        }
        Adres other = (Adres) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "workshop3lo.domain.Adres[ id=" + id + " ]";
    }

}
