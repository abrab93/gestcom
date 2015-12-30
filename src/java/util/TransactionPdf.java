/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import bean.Compte;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Khalid
 */
public class TransactionPdf {
    public void generateTransactionPdf(Compte compte) throws JRException, IOException{
        Map<String, Object> params = new HashMap<>();
        params.put("rib", compte.getRib());
        params.put("solde", compte.getSolde());
        String fileName = "Bilan-" + compte.getRib();
        PdfUtil.generatePdf(compte.getOperations(), params, fileName);
    }
}
