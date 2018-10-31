/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import workshop3lo.domain.Artikel;

/**
 *
 * @author liz20
 */
@Stateless
public class ArtikelFacade extends AbstractFacade<Artikel> {

    @PersistenceContext(unitName = "workshop3LO_workshop3LO_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArtikelFacade() {
        super(Artikel.class);
    }

    public Artikel findByNaam(String naam) {
        // select * from Artikel where name = ?
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Artikel> query = cb.createQuery(Artikel.class);
        Root<Artikel> root = query.from(Artikel.class);
        query = query.select(root).where(cb.equal(root.get("naam"), naam));
        try {
            return em.createQuery(query).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
    
}
