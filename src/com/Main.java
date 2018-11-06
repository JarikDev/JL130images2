package com;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        File file = new File("smile.jpg");
        BufferedImage image = ImageIO.read(file);

        WritableRaster raster = image.getRaster();
        for (int i = 0; i < raster.getWidth(); i++) {
            int[] pixel = raster.getPixel(i, 0, new int[4]);
            pixel[0] = 255;
            pixel[1] = 255;
            pixel[2] = 255;
            raster.setPixel(i, 0, pixel);
        }
        raster.getPixels(0,0,image.getHeight(),image.getWidth(),new int[4*image.getHeight()*image.getWidth()]);
        raster.setPixels(0,0,image.getHeight(),image.getWidth(),new int[4*image.getHeight()*image.getWidth()]);

        Object data=raster.getDataElements(0,0,null);
        ColorModel cm=image.getColorModel();
        Color color=new Color(cm.getRGB(data),true);
        raster.setDataElements(0,0,data);

        image.setData(raster);
        ImageIO.write(image, "png", new File("smile.png"));
    }
}
