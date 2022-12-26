package com.mycompany.platecircus.object;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ClownObject extends ImageObject{
    
	private static final int MAX_MSTATE = 1;
	// an array of sprite images that are drawn sequentially
	private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
        private static final int HEIGHT = 150;
	public ClownObject(int posX, int posY, String path) {
		super(posX, posY, null);
               
                for(int i=0;i<MAX_MSTATE;i++)
                {
                try {
                        Image img = ImageIO.read(getClass().getResourceAsStream(String.format(path,i))).getScaledInstance(-1, HEIGHT, BufferedImage.SCALE_REPLICATE);
			spriteImages[i] = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_4BYTE_ABGR);
                        Graphics2D gr = spriteImages[i].createGraphics();
                        gr.drawImage(img, 0,0, null);
                        gr.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
                }
                super.setSpriteImages(spriteImages);
		
	}

    @Override
    public void setY(int mY) {
        
    }
    public static int getHEIGHT()
    {
        return HEIGHT;
    }

    @Override
    public int getWidth() {
        return (int)(super.getWidth()*1.5f);
    }
    
	

}
