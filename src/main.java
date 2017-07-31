import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.swing.*;
import java.io.*;
import java.util.Hashtable;

/**
 * Created by Administrator on 2017-07-21.
 */
public class main {
    public static void Encode_QR_CODE(String contents,String filename) throws IOException, WriterException {
        int width = 430; // 二维码图片宽度 300
        int height = 430; // 二维码图片高度300

        String format = "png";// 二维码的图片格式 gif

        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        // 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 内容所使用字符集编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//      hints.put(EncodeHintType.MAX_SIZE, 350);//设置图片的最大值
//      hints.put(EncodeHintType.MIN_SIZE, 100);//设置图片的最小值
        hints.put(EncodeHintType.MARGIN, 1);//设置二维码边的空度，非负数

        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,//要编码的内容
                //编码类型，目前zxing支持：Aztec 2D,CODABAR 1D format,Code 39 1D,Code 93 1D ,Code 128 1D,
                //Data Matrix 2D , EAN-8 1D,EAN-13 1D,ITF (Interleaved Two of Five) 1D,
                //MaxiCode 2D barcode,PDF417,QR Code 2D,RSS 14,RSS EXPANDED,UPC-A 1D,UPC-E 1D,UPC/EAN extension,UPC_EAN_EXTENSION
                BarcodeFormat.QR_CODE,
                width, //条形码的宽度
                height, //条形码的高度
                hints);//生成条形码时的一些配置,此项可选

        // 生成二维码
        File folder=new File("e:" + File.separator+"QRCode");
        folder.mkdir();
        File outputFile = new File("e:" + File.separator + "QRCode"+File.separator+filename);//指定输出路径
        System.out.print("e:" + File.separator + "QRCode"+File.separator+filename+".png");
        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile,filename);
    }

    public static void main(String[] args) throws Exception {
        try {
            initUI();
            FileInputStream fis=new FileInputStream("E:\\QRCode\\3\\3.txt");
            BufferedReader bfs=new BufferedReader(new InputStreamReader(fis,"GBK"));
            String content=null;
            while ((content=bfs.readLine())!=null){
                String[] st=content.split("%");
                String[] f=st[0].split("\t");

                Encode_QR_CODE(content,f[0]+".png");
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    private static void initUI() {
        JFrame jFrame=new JFrame("二维码生成器");
        jFrame.setSize(400,500);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
