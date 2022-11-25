package com.nt.common.utils.api;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Hashtable;

/**
 * @Author: 唐僧
 * @Desc: 二维码生成
 */
public class QrCodeUtils {
    private static final Logger log = LoggerFactory.getLogger(QrCodeUtils.class);

    private static final String CHARSET = "utf-8";
    private static final String FORMAT_NAME = "JPG";
    /**
     * 二维码尺寸
     */
    private static final Integer QRCODE_SIZE = 350;
    /**
     * LOGO宽度
     */
    private static final Integer WIDTH = 100;
    /**
     * LOGO高度
     */
    private static final Integer HEIGHT = 100;

    private static BufferedImage createImage(String content, String imgPath, boolean needCompress) throws Exception {
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter()
                .encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (imgPath == null || "".equals(imgPath)) {
            return image;
        }
        // 插入图片
        insertImage(image, imgPath, needCompress);
        return image;
    }

    private static void insertImage(BufferedImage source, String imgPath, boolean needCompress) throws Exception {
        File file = new File(imgPath);
        if (!file.exists()) {
            log.error(imgPath + "   该文件不存在！");
            return;
        }
        Image src = ImageIO.read(new File(imgPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        // 压缩LOGO
        if (needCompress) {
            if (width > WIDTH) {
                width = WIDTH;
            }
            if (height > HEIGHT) {
                height = HEIGHT;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            // 绘制缩小后的图
            g.drawImage(image, 0, 0, null);
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    /**
     * 生成二维码--方法1
     *
     * @param content      存放在二维码中的内容
     * @param imgPath      logo的路径及名称
     * @param destPath     生成的二维码的路径
     * @param needCompress 是否需要压缩
     * @return 生成的二维码的名称
     * @throws Exception
     */
    public static String encode(String content, String imgPath, String destPath, boolean needCompress) {
        BufferedImage image;
        try {
            image = createImage(content, imgPath, needCompress);
            mkdirs(destPath);
            //若destPath仅仅是文件夹
            /*String qrPath = destPath+"/QR"+System.currentTimeMillis()+".jpg";
            ImageIO.write(image, FORMAT_NAME, new File(qrPath));
            return qrPath;*/

            //若destPath带有文件名
            File file = new File(destPath);
            ImageIO.write(image, FORMAT_NAME, file);
            return file.getName();
        } catch (Exception e) {
            log.error("二维码生成失败");
            return null;
        }
    }

    public static void mkdirs(String destPath) {
        File file = new File(destPath);
        // 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }

    /**
     * 生成二维码
     *
     * @param content      存放在二维码中的内容
     * @param imgPath      logo的路径及名称
     * @param needCompress 是否需要压缩
     * @return
     * @throws Exception
     */
    public static BufferedImage encode(String content, String imgPath, boolean needCompress) throws Exception {

        return createImage(content, imgPath, needCompress);
    }

    /**
     * 生成二维码
     * 不需要压缩
     *
     * @param content  存放在二维码中的内容
     * @param imgPath  logo的路径及名称
     * @param destPath 生成的二维码的路径
     * @throws Exception
     */
    public static String encode(String content, String imgPath, String destPath) {

        return encode(content, imgPath, destPath, false);
    }

    /**
     * 生成二维码
     * 无logo，不需要压缩
     *
     * @param content  存放在二维码中的内容
     * @param destPath 生成的二维码的路径
     * @throws Exception
     */
    public static String encode(String content, String destPath) {

        return encode(content, null, destPath, false);
    }

    /**
     * 生成二维码--方法2
     * 无输出路径
     *
     * @param content      存放在二维码中的内容
     * @param imgPath      logo的路径及名称
     * @param output       输出流
     * @param needCompress 是否需要压缩
     * @throws Exception
     */
    public static void encode(String content, String imgPath,
                              OutputStream output, boolean needCompress) throws Exception {
        BufferedImage image = createImage(content, imgPath, needCompress);
        ImageIO.write(image, FORMAT_NAME, output);
    }

    /**
     * 生成二维码
     * 无logo,无生成路径，不需要压缩
     *
     * @param content 存放在二维码中的内容
     * @param output  输出流
     * @throws Exception
     */
    public static void encode(String content, OutputStream output) throws Exception {
        encode(content, null, output, false);
    }

    /**
     * 根据二维码路径解析二维码
     *
     * @param path
     * @return java.lang.String
     */
    public static String decode(String path) throws Exception {
        return decode(new File(path));
    }

    /**
     * 根据二维码解析二维码
     *
     * @param file
     * @return java.lang.String
     */
    public static String decode(File file) throws Exception {
        BufferedImage image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable hints = new Hashtable();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, hints);
        return result.getText();
    }

    /**
     * 初始化二维码 MultipartFile
     *
     * @param content  二维码内容
     * @param logoPath logo路径
     * @return
     * @throws Exception
     */
    private static MultipartFile initMultipartFile(String content, String logoPath) throws Exception {
        //二维码名称
        String qrName = "qr" + System.currentTimeMillis() + ".jpg";
        //得到BufferedImage对象
        BufferedImage bufferedImage = createImage(content, logoPath, true);
        //创建一个ByteArrayOutputStream
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //把BufferedImage写入ByteArrayOutputStream
        ImageIO.write(bufferedImage, "jpg", os);
        //ByteArrayOutputStream转成InputStream
        InputStream input = new ByteArrayInputStream(os.toByteArray());
        //InputStream转成MultipartFile
        return new MockMultipartFile("qrFile", qrName, "text/plain", input);
    }

    /**
     * 生成二维码（有logo）
     *
     * @param jsonString 二维码内容  JSON字符串
     * @param logoPath   logo路径
     * @return
     */
    public static MultipartFile createQrCode(String jsonString, String logoPath) throws Exception {

        return initMultipartFile(jsonString, logoPath);
    }

    /**
     * 生成二维码（无logo）
     *
     * @param jsonString 二维码内容  JSON字符串
     * @return
     */
    public static MultipartFile createQrCode(String jsonString) throws Exception {

        return initMultipartFile(jsonString, null);
    }

}
