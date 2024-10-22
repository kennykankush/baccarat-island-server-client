import java.io.*;
import java.net.*;
import java.util.*;


public class ClientApp{
    private Socket socket;
    private DataInputStream in_msg;
    private DataOutputStream out;
    private Scanner in;

    public ClientApp(){
        try {
            socket = new Socket("localhost", ServerApp.PORT);
            in_msg = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            in = new Scanner(System.in);

            String welcomeMessage = in_msg.readUTF();
            System.out.println(welcomeMessage);

            String welcomeMessage2 = in_msg.readUTF();
            System.out.println(welcomeMessage2);

            String welcomeMessage3 = in_msg.readUTF();
            System.out.println(welcomeMessage3);

            String welcomeMessage4 = in_msg.readUTF();
            System.out.println(welcomeMessage4);
            writeMessages();

        } catch (IOException e){
            e.printStackTrace();
        }
        
        
    }

    private void writeMessages() throws IOException{
        String line = "";

        while(!line.equals(ServerApp.STOP_STRING)){
            line = in.nextLine();
            out.writeUTF(line);
            out.flush(); 

            String response = in_msg.readUTF();  // Read server response
            System.out.println(response);  // Print server's response

            String response2 = in_msg.readUTF();  // Read server response
            System.out.println(response2);  // Print server's response

            if (line.equals("play")){

            String player_Card = in_msg.readUTF();  // Read server response
            System.out.println(player_Card);  // Print server's response

            String dealer_Card = in_msg.readUTF();  // Read server response
            System.out.println(dealer_Card);  // Print server's response

            String player_Card2 = in_msg.readUTF();  // Read server response
            System.out.println(player_Card2);  // Print server's response

            String dealer_Card2 = in_msg.readUTF();  // Read server response
            System.out.println(dealer_Card2);  // Print server's response

            String playerValue = in_msg.readUTF();  // Read server response
            System.out.println(playerValue);  // Print server's response

            String dealerValue = in_msg.readUTF();  // Read server response
            System.out.println(dealerValue);  // Print server's response

            String evaluatePlayerHand = in_msg.readUTF();
            System.out.println(evaluatePlayerHand);

            String dealer_Decision = in_msg.readUTF();
            System.out.println(dealer_Decision);

            String dealerFollowUp = in_msg.readUTF();
            System.out.println(dealerFollowUp);

            String winner = in_msg.readUTF();
            System.out.println(winner);

            String payOutMSG = in_msg.readUTF();
            System.out.println(payOutMSG);

            }

        }

        close();
    }

    private void close() throws IOException{
        socket.close();
        out.close();
        in.close();
    }

    public static void main(String[] args){
        new ClientApp();
    }

}