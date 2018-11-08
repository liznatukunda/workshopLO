/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import workshop3lo.domain.Bestelling;
import workshop3lo.domain.Bestelregel;

/**
 *
 * @author liz20
 */
@Stateless
public class BestelregelFacade extends AbstractFacade<Bestelregel> {

    @PersistenceContext(unitName = "workshop3LO_workshop3LO_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BestelregelFacade() {
        super(Bestelregel.class);
    }

    public List<Bestelregel> findByBestelling(Integer bestellingId) {
        Bestelling bestelling;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Bestelling> query = cb.createQuery(Bestelling.class);
        Root<Bestelling> regel = query.from(Bestelling.class);
        query.select(regel);

        query = query.select(regel).where(cb.equal(regel.get("id"), bestellingId));

        try {
            bestelling = em.createQuery(query).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }

        CriteriaBuilder cb2 = em.getCriteriaBuilder();
        CriteriaQuery<Bestelregel> query2 = cb2.createQuery(Bestelregel.class);
        Root<Bestelregel> regel2 = query2.from(Bestelregel.class);
        query2.select(regel2);

        query2 = query2.select(regel2).where(cb.equal(regel2.get("bestellingidBestelling"), bestelling));

        try {
            return em.createQuery(query2).getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

//	public ArrayList<Bestelregel> findByBestelling(Bestelling bestelling) {
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery<Bestelregel> query = cb.createQuery(Bestelregel.class);
//		Root<Bestelregel> regel = query.from(Bestelregel.class);
//		query.select(regel);
//
//		query = query.select(regel).where(cb.equal(regel.get("bestelling_id"), bestelling.getId()));
//
//       try {
//            return (ArrayList<Bestelregel>) em.createQuery(query).getResultList();
//        } catch (NoResultException nre) {
//            return null;
//        }
//    }
}
