package xyz.yuchao.tank.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;

import static xyz.yuchao.tank.constant.CommonConstant.bulletList;
import static xyz.yuchao.tank.constant.CommonConstant.tankList;

/**
 * @author yc
 * @date 2022/4/13 13:53
 */
public class FreshFrameThread extends Thread {
    private static final Logger logger = LogManager.getLogger(FreshFrameThread.class);
    private Frame frame;


    public FreshFrameThread(Frame frame) {
        this.frame = frame;
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            logger.info("当前存活tank数量{},子弹数量{}", tankList.size(), bulletList.size());
            frame.repaint();
        }
    }

    public Frame getFrame() {
        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }
}
