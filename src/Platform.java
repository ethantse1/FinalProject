import java.awt.*;

public class Platform {
    private Rectangle r;
    private boolean kill;
    private boolean disappear;
    public Platform(int x, int y, int width, int height, boolean k){
        r = new Rectangle(x,y,width,height);
        kill = k;

    }
    public Platform(int x, int y, int width, int height, boolean k, boolean dis){
        r = new Rectangle(x,y,width,height);
        kill = k;
        disappear = dis;

    }
    public Rectangle getR(){

        return r;
    }
    public void drawR(Graphics2D g2){
        //Color a = new Color(255,204,51);
        //g2.setColor(a);
        if (kill) {
            g2.setColor(Color.RED);
        }else if (disappear){
            g2.setColor(Color.YELLOW);
        }else{
            g2.setColor(Color.BLACK);
        }

        g2.fill(r);
    }
    public boolean getKill(){
        return kill;
    }

    public boolean isDisappear() {
        return disappear;
    }
}

