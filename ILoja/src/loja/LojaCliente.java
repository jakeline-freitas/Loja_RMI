
package loja;

import intermediateloja.ILoja;
import intermediateloja.Produto;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;


public class LojaCliente {
    
    public static String menu(){
        String menu = "1- Inserir produto\n" +
                      "2- Remover Produto\n" +
                      "3- Procurar Produto\n"+
                      "4- Alterar Preço\n" +
                      "0- Sair";
        return menu;
    }
    public static void main(String[] args) {
        
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 9999);
            ILoja loja = (ILoja) registry.lookup("MinhaLoja");
            
            String op = "0";
            do { 
                op = JOptionPane.showInputDialog(null, menu());
                
                switch(op){
                    case "1":
                        String nome = JOptionPane.showInputDialog("Digite o nome do novo produto");
                        double preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço do novo produto =>"));
                        if(loja.Inserir(nome, preco)){
                            //System.out.println("Produto inserido com sucesso");
                            JOptionPane.showMessageDialog(null,"Produto inserido com sucesso!");
                        }else{
                            //System.out.println("Produto não inserido,Já existe!");
                            JOptionPane.showMessageDialog(null,"Produto não inserido,Já existe!");
                        }
                        break;
                    case "2":
                        String nomeRemov = JOptionPane.showInputDialog("Digite o nome do produto que deseja remover");
                        
                        if(loja.Remover(nomeRemov)){
                           
                            JOptionPane.showMessageDialog(null,"Produto Removido com sucesso!");
                        }else{
                             JOptionPane.showMessageDialog(null,"Produto não existe!");
                            //System.out.println();
                        }
                        break;
                    case "3":
                        String nomeProcura =  JOptionPane.showInputDialog("Digite o nome do produto que deseja procurar");
                        String p = loja.Procurar(nomeProcura);
                        if(!(p == null)){
                            
                            JOptionPane.showMessageDialog(null,p);
                        }else{
                            JOptionPane.showMessageDialog(null,"Produto não encontrado!");
                            //System.out.println("Produto não encontrado!");
                        }
                        break;
                    case "4":
                        String nomePA = JOptionPane.showInputDialog("Digite o nome do produto que deseja alterar o preço(0.00)");
                        double precoAlterar = Double.parseDouble(JOptionPane.showInputDialog("Digite o novo do preço")) ;
                       
                        if(loja.AlterarPreco(nomePA, precoAlterar)){
                            //System.out.println("Preço Alterado com sucesso");
                            JOptionPane.showMessageDialog(null,"Preço Alterado com sucesso");
                        }else{
                           // System.out.println("");
                            JOptionPane.showMessageDialog(null,"Ocorreu um erro, tente novamente!");
                        }
                        break;
                    case "5"://Verifica lista completa apenas no servidor
                        loja.listar();
                        break;
                    case "0":
                        System.out.println("Loja Encerrada");
                        System.exit(0);
                }
   
            } while (!op.equals("0"));
            
          
        } catch (Exception e) {
        }
    }
    
}
