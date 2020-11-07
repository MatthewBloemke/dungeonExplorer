import java.util.Random;
/**
 * Write a description of class Bomb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bomb extends Player
{
    // instance variables - replace the example below with your own
    private int x;
    private int y;
    public Bomb( int xPosition, int yPosition)
    {
        super(xPosition, yPosition, "" , 0 , 0, "", "");
        x = xPosition;
        y = yPosition;
    }
}
