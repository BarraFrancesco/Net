
package net;

import java.io.IOException;

/**
 *
 * @author Francy10
 */
public class Net {

   
    public static void main(String[] args) throws IOException {
      ServerThread st=new ServerThread(1111);
      ClientThread ct=new ClientThread("localhost",1111);
      try
      {
          st.join(3600);
          ct.join(3600);
          
      }
      catch (InterruptedException e)
      {
          System.exit(0);
      }
    }
    
}
