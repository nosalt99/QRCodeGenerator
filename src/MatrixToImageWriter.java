import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2017-07-21.
 */
public class MatrixToImageWriter {

    private static final int BLACK = 0xFF000000;//用于设置图案的颜色
    private static final int WHITE = 0xFFFFFFFF; //用于背景色

    public static void writeToFile(BitMatrix matrix, String format, File file,String filename) throws IOException {
        BufferedImage image = toBufferedImage(matrix);

        image=NumberingQRCode.numberingQRCode(image,filename);

        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }else{
            System.out.println("图片生成成功！");
        }
    }

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y,  (matrix.get(x, y) ? BLACK : WHITE));
            }
        }
        return image;
    }
}
