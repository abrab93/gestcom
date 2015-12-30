package controler;

import bean.Compte;
import java.io.IOException;
import service.CompteFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;
import service.OperationFacade;
import util.TransactionPdf;

@ManagedBean(name = "compteController")
@SessionScoped
public class CompteController implements Serializable {

    private List<Compte> comptes = new ArrayList<>();
    private Compte compte = new Compte();
    @EJB
    private CompteFacade compteFacade;

    @EJB
    private OperationFacade operationFacade;

    public String create() {
        compteFacade.create(compte);
        return null;
    }

    public String update() {
        compteFacade.edit(compte);
        return "create";
    }

    public String delete(Compte compte) {
        compteFacade.remove(compte);
        return null;
    }

    public List<Compte> list() {
        comptes = compteFacade.findAll();
        return comptes;
    }

    public void loadSessionCompte(Compte compte) {
        this.compte = compteFacade.find(compte.getRib());
    }

    public String gerer(Compte compte) {
        Compte loaded = compteFacade.find(compte.getRib());
        this.compte = loaded;
        return "/operation/create";
    }

    public void bilanPdf(Compte compte) throws JRException, IOException {
        Compte loaded = compteFacade.find(compte.getRib());
        loaded.setOperations(operationFacade.findCompteOperations(loaded));
        new TransactionPdf().generateTransactionPdf(loaded);
        FacesContext.getCurrentInstance().responseComplete();
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public List<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }
}
