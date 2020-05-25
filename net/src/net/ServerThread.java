
package net;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author Francy10
 */
public class ServerThread extends Thread{
    
    private ServerSocket server;                       //Socket del Server
    private Socket client;                             //Socket del client
    private DataInputStream in;                        //input stream(dati in ingresso)
    private DataOutputStream out;                      //output stream(dati in uscita)
   
    
    public ServerThread(int port) throws IOException
    {
        try{
            //Creazione del Socket su server sulla porta desiderata
            server =new ServerSocket(port);
        }
        catch(IOException e)
        {
            System.out.println("[SERVER] Errore durante l'avvio del server sulla porta " + port);
        }
        System.out.println("[SERVER] Server avviato sulla porta " +port);
        
        //Avvio del thread (run)
        start();
        }
    @Override
    public void run(){
            System.out.println("[SERVER] In ascolto");
            try{
                client = server.accept();
            }
            catch(IOException e)
            {
                System.out.println("[SERVER]connessione fallita(I/O error)");
                try
                {
                    //Inizializzazione degli stream
                    BufferedInputStream bin=new
                BufferedInputStream(client.getInputStream());
                    in = new DataInputStream(bin);
                    BufferedOutputStream bout=new
                BufferedOutputStream(client.getOutputStream());
                    out=new DataOutputStream(bout);
                    //Ricezione del messaggio proveniente dal client
                    String message=in.readUTF();
                    System.out.println("[SERVER] Ricevuto pacchetto:'"+message+"'");
                    
                    String echoMessage="Hello client"+client.getInetAddress().getHostAddress()+":" +client.getPort()+"=)";
                    out.writeUTF(echoMessage);   //manda la stringa in out
                    out.flush();
                    System.out.println("[SERVER] Invio Pacchetto echo:'"+echoMessage+"'");
                    
                    //chiusura degli stream
                    out.close();
                    in.close();
                    client.close();
                    
                }
                catch(IOException r)
                {
                    System.out.println("[SERVER] Errore durante la lettura/scrittura del pacchetto.");
                }
            }
            
    }
}
