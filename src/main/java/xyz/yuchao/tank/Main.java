package xyz.yuchao.tank;

import xyz.yuchao.tank.constant.CommonConstant;
import xyz.yuchao.tank.thread.FreshFrameThread;

/**
 * @author yc
 * @date 30/3/2022 上午11:55
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        init();
    }


    public static void init() {
        TankFrame tankFrame = new TankFrame();
        FreshFrameThread thread = new FreshFrameThread(tankFrame);
        CommonConstant.addTank();
        CommonConstant.tankList.add(CommonConstant.mainTank);
        thread.start();
    }
}
