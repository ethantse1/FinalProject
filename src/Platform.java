import java.awt.*;

public class Platform {
    private Rectangle r;
    private boolean kill;
    public Platform(int x, int y, int width, int height, boolean k){
        r = new Rectangle(x,y,width,height);
        kill = k;

    }
    public Rectangle getR(){
        return r;
    }
    public void drawR(Graphics2D g2){
        if (kill){
            g2.setColor(Color.RED);
        }else{
            g2.setColor(Color.BLACK);
        }
        g2.fill(r);
    }
    public boolean getKill(){
        return kill;
    }
}

