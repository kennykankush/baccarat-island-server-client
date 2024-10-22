
baccaratEngine engine = new baccaratEngine();
Player user = new Player();
Dealer dealer = new Dealer();

if (line.equals("P")){
    user.setBet("Player");
} else if (line.equals("D")){
    user.setBet("Dealer");
} else {
    user.setBet("Tie");
}

dealer.dealCardsToPlayer(user);

try {
    Thread.sleep(2000); 
}//2 secs
catch (InterruptedException e) {
    

}
dealer.dealCardsToDealer();

try {
    Thread.sleep(2000); 
}//2 secs
catch (InterruptedException e) {
    
}
dealer.dealCardsToPlayer(user);

try {
    Thread.sleep(2000); 
}//2 secs
catch (InterruptedException e) {
    

}
dealer.dealCardsToDealer();

try {
    Thread.sleep(2000); 
}//2 secs
catch (InterruptedException e) {
}

System.out.println(user.getPlayerHand());

try {
    Thread.sleep(2000); 
}//2 secs
catch (InterruptedException e) {
}
System.out.println(dealer.getDealerHand());

try {
    Thread.sleep(2000); 
}//2 secs
catch (InterruptedException e) {
}

engine.assignValueToPlayer(user);
engine.assignValueToDealer(dealer);

System.out.println(engine.playerValueList);
System.out.println(engine.dealerValueList);

try {
    Thread.sleep(2000); 
}//2 secs
catch (InterruptedException e) {
}

engine.calculatePlayerHand();
engine.calculateDealerHand();

try {
    Thread.sleep(2000); 
}//2 secs
catch (InterruptedException e) {
}

System.out.println("Total value is: " + engine.getSumPlayer());
System.out.println("Total value is: " + engine.getSumDealer());

engine.evaluateInitialPlayerHand();

try {
    Thread.sleep(2000); 
}//2 secs
catch (InterruptedException e) {
}

engine.playerFollowUp(dealer, user);

try {
    Thread.sleep(2000); 
}//2 secs
catch (InterruptedException e) {
}

engine.dealerDecision();
engine.dealerFollowUp(dealer);

try {
    Thread.sleep(2000); 
}//2 secs
catch (InterruptedException e) {
}

engine.snapshotResult();
engine.payOut(user, 50, user.getBet());

try {
    Thread.sleep(2000); 
}//2 secs
catch (InterruptedException e) {
}

engine.resetGame(user);






            
String response = "";
String meow = "";

while (hasChip == false){


    switch(meow){
        case "P":
        user.setBet("Player");
        hasChip = true;
        break;

        case "D":
        user.setBet("Dealer");
        hasChip = true;
        break;

        case "T":
        user.setBet("Tie");
        hasChip = true;
        break;

        default:
        response = "Invalid option, please try again.";
        out.writeUTF(response);
        out.flush();
        break;

        }

        if (hasDeposited == false){
            switch(line){
                
                case "1":
                    response = "How many berries ya willing to give up?";
                    out.writeUTF(response);
                    out.flush();

                    String amtDepositInput = in.readUTF();
                    amtDeposit = Double.parseDouble(amtDepositInput);
                    user.deposit(amtDeposit);

                    response = "You have deposited: " + amtDeposit;
                    out.writeUTF(response);
                    out.flush();

                    hasDeposited = true;

                    break;

                default:
                    response = "Invalid option, please try again.";
                    out.writeUTF(response);
                    out.flush();
                    break;
                }  

        } else if (hasChip == false){
            response = "Bet P | D | T";
            out.writeUTF(response);
            out.flush();

            betMenu();

        } else if (hasDeposited == true && hasChip == true){
            play();
        }
    }

