package com.progragdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Carlos on 3/19/2015.
 */
public class Snake {
    Vector2 position;

    Animation animation;
    TextureRegion currentFrame;
    float stateTime;

    public Snake(float x, float y){

        position = new Vector2(x,y);

        //Carga la animacion
        animation = new Animation(1f / 3f, new Sprite(new Texture("snake1.png")),new Sprite(new Texture("snake2.png")),new Sprite(new Texture("snake3.png")),new Sprite(new Texture("snake4.png")));

    }

    public void render(SpriteBatch batch){
        batch.draw(currentFrame, position.x, position.y);
    }

}
