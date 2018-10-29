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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
    , @NamedQuery(name = "Account.findById", query = "SELECT a FROM Account a WHERE a.id = :id")
    , @NamedQuery(name = "Account.findByUsername", query = "SELECT a FROM Account a WHERE a.username = :username")
    , @NamedQuery(name = "Account.findByPassword", query = "SELECT a FROM Account a WHERE a.password = :password")
    , @NamedQuery(name = "Account.findByRol", query = "SELECT a FROM Account a WHERE a.rol = :rol")})
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    
        public enum Rol {
        klant, medewerker, beheerder;

        public static Rol toRol(String rol) {
            String rollowercase = rol.toLowerCase();
            if (rollowercase.equals("beheerder")) {
                return Rol.beheerder;
            }

            if (rollowercase.equals("medewerker")) {
                return Rol.medewerker;
            } else {
                return Rol.klant;
            }
        }
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "username")
    private String username;
    @Size(max = 60)
    @Column(name = "password")
    private String password;
    @Size(max = 10)
    @Column(name = "rol")
    @Enumerated(EnumType.STRING)
    private Rol rol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
    private Collection<Klant> klantCollection;

    public Account() {
    }

    public Account(Integer id) {
        this.id = id;
    }

    @JsonbProperty ("id ")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
 @JsonbProperty ("username ")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
 @JsonbProperty ("password ")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @JsonbTransient
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<Klant> getKlantCollection() {
        return klantCollection;
    }

    public void setKlantCollection(Collection<Klant> klantCollection) {
        this.klantCollection = klantCollection;
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
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "workshop3lo.domain.Account[ id=" + id + " ]";
    }
    
}
