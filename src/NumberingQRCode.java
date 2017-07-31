import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;

/**
 * Created by Administrator on 2017-07-21.
 */
public class NumberingQRCode {
    public static BufferedImage numberingQRCode(BufferedImage bufferedImage,String numbering){
      //  Graphics2D g2=bufferedImage.createGraphics();

        int width=bufferedImage.getWidth();
        int height=bufferedImage.getHeight();

        BufferedImage bimage = new BufferedImage(width, height+80,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g=bimage.createGraphics();
        g.setBackground(Color.black);
        g.drawImage(bufferedImage,0,0,null);
        g.setPaint(Color.white);
        Font font=new Font("微软雅黑",Font.BOLD,30);
        g.setFont(font);

        FontRenderContext context = g.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(numbering, context);
        double x = (width - bounds.getWidth()) / 2;
        double y = height+bounds.getHeight() / 2;
        double ascent = -bounds.getY();
        double baseY = y + ascent;

        g.drawString(numbering, (int)x, (int)baseY);

        bufferedImage.flush();
        return bimage;
    }
}
