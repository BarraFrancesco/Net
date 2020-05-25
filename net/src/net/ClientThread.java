
package net;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 *
 * @author Francy10
 */
public class ClientThread extends Thread{
    private Socket server;       //Socket del server
    private DataInputStream in;  //Input stream(dati in ingresso del server)
    private DataOutputStream out;//Output stream(dati in uscita server il server)
    
    public ClientThread(String host,int port){
        try{
            // connessione al server 
            server=new Socket(host,port);
        }
        catch (UnknownHostException e)
        {
            System.out.println("[CLIENT] Connessione fallita (Host error).");
            
        }
        catch(IOException e){
            System.out.println("[CLIENT Connessione fallita(I/O error)]");
        }
        System.out.println("[CLIENT] Connesso a"+ server.toString());
        //avvio del thread 
        start();
    }
    @Override
    public void run()
    {
        try
        {
            //Inizializzazione degli stream
            BufferedInputStream bin =new
        BufferedInputStream(server.getInputStream());
            in = new DataInputStream(bin);
            BufferedOutputStream bout=new
        BufferedOutputStream(server.getOutputStream());
            out=new DataOutputStream(bout);
            // Invio del messaggio al server 
            String message="Hel"
                    + "lo server"+server.getInetAddress().getHostAddress()+":"+ server.getPort()+"=)"; 
            out.writeUTF(message);
            out.flush();
            System.out.println("[CLIENT] Invio pacchetto:'"+message+"'" );
            String echoMessage=in.readUTF();
            System.out.println("[CLIENT] Ricevuto pacchettoecho: '" + echoMessage+"'");
            //Chiusura degli stream
            out.close();
            in.close();
            server.close();
                    }
        catch(Exception e){
            System.out.println("[CLIENT] Errore durante la scrittura/letteratura del pacchetto.");
        }
       }
    
}
