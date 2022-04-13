package xyz.yuchao.tank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.yuchao.tank.resource.ExplodeResource;

import java.awt.*;

/**
 * @author yc
 * @date 2022/4/13 16:36
 */
public class Explode {
    private int current = 0;
    private boolean liveFlag = true;
    private Integer x;
    private Integer y;
    private static final Logger logger= LogManager.getLogger(Explode.class);

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Explode(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics g) {
        if(liveFlag){
            logger.info("当前第{}帧",current);
            g.drawImage(ExplodeResource.bufferedImageList[current++],x,y,null);
        }
        if(current>15){
            liveFlag=false;
        }
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public boolean isLiveFlag() {
        return liveFlag;
    }

    public void setLiveFlag(boolean liveFlag) {
        this.liveFlag = liveFlag;
    }
}
