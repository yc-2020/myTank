package xyz.yuchao.tank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.yuchao.tank.constant.CommonConstant;
import xyz.yuchao.tank.enums.Dir;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static xyz.yuchao.tank.constant.CommonConstant.*;

/**
 * @author yc
 * @date 31/3/2022 下午1:39
 */
public class TankFrame extends Frame {
    private static final Logger logger = LogManager.getLogger(TankFrame.class);


    public TankFrame() throws HeadlessException {
        this.setSize(CommonConstant.GAME_WIGHT, CommonConstant.GAME_HEIGHT);
        this.setResizable(false);
        this.setVisible(true);
        this.setBackground(Color.BLACK);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new MyKeyListener());
    }

    Image offScreeImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreeImage == null) {
            offScreeImage = this.createImage(CommonConstant.GAME_WIGHT, CommonConstant.GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreeImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, CommonConstant.GAME_WIGHT, CommonConstant.GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreeImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
//        mainTank.paint(g);
        for (Bullet bullet : bulletList) {
            for (Tank tank : tankList) {
                bullet.collideWith(tank);
            }
        }
        for (int i = 0; i < bulletList.size(); i++) {
            Bullet bullet = bulletList.get(i);
            if (!bullet.isLiveFlag()) {
                bulletList.remove(bullet);
            }
            else{
                bullet.paint(g);
            }

        }
        for(int i=0;i<explodeList.size();i++){
            Explode explode=explodeList.get(0);
            if(!explode.isLiveFlag()){
                explodeList.remove(explode);
            }
            explode.paint(g);
        }
        for (int j = 0; j < tankList.size(); j++) {
            Tank tank = tankList.get(j);
            if (!tank.isLiveFlag()) {
                tankList.remove(tank);
                explodeList.add(new Explode(tank.getX(),tank.getY()));
            }
            else{
                tank.paint(g);
            }
        }
    }

    class MyKeyListener extends KeyAdapter {

        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }

            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_SPACE:
                    mainTank.fire();
                default:
                    break;
            }

            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!bL && !bU && !bR && !bD) {
                CommonConstant.mainTank.setMoving(false);
            }
            else {
                CommonConstant.mainTank.setMoving(true);

                if (bL) {
                    CommonConstant.mainTank.setDir(Dir.LEFT);
                }
                if (bU) {
                    CommonConstant.mainTank.setDir(Dir.UP);
                }
                if (bR) {
                    CommonConstant.mainTank.setDir(Dir.RIGHT);
                }
                if (bD) {
                    CommonConstant.mainTank.setDir(Dir.DOWN);
                }
            }


        }

    }
}
