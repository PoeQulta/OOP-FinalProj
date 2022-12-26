/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platecircus.world;

/**
 *
 * @author poequ
 */
public class MediumState implements WorldState {
     private static final int SPEED = 2;
    @Override
    public int getSpeed() {
        return SPEED;
    }
}
