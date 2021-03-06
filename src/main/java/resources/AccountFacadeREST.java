/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Subquery;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import workshop3lo.domain.Account;
import workshop3lo.domain.Klant;

/**
 *
 * @author LIZ
 */
@Stateless
@Path("workshop3lo.domain.account")
public class AccountFacadeREST extends AbstractFacade<Account> {

    @PersistenceContext(unitName = "workshop3LO_workshop3LO_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public AccountFacadeREST() {
        super(Account.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Account entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Account entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Account find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Account> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Account> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @GET
    @Path("search/{naam}")
    @Produces({MediaType.APPLICATION_JSON})
    public Account findByUserName(@PathParam("naam") String naam) {
        return findByName(naam);
    }

//    @GET
//    @Path("query")
//    @Produces({MediaType.APPLICATION_JSON})
//    public List<Account> getKlantAccountsZonderKlant(@PathParam("query") String query) {
//        return getKlantAccountsZonderKlantQuery();
//    }

    public Account findByName(String naam) {
        // select * from account where name = ?
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Account> query = cb.createQuery(Account.class);
        Root<Account> root = query.from(Account.class);
        query = query.select(root).where(cb.equal(root.get("username"), naam));
        try {
            return em.createQuery(query).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

 //   public List<Account> getKlantAccountsZonderKlantQuery() {
//
//        // SELECT * FROM account a  LEFT join klant k on a.id=k.account_id 
//        //          where k.id is Null and a.rol = "klant"
//        // Dit kunnen we ook oplossen als:
//        // 1. Bouw een lijst van alle accounts where rol = klant
//        // 2. Bouw een lijst van alle klanten 
//        // 3. Loop door de accountlist.
//        // 4. Indien account.id niet voorkomt in klantList.account_id, voeg dan dat account
//        // toe aan de resultList.
//        //
//        // Of een ander SQL (met hetzelfde resultaat):
//        // select * from account where rol = "klant" and id not in (select account_id from klant);
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Account> query = cb.createQuery(entityClass);
//        Root<Account> root = query.from(entityClass);
//        query.select(root);
//
//        // Nu samenstellen de subquery: select account_id from klant
//        Subquery<Integer> subquery = query.subquery(Integer.class);
//        Root<Klant> subRoot = subquery.from(Klant.class);
//        subquery.select(subRoot.get("account"));
//
//        query.where(cb.equal(root.get("rol"), Account.Rol.klant),
//                cb.not(cb.in(root.get("id")).value(subquery)));
//        TypedQuery<Account> q = em.createQuery(query);
//        return q.getResultList();
}

