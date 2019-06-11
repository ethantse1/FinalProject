//import javafx.scene.shape.Circle;

import javax.sound.sampled.Line;
import java.awt.*;
import java.awt.geom.Area;




public class BallBoy {


    private int x,y,radius;
    private Rectangle Rect;
    private Point spawn;
    private boolean isGrounded;


    public BallBoy(int xx, int yy, int rad) {
        Rect = new Rectangle(xx,yy,rad, rad);
        radius = rad;
        x = xx;
        y = yy;
        spawn = new Point(0,0);
    }
    public void drawB(Graphics2D g2){
        g2.fillRect(x,y,radius,radius);
    }
    public Point getCenter(){
        return new Point(x+radius, y+radius);
    }

    public Point getLoc() {
        return new Point(x,y);
    }
    public void moveY(int yy){
        y -= yy;
        Rect = new Rectangle(x,y,radius, radius);

    }
    public void moveX(int xx){
        x += xx;
        Rect = new Rectangle(x,y,radius, radius);

    }
    public Rectangle getC(){
        return Rect;
    }
    public int getRadius(){
        return radius;
    }
    public void spawn(){
        x = spawn.x;
        y = spawn.y;
    }
    public void setSpawn(int xx, int yy){
        spawn.setLocation(xx,yy);
    }
    public void ground(){
        isGrounded = true;
    }
    public void Fground(){
        isGrounded = false;
    }
    public void setLocation(int xx, int yy){
        x = xx;
        y = yy;
    }
    //comment for commmitting
}