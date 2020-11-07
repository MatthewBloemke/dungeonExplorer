import java.util.Random;
/**
 * Write a description of class player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    private int x;
    private int y;
    private int health;
    private String name;
    private String s;
    private String c;
    private int score;
    private Random coordGen = new Random();
    /**
     * Constructor for objects of class player
     */
    public Player(int xPosition, int yPosition, String firstName, int playerHealth, int playerScore, String status, String cause)
    {
        // initialise instance variables
        x = xPosition;
        y = yPosition; 
        name = firstName;
        health = playerHealth;
        score = playerScore;
        s = status;
        c = cause;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getHealth()
    {
        return health;
    }
    public String getName()
    {
        return name;
    }
    public int getScore()
    {
        return score;
    }
    public String getStatus()
    {
        return s;
    }
    public String getCause()
    {
        return c;
    }
    public int movePlayer(int currentPosition, int moveSpaces)
    {
        return currentPosition + moveSpaces;
    }
    public int newHealth( int healthLoss)
    {
        return health + healthLoss;
    }
    public int newScore()
    {
        return score+1;
    }
    public void setHealth( int playerHealth)
    {
        health = playerHealth;
    }
    public void setX (int xPosition)
    {
        x = xPosition;
    }
    public void setY (int yPosition)
    {
        y = yPosition;
    }
    public void setName (String firstName)
    {
        name = firstName;
    }
    public void setScore (int playerScore)
    {
        score = playerScore;
    }
    public void setStatus (String status)
    {
        s = status;
    }
    public void setCause (String cause)
    {
        c = cause;
    }
    public void move()
    {
        x = coordGen.nextInt(10);
        y = coordGen.nextInt(10);
    }
}
