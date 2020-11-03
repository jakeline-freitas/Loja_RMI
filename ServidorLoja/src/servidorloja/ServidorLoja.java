
package servidorloja;

import intermediateloja.ILoja;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServidorLoja {

    
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(9999);
            ILoja objLoja = new LojaServe();
            registry.rebind("MinhaLoja", objLoja);
            
            System.out.println("servidor da loja pronto");
        } catch (RemoteException ex) {
            Logger.getLogger(ServidorLoja.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
}
