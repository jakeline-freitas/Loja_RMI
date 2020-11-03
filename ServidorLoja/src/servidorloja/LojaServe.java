
package servidorloja;

import intermediateloja.ILoja;
import intermediateloja.Produto;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class LojaServe extends UnicastRemoteObject implements ILoja{
    //ConectionBD con =new ConectionBD() ;
    List<Produto> produtos ;
    public LojaServe() throws RemoteException {
        super();
        
       this.produtos = new ArrayList<>();
       //this.con = new ConectionBD();
       
    }

    @Override
    public boolean Inserir(String nome, double preco) throws RemoteException {
         for(Produto p: produtos){
            if(p.getNome().equals(nome)){
                return false;
            }
            
        }
        Produto p = new Produto(nome, preco);      
        produtos.add(p);
        return true; 
    }

    @Override
    public boolean Remover(String string) throws RemoteException {
        for (Produto produto : produtos) {
            if(produto.getNome().equals(string)){
                produtos.remove(produto);
                return true;
            }
        }
        return false;
    }

    @Override
    public String Procurar(String nome) throws RemoteException {
        for (Produto produto : produtos) {
            if(produto.getNome().equals(nome)){
                return produto.toString();
            }
        }
        return null;
        
    }

    @Override
    public boolean AlterarPreco(String nome, double novoPreco) throws RemoteException {
        for (Produto produto : produtos) {
            if(produto.getNome().equals(nome)){
                produto.setPreco(novoPreco);
                return true;
            }
        }
        return false;
    }

    @Override
    public void listar() throws RemoteException {
        
        for (Produto produto : produtos) {
            System.out.println("-----------------");
            System.out.println( produto.toString());
        }
    }

   

          
    
}
