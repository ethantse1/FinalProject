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
    private ArrayList<Platform> plat;

    public static final int FRAMEWIDTH = 1000, FRAMEHEIGHT = 600;
    public BallMain() {
        timer = new Timer(1000/60, this);
        bb = new BallBoy(50,300, 20);
        bb.setSpawn(50,300);
        timer.start();
        addKeyListener(this);
        plat = new ArrayList<Platform>();
        levelOne();
    }
    public void levelOne(){
        plat.clear();
        plat.add(new Platform(0,550,1000,100, false));
//        plat.add(new Platform(0,400,299,40, false));
//        plat.add(new Platform(400,0,40,370, true));
//        plat.add(new Platform(500,480,100,40, false));


        plat.add(new Platform(500,300,40,1000, false));
        plat.add(new Platform(700,200,40,1000, false));
        plat.add(new Platform(900,150,40,1000, true));



    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

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
                jumps = 2;
                break;
            }
            if (p.getR().getBounds2D().contains(tr) || p.getR().getBounds2D().contains(br)) {
                touchR = true;
            }
            if (p.getR().getBounds2D().contains(bl) || p.getR().getBounds2D().contains(tl)) {
                touchL = true;
            }
            tl.translate(0, -2);
            tr.translate(0, -2);
            bl.translate(0, 1);
            br.translate(0, 1);
            bl.translate(1, 0);
            br.translate(-1, 0);

            if (p.getR().getBounds2D().contains(bl) || p.getR().getBounds2D().contains(br)) {
                if (p.getKill()) {
                    bb.spawn();
                    break;
                }
                touchB = true;
                System.out.println(bb.getLoc().x);
                bb.ground();

                //System.out.println(bl.x);
            } else {
                bb.Fground();
            }
            if (p.getR().getBounds2D().intersects(bb.getC()) && p.getKill()){
                bb.spawn();
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
                System.out.println(jumps);
            }
        }
        if(key == KeyEvent.VK_LEFT && !left){
            left = true;
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

        JPanel panel = new BallMain();
        panel.setSize(FRAMEWIDTH, FRAMEHEIGHT);

        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }
}
