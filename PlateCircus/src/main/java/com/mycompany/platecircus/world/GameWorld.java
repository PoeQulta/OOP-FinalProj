/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platecircus.world;
import com.mycompany.platecircus.object.ClownObject;
import com.mycompany.platecircus.object.ImageObject;
import com.mycompany.platecircus.object.IntrinsicObject;
import com.mycompany.platecircus.object.MovingObject;
import com.mycompany.platecircus.object.MovingObjectFactory;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.util.ArrayList;
import java.util.function.Predicate;
/**
 *
 * @author poequ
 */
public class GameWorld implements World {
    private static GameWorld instance = new GameWorld();
    private static final int CONTROLSPEED = 10;
    private static WorldState difState = new EasyState();;
    private static int MAX_TIME = 5 * 60 * 1000;
    private static int OBJ_GEN_TIMEOUT = 3 * 1000;
    private static int SPAWN_RANGE;
    private static int SPAWN_OFFSET;
    private int score = 0;
    private long endTime, gentime, startTime = System.currentTimeMillis();
    private final int width = 800;
    private final int height = 600;
    private final List<GameObject> constantLst = new LinkedList<GameObject>();
    private final List<GameObject> movingLst = new LinkedList<GameObject>();
    private final List<GameObject> controlLst = new LinkedList<GameObject>();
    private GameWorld()
    {
		controlLst.add(new ClownObject(width/3, (int)((height-ClownObject.getHEIGHT())*0.9), "/clown%d.png"));
                
                //controlLst.add(new MovingObject(new IntrinsicObject(Color.BLUE,"/plate%d.png",1,50,0),100,400));
                movingLst.add(MovingObjectFactory.getRandMovingObj(50, -50));
                gentime = System.currentTimeMillis();
                SPAWN_RANGE = (width-controlLst.get(0).getWidth());
                SPAWN_OFFSET = controlLst.get(0).getWidth()/2;

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
    private boolean intersect(GameObject o1, GameObject o2){
		return  o1.getX()< o2.getX() + o2.getWidth() && o1.getX() + o1.getWidth() > o2.getX() && o1.getY() < o2.getY() + o2.getHeight() &&  o1.getY() > o2.getY();
                        
	}
    private boolean ConsecConnect()
    {
        int lastIndex = controlLst.size()-1;
        MovingObject a[] = new MovingObject[3];
        for(int i = 0;i<3&&i<lastIndex;i++)
        {
            if(controlLst.get(lastIndex-i) instanceof MovingObject k) a[i] = k;
        }
        if(a[0]==null ||a[1]==null||a[2]==null) return false;
        return a[0].getColor().equals(a[1].getColor()) && a[0].getColor().equals(a[2].getColor());
    }
    @Override
    public boolean refresh() {
        
        
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME;// time end and game over
        boolean ObjGenTime = System.currentTimeMillis() - gentime > (OBJ_GEN_TIMEOUT/difState.getSpeed());
        if(ObjGenTime)
        {
            movingLst.add(MovingObjectFactory.getRandMovingObj((int)(Math.random()*SPAWN_RANGE)+SPAWN_OFFSET, -50));
            gentime = System.currentTimeMillis();
        }
        
        movingLst.removeIf(ob -> ob.getY()>height || !ob.isVisible());
        controlLst.removeIf(ob -> !ob.isVisible());
        movingLst.stream().forEach(
                (obj) -> {if(obj instanceof MovingObject k) k.Update(getSpeed());}
        );
        List<GameObject> holder = new ArrayList();
        
        for(int i=0; i<controlLst.size();i++)
        {
            GameObject obj = controlLst.get(i);
            Predicate ObjCollision = (ob) -> {
                        if(ob instanceof MovingObject k)
                        {
                            return intersect(obj,k) && k.getType()==0;
                        }
                    return false;
                    };
            Predicate BombCollision = (ob) -> {
                        if(ob instanceof MovingObject k)
                        {
                            return intersect(obj,k) && k.getType()==1;
                        }
                    return false;
                    };
            holder.addAll(movingLst.stream().filter(ObjCollision).toList());
            holder.stream().forEach(
                    (ob) -> {if(ob instanceof MovingObject k) k.setFreefall(false);}
            );
            movingLst.removeIf(ObjCollision);
            movingLst.stream().filter(BombCollision).forEach(
                    (ob) -> {if(ob instanceof MovingObject k) k.setVisible(false); score = Math.max(0, score-10);}
            );
            
        }
        controlLst.addAll(holder);
        if(ConsecConnect()){
            score+=10;
            int lastIndex = controlLst.size()-1;
            for(int i = 0;i<3&&i<lastIndex;i++)
            {
                if(controlLst.get(lastIndex-i) instanceof MovingObject k) k.setVisible(false);
            }
        }
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
