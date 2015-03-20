package com.progragdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Carlos on 3/19/2015.
 */
public class Cocodrile {

    public static final float SPEED = 100f;

    Vector2 position;

    Animation animation;
    TextureRegion currentFrame;
    float stateTime;

    public Cocodrile(float x, float y){

        position = new Vector2(x,y);

        //Carga la animacion
        animation = new Animation(0.25f, new Sprite(new Texture("crocodile1.png")),new Sprite(new Texture("crocodile2.png")));

    }

    //Desplaza la tabla en el eje x

    public void move(Vector2 movement){
        movement.scl(SPEED);
        position.add(movement);
    }

    public void render(SpriteBatch batch){

        batch.draw(currentFrame, position.x, position.y);
    }

    public void update(float dt){
        //Calcula el tiempo para cargar el frame que proceda de la animacion
        stateTime += dt;
        currentFrame = animation.getKeyFrame(stateTime, true);

        move(new Vector2(dt, 0));

        //Comprueba los limites de la pantalla
        if(position.x > 1280 + currentFrame.getRegionWidth())
            position.x = 0 - currentFrame.getRegionWidth();
    }
}
