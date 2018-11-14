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
import workshop3lo.domain.Account;
import workshop3lo.domain.Klant;

/**
 *
 * @author liz20
 */
@Stateless
public class KlantFacade extends AbstractFacade<Klant> {

    @PersistenceContext(unitName = "workshop3LO_workshop3LO_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KlantFacade() {
        super(Klant.class);
    }

    public Klant findByAccountId(Integer accountId) {
         Account account;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Account> query = cb.createQuery(Account.class);
        Root<Account> regel = query.from(Account.class);
        query.select(regel);

        query = query.select(regel).where(cb.equal(regel.get("id"), accountId));

        try {
            account = em.createQuery(query).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }

        CriteriaBuilder cb2 = em.getCriteriaBuilder();
        CriteriaQuery<Klant> query2 = cb2.createQuery(Klant.class);
        Root<Klant> regel2 = query2.from(Klant.class);
        query2.select(regel2);

        query2 = query2.select(regel2).where(cb.equal(regel2.get("accountId"), account));

        try {
            return em.createQuery(query2).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
    
}
