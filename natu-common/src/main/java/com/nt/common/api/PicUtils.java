package com.nt.common.api;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;

/**
 * 图片处理工具类.
 *
 * @author 唐僧
 */
public class PicUtils {

    public static void main(String[] args) {

        PicUtils.compressPicForScale("C:\\Users\\Li\\Downloads\\test.jpeg", "C:\\Users\\Li\\Downloads\\test1.jpeg",
                100, 0.8, 2560, 1440); // 图片小于1000kb

    }

    /**
     * 压缩图片通用方法(仅适用于数据库数据格式为 例:/profile/upload/2022/10/26/活动1.2_20221026171704A003.png)
     * @param pictureUrl 图片url
     * @param pictureUrl 系统存储路径
     * @param desMaxWidth 宽度
     * @param desMaxHeight 高度
     * @return compressPicture 返回的图片路径(本地当中的路径)
     */
    public String compressPicture (String pictureUrl, String profile, int desMaxWidth, int desMaxHeight) {
        // 分割入参url
        String[] urls = pictureUrl.split("/");
        // 拼接图片url
        StringBuffer proFile = new StringBuffer();
        proFile.append("/www/bmlj/");
        proFile.append(profile);
        // 拼接图片路径
        for (int i = 0; i < urls.length; i++) {
            if (i <= 1) {
                continue;
            } else {
                proFile.append("/" + urls[i]);
            }
        }
        String retUrl = compressPicForScale(proFile.toString(),
                proFile.toString(),
                100,
                0.8,
                desMaxWidth,
                desMaxHeight);
        return retUrl;
    }



    /**
     * 根据指定大小和指定精度压缩图片
     *
     * @param srcPath      源图片地址
     * @param desPath      目标图片地址 (存到数据库的路径)
     * @param desFileSize  指定图片大小，单位kb（压缩到多大以内）
     * @param accuracy     精度，递归压缩的比率，建议小于0.9
     * @param desMaxWidth  目标最大宽度
     * @param desMaxHeight 目标最大高度
     * @return 目标文件路径
     */
    public static String compressPicForScale(String srcPath, String desPath, long desFileSize, double accuracy, int desMaxWidth, int desMaxHeight) {
        if (!new File(srcPath).exists()) {
            System.out.println("===================================" + srcPath + "==========================");
            System.out.println("===================================图片不存在================================");
            return null;
        }
        try {
            File srcFile = new File(srcPath);
            long srcFileSize = srcFile.length();
            System.out.println("源图片：" + srcPath + "，大小：" + srcFileSize / 1024 + "kb");
            if (srcFileSize/1024 <= desFileSize) {
                return srcPath;
            }
            //获取图片信息
            BufferedImage bim = ImageIO.read(srcFile);
            int srcWidth = bim.getWidth();
            int srcHeight = bim.getHeight();

            //先转换成jpg
            Thumbnails.Builder<File> builder = Thumbnails.of(srcFile).outputFormat("jpg");

            // 指定大小（宽或高超出会才会被缩放）
            if (srcWidth > desMaxWidth || srcHeight > desMaxHeight) {
                builder.size(desMaxWidth, desMaxHeight);
            } else {
                //宽高均小，指定原大小
                builder.size(srcWidth, srcHeight);
            }
            // 写入到内存
            ByteArrayOutputStream bos = new ByteArrayOutputStream(); //字节输出流（写入到内存）
            builder.toOutputStream(bos);

            // 递归压缩，直到目标文件大小小于desFileSize
            byte[] bytes = compressPicCycle(bos.toByteArray(), desFileSize, accuracy);

            // 输出到文件
            File desFile = new File(desPath);
            FileOutputStream fos = new FileOutputStream(desFile);
            fos.write(bytes);
            fos.close();

            System.out.println("目标图片：" + desPath + "，大小" + desFile.length() / 1024 + "kb");
            System.out.println("图片压缩完成！");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return desPath;
    }

    private static byte[] compressPicCycle(byte[] bytes, long desFileSize, double accuracy) throws IOException {
        // File srcFileJPG = new File(desPath);
        long srcFileSizeJPG = bytes.length;
        // 2、判断大小，如果小于500kb，不压缩；如果大于等于500kb，压缩
        if (srcFileSizeJPG <= desFileSize * 1024) {
            return bytes;
        }
        // 计算宽高
        BufferedImage bim = ImageIO.read(new ByteArrayInputStream(bytes));
        int srcWidth = bim.getWidth();
        int srcHeight = bim.getHeight();
        int desWidth = new BigDecimal(srcWidth).multiply(new BigDecimal(accuracy)).intValue();
        int desHeight = new BigDecimal(srcHeight).multiply(new BigDecimal(accuracy)).intValue();

        ByteArrayOutputStream bos = new ByteArrayOutputStream(); //字节输出流（写入到内存）
        Thumbnails.of(new ByteArrayInputStream(bytes)).size(desWidth, desHeight).outputQuality(accuracy).toOutputStream(bos);
        return compressPicCycle(bos.toByteArray(), desFileSize, accuracy);
    }

}
