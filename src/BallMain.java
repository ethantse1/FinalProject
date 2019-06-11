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
    private int jumping = 0;
    private int level = 1;
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
    }
    public void level(){
        plat.clear();
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
            plat.add(new Platform(300,450,40,50,false));
            plat.add(new Platform(400,400,40,100,false));
            plat.add(new Platform(600,300,40,200,false));
            plat.add(new Platform(800,200,40,50,false,false,true));
            plat.add(new Platform(0,500,1000,100,false,false,false,true));
        }
        if (level == 3) {
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
            plat.add(new Platform(750, 200, 40, 1000, true));
            plat.add(new Platform(350, 300, 40, 1000, false));
            plat.add(new Platform(550, 200, 40, 1000, false));
            plat.add(new Platform(900,450,40,50,false,false,true));
            plat.add(new Platform(0, 500, 1000, 100, false,false,false,true));
        }
        if (level == 5) {
            plat.add(new Platform(350, 300, 40, 1000, false));
            plat.add(new Platform(550, 200, 40, 1000, false));
            plat.add(new Platform(900, 100, 40, 50, false, false, true));
            plat.add(new Platform(900,150,40,20,false));
            plat.add(new Platform(750,150,40,30,false,true));
            plat.add(new Platform(0, 500, 1000, 100, false,false,false,true));
        }
        if (level == 6) {
            plat.add(new Platform(960,450,40,50,false,false,true));
            plat.add(new Platform(0,500,150,100,false));
            plat.add(new Platform(150,520,850,100,true));
            plat.add(new Platform(850,500,150,100,false));
            //150 - 850
            for (int i = 155; i <= 795; i+=64) {
                plat.add(new Platform(i,480,50,20,false,true));
            }
        }
        if (level == 7) {
            plat.add(new Platform(750, 150, 40, 1000, true));
            plat.add(new Platform(350, 300, 40, 1000, false));
            plat.add(new Platform(550, 200, 40, 1000, false));
            plat.add(new Platform(700,150,40,30,false,true));
            plat.add(new Platform(800,150,40,30,false,true));
            plat.add(new Platform(900, 100, 40, 50, false, false, true));
            plat.add(new Platform(900,150,40,20,false));
            plat.add(new Platform(0, 500, 1000, 100, false,false,false,true));
        }
        if (level == 8) {
            plat.add(new Platform(500,300,40,200,false,false,false,false,true));
            plat.add(new Platform(900,300,40,200,false,false,false,false,true));
            plat.add(new Platform(0,500,1000,100,false,false,false,true));
        }

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //window.setTitle("Time survived: " + counter/23 + "           " + "Score: " + score + "           " + "Lives Left: " + lives);


        bb.drawB(g2);
        for (Platform p: plat){
            p.drawR(g2);
        }

    }
    public void actionPerformed(ActionEvent e) {
        bb.moveY(-gravity);
        if (left) {
            if (bb.getLoc().x <= 0 || touchL) {
            } else {
                bb.moveX(-3);
            }
        }
        if (right) {
            if (bb.getLoc().x + bb.getRadius() >= getWidth() || touchR) {
            } else {
                bb.moveX(3);
            }
        }

        Point bl = new Point(bb.getLoc().x - 1, bb.getLoc().y + bb.getRadius());
        Point br = new Point(bb.getLoc().x + bb.getRadius() + 1, bb.getLoc().y + bb.getRadius());
        Point tr = new Point(bb.getLoc().x + bb.getRadius() - 1, bb.getLoc().y);
        Point tl = new Point(bb.getLoc().x + 1, bb.getLoc().y);

        touchB = false;
        touchT = false;
        touchR = false;
        touchL = false;

//        for (Platform p : plat) {
//            if (!p.getKill()) {
//                if (p.getR().getBounds2D().contains(new Point(bb.getLoc().x + bb.getRadius(), bb.getLoc().y + 1)) || p.getR().getBounds2D().contains(new Point(bb.getLoc().x + bb.getRadius() - 1, bb.getLoc().y + bb.getRadius() - 1))) {
//                    bb.moveX(-1);
//                }
//                if (p.getR().getBounds2D().contains(new Point(bb.getLoc().x + bb.getRadius() - 1, bb.getLoc().y + 1)) || p.getR().getBounds2D().contains(new Point(bb.getLoc().x + bb.getRadius() - 1, bb.getLoc().y + bb.getRadius() - 1))) {
//                    bb.moveX(-1);
//                }
//                if (p.getR().getBounds2D().contains(new Point(bb.getLoc().x + bb.getRadius() - 1, bb.getLoc().y + 1)) || p.getR().getBounds2D().contains(new Point(bb.getLoc().x + bb.getRadius() - 1, bb.getLoc().y + bb.getRadius() - 1))) {
//                    bb.moveX(-1);
//                }
//
//
//                if (p.getR().getBounds2D().contains(new Point(bb.getLoc().x, bb.getLoc().y + 1)) || p.getR().getBounds2D().contains(new Point(bb.getLoc().x, bb.getLoc().y + bb.getRadius() - 1))) {
//                    bb.moveX(1);
//                }
//                if (p.getR().getBounds2D().contains(new Point(bb.getLoc().x, bb.getLoc().y + 1)) || p.getR().getBounds2D().contains(new Point(bb.getLoc().x, bb.getLoc().y + bb.getRadius() - 1))) {
//                    bb.moveX(1);
//                }
//                if (p.getR().getBounds2D().contains(new Point(bb.getLoc().x, bb.getLoc().y)) || p.getR().getBounds2D().contains(new Point(bb.getLoc().x, bb.getLoc().y + bb.getRadius() - 1))) {
//                    bb.moveX(1);
//                }
//
//            }
//        }
        for (Platform p : plat) {
            if (p.getR().getBounds2D().contains(new Point(bb.getLoc().x + bb.getRadius()-2, bb.getLoc().y+3)) || p.getR().getBounds2D().contains(new Point(bb.getLoc().x + bb.getRadius()-2, bb.getLoc().y + bb.getRadius()-3))) {
                bb.moveX(-1);
            }
            if (p.getR().getBounds2D().contains(new Point(bb.getLoc().x+2, bb.getLoc().y + bb.getRadius()-3)) || p.getR().getBounds2D().contains(new Point(bb.getLoc().x+2, bb.getLoc().y+3))) {
                bb.moveX(1);
            }
            if (p.getR().getBounds2D().contains(new Point(bb.getLoc().x + bb.getRadius()-2, bb.getLoc().y+3)) || p.getR().getBounds2D().contains(new Point(bb.getLoc().x + bb.getRadius()-2, bb.getLoc().y + bb.getRadius()-3))) {
                bb.moveX(-1);
            }
            if (p.getR().getBounds2D().contains(new Point(bb.getLoc().x+2, bb.getLoc().y + bb.getRadius()-3)) || p.getR().getBounds2D().contains(new Point(bb.getLoc().x+2, bb.getLoc().y+3))) {
                bb.moveX(1);
            }
            if (p.getR().getBounds2D().contains(new Point(bb.getLoc().x + bb.getRadius()-2, bb.getLoc().y+3)) || p.getR().getBounds2D().contains(new Point(bb.getLoc().x + bb.getRadius()-2, bb.getLoc().y + bb.getRadius()-3))) {
                bb.moveX(-1);
            }
            if (p.getR().getBounds2D().contains(new Point(bb.getLoc().x+2, bb.getLoc().y + bb.getRadius()-3)) || p.getR().getBounds2D().contains(new Point(bb.getLoc().x+2, bb.getLoc().y+3))) {
                bb.moveX(1);
            }
            for (int i = 0; i < gravity - jumpHeight; i++) {
                if (p.getR().getBounds2D().contains(new Point(bb.getLoc().x + bb.getRadius() - 2, bb.getLoc().y-2)) || p.getR().getBounds2D().contains(new Point(bb.getLoc().x + 2, bb.getLoc().y-2))) {
                    bb.moveY(-1);
                    //System.out.println(1);
                }
            }
            for (int i = 0; i < jumpHeight - gravity; i++) {
                if (p.getR().getBounds2D().contains(new Point(bb.getLoc().x + 2, bb.getLoc().y + bb.getRadius()-1)) || p.getR().getBounds2D().contains(new Point(bb.getLoc().x + bb.getRadius() - 2, bb.getLoc().y + bb.getRadius()-1))) {
                    bb.moveY(1);
                    //System.out.println(i);
                    jumps = 2;
                    jumped = false;
                    touchB = true;
                }
            }
        }
        for (Platform p : plat) {
            bl.translate(0, -2);
            br.translate(0, -2);
            tl.translate(0, 2);
            tr.translate(0, 2);
            if (bb.getC().intersects(p.getR()) && p.getKill()) {
                bb.spawn();
                level();
                jumps = 2;
                break;
            }
            if (p.getR().getBounds2D().contains(tr) || p.getR().getBounds2D().contains(br)) {
                touchR = true;
            }
            if (p.getR().getBounds2D().contains(bl) || p.getR().getBounds2D().contains(tl)) {
                touchL = true;
            }

            if (bb.getC().intersects(p.getR()) && p.isDisappear()){
                plat.remove(p);
                repaint();
                break;
            }
            if (bb.getC().intersects(p.getR()) && p.isGoal()){
                level++;
                level();
                bb.spawn();
                repaint();
                break;
            }

            if (bb.getC().intersects(p.getR()) && p.isTeleport()){
                bb.getLoc().setLocation(p.getX(),p.getY());
                repaint();
                break;
            }

            tl.translate(0,-2);
            tr.translate(0,-2);
            bl.translate(0,1);
            br.translate(0,1);
            bl.translate(1,0);
            br.translate(-1,0);

            if (p.getR().getBounds2D().contains(bl) || p.getR().getBounds2D().contains(br)) {
                if (p.getKill()) {
                    bb.spawn();
                    level();
                    break;
                }
                touchB = true;
                //System.out.println(bb.getLoc().x);
                //bb.ground();

                //System.out.println(bl.x);
            } else {
                //bb.Fground();
            }
            if (p.getR().getBounds2D().contains(bl) || p.getR().getBounds2D().contains(br)) {
                if (p.isDisappear()) {
                    plat.remove(p);
                    repaint();
                    break;
                }
                touchB = true;
                //System.out.println(bl.x);
            }
            if (p.getR().getBounds2D().contains(bl) || p.getR().getBounds2D().contains(br)) {
                if (p.isGoal()) {
                    level++;
                    level();
                    bb.spawn();
                    repaint();
                    break;
                }
                touchB = true;
                //System.out.println(bl.x);
            }
            bl.translate(0, 1);
            br.translate(0, 1);

            tl.translate(1, 0);
            tr.translate(-1, 0);
//
            if (p.getR().getBounds2D().contains(tr) || p.getR().getBounds2D().contains(tl)) {
                touchT = true;
            }
            tl.translate(-1, 0);
            tr.translate(1, 0);
        }








            if (!touchB) {
                if (gravity < jumpHeight + 12) {
                    gravity++;
                    if (gravity > jumpHeight+1){
                        jumped = false;
                    }
                }
            } else {
                gravity = 0;
            }
            if (touchB) {
                jumps = 2;
                gravity = 0;
                jumping = 0;
            }
            if (jumping > 0) {
                if (touchT && !touchB) {
                    jumping = 0;
                } else {
                    bb.moveY(jumpHeight);
                }
            }
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
            jumping++;
            jumps--;
            //repaint();
        }else{
            if (touchB){
                jumps = 2;
                //System.out.println(jumps);
            }
        }
        if(key == KeyEvent.VK_LEFT && !left){
            left = true;
        }
        if(key == KeyEvent.VK_R){
            bb.spawn();
            level();
            //lives--;
        }
        if(key == KeyEvent.VK_N){
            bb.spawn();
            level++;
            level();
            //lives--;
        }
        if(key == KeyEvent.VK_B){
            bb.spawn();
            level--;
            level();
            //lives--;
        }

            if(key == KeyEvent.VK_RIGHT && !right){
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
        if(key == KeyEvent.VK_RIGHT && right){
            right = false;
        }
    }


    public static void main(String[] args) {
        JFrame window = new JFrame("Platformer!");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, FRAMEWIDTH, FRAMEHEIGHT + 22);

        BallMain panel = new BallMain();
        panel.setSize(FRAMEWIDTH, FRAMEHEIGHT);

        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }
}