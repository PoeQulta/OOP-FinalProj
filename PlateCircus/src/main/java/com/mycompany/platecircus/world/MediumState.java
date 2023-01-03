/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platecircus.world;

/**
 *
 * @author poequ
 */
public class MediumState implements WorldStrat {
     private static final int SPEED = 2;
     private static final int PREVIEW_NUM = 5;
    @Override
    public int getSpeed() {
        return SPEED;
    }

    @Override
    public int getPreviewNum() {
        return PREVIEW_NUM;
    }
}
