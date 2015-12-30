package controler;

import bean.Compte;
import bean.Operation;
import controler.util.JsfUtil;
import controler.util.JsfUtil.PersistAction;
import service.OperationFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import service.CompteFacade;

@ManagedBean(name = "operationController")
@SessionScoped
public class OperationController implements Serializable {

    @EJB
    private OperationFacade operationFacade;

    @EJB
    private CompteFacade compteFacade;

    @ManagedProperty(value = "#{compteController}")
    private CompteController compteController;

    private Operation operation = new Operation();

    private List<Operation> operations = new ArrayList<>();

    public String affecter() {
        Compte loaded = compteFacade.find(compteController.getCompte().getRib());
        operation.setId(null);
        boolean success = operationFacade.affecter(loaded, operation);
        return null;
    }

    public String annuler(Operation operation) {
        Operation loaded = operationFacade.find(operation.getId());
        operationFacade.annulerOperation(loaded);
        return null;
    }

    public List<Operation> list() {
        Compte loaded = compteFacade.find(compteController.getCompte().getRib());
        operations = operationFacade.findCompteOperations(loaded);
        return operations;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public CompteController getCompteController() {
        return compteController;
    }

    public void setCompteController(CompteController compteController) {
        this.compteController = compteController;
    }
}
