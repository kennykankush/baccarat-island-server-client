import java.net.*;
import java.io.*;
import java.util.List;


public class ServerApp {

        private ServerSocket server;
        private DataInputStream in;
        private DataOutputStream out;
        public static final int PORT = 3000;
        public static final String STOP_STRING = "quit";
        baccaratEngine engine;
        Dealer dealer;
        Player user;
        Boolean run = true;
        Boolean hasDeposited = false;
        Boolean hasChip = false;

        public ServerApp(){
            try{
                server = new ServerSocket(PORT);
                System.out.println("Awaiting connection...");
                initConnection();
                
            } catch (IOException e){
                e.printStackTrace();
            }
    
        }
    
        public void initConnection() throws IOException{
            Socket clientSocket = server.accept();
            System.out.println("Someone has connected!");
            dealer = new Dealer();
            engine = new baccaratEngine();
            user = new Player();

            in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            out = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
            
            String WELCOME_MSG = "Luffy: Good mythical morning " + user.getHostname() + "!";
            String WELCOME_MSG2 = "Luffy: Welcome to Baccarat Island!";
            String WELCOME_MSG3 = "Luffy: You ready to play? Better drop some berries first, hahaha!";
            String WELCOME_MSG4 = 
                "╔═══════════════════════════╗\n" +
                "║         Main Menu         ║\n" +
                "╠═══════════════════════════╣\n" +
                "║   1. [DEPOSIT]            ║\n" +
                "║   2. [QUIT]               ║\n" +
                "╚═══════════════════════════╝\n";

            out.writeUTF(WELCOME_MSG);
            out.flush();

            try {Thread.sleep(2000); }
            catch (InterruptedException e) {}
  
            out.writeUTF(WELCOME_MSG2);
            out.flush();

            try {Thread.sleep(2000); }
            catch (InterruptedException e) {}

            out.writeUTF(WELCOME_MSG3);
            out.flush();

            try {Thread.sleep(2000); }
            catch (InterruptedException e) {}

            out.writeUTF(WELCOME_MSG4);
            out.flush();

            serverFlow();
            close();
        }
    
        public void close() throws IOException{
            in.close();
            out.close();
            server.close();
        }
        
        public void serverFlow() throws IOException {

            String response = "";
            String response2 = "";
            String line = "";

            while (run){
                line = in.readUTF();

                if (line.startsWith("deposit")){
                        System.out.println("User's balance: " + user.getBalance());
                    
                        String[] lineData = line.split(" ");
                        String depositAmount = lineData[1];

                        user.deposit(Double.parseDouble(depositAmount));
                        
                        hasDeposited = true;

                        System.out.println("User's balance: " + user.getBalance());

                        if (hasDeposited && !hasChip){
                            response = "You have deposited " + depositAmount + " berries!";
                            out.writeUTF(response);
                            out.flush();

                            try {Thread.sleep(2000); }
                            catch (InterruptedException e) {}

                            response2 = "type bet amount team  P | D | T";
                            out.writeUTF(response2);
                            out.flush();
                        }


                    } else if (line.startsWith("bet")){
                        String[] lineData = line.split(" ");
                        user.setBetAmount(Double.parseDouble(lineData[1]));
                        user.setBet(lineData[2]);
                        hasChip = true;

                        System.out.println(user.getBet());
                        System.out.println(user.getBetAmount());

                        if (hasDeposited & hasChip){
                            response = "You have betted " + user.getBetAmount() + " on " + user.getBet();
                            out.writeUTF(response);
                            out.flush();
            
                            try {Thread.sleep(2000); }
                            catch (InterruptedException e) {}
            
                            response2 = "Type play to play";
                            out.writeUTF(response2);
                            out.flush();
                        }

                    } else if (line.startsWith("play")){
                        dealer.dealCardsToPlayer(user);
                        String player_Card = "You get a " + user.getPlayerHand();
                        out.writeUTF(player_Card);
                        out.flush();
                        try {Thread.sleep(1000); }
                        catch (InterruptedException e) {}

                        dealer.dealCardsToDealer();
                        String dealer_Card = "Dealer get a " + dealer.getDealerHand();
                        out.writeUTF(dealer_Card);
                        out.flush();
                        try {Thread.sleep(1000); }
                        catch (InterruptedException e) {}

                        dealer.dealCardsToPlayer(user);
                        String player_Card2 = "You get a " + user.getPlayerHand();
                        out.writeUTF(player_Card2);
                        out.flush();
                        try {Thread.sleep(1000); }
                        catch (InterruptedException e) {}

                        dealer.dealCardsToDealer();
                        String dealer_Card2 = "Dealer get a " + dealer.getDealerHand();
                        out.writeUTF(dealer_Card2);
                        out.flush();
                        try {Thread.sleep(1000); }
                        catch (InterruptedException e) {}
                        
                        engine.assignValueToPlayer(user);
                        engine.calculatePlayerHand();
                        String playerValue = "Your card value is " + engine.playerValueList + "\nSum: " + engine.getSumPlayer();
                        out.writeUTF(playerValue);
                        out.flush();
                        try {Thread.sleep(1000); }
                        catch (InterruptedException e) {}

                        engine.assignValueToDealer(dealer);
                        engine.calculateDealerHand();
                        String dealerValue = "Dealer value is " + engine.dealerValueList + "\nSum: " + engine.getSumDealer();
                        out.writeUTF(dealerValue);
                        out.flush();
                        try {Thread.sleep(1000); }
                        catch (InterruptedException e) {}

                        engine.evaluateInitialPlayerHand();
                        String evaluatePlayerHand = engine.stateToString();
                        out.writeUTF(evaluatePlayerHand);
                        out.flush();
                        try {Thread.sleep(1000); }
                        catch (InterruptedException e) {}

                        engine.playerFollowUp(dealer, user);
                        String whatDidYouDraw = "Your hand now " + user.getPlayerHand();
                        out.writeUTF(whatDidYouDraw);
                        out.flush();
                        try {Thread.sleep(1000); }
                        catch (InterruptedException e) {}

                        engine.dealerDecision(user);
                        String dealer_Decision = engine.dealer_dialogue;
                        out.writeUTF(dealer_Decision);
                        out.flush();
                        try {Thread.sleep(1000); }
                        catch (InterruptedException e) {}

                        engine.dealerFollowUp(dealer);
                        String dealerFollowUp = "Dealer hand now " + dealer.getDealerHand();
                        out.writeUTF(dealerFollowUp);
                        out.flush();
                        try {Thread.sleep(1000); }
                        catch (InterruptedException e) {}

                        engine.determineWinner();
                        System.out.println(engine.getWinner());
                        String winner = "The result: " + engine.getWinner();
                        out.writeUTF(winner);
                        out.flush();
                        try {Thread.sleep(1000); }
                        catch (InterruptedException e) {}
                        
                        System.out.println(user.getBetAmount() +  user.getBet());
                        engine.payOut(user, user.getBetAmount(), user.getBet());
                        System.out.println(engine.getPayOut_MSG());
                        String payOut_MSG = engine.getPayOut_MSG();
                        out.writeUTF(payOut_MSG);
                        out.flush();
                        try {Thread.sleep(1000); }
                        catch (InterruptedException e) {}

                        engine.resetGame(user, dealer);
                        hasChip = false;

                    } else if (line.equals("quit")){
                        run = false;

                    } else {
                        response = "What are yer typing?";
                        out.writeUTF(response);
                        out.flush();

                    }
                System.out.println("Is the loop working?");
            }
            
        }
        public static void main(String[] args){
        new ServerApp();
        }
    }
    