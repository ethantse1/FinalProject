import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;
import javax.swing.event.ChangeListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Dialog;

public class BallMain extends JPanel implements KeyListener, ActionListener{
    private Timer timer;
    private BallBoy bb;
    private boolean jumped = false;
    private boolean left = false;
    private boolean right = false;
    private int jumpHeight = 17;
    private boolean touchB = false;
    private boolean touchT = false;
    private boolean touchR = false;
    private boolean touchL = false;
    private int jumps = 2;
    private int gravity = 0;
    private static JFrame window = new JFrame("window");
    private int deaths;
    private int jumping = 0;
    private int level = 1;
    private String name;
    private ArrayList<Platform> plat;

    public static final int FRAMEWIDTH = 1000, FRAMEHEIGHT = 600;
    public BallMain() {
        timer = new Timer(1000/60, this);
        bb = new BallBoy(50,300, 20);
        bb.setSpawn(50,300);
        timer.start();
        addKeyListener(this);
        plat = new ArrayList<Platform>();
        level();
        deaths = 0;
        name = "PLATFORMER!";

        JOptionPane.showMessageDialog(null,"D/Right = Right" +
                        "\nA/Left = Left" +
                        "\nW/Space/Up = Jump" +
                        "\nR = Reset" +
                        "\nN = Next Level" +
                        "\nB = Previous Level",
                "Controls", JOptionPane.INFORMATION_MESSAGE);
    }
    public void level(){
        plat.clear();
        plat.add(new Platform(-10, 0,10,1000,false));
        //plat.add(new Platform(getWidth(), 0,20,1000,false));


        if(level == 1){
            plat.add(new Platform(250, 450,40,50,false));
            plat.add(new Platform(350, 450,40,50,false));
            plat.add(new Platform(450,400,40,100,false));
            plat.add(new Platform(550,350,40,150,false));
            plat.add(new Platform(700,350,40,150,false));
            plat.add(new Platform(900,450,40,50,false,false,true));
            plat.add(new Platform(0, 500, 1000, 100, false,false,false,true));

        }
        if(level == 2){
            name = "Double Jump";
            plat.add(new Platform(300,450,40,50,false));
            plat.add(new Platform(400,400,40,100,false));
            plat.add(new Platform(600,300,40,200,false));
            plat.add(new Platform(800,200,40,50,false,false,true));
            plat.add(new Platform(0,500,1000,100,false,false,false,true));
        }
        if (level == 3) {
            name = "I'm gonna start naming levels now";
            plat.add(new Platform(750, 150, 40, 1000, false));
            plat.add(new Platform(350, 300, 40, 1000, false));
            plat.add(new Platform(550, 200, 40, 1000, false));
            plat.add(new Platform(0, 500, 1000, 100, false,false,false,true));
//        plat.add(new Platform(0,400,300,40, false));
//        plat.add(new Platform(500,300,40,100, false));
//        plat.add(new Platform(700,200,40,100, false));
//        plat.add(new Platform(900,150,40,100, true));



            plat.add(new Platform(900, 100, 40, 50, false, false, true));
            plat.add(new Platform(900,150,40,20,false));
           // plat.add(new Platform(790,500,1000,100,true));
        }
        if (level == 4) {
            name = "You see red, you're dead";
            plat.add(new Platform(750, 200, 40, 1000, true));
            plat.add(new Platform(350, 300, 40, 1000, false));
            plat.add(new Platform(550, 200, 40, 1000, false));
            plat.add(new Platform(900,450,40,50,false,false,true));
            plat.add(new Platform(0, 500, 1000, 100, false,false,false,true));
        }
        if (level == 5) {
            name = "You see yellow, you're mellow";
            plat.add(new Platform(400,150,40,500,false,true));
            plat.add(new Platform(900,530,40,50,false,false,true));
            plat.add(new Platform(0, 500, 700, 100, false,false,false,true));
            plat.add(new Platform(800,500,200,30,false,false,false,true));
            plat.add(new Platform(700,570,300,30,false,false,false,true));
            plat.add(new Platform(700,500,100,30,false,true));
            plat.add(new Platform(940,530,60,40,false,false,false,true));

        }
        if (level == 6) {
            name = "Generic Level #36";
            plat.add(new Platform(350, 300, 40, 1000, false));
            plat.add(new Platform(550, 200, 40, 1000, false));
            plat.add(new Platform(900, 100, 40, 50, false, false, true));
            plat.add(new Platform(900,150,40,20,false));
            plat.add(new Platform(750,150,40,30,false,true));
            plat.add(new Platform(0, 500, 1000, 100, false,false,false,true));
        }
        if (level == 7) {
            name = "THE FLOOR IS LAVA!";
            plat.add(new Platform(960,450,40,50,false,false,true));
            plat.add(new Platform(0,480,150,120,false));
            plat.add(new Platform(150,520,750,100,true));
            plat.add(new Platform(850,480,150,120,false));
            //150 - 850
            for (int i = 155; i <= 795; i+=64*3.1) {
                plat.add(new Platform(i,480,50,30,false,true));
            }
        }
        if (level == 8) {
            name = "Ketchup and Mustard";
            plat.add(new Platform(750, 150, 40, 1000, true));
            plat.add(new Platform(350, 300, 40, 1000, false));
            plat.add(new Platform(550, 200, 40, 1000, false));
            plat.add(new Platform(700,150,40,30,false,true));
            plat.add(new Platform(800,150,40,30,false,true));
            plat.add(new Platform(900, 100, 40, 30, false, false, true));
            plat.add(new Platform(0, 500, 1000, 100, false,false,false,true));
        }
        if (level == 9) {
            name = "Don't fire til you see the whites of their eyes";
            //plat.add(new Platform(500,300,40,200,false,false,false,false,true));
            //plat.add(new Platform(900,300,40,200,false,false,false,false,true));
            plat.add(new Platform(200,450,40,50,false));
            plat.add(new Platform(300,400,40,100,false));
            plat.add(new Platform(450,300,40,200,false));
            plat.add(new Platform(550,300,40,20,false,false,false,false,true));//invis block
            plat.add(new Platform(700,450,40,40,false));
            plat.add(new Platform(740,350,40,150,false));
            plat.add(new Platform(740,310,40,40,false,false,true));
            plat.add(new Platform(0,500,1000,100,false,false,false,true));
            plat.add(new Platform(490,499,250,101,true));
        }
        if (level == 10) {
            name = "Portal 2";
            //teleport level
            plat.add(new Platform(200,350,40,50,false,false,false,false,true,1));
            plat.add(new Platform(700,350,40,50,false,false,false,false,true,1));
            plat.add(new Platform(0,500,1000,100,false));
            plat.add(new Platform(240,499,460,101,true));
            plat.add(new Platform(900,459,40,41,false,false,true));



        }
        if (level == 11) {
            name = "The Office Season 10";
            plat.add(new Platform(960,450,40,50,false,false,true));
            plat.add(new Platform(0,480,150,120,false));
            plat.add(new Platform(150,520,700,100,true));
            plat.add(new Platform(150,560,700,40,false,false,false,true));
            plat.add(new Platform(850,480,150,120,false));

            //150 - 850
            for (int i = 155; i <= 795; i+=64) {
                plat.add(new Platform(i,480,50,30,false,true));
            }
            for (int i = 155; i <= 795; i+=64) {
                plat.add(new Platform(i,340,50,30,false,true));
            }
            plat.add(new Platform(150,0,700,340,true));
            plat.add(new Platform(150,0,700,300,false,false,false,true));
            plat.add(new Platform(450,240,25,25,false,false,false,false,true,1));
            plat.add(new Platform(525,240,25,25,false,false,false,false,true,1));

            //150-850, width 700
            plat.add(new Platform(225,130,125,80,false,false,false,false,true));
            plat.add(new Platform(650,130,125,80,false,false,false,false,true));
            plat.add(new Platform(250,150,75,40,false,false,false,false,true,1));
            plat.add(new Platform(675,150,75,40,false,false,false,false,true,1));
            //plat.add(new Platform(150,340,700,17,false));
        }
//        if(level == 11){
//            plat.add(new Platform(500,250,40,50,0,10,300,700,true));
//            plat.add(new Platform(0,500,1000,100,false,false,false,true));    // tests moving obstacle
//        }

        if(level == 12){
             name = "You should have gone for the head";
            plat.add(new Platform(100,400,40,50,false,false,false,false,true,1));
            plat.add(new Platform(250,0,40,500,false));
            plat.add(new Platform(400,150,40,50,false,false,false,false,true,1));
            plat.add(new Platform(400,300,40,200,false));
            plat.add(new Platform(600,200,40,30,false,true));
            plat.add(new Platform(800,100,40,30,false,true));
            plat.add(new Platform(900,50,40,30,false,false,true));
            plat.add(new Platform(0,500,1000,100,false,false,false,true));
            plat.add(new Platform(290,499,810,101,true));
        }
        if(level == 13){
            name = "Hotel? Trivago";
            plat.add(new Platform(200,450,40,50,false));
            plat.add(new Platform(400,350,40,30,false,true));
            plat.add(new Platform(600,400,40,30,false,true));
            plat.add(new Platform(800,300,40,30,false,true));
            plat.add(new Platform(600,150,40,30,false,true));
            plat.add(new Platform(400,50,40,30,false,false,true));
            plat.add(new Platform(400,80,40,30,true));
            plat.add(new Platform(0,500,1000,100,false,false,false,true));
            plat.add(new Platform(240,499,760,101,true));
        }
        if(level == 14){
            name = "Dropper";
            plat.add(new Platform(200,0,40,500,false));
            plat.add(new Platform(160,450,40,50,false,false,false,false,true,1));
            plat.add(new Platform(600,0,40,50,false,false,false,false,true,1));
            plat.add(new Platform(240,200,440,30,true));
            plat.add(new Platform(660,400,340,30,true));
            plat.add(new Platform(0,500,240,100,false));
            plat.add(new Platform(240,499,760,101,false,false,true));
        }
        if(level == 15){
            name = "More than Meets the Eye";
            bb.setSpawn(50,300);
            bb.spawn();
            plat.add(new Platform(0,400,150,200,false));
            plat.add(new Platform(0,400,850,30,false));
            plat.add(new Platform(960,350,40,50,false,false,false,true));
            plat.add(new Platform(910,400,90,30,false));
            plat.add(new Platform(810,150,40,250,true));
            plat.add(new Platform(200,300,40,30,false));
            plat.add(new Platform(400,200,40,30,false));
            plat.add(new Platform(600,100,40,30,false));
            plat.add(new Platform(700,100,40,30,false,true));
            plat.add(new Platform(150,599,850,1,true));
            plat.add(new Platform(830,490,40,30,false));
            plat.add(new Platform(790,520,40,30,false));
            plat.add(new Platform(720,490,40,30,false));
            plat.add(new Platform(660,570,40,30,false));
            plat.add(new Platform(570,570,40,30,false));
            plat.add(new Platform(510,520,40,30,false));
            plat.add(new Platform(450,550,40,30,false));
            plat.add(new Platform(320,570,40,30,false));
            plat.add(new Platform(400,570,40,30,false));
            plat.add(new Platform(250,570,40,30,false));
            plat.add(new Platform(150,550,40,50,false,false,true));
            plat.add(new Platform(150,599,850,1,true));
            plat.add(new Platform(150,430,850,170,false,true));
        }
        if(level == 16){
            name = "MERRY CHRISTMAS!";
            bb.setSpawn(950,525);
            bb.spawn();
            //plat.add(new Platform(0,550,900,50,true));
            for (int i = 50; i <= 900; i+=90) {
                plat.add(new Platform(i,550,40,50,true));
            }
            for (int i = 0; i <= 900; i+=90) {
                plat.add(new Platform(i,549,50,51,false,false,false,true));
            }
            //plat.add(new Platform(150,395,850,30,false));
            for (int i = 200; i <=870; i+=90) {
                plat.add(new Platform(i,395,40,30,true));
            }
            for (int i = 150; i <=910; i+=90) {
                plat.add(new Platform(i,394,50,32,false,false,false,true));
            }
            plat.add(new Platform(870,394,130,32,false,false,false,true));
            plat.add(new Platform(0,475,40,30,false,false,false,true));
            plat.add(new Platform(0,435,40,40,false,false,true));
            plat.add(new Platform(0,395,40,45,false,false,false,false,true,1));
            plat.add(new Platform(40,395,40,110,false,false,false,true));
            plat.add(new Platform(900,549,100,52,false,false,false,true));
            plat.add(new Platform(880,80,120,30,false,false,false,false,true)); //860-100 = 760
            for (int i = 340; i <= 860; i+=90) {
                plat.add(new Platform(i,225,40,30,true));
            }
            for (int i = 290; i <= 860; i+=90) {
                plat.add(new Platform(i,224,50,32,false,false,false,true));
            }
            plat.add(new Platform(150,50,620,30,false,false,false,true));
            plat.add(new Platform(876,50,90,30,false,false,false,true));
            plat.add(new Platform(870,50,40,206,false,false,false,true));
            plat.add(new Platform(960,35,40,45,false,false,false,false,true,1));
            plat.add(new Platform(0,549,50,52,false,false,false,true));
            plat.add(new Platform(150,0,40,395,false,false,false,true));
            //plat.add(new Platform(0,550,1000,50,false,false,false,true));
        }
        if(level == 17){
            name = "The Last One";
            bb.setSpawn(50,150);
            bb.spawn();
            plat.add(new Platform(0,270,960,30,false));
            plat.add(new Platform(960,250,40,50,false,false,false,false,true,1));
            plat.add(new Platform(850,300,150,30,false,true));
            plat.add(new Platform(960,330,40,50,false,false,true));
            plat.add(new Platform(920,330,40,50,false));
            plat.add(new Platform(920,380,80,30,false));
            plat.add(new Platform(920,50,40,220,false));
            //plat.add(new Platform(780,50,140,30,true));
            plat.add(new Platform(100,550,900,50,true));
            plat.add(new Platform(240,240,680,30,true));
            plat.add(new Platform(0,550,100,50,false,false,false,true));
            plat.add(new Platform(0,300,40,50,false,false,false,false,true,1));
            plat.add(new Platform(0,350,40,30,false,false,false,false,true));
            plat.add(new Platform(40,350,660,30,false));
            plat.add(new Platform(920,300,40,30,true));
            plat.add(new Platform(200,220,40,50,false));
            plat.add(new Platform(400,150,40,30,false,true));
            plat.add(new Platform(600,100,40,30,false,true));
            plat.add(new Platform(780,20,40,30,false,true));
            plat.add(new Platform(150,500,40,30,false,true));
            plat.add(new Platform(200,450,40,30,false,true));
            plat.add(new Platform(250,500,40,30,false,true));
            plat.add(new Platform(300,450,40,30,false,true));
            plat.add(new Platform(400,500,40,30,false,true));
            plat.add(new Platform(450,520,40,30,false));
            plat.add(new Platform(500,500,40,30,false,true));
            plat.add(new Platform(575,500,40,30,false,true));
            plat.add(new Platform(650,500,40,30,false,true));
            plat.add(new Platform(700,450,40,30,false,true));
            plat.add(new Platform(750,500,40,30,false,true));
            plat.add(new Platform(850,500,40,30,false,true));
            plat.add(new Platform(775,400,40,30,false,true));
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        window.setTitle("Level: " + level + "                 " + name + "                 Deaths: " + deaths);
        bb.drawB(g2);
        for (Platform p: plat){
            p.drawR(g2);
        }
    }
    public void actionPerformed(ActionEvent e) {
        Point tr = new Point(bb.getLoc().x + bb.getRadius(), bb.getLoc().y);
        Point tl = new Point(bb.getLoc().x, bb.getLoc().y);
        tl.translate(1, 0);
        tr.translate(-1, 0);
////////
        int iii = 0;
        for (Platform p: plat) {

            if (p.getR().getBounds2D().contains(tr) || p.getR().getBounds2D().contains(tl)) {
                if (!p.getKill()) {
                    touchT = true;
                }
            }
            bb.moveY(-1);
            bb.moveX(1);
            if (bb.getC().intersects(p.getR()) && p.isDisappear() || p.isDisappear() && p.getDissapearing()){
                plat.get(iii).addTime();
                plat.get(iii).setDissapearing(true);

                if (p.getTime()>5) {
                    plat.remove(p);
                    break;
                }
            }
            bb.moveX(-2);

            if (bb.getC().intersects(p.getR()) && p.isDisappear()){
                plat.get(iii).addTime();
                plat.get(iii).setDissapearing(true);
                if (p.getTime()>5) {
                    plat.remove(p);
                    break;
                }
            }
            bb.moveX(1);
            bb.moveY(1);
            iii++;
        }
        //System.out.println(touchT);

        tl.translate(-1, 0);
        tr.translate(1, 0);
        if (jumping > 0) {
            if(touchT){
                jumping = 0;
            }else {

                bb.moveY(jumpHeight);
            }

        }
        touchT = false;
        Point bl = new Point(bb.getLoc().x, bb.getLoc().y + bb.getRadius());
        Point br = new Point(bb.getLoc().x + bb.getRadius(), bb.getLoc().y + bb.getRadius());

        touchB = false;
//        touchT = false;
        touchR = false;
        touchL = false;
        bl = new Point(bb.getLoc().x, bb.getLoc().y + bb.getRadius());
        br = new Point(bb.getLoc().x + bb.getRadius(), bb.getLoc().y + bb.getRadius());
        tr = new Point(bb.getLoc().x + bb.getRadius(), bb.getLoc().y);
        tl = new Point(bb.getLoc().x, bb.getLoc().y);
        int i = 0;
        for(Platform p: plat){
            if (p.getKill() && p.getR().getBounds2D().intersects(bb.getC())){
                bb.spawn();
                deaths++;
                level();
                break;
            }
            if (p.isGoal() && p.getR().getBounds2D().intersects(bb.getC())){
                level++;
                bb.spawn();
                level();
                break;
            }
//            if(p.isMove()) {
//                p.update();
//                if (p.getLoc().x > p.getRightBorder()) {
//                    if (p.facingEast())
//                        p.facingWest();
//                }
//                if(p.getLoc().x < p.getLeftBorder()){
//                    if(p.facingWest())
//                        p.facingEast();
//                }
//            }
        }
        for (i = 0; i < 3;) {
            for(Platform p: plat){
                if (bb.getLoc().x-i<0 || p.getR().contains(new Point(bb.getLoc().x-i, bb.getLoc().y + bb.getRadius()-1)) || p.getR().contains(new Point(bb.getLoc().x-i, bb.getLoc().y+1))){
                    if (p.getKill()|| p.isGoal() || p.isTeleport()){
                        i++;
                    }
                    if (!p.getInvis()) {
                        touchL = true;
                        break;
                    }
                }
            }
            if (touchL){
                break;
            }
            else{
                i++;
            }
        }
        int j;
        for (j = 0; j < 3;) {
            for (Platform p : plat) {
                if (bb.getLoc().x + bb.getRadius() + j > getWidth() || p.getR().contains(new Point(bb.getLoc().x + bb.getRadius() + j, bb.getLoc().y + bb.getRadius() - 1)) || p.getR().contains(new Point(bb.getLoc().x + bb.getRadius() + j, bb.getLoc().y + 1))) {
                    if (p.getKill() || p.isGoal() || p.isTeleport()){
                        j++;
                    }
                    if (!p.getInvis()) {
                        touchR = true;
                        break;
                    }
                }
            }
            if (touchR) {
                break;
            } else {
                j++;
            }
        }
        if (left) {

            bb.moveX(-i);

        }
        if (right) {

            bb.moveX(j);

        }
        bl = new Point(bb.getLoc().x, bb.getLoc().y + bb.getRadius());
        br = new Point(bb.getLoc().x + bb.getRadius(), bb.getLoc().y + bb.getRadius());
        tr = new Point(bb.getLoc().x + bb.getRadius(), bb.getLoc().y);
        tl = new Point(bb.getLoc().x, bb.getLoc().y);
        bl.translate(1,0);
        br.translate(-1,0);
        for (Platform p: plat){
            if ((p.getR().getBounds2D().contains(br) || p.getR().getBounds2D().contains(bl)) && !p.getKill() && !p.isGoal()){
                if (!p.getInvis()) {
                    gravity = 0;
                    jumped = false;
                    jumps = 2;
                    jumping = 0;
                    touchB = true;
                }
            }
        }
        bl.translate(-1,0);
        br.translate(1,0);
        if (!touchB){
            if (gravity < 12) {
                if (jumpHeight > 0) {
                    jumpHeight--;
                }else{
                    gravity++;
                }
            }
            bb.moveY(-gravity);

        }

        else{
            gravity = 0;
        }
        if (gravity == jumpHeight + 1){
            jumped = false;
        }







        for (Platform p : plat) {
            for (int ii = 0; ii < 8; ii++) {
                if (p.getR().getBounds2D().contains(new Point(bb.getLoc().x + bb.getRadius() - 2, bb.getLoc().y - 2)) || p.getR().getBounds2D().contains(new Point(bb.getLoc().x + 2, bb.getLoc().y - 2))) {
                    //bb.moveY(-1);
                    jumpHeight = 0;
                    //System.out.println(1);
                }
            }
////
            for (int ii = 0; ii < 10; ii++) {
                if (p.getR().getBounds2D().contains(new Point(bb.getLoc().x + 2, bb.getLoc().y + bb.getRadius()-1)) || p.getR().getBounds2D().contains(new Point(bb.getLoc().x + bb.getRadius() - 2, bb.getLoc().y + bb.getRadius()-1))) {

                    if (!p.isTeleport() && !p.isGoal() && !p.getKill() && !p.getInvis()) {
                        bb.moveY(1);
                        //System.out.println(i);
                        jumps = 2;
                        jumped = false;
                        touchB = true;
                    }
                }
            }

//        }
////        for (Platform p : plat) {
//            bl.translate(0, -2);
//            br.translate(0, -2);
//            tl.translate(0, 2);
//            tr.translate(0, 2);
//            if (bb.getC().intersects(p.getR()) && p.getKill()) {
//                bb.spawn();
//                jumps = 2;
//                break;
//            }
////            if (p.getR().getBounds2D().contains(tr) || p.getR().getBounds2D().contains(br)) {
////                touchR = true;
////            }
////            if (p.getR().getBounds2D().contains(bl) || p.getR().getBounds2D().contains(tl)) {
////                touchL = true;
////            }

//            tl.translate(0,-2);
//            tr.translate(0,-2);
//            bl.translate(0,1);
//            br.translate(0,1);
//            bl.translate(1,0);
//            br.translate(-1,0);
////
//            if (p.getR().getBounds2D().contains(bl) || p.getR().getBounds2D().contains(br)) {
//                if (p.getKill()) {
//                    bb.spawn();
//                    break;
//                }
//                touchB = true;
//                System.out.println(bb.getLoc().x);
//                //bb.ground();
//
//                //System.out.println(bl.x);
//            } else {
//                //bb.Fground();
//            }
//            if (p.getR().getBounds2D().contains(bl) || p.getR().getBounds2D().contains(br)) {
//                if (p.isDisappear()) {
//                    plat.remove(p);
//                    break;
//                }
//                touchB = true;
//                //System.out.println(bl.x);
//            }
//            bl.translate(0, 1);
//            br.translate(0, 1);
////

            bl.translate(1, 0);
            br.translate(-1, 0);
            if (p.getR().getBounds2D().contains(bl) || p.getR().getBounds2D().contains(br)) {
                if (!p.getInvis()) {
                    touchB = true;
                }
            }
            bl.translate(-1, 0);
            br.translate(1, 0);
        }
////        }
//            if (!touchB) {
//                if (gravity < jumpHeight + 12) {
//                    gravity++;
//                    if (gravity > jumpHeight+1){
//                        jumped = false;
//                    }
//                }
//            } else {
//                gravity = 0;
//            }
//            if (touchB) {
//                jumps = 2;
//                gravity = 0;
//                jumping = 0;
//            }
//            if (jumping > 0) {
//                if (touchT && !touchB) {
//                    jumping = 0;
//                } else {
//                    bb.moveY(jumpHeight);
//                }
//            }
//        }
        if (touchB){
            jumped = false;
            jumps = 2;
        }
        int link = 0;
        Platform pp = null;
        for (Platform p: plat){
            if (p.isTeleport() && p.getR().getBounds2D().intersects(bb.getC())){
                pp = p;
                link = p.getLinked();
                break;
            }
        }
        for (Platform p: plat){
            if (link > 0 && p.isTeleport() && !p.getR().getBounds2D().intersects(bb.getC()) && !p.equals(pp) && p.getLinked() == link){

                bb.setLocation(p.getX(), p.getY()+p.getR().height-1);
                break;
            }
        }
        for (Platform p: plat){

        }

//        System.out.println(jumps);
//        System.out.println(jumped);
        //System.out.println(jumping);

        repaint();

    }

    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_UP && jumps>0 && !jumped){
            gravity = 0;
            jumped = true;
            bb.moveY(1);
            touchB = false;
            jumping = 1;
            jumps--;
            jumpHeight = 17;
            //System.out.println(jumps);
        }else{
            if (touchB){
                jumps = 2;
                //System.out.println(jumps);
            }
        }
        if(key == KeyEvent.VK_W && jumps>0 && !jumped){
            gravity = 0;
            jumped = true;
            bb.moveY(1);
            touchB = false;
            jumping = 1;
            jumps--;
            jumpHeight = 17;
            //System.out.println(jumps);
        }else{
            if (touchB){
                jumps = 2;
                //System.out.println(jumps);
            }
        }
        if(key == KeyEvent.VK_SPACE && jumps>0 && !jumped){
            gravity = 0;
            jumped = true;
            bb.moveY(1);
            touchB = false;
            jumping = 1;
            jumps--;
            jumpHeight = 17;
            //System.out.println(jumps);
        }else{
            if (touchB){
                jumps = 2;
                //System.out.println(jumps);
            }
        }
        if(key == KeyEvent.VK_LEFT && !left){
            left = true;
        }
        if(key == KeyEvent.VK_A && !left){
            left = true;
        }
        if(key == KeyEvent.VK_R){
            bb.spawn();
            deaths++;
            level();
            //lives--;
        }
        if(key == KeyEvent.VK_N){
            bb.spawn();
            level++;
            level();
            //lives--;
        }
        if(key == KeyEvent.VK_B) {
            bb.spawn();
            level--;
            level();
        }
        if(key == KeyEvent.VK_1){
            level = 1;
            bb.spawn();
            level();
            //lives--;
        }

        if(key == KeyEvent.VK_RIGHT && !right){
            right = true;
        }
        if(key == KeyEvent.VK_D && !right){
            right = true;
        }
//        if(key == KeyEvent.VK_LEFT && touchL){
//            bb.moveY(1);
//        }
//        if(key == KeyEvent.VK_RIGHT && touchR){
//            bb.moveY(1);
//        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_LEFT && left){
            left = false;
        }
        if(key == KeyEvent.VK_A && left){
            left = false;
        }
        if(key == KeyEvent.VK_RIGHT && right){
            right = false;
        }
        if(key == KeyEvent.VK_D && right){
            right = false;
        }
    }


    public static void main(String[] args) {
        //JFrame window = new JFrame("Platformer!");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, FRAMEWIDTH, FRAMEHEIGHT + 22);

        JPanel panel = new BallMain();
        panel.setSize(FRAMEWIDTH, FRAMEHEIGHT);

        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
        
    }
}
