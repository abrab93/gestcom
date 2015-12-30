/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Compte;
import bean.Operation;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author abdelmoughit
 */
@Stateless
public class OperationFacade extends AbstractFacade<Operation> {

    @PersistenceContext(unitName = "jasperAndUploadPU")
    private EntityManager em;

    @EJB
    private CompteFacade compteFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OperationFacade() {
        super(Operation.class);
    }

    @Override
    public void create(Operation entity) {
        entity.setAnnuler(Boolean.FALSE);
        super.create(entity);
    }

    public List<Operation> findCompteOperations(Compte compte) {
        return (List<Operation>) em.createQuery("select op from Operation op where op.compte.rib = '" + compte.getRib() + "'").getResultList();
    }

    public boolean affecter(Compte compte, Operation operation) {
        if (operation.getType().equals("credit")
                || (operation.getType().equals("debit")) && compte.getSolde() >= operation.getMontant()) {
            operation.setCompte(compte);
            updateCompteSolde(compte, operation);
            compteFacade.edit(compte);
            create(operation);
            return true;
        }
        return false;
    }

    public void annulerOperation(Operation operation) {
        if (!operation.getAnnuler()) {
            Compte compte = operation.getCompte();
            this.affecter(compte, this.createAnnulerOperation(operation));
            operation.setAnnuler(Boolean.TRUE);
            this.edit(operation);
        }
    }

    private void updateCompteSolde(Compte compte, Operation operation) {
        double newSolde = 0;
        if (operation.getType().equals("debit")) {
            newSolde = compte.getSolde() - operation.getMontant();
        } else if (operation.getType().equals("credit")) {
            newSolde = compte.getSolde() + operation.getMontant();
        }
        compte.setSolde(newSolde);

    }

    private Operation createAnnulerOperation(Operation operation) {
        Operation annulerOp = new Operation();
        annulerOp.setCompte(operation.getCompte());
        annulerOp.setMontant(operation.getMontant());
        String type = operation.getType().equals("debit") ? "credit" : "debit";
        annulerOp.setType(type);
        return annulerOp;
    }

}
