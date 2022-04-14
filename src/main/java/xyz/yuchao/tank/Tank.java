package xyz.yuchao.tank;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.yuchao.tank.constant.CommonConstant;
import xyz.yuchao.tank.enums.Dir;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import static xyz.yuchao.tank.constant.CommonConstant.*;
import static xyz.yuchao.tank.resource.TankResource.*;

/**
 * @author yc
 * @date 31/3/2022 下午1:37
 */
public class Tank {

    private static final Logger logger = LogManager.getLogger(Tank.class);
    /**
     * 当前坦克图片
     */
    private BufferedImage nowImage = tankD;
    /**
     * 坦克横坐标
     */
    private int x;
    /**
     * 坦克纵坐标
     */
    private int y;

    /**
     * 坦克方向
     */
    private Dir dir;
    /**
     * 移动标记
     */
    private boolean moving = true;

    /**
     * 存活标记
     */
    private boolean liveFlag = true;

    /**
     * 队伍
     */
    private String team;


    /**
     * 碰撞体积
     */
    private Rectangle tankRectangle;

    private Random random = new Random();


    public Tank(int x, int y, Dir dir, String team) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.team = team;
        changeDir();
        tankRectangle = new Rectangle(x, y, nowImage.getWidth(), nowImage.getHeight());
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

    public boolean getMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public BufferedImage getNowImage() {
        return nowImage;
    }

    public void setNowImage(BufferedImage nowImage) {
        this.nowImage = nowImage;
    }

    public boolean isMoving() {
        return moving;
    }

    public boolean isLiveFlag() {
        return liveFlag;
    }

    public void setLiveFlag(boolean liveFlag) {
        this.liveFlag = liveFlag;
    }

    public Rectangle getTankRectangle() {
        return tankRectangle;
    }

    public void setTankRectangle(Rectangle tankRectangle) {
        this.tankRectangle = tankRectangle;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    private void move() {
        if (!moving) {
            return;
        }

        switch (dir) {
            case LEFT:
                x -= TANK_SPEED;
                break;
            case UP:
                y -= TANK_SPEED;
                break;
            case RIGHT:
                x += TANK_SPEED;
                break;
            case DOWN:
                y += TANK_SPEED;
                break;
            default:
                break;
        }
        x = x < 0 ? 0 : x;
        x = x > GAME_WIGHT - 50 ? GAME_WIGHT - 50 : x;
        y = y < 20 ? 20 : y;
        y = y > GAME_HEIGHT - 50 ? GAME_HEIGHT - 50 : y;
        collideWith();
        tankRectangle.setLocation(x, y);
        if(TEAM_COMPUTER.equals(team)){
            if(random.nextInt(100)>95){
                randomDir();
            }
            if(random.nextInt(100)>80){
                fire();
            }
        }
    }

    public void paint(Graphics g) {
        changeDir();
        g.drawImage(nowImage, x, y, null);
        move();
    }

    private void randomDir(){
        dir=Dir.values()[random.nextInt(4)];
    }

    private void changeDir() {
        switch (dir) {
            case LEFT:
                nowImage = tankL;
                break;
            case UP:
                nowImage = tankU;
                break;
            case RIGHT:
                nowImage = tankR;
                break;
            case DOWN:
                nowImage = tankD;
                break;
            default:
                nowImage = tankU;
                break;
        }
    }

    public void fire() {
        bulletList.add(new Bullet(x, y, dir, this.team));
    }

    public void collideWith() {
        logger.info("当前坦克位置x:{}y:{}", this.getTankRectangle().getX(), this.getTankRectangle().getY());
        for (int i = 0; i < tankList.size(); i++) {
            Tank tank = tankList.get(i);
            if (this == tank) {
                continue;
            }
            logger.info("当前坦克{}位置x:{}y:{}", i, tank.getTankRectangle().getX(), tank.getTankRectangle().getY());
            logger.info("是否碰撞{}", this.getTankRectangle().intersects(tank.getTankRectangle()));
            if (this.getTankRectangle().intersects(tank.getTankRectangle())) {
                this.liveFlag = false;
                tank.setLiveFlag(false);
            }
        }
    }

}
