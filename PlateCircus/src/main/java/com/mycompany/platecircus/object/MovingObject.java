/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platecircus.object;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author poequ
 */
public class MovingObject implements GameObject {
    private IntrinsicObject IntrnObj;
    private int x;
    private int y;
    private boolean freefall = true;
    
    private boolean visible;

    public MovingObject(IntrinsicObject IntrnObj, int x, int y) {
        this.IntrnObj = IntrnObj;
        this.x = x;
        this.y = y;
        visible = true;
    }
    
    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        if(freefall)
            this.y = y;
    }

    @Override
    public int getWidth() {
        return IntrnObj.getWidth();
    }

    @Override
    public int getHeight() {
        return IntrnObj.getHeight();
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return IntrnObj.getSpriteImages();
    }
    public void setVisible(boolean visible){
		this.visible = visible;
	}

    public void Update(int speed)
    {
        y+=speed;
    }
    public int getType()
    {
        return IntrnObj.getType();
    }

    public void setFreefall(boolean freefall) {
        this.freefall = freefall;
    }
    public Color getColor()
    {
        return IntrnObj.getColor();
    }
}
