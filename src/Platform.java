import java.awt.*;

public class Platform {
    public static final int NORTH = 90, SOUTH = 270, WEST = 180, EAST = 0, NE = 45, NW = 135, SW = 225, SE = 315;
    private Rectangle r;
    private boolean kill;
    private boolean disappear;
    private boolean dissapearing;
    private boolean goal;
    private int time;
    private boolean ground;
    private int x,y,radius;
    //private Rectangle Rect;
    private boolean move;
    private boolean teleport;
    private int linked = 0;
    private Point loc;
    private int speed;
    private int dir, picOrientation;
    private int rightborder,leftborder;
    private boolean invisible;

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
    public Platform(int x, int y, int width, int height, boolean k, boolean dis,boolean go,boolean suelo,boolean invis){
        r = new Rectangle(x,y,width,height);
        kill = k;
        disappear = dis;
        time = 0;
        goal = go;
        ground = suelo;
        invisible = invis;

    }
    public Platform(int x, int y, int width, int height, boolean k, boolean dis,boolean go,boolean groundd,boolean tele, int link){
        r = new Rectangle(x,y,width,height);
        this.x = x;
        this.y = y;
        kill = k;
        disappear = dis;
        time = 0;
        goal = go;
        ground = groundd;
        teleport = tele;
        linked = link;

    }
    public Platform(int x, int y, int width,int height,int dir, int speed, int left, int right,boolean mo){
        r = new Rectangle(x,y,width,height);
        this.loc = new Point(x,y);
        speed = r.height;
        //id = nextID;
        this.dir = dir;
        move = mo;
        setSpeed(speed);
        this.rightborder = right;
        this.leftborder = left;
    }
    public void update() {
        int dx = (int) (Math.cos(Math.toRadians(dir)) * speed);
        int dy = -(int) (Math.sin(Math.toRadians(dir)) * speed);
        loc.translate(dx, dy);
    }
    public Point getLoc() {
        return new Point(x,y);
    }

    public int getRightBorder(){
        return rightborder;
    }

    public int getLeftBorder() {
        return leftborder;
    }

    public void setDir(int newDir) {
        dir = newDir;
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
    public boolean getDissapearing(){
        return dissapearing;
    }
    public void setDissapearing(boolean xxx){
        dissapearing = xxx;
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
        }else if (invisible){
            g2.setColor(Color.WHITE);
        }else{
            g2.setColor(Color.GRAY.darker());
        }
//        double rotationRequired = Math.toRadians(picOrientation - dir);
//        double halfWidth = r.getWidth() / 2;
//        double halfHeight = r.getHeight() / 2;
//        g2.rotate(rotationRequired, loc.x + halfWidth, loc.y + halfHeight);
//        g2.rotate(-rotationRequired, loc.x + halfWidth, loc.y + halfHeight);

        g2.fill(r);

    }

    public Rectangle getBoundingRectangle() {
        Rectangle box = null;
        if (picOrientation % 180 != 0)
            if (facingEast() || facingWest())
                box = new Rectangle(loc.x, loc.y, (int)r.getHeight(), 25);
            else
                box = new Rectangle(loc.x, loc.y, (int)r.getWidth(), 25);
        else if (facingEast() || facingWest())
            box = new Rectangle(loc.x, loc.y, (int)r.getWidth(), 25);
        else
            box = new Rectangle(loc.x, loc.y, (int)r.getHeight(), 25);

        return box;

    }

    public void setLoc(Point loc) {
        this.loc = loc;
    }


    public boolean facingEast() {
        return dir % 360 < 90 || dir % 360 > 270;
    }

    public boolean facingWest() {
        return dir % 360 > 90 && dir % 360 < 270;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int newSpeed) {
        speed = newSpeed;
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
    public int getLinked(){
        return linked;
    }

    public boolean isMove() {
        return move;
    }
    public boolean getInvis(){
        return invisible;
    }
}