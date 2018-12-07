/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa.session;

import dao.entities.Taxa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gabriel
 */
@Stateless
public class TaxaFacade extends AbstractFacade<Taxa> {
    @PersistenceContext(unitName = "clinicamedica2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TaxaFacade() {
        super(Taxa.class);
    }
    
}
