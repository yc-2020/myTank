package xyz.yuchao.tank;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.yuchao.tank.enums.Dir;

import java.awt.*;
import java.awt.image.BufferedImage;

import static xyz.yuchao.tank.constant.CommonConstant.*;
import static xyz.yuchao.tank.resource.BulletResource.*;

/**
 * @author yc
 * @date 31/3/2022 下午1:37
 */
public class Bullet {

    private static final Logger logger = LogManager.getLogger(Bullet.class);
    private int x = 50;
    private int y = 50;
    private Dir dir = Dir.LEFT;
    private boolean liveFlag = true;
    private BufferedImage nowImage;
    private Rectangle bulletRectangle;
    private String team = "1";

    public Bullet(int x, int y, Dir dir, String team) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.team = team;
        changeDir();
        bulletRectangle = new Rectangle(x, y, nowImage.getWidth(), nowImage.getHeight());
    }

    public void paint(Graphics g) {

        changeDir();
        g.drawImage(nowImage, x + 23, y + 25, null);
        move();
    }

    private void changeDir() {
        switch (dir) {
            case LEFT:
                nowImage = bulletL;
                break;
            case UP:
                nowImage = bulletU;
                break;
            case RIGHT:
                nowImage = bulletR;
                break;
            case DOWN:
                nowImage = bulletD;
                break;
            default:
                break;
        }
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isLiveFlag() {
        return liveFlag;
    }

    public void setLiveFlag(boolean liveFlag) {
        this.liveFlag = liveFlag;
    }

    public BufferedImage getNowImage() {
        return nowImage;
    }

    public void setNowImage(BufferedImage nowImage) {
        this.nowImage = nowImage;
    }

    public Rectangle getBulletRectangle() {
        return bulletRectangle;
    }

    public void setBulletRectangle(Rectangle bulletRectangle) {
        this.bulletRectangle = bulletRectangle;
    }

    private void move() {
        switch (dir) {
            case LEFT:
                x -= BULLET_SPEED;
                break;
            case UP:
                y -= BULLET_SPEED;
                break;
            case RIGHT:
                x += BULLET_SPEED;
                break;
            case DOWN:
                y += BULLET_SPEED;
                break;
            default:
                break;
        }
        if (x < 0 || y < 0 || x > GAME_WIGHT || y > GAME_HEIGHT) {
            liveFlag = false;
        }
        bulletRectangle.setLocation(x, y);
    }

    public void collideWith(Tank tank) {
        if (this.team.equals(tank.getTeam())) {
            return;
        }
        if (bulletRectangle.intersects(tank.getTankRectangle())) {
            this.setLiveFlag(false);
            tank.setLiveFlag(false);
        }
    }
}
