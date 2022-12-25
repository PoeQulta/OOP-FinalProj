/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platecircus.world;
import com.mycompany.platecircus.object.ClownObject;
import com.mycompany.platecircus.object.ImageObject;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
/**
 *
 * @author poequ
 */
public class GameWorld implements World {
    private static GameWorld instance = new GameWorld();
    private static final int CONTROLSPEED = 10;
    private static WorldState difState = new EasyState();;
    private static int MAX_TIME = 1 * 60 * 1000;
    private int score = 0;
    private long endTime, startTime = System.currentTimeMillis();
    private final int width = 800;
    private final int height = 600;
    private final List<GameObject> constantLst = new LinkedList<GameObject>();
    private final List<GameObject> movingLst = new LinkedList<GameObject>();
    private final List<GameObject> controlLst = new LinkedList<GameObject>();
    private GameWorld()
    {
		controlLst.add(new ClownObject(width/3, (int)((height-ClownObject.GetHeight())*0.8), "/clown%d.png"));

    }
    public static GameWorld getWorld()
    {
        return instance;
    }
    public static void resetWorld(String dif)
    {
        if(dif.equalsIgnoreCase("hard"))
            difState = new HardState();
        else if(dif.equalsIgnoreCase("medium"))
            difState = new MediumState();
        else
            difState = new EasyState();
        instance = new GameWorld();
    }
    @Override
    public List<GameObject> getConstantObjects() {
        return constantLst;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return movingLst;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return controlLst;
        }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public boolean refresh() {
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME; // time end and game over
		return !timeout;
    }

    @Override
    public String getStatus() {
        return "Score=" + score + "   |   Time=" + Math.max(0, (MAX_TIME - (System.currentTimeMillis()-startTime))/1000);
    }

    @Override
    public int getSpeed()
    {
        return difState.getSpeed();
    }

    @Override
    public int getControlSpeed() {
        return CONTROLSPEED;
    }
    
}
