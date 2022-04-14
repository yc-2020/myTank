package xyz.yuchao.tank.constant;

import xyz.yuchao.tank.Bullet;
import xyz.yuchao.tank.Explode;
import xyz.yuchao.tank.Tank;
import xyz.yuchao.tank.enums.Dir;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yc
 * @date 31/3/2022 下午2:32
 */
public interface CommonConstant {
    /**
     * 游戏界面宽度
     */
    Integer GAME_WIGHT = 800;
    /**
     * 游戏界面高度
     */
    Integer GAME_HEIGHT = 600;
    /**
     * 子弹速度
     */
    Integer BULLET_SPEED = 50;
    /**
     * 坦克速度
     */
    Integer TANK_SPEED = 5;
    /**
     * 玩家1
     */
    Tank mainTank = new Tank(0, 25, Dir.DOWN,"1");
    /**
     * 坦克群
     */
    List<Tank> tankList=new ArrayList<>();

    /**
     * 子弹集合
     */
    List<Bullet> bulletList = new ArrayList<>();

    List<Explode> explodeList=new ArrayList<>();
    /**
     * 添加坦克数
     */
    Integer tankNum=5;

    /**
     * 电脑
     */
    String TEAM_COMPUTER="TEAM_COMPUTER";

    /**
     * 添加坦克
     */
    static void addTank(){
        for(int i=0;i<tankNum;i++){
            CommonConstant.tankList.add(new Tank(100+i*50,400, Dir.UP,TEAM_COMPUTER));
        }
    }
}
