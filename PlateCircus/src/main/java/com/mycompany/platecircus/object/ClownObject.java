package com.mycompany.platecircus.object;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;

public class ClownObject extends ImageObject{
    
	private static final int MAX_MSTATE = 19;
	// an array of sprite images that are drawn sequentially
	private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
        private static final int HEIGHT = 150;
        private List<MovingObject> plates;
	public ClownObject(int posX, int posY, String path) {
		super(posX, posY, null);
                plates = new LinkedList<MovingObject>();
                for(int i=0;i<MAX_MSTATE;i++)
                {
                try {   
                        //System.out.println(getClass().getResourceAsStream("/clown Art0012.png"));
                        Image img = ImageIO.read(getClass().getResourceAsStream(String.format(path,2*i))).getScaledInstance(-1, HEIGHT, BufferedImage.SCALE_REPLICATE);
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

    public List<MovingObject> getPlates() {
        return plates;
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
    
    public void updatePlates()
    {
        if(plates.stream().anyMatch(o -> !o.isVisible())){
            plates.removeIf(ob -> !ob.isVisible());
            plates.forEach(x ->x.setFreefall(true));
        }
    }

}
