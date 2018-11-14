/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import workshop3lo.domain.Adres;
import workshop3lo.domain.Bestelling;
import workshop3lo.domain.Klant;

/**
 *
 * @author liz20
 */
@Stateless
public class AdresFacade extends AbstractFacade<Adres> {

    @PersistenceContext(unitName = "workshop3LO_workshop3LO_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdresFacade() {
        super(Adres.class);
    }

    public List<Adres> findByKlant(Integer klantId) {
         Klant klant;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Klant> query = cb.createQuery(Klant.class);
        Root<Klant> regel = query.from(Klant.class);
        query.select(regel);

        query = query.select(regel).where(cb.equal(regel.get("id"), klantId));

        try {
            klant = em.createQuery(query).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }

        CriteriaBuilder cb2 = em.getCriteriaBuilder();
        CriteriaQuery<Adres> query2 = cb2.createQuery(Adres.class);
        Root<Adres> regel2 = query2.from(Adres.class);
        query2.select(regel2);

        query2 = query2.select(regel2).where(cb.equal(regel2.get("klantidKlant"), klant));

        try {
            return em.createQuery(query2).getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }
    
}
