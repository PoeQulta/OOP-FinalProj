/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platecircus.world;

/**
 *
 * @author poequ
 */
public class HardState implements WorldStrat {
     private static final int SPEED = 3;
     private static final int PREVIEW_NUM = 3;
    @Override
    public int getSpeed() {
        return SPEED;
    }

    @Override
    public int getPreviewNum() {
        return PREVIEW_NUM;
    }
}
