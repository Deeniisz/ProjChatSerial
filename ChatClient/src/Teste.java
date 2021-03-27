import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import javax.swing.JOptionPane;
public class Teste {

	public static void main(String[] args) {
		String nome = "";
		String mensagem = "";
		
		nome = JOptionPane.showInputDialog("Informe seu Nome: ");
		try {
			while (mensagem != "0") {
				mensagem = JOptionPane.showInputDialog(nome	+ "Mensagem:");
				IChatAula objChat = (IChatAula) Naming
						.lookup("rmi://localhost:8282/chat");
				Message msg = new Message(nome, mensagem);
				objChat.sendMessage(msg);
				System.out.print(returnMessage(objChat.retrieveMessage()));
				new CadastroDB().inserir(msg);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Retorna Lista Com as Mensagens
	private static String returnMessage(List<Message> lst) {		
			String valor = "";
			for (Message message : lst) {
				valor = message.getUsuario() + ": " + message.getMensagem()  + "\n";
			}
			return valor;
		}
	}
