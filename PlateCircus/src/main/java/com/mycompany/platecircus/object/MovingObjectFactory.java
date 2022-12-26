/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.platecircus.object;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author poequ
 */
public class MovingObjectFactory 
{
    private static Color colors[] = {Color.RED,Color.BLUE,Color.CYAN,Color.GREEN,Color.MAGENTA,Color.ORANGE,Color.PINK,Color.YELLOW};
    private static String Paths[] = {"/Bomb%d.png","/plate%d.png","/Bowl%d.png"};
    private static int heights[] = {100,20,30};
    private static int frames[] = {1,1,1};
    private static int type[] = {1,0,0};
    private static final ArrayList<IntrinsicObject> intrObj = new ArrayList();
    public static MovingObject getRandMovingObj(int x,int y)
    {
        int colorInt = (int) Math.round(Math.random()*7);
        int ObjSprite = (int) Math.round(Math.random()*2);
        Optional<IntrinsicObject> objHolder = intrObj.stream().filter(obj -> obj.getColor().equals(colors[colorInt]) && obj.getPath().equals(Paths[ObjSprite])).findFirst();
        IntrinsicObject obj;
        if(objHolder.isEmpty())
        {
            obj = new IntrinsicObject(colors[colorInt],Paths[ObjSprite],frames[ObjSprite],heights[ObjSprite],type[ObjSprite]);
            intrObj.add(obj);
        }
        else
        {
            obj = objHolder.get();
        }
            return new MovingObject(obj,x,y);
        
    }
}


