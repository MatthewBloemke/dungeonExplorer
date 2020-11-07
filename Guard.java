import java.util.Random;
/**
 * Write a description of class Guard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Guard extends Player{
    
    private int x;
    private int y;
    public Guard( int xPosition, int yPosition)
    {   
        super( xPosition,  yPosition,  "", 100, 0, "", "");
        x = xPosition;
        y = yPosition;
    }
    public void moveGuard()
    {
        Random direction = new Random();
        int coordChange = direction.nextInt(4);
        switch (coordChange){
            case 0: x++;
                    break;
            case 1: x--;
                    break;
            case 2: y++;
                    break;
            case 3: y--;
                    break;
            default: int z=x+y;
        }
        if (x>9){
            x--;
        }else if(x<0){
            x++;
        }else if (y>9){
            y--;
        }else if (y<0){
            y++;
        }
        
    }
}
