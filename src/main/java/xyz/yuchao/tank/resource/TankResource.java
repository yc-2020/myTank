package xyz.yuchao.tank.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.yuchao.tank.Tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author yc
 * @date 2022/4/13 14:45
*/
public class TankResource {
    private static final Logger logger= LogManager.getLogger(TankResource.class);
    public static BufferedImage tankU, tankD, tankL, tankR;
    static {
        try {
            tankU = ImageIO.read(Tank.class.getResourceAsStream("/image/tank/tankU.gif"));
            tankD = ImageIO.read(Tank.class.getResourceAsStream("/image/tank/tankD.gif"));
            tankL = ImageIO.read(Tank.class.getResourceAsStream("/image/tank/tankL.gif"));
            tankR = ImageIO.read(Tank.class.getResourceAsStream("/image/tank/tankR.gif"));
        } catch (IOException e) {
            logger.error("加载坦克资源失败",e);
        }
    }
}
