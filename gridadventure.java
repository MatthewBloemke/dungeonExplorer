/**
 * A grid game based on finding the exit. Multiple obstacles to avoid
 * 
 * @author (Matt Bloemke) 
 * @version (4.0, March 28th)
 */
//imports modules
import java.util.*;
public class gridadventure
{
    public static void main (String[] args)
    {
        //input and random number gen for the entire code
        Random coordGen = new Random();
        Scanner inputScan = new Scanner(System.in);
        
        //all array lists used 
        ArrayList<Player> playerList = new ArrayList<Player>();
        ArrayList<Bomb> bombList = new ArrayList<Bomb>();
        ArrayList<Guard> guardList = new ArrayList<Guard>();
        ArrayList<Player> scoreList = new ArrayList<Player>();
        ArrayList<Goal> goalList = new ArrayList<Goal>();
        
        boolean[][] coordinates = new boolean[10][];
        for (int x = 0; x<10; x++)
        {
            coordinates[x] = new boolean[10];
            for (int y = 0; y<10; y++)
            {
                coordinates[x][y]=true;
            }
        }
        
        //variable for determining the number of players
        int inputPlayer= 0;
        do{
            //Game will not continue until a number is entered
            try{
                System.out.println("How many players?");
                inputPlayer = inputScan.nextInt();
                if (inputPlayer > 10) {
                    System.out.println("That is too many players.");
                    inputPlayer = 0;
                }
            }catch(InputMismatchException e){
                System.out.println("That is not an option.");
            }
            inputScan.nextLine();
                
        }while(inputPlayer==0);
        
        //determines number of npc's and bombs
        double percent = (100-inputPlayer)*.1;
        if (inputPlayer>=100){
            percent = 1;
        }
        for (int z = 0;z <Math.ceil(percent); z++)
        {
            int x = coordGen.nextInt(10);
            int y = coordGen.nextInt(10);
            if (coordinates[x][y]==true){
                goalList.add(new Goal(x, y));
                coordinates[x][y]=false;
            }else{
                z--;
            }
        }
        for (int z = 0;z <Math.ceil(percent); z++)
        {
            int x = coordGen.nextInt(10);
            int y = coordGen.nextInt(10);
            if (coordinates[x][y]==true){
                bombList.add(new Bomb(x, y));
                coordinates[x][y]=false;
            }else{
                z--;
            }
        }
        for (int z = 0;z <Math.ceil(percent); z++)
        {
            int x = coordGen.nextInt(10);
            int y = coordGen.nextInt(10);
            if (coordinates[x][y]==true){
                guardList.add(new Guard(x, y));
                coordinates[x][y]=false;
            }else{
                z--;
            }
        }
        System.out.println(goalList.get(0).getX()+ " , " +goalList.get(0).getY());
        //asks for each player's name
        for (int pNumb = 1 ; pNumb <= inputPlayer; pNumb++){
            System.out.println("Player "+pNumb+", enter your name.");
            while (true){
                int x = coordGen.nextInt(10);
                int y = coordGen.nextInt(10);
                if (coordinates[x][y]==true){
                    playerList.add(new Player(x, y,inputScan.next() , 100, 0, "Living", "N/A"));
                    break;
                }
            }
        }
        //prints the names of all contestents for intro
        for (int pCount = playerList.size();pCount >= 1; pCount--){
            if (pCount-1 == 0){
                System.out.print("and " + playerList.get(pCount-1).getName() + ", ");
                break;
            }
            System.out.print(playerList.get(pCount-1).getName()+", ");
        }
        
        System.out.println("you are time travellers, and teleported back in time and are being chased by the Mafia.");
        System.out.println("You have been trapped in a labyrinth, and are being hunted.");
        System.out.println("You need to move around and find the most pieces of the teleporter.");
        System.out.println("Be careful, there are many traps, and a few guards still looking for you");
        
        int f = 0;
        int p = 0;
        
        while (f == 0)
        {
            if (p == playerList.size()){
                p = 0;
            }
            if (playerList.size()==0){
                f =-1;
            }
            //randomly moves the bombs and guards
            for (int x = 0;x<bombList.size(); x++){
                coordinates[bombList.get(x).getX()][bombList.get(x).getY()]=true;
                while (true){
                    bombList.get(x).move();
                    if (coordinates[bombList.get(x).getX()][bombList.get(x).getY()]==true){
                        break;
                    }
                }
                coordinates[bombList.get(x).getX()][bombList.get(x).getY()]=false;
                guardList.get(x).moveGuard();
            }
            
            while (p<playerList.size()){
                if(playerList.size()==0) {
                    f=-1;
                    break;
                }
                //instruction statements
                System.out.println(playerList.get(p).getName()+ " , "+"your coordinates are ( " + playerList.get(p).getX() +","+playerList.get(p).getY()+" ), and your health is "+playerList.get(p).getHealth());
                System.out.println("If you would like to quit, press Q.");
                System.out.println("If another player would like to join, press J.");
                System.out.println (playerList.get(p).getName()+ ", Choose a direction to move");
                System.out.println ("N\nS\nE\nW");
                
                //variable for direction
                String movePrompt;
                while (true){
                    //input for direction
                    movePrompt = inputScan.next();
                    
                    //changes position based on  movement choice
                    if (movePrompt.equals("w") || movePrompt.equals("W")){
                        playerList.get(p).setX(playerList.get(p).movePlayer(playerList.get(p).getX(), -1));
                        break;
                    }else if (movePrompt.equals("e") || movePrompt.equals("E")){
                        playerList.get(p).setX(playerList.get(p).movePlayer(playerList.get(p).getX(), 1));
                        break;
                    }else if (movePrompt.equals("n") || movePrompt.equals("N")){
                        playerList.get(p).setY(playerList.get(p).movePlayer(playerList.get(p).getY(), 1));
                        break;
                    }else if (movePrompt.equals("s") || movePrompt.equals("S")){
                        playerList.get(p).setY(playerList.get(p).movePlayer(playerList.get(p).getY(), -1));
                        break;
                    }
                    //allows quiting of a player
                    else if (movePrompt.equals("q") || movePrompt.equals("Q")){
                        System.out.println(playerList.get(p).getName()+" couldn't handle the pressure and wimped out.");
                        System.out.println("");
                        playerList.remove(p);
                        break;
                    }
                    //allows a new player to join
                    else if (movePrompt.equals("j") || movePrompt.equals("J")){
                        System.out.println("New player, enter your name.");
                        while (true){
                            int x = coordGen.nextInt(10);
                            int y = coordGen.nextInt(10);
                            if (coordinates[x][y]==true){
                                playerList.add(new Player(x, y, inputScan.next() , 100, 0, "Living", "N/A"));
                                break;
                            }
                        }
                        break;
                    }else{
                        System.out.println("");
                        System.out.println("That's not an option right now");
                    }
                }
                //breaks out of main loop if a player is joining or quiting
                if (movePrompt.equals("q") || movePrompt.equals("Q")){
                    break;
                }else if (movePrompt.equals("j") || movePrompt.equals("J")){
                    break;
                }
                
                //cause of player death
                int cause=0;
                
                //responds with actions if player finds a goal, a bomb, another player, or a guard
                for (int x = 0; x<bombList.size(); x++){
                    if (bombList.get(x).getX() == playerList.get(p).getX() && bombList.get(x).getY() == playerList.get(p).getY()){
                        System.out.println("");
                        System.out.println (playerList.get(p).getName()+", you stepped on a bomb!");
                        playerList.get(p).setHealth(playerList.get(p).newHealth(-25));
                        cause = 1;
                    }else if (guardList.get(x).getX() == playerList.get(p).getX() && guardList.get(x).getY() == playerList.get(p).getY()){
                        System.out.println("");
                        System.out.println("");
                        System.out.println (playerList.get(p).getName()+", one of the guards found you and shot you!");
                        playerList.get(p).move();
                        playerList.get(p).setHealth(playerList.get(p).newHealth(-50));
                        if (playerList.get(p).getHealth()>0){
                            System.out.println("You have been teleported away for safety.");
                        }
                        cause = 2;
                    }else if (playerList.get(p).getX() < 0 || playerList.get(p).getX() > 9 || playerList.get(p).getY() < 0 || playerList.get(p).getY() > 9){
                        System.out.println("");
                        System.out.println("");
                        System.out.println (playerList.get(p).getName()+", you hit a wall that opened a trap door and you fell onto lots of spikes");
                        System.out.println ("Maybe you should learn how to stay in the room, moron.");
                        System.out.println("");
                        playerList.get(p).setHealth(-1000);
                        playerList.get(p).setCause("Stupidity");
                        playerList.get(p).setStatus("Dead");
                        break;    
                    }
                    if (playerList.get(p).getHealth()==0){
                        break;
                    }
                    for (int z = 0; z<goalList.size();z++){
                        if (playerList.get(p).getX() == goalList.get(z).getX() && playerList.get(p).getY() == goalList.get(z).getY()){
                            System.out.println(playerList.get(p).getName()+", you found a teleporter piece!");
                            System.out.println("You have been rewarded with health");
                            System.out.println("There are "+(goalList.size()-1)+" teleporter pieces remaining.");
                            if (playerList.get(p).getHealth()<=125){
                                playerList.get(p).setHealth(playerList.get(p).newHealth(75));
                            }
                            playerList.get(p).setScore(playerList.get(p).newScore());
                            goalList.remove(z);
                            if (goalList.size()==0){
                                f=1;
                                break;
                            }
                            break;
                        }
                    }
                }
                //used to break out if f doesn't equal 0
                if (f !=0){
                    break;
                }
                
                //used for directing code if players meet
                int fight = 0;
                
                //variable for player 2 in the event of a battle
                int pTwo = 0;
                for (;pTwo<playerList.size();pTwo++){
                    if(playerList.get(p).getX()==playerList.get(pTwo).getX() && playerList.get(p).getY()==playerList.get(pTwo).getY() && p!=pTwo){
                        System.out.println(playerList.get(p).getName()+" and "+playerList.get(pTwo).getName()+ " are battling!!!");
                        fight = 1;
                        cause = 3;
                        break;
                    }
                }
                
                //used to prevent index errors
                if (pTwo == playerList.size()){
                    pTwo = playerList.size()-1;
                }
                
                //sets up events for a battle
                if (fight == 1){
                    for (int a = 5;a>0; a--){
                        playerList.get(p).setHealth(playerList.get(p).newHealth(-coordGen.nextInt(21)));
                        System.out.println(playerList.get(p).getName()+", your health is now "+playerList.get(p).getHealth());
                        if (playerList.get(p).getHealth()<=0){
                            break;
                        }
                        playerList.get(pTwo).setHealth(playerList.get(pTwo).newHealth(-coordGen.nextInt(21)));
                        System.out.println(playerList.get(pTwo).getName()+", your health is now "+playerList.get(pTwo).getHealth());
                        if (playerList.get(pTwo).getHealth()<=0){
                            break;
                        }
                    }
                }
                if (playerList.get(pTwo).getHealth()<=0 && fight==1){
                    System.out.println(playerList.get(pTwo).getName()+ " was killed by "+playerList.get(p).getName());
                    System.out.println(playerList.get(p).getName()+" , your new coordinates are ( " + playerList.get(p).getX() +","+playerList.get(p).getY()+" ), and your health is "+playerList.get(p).getHealth());
                    System.out.println("");
                    
                    playerList.get(pTwo).setCause("Killed by " + playerList.get(p).getName());
                    playerList.get(pTwo).setStatus("Dead");
                        
                    if (scoreList.size() == 0){
                        scoreList.add(playerList.get(pTwo));
                    }else{
                        for (int x = 0; x<=scoreList.size();x++)
                        {
                            if (x==scoreList.size()){
                                scoreList.add(playerList.get(pTwo));
                                break;
                            }
                    
                            if (playerList.get(pTwo).getScore() > scoreList.get(x).getScore()){
                                scoreList.add(x, playerList.get(pTwo));
                                break;
                            }
                        }
                    }
                        
                    playerList.remove(pTwo);
                    break;
                }else if (playerList.get(p).getHealth()<=0){
                    if (fight == 1){
                        System.out.println(playerList.get(p).getName()+ " was killed by "+playerList.get(pTwo).getName());
                        playerList.get(p).setCause("Killed by " + playerList.get(pTwo).getName());
                        playerList.get(p).setStatus("Dead");
                    }else{
                        switch (cause){
                            case 1: System.out.println(playerList.get(p).getName()+" was killed by a bomb.");
                                    playerList.get(p).setCause("Bomb");
                                    playerList.get(p).setStatus("Dead");
                                    System.out.println("");
                                    break;
                            case 2: System.out.println(playerList.get(p).getName()+" was killed by a guard.");
                                    playerList.get(p).setCause("Guard");
                                    playerList.get(p).setStatus("Dead");
                                    System.out.println("");
                                    break;
                            default : System.out.print("");
                        }
                    }
                    if (scoreList.size() == 0){
                        scoreList.add(playerList.get(p));
                    }else{
                        for (int x = 0; x<=scoreList.size();x++)
                        {
                            if (x==scoreList.size()){
                                scoreList.add(playerList.get(p));
                                break;
                            }
                    
                            if (playerList.get(p).getScore() > scoreList.get(x).getScore()){
                                scoreList.add(x, playerList.get(p));
                                break;
                            }
                        }
                    }
                        
                    
                    playerList.remove(p);
                    break;            
                }else if (fight ==1){
                    playerList.get(pTwo).setX(coordGen.nextInt(10));
                    playerList.get(pTwo).setY(coordGen.nextInt(10));
                }
                System.out.print(playerList.get(p).getName()+" , your new coordinates are ( " + playerList.get(p).getX() +","+playerList.get(p).getY()+
                                 " ), your health is "+playerList.get(p).getHealth()+
                                 ", and your current score is "+playerList.get(p).getScore());
                System.out.println("");
                System.out.println("");
                p++;
                
                break;
                
            }
        }
        
        System.out.println("");
        //adds all players to the highscore list
        for (int h = 0;h<playerList.size();h++)
        {
            if (scoreList.size() == 0){
                scoreList.add(playerList.get(h));
            }else{
                for (int x = 0; x<=scoreList.size() && x<10;x++)
                {
                    if (x==scoreList.size()){
                        scoreList.add(playerList.get(h));
                        break;
                    }
                    if (playerList.get(h).getScore() > scoreList.get(x).getScore()){
                        scoreList.add(x, playerList.get(h));
                        break;
                    }
                }
            }
        }
        //checks if one player found all of the exits
        for (p=0;p<scoreList.size();p++){
            if (scoreList.get(p).getScore()==Math.ceil(percent)){
                System.out.println("Are you going wait for the others with or leave without them?");
                while (true){
                    if (inputScan.next().equals("wait")){
                        f = 3;
                        break;
                    }else if (inputScan.next().equals("leave")){
                        f = 2;
                        break;
                    }else{
                        System.out.println("That's not an option.");
                    }
                }
                break;
            }
        }
        //switch statement for end results
        switch (f){
            case 1: System.out.println ("The teleporter has been completed!");
                    break;
            case 2: System.out.println (playerList.get(p).getName() + " has completed the teleporter and escaped! Everyone else has been left behind.");
                    break;
            case 3: System.out.println (playerList.get(p).getName() + " has been generous, and everyone still living has escaped with them.");
                    break;
            default : System.out.println ("Everyone died!!\nTruly pathetic.");       
                      System.out.println ("The locations of the escapes!");
                      for (int x = 0;x<10 && x < goalList.size();x++)
                      {
                          System.out.println(goalList.get(x).getX()+" , "+goalList.get(x).getY());
                      }
        }
        System.out.println("The top ten players!");
        for (int x = 0;x<10 && x < scoreList.size();x++)
        {
            System.out.println("Rank "+(x+1)+": "+scoreList.get(x).getName()+"| Score: "+scoreList.get(x).getScore()+"| Status: " + scoreList.get(x).getStatus() + "| Cause of death: " + scoreList.get(x).getCause());
        }
    }
}