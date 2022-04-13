package xyz.yuchao.tank.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.yuchao.tank.Tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author yc
 * @date 2022/4/13 14:51
*/
public class BulletResource {
    public static BufferedImage bulletU, bulletD, bulletL, bulletR;
    private static final Logger logger= LogManager.getLogger(TankResource.class);
    static {
        try {
            bulletU = ImageIO.read(BulletResource.class.getResourceAsStream("/image/bullet/bulletU.gif"));
            bulletD = ImageIO.read(BulletResource.class.getResourceAsStream("/image/bullet/bulletD.gif"));
            bulletL = ImageIO.read(BulletResource.class.getResourceAsStream("/image/bullet/bulletL.gif"));
            bulletR = ImageIO.read(BulletResource.class.getResourceAsStream("/image/bullet/bulletR.gif"));
        } catch (IOException e) {
            logger.error("加载子弹资源失败",e);
        }
    }
}
