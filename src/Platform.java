import java.awt.*;

public class Platform {
    private Rectangle r;
    private boolean kill;
    private boolean disappear;
    private boolean goal;
    private int time;
    private boolean ground;
    //private int x,y,speed, vx, vy;
    private boolean move;
    private boolean teleport;
    private int x,y;

    public Platform(int x, int y, int width, int height, boolean k){
        this.x = x;
        this.y = y;
        r = new Rectangle(x,y,width,height);
        kill = k;

    }
    public Platform(int x, int y, int width, int height, boolean k, boolean dis){
        r = new Rectangle(x,y,width,height);
        kill = k;
        disappear = dis;
        time = 0;

    }
    public Platform(int x, int y, int width, int height, boolean k, boolean dis,boolean go){
        r = new Rectangle(x,y,width,height);
        kill = k;
        disappear = dis;
        time = 0;
        goal = go;

    }
    public Platform(int x, int y, int width, int height, boolean k, boolean dis,boolean go,boolean suelo){
        r = new Rectangle(x,y,width,height);
        kill = k;
        disappear = dis;
        time = 0;
        goal = go;
        ground = suelo;

    }
    public Platform(int x, int y, int width, int height, boolean k, boolean dis,boolean go,boolean suelo,boolean tele){
        r = new Rectangle(x,y,width,height);
        this.x = x;
        this.y = y;
        kill = k;
        disappear = dis;
        time = 0;
        goal = go;
        ground = suelo;
        teleport = tele;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getR(){

        return r;
    }
    public void addTime(){
        time++;
    }
    public int getTime(){
        return time;
    }
    public void drawR(Graphics2D g2){
        //Color a = new Color(255,204,51);
        //g2.setColor(a);
        if (kill) {
            g2.setColor(Color.RED);
        }else if (disappear) {
            g2.setColor(Color.YELLOW);
        }else if (goal) {
            g2.setColor(Color.GREEN);
        }else if (ground) {
            g2.setColor(Color.GREEN.darker().darker());
        }else if (teleport) {
            g2.setColor(Color.BLUE.darker());
        }else{
            g2.setColor(Color.BLACK);
        }

        g2.fill(r);

    }

    public boolean getKill(){
        return kill;
    }

    public boolean isDisappear() {
        if (time >2) {
            return disappear;
        }
        else {
            time++;
            return false;
        }
    }

    public boolean isGoal() {
        return goal;
    }

    public boolean isGround() {
        return ground;
    }

    public boolean isTeleport() {
        return teleport;
    }

    public Platform(int x, int y, int width, int height, int speed, int vx, boolean mo) {
        r = new Rectangle(x,y,width, height);
        move = mo;
    }

//    public boolean isMove() {
//        if(move) {
//            x = x + vx;
//            if (x + speed >= 800 && vx > 0) {
//                vx = -vx;
//            } else if (x < 0 && vx < 0) { //or "x+diameter <=0 && -vx>0
//                vx = -vx;
//            }
//        }
//        return false;
//    }



}