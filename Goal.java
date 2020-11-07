import java.util.Random;
/**
 * Write a description of class Health here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Goal extends Player
{
    private int x;
    private int y;
    public Goal(int xPosition, int yPosition)
    {
        super( xPosition, yPosition, "", 0, 0, "", "");
        x = xPosition;
        y = yPosition;
    }
}
