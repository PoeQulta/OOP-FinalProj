/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platecircus.world;

/**
 *
 * @author poequ
 */
public class EasyState implements WorldStrat {
     private static final int SPEED = 1;
     private static final int PREVIEW_NUM = 7;
    @Override
    public int getSpeed() {
        return SPEED;
    }

    @Override
    public int getPreviewNum() {
        return PREVIEW_NUM;
    }
    
}
