package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by alexd on 06-Jan-18.
 */

public class Unicorn {
    private static final int MOVEMENT=100;
    public static final int GRAVITY=-15;
    private Vector3 position;
    private Vector3 velosity;
    private Rectangle bounds;

    private Texture unicorn;

    public Unicorn(int x, int y){
        position = new Vector3(x,y,0);
        velosity = new Vector3(0,0,0);
        unicorn = new Texture("PAb1W_croper_ru.png");
        bounds = new Rectangle(x,y,unicorn.getWidth(),unicorn.getHeight());
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getUnicorn() {
        return unicorn;
    }

    public void  update(float dt){
        if (position.y>0)
            velosity.add(0,GRAVITY,0);
        velosity.scl(dt);
        position.add(MOVEMENT*dt,velosity.y,0);

        velosity.scl(1/dt);

        if (position.y<0){
            position.y=0;
        }
        bounds.setPosition(position.x, position.y);
    }

    public void jump(){
        velosity.y=350;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose() {
        unicorn.dispose();
    }
}
