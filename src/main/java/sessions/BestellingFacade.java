/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import workshop3lo.domain.Bestelling;

/**
 *
 * @author liz20
 */
@Stateless
public class BestellingFacade extends AbstractFacade<Bestelling> {

    @PersistenceContext(unitName = "workshop3LO_workshop3LO_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BestellingFacade() {
        super(Bestelling.class);
    }
    
}