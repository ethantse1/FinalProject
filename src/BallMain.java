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
    private int jumpHeight = 80;
    private boolean touchB = false;
    private boolean touchT = false;
    private boolean touchR = false;
    private boolean touchL = false;
    private int jumps = 2;

    private int jumping = 0;
    private ArrayList<Platform> plat;

    public static final int FRAMEWIDTH = 1000, FRAMEHEIGHT = 600;
    public BallMain() {
        timer = new Timer(4, this);
        bb = new BallBoy(100,450, 20);
        bb.setSpawn(100,450);
        timer.start();
        addKeyListener(this);
        plat = new ArrayList<Platform>();
        levelOne();
    }
    public void levelOne(){
        plat.clear();
        plat.add(new Platform(0,500,1000,100, false));
        plat.add(new Platform(0,400,300,40, false));
        plat.add(new Platform(500,300,40,100, false));
        plat.add(new Platform(700,200,40,100, false));
        plat.add(new Platform(900,150,40,100, true));
        plat.add(new Platform(850,150,40,20,false, true));
        plat.add(new Platform(950,150,40,20,false,true));




    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        bb.drawB(g2);
        for (Platform p: plat){
            p.drawR(g2);
        }
    }
    public void actionPerformed(ActionEvent e){
        Point bl = new Point(bb.getLoc().x-1, bb.getLoc().y+bb.getRadius());
        Point br = new Point(bb.getLoc().x+bb.getRadius()+1, bb.getLoc().y+bb.getRadius());
        Point tr = new Point(bb.getLoc().x+bb.getRadius()-1, bb.getLoc().y);
        Point tl = new Point(bb.getLoc().x+1, bb.getLoc().y);

        touchB = false;
        touchT = false;
        touchR = false;
        touchL = false;



        for(Platform p: plat){

            bl.translate(0,-2);
            br.translate(0,-2);
            tl.translate(0,2);
            tr.translate(0,2);
            if (bb.getC().intersects(p.getR()) && p.getKill()){
                bb.spawn();
                repaint();
                break;
            }
            if (p.getR().getBounds2D().contains(tr) || p.getR().getBounds2D().contains(br)) {
                touchR = true;
            }
            if (p.getR().getBounds2D().contains(tl) || p.getR().getBounds2D().contains(bl)) {
                touchL = true;
            }
            if (bb.getC().intersects(p.getR()) && p.isDisappear()){
                plat.remove(p);
                repaint();
                //break;
            }
            tl.translate(0,-2);
            tr.translate(0,-2);
            bl.translate(0,2);
            br.translate(0,2);
            bl.translate(1,0);
            br.translate(-1,0);

            if (p.getR().getBounds2D().contains(bl) || p.getR().getBounds2D().contains(br)) {
                if (p.getKill()) {
                    bb.spawn();
                    repaint();
                    break;
                }
                touchB = true;
                //System.out.println(bl.x);
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
            tl.translate(1,0);
            tr.translate(-1,0);

            if (p.getR().getBounds2D().contains(tr) || p.getR().getBounds2D().contains(tl)) {
                touchT = true;
            }
            tl.translate(-1,0);
            tr.translate(1,0);
//            br.translate(1,0);
//            bl.translate(-1,0);



            if (p.getR().getBounds2D().contains(new Point(bb.getLoc().x+bb.getRadius()-1, bb.getLoc().y+1)) || p.getR().getBounds2D().contains(new Point(bb.getLoc().x+bb.getRadius()-1, bb.getLoc().y+bb.getRadius()-1))) {
                bb.moveX(-1);
            }
            if (p.getR().getBounds2D().contains(new Point(bb.getLoc().x, bb.getLoc().y+1)) || p.getR().getBounds2D().contains(new Point(bb.getLoc().x, bb.getLoc().y+bb.getRadius()-1))) {
                bb.moveX(1);
            }

        }
        if (touchB && !touchT){
            jumps = 2;
            jumping = 0;
        }
        if (jumping>0) {
            if(touchT && !touchB){
                jumping = 0;
            }else {
                bb.moveY(1);
                jumping -= 1;
            }
        }

        if (!touchB){
            if (jumping<=0) {
                bb.moveY(-1);
            }
        } else{
            //System.out.println(bl.x + " " + br.x);
        }

        if (left) {
            if (bb.getLoc().x<=0 || touchL){
            }
            else {
                bb.moveX(-1);
            }
        }
        if (right) {
            if (bb.getLoc().x+bb.getRadius() >= getWidth() || touchR){
            }
            else {
                bb.moveX(1);
            }
        }


        repaint();
    }
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_UP && jumps>0 && jumping <=0){
            jumpHeight = 80;
            jumped = true;
            bb.moveY(1);
            jumping = jumpHeight;
            jumpHeight -= 20;
            jumps--;
            repaint();
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
