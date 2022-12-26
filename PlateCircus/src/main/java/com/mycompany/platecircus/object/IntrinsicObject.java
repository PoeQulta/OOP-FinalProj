/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platecircus.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author poequ
 */
public class IntrinsicObject extends ImageObject {
    
    private Color color;
    private BufferedImage[] spriteImages;
    private String path;
    private int height;
    private int type;
    
    public IntrinsicObject(Color color, String path, int frames, int height, int type) {
        super(0, 0, null);
        this.height = height;
        this.path = path;
        this.type = type;
        spriteImages = new BufferedImage[frames];
        for(int i=0;i<frames;i++)
                {
                try {
                        Image img = ImageIO.read(getClass().getResourceAsStream(String.format(path,i))).getScaledInstance(-1, height, BufferedImage.SCALE_REPLICATE);
			spriteImages[i] = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_4BYTE_ABGR);
                        Graphics2D gr = spriteImages[i].createGraphics();
                        gr.drawImage(img, 0,0, null);
                        gr.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
                }
        super.setSpriteImages(spriteImages);
        this.color = color;
        ColorFilter();
    }
    private void ColorFilter()
    {
        BufferedImage img[] = super.getSpriteImages();
        for (BufferedImage img1 : img) {
            for (int x = 0; x < img1.getWidth(); x++) {
                for (int y = 0; y < img1.getHeight(); y++) {
                    Color pixelColor = new Color(img1.getRGB(x, y), true);
                    int r = (pixelColor.getRed()* color.getRed()) / 255;
                    int g = (pixelColor.getGreen() * color.getGreen()) / 255;
                    int b = (pixelColor.getBlue() * color.getBlue()) / 255;
                    int a = pixelColor.getAlpha();
                    int rgba = (a << 24) | (r << 16) | (g << 8) | b;
                    img1.setRGB(x, y, rgba);
                }
            }
        }
        
        super.setSpriteImages(img);
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public String getPath() {
        return path;
    }
        public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
}
