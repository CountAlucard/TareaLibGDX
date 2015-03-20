package com.progragdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Frog {

    public static final float SPEED = 150f;

    Vector2 position;

    Animation rightAnimation, leftAnimation, upAnimation, downAnimation;
    TextureRegion currentFrame;

    float stateTime;

    public enum State{
        RIGHT, LEFT, UP, DOWN, IDLE
    }

    public State state;

    public Frog(float x, float y){
        position = new Vector2(x, y);
        state = State.IDLE;

        //Carga las animaciones para cada direccion
        rightAnimation = new Animation(0.25f, new Sprite(new Texture("frog_right1.png")), new Sprite(new Texture("frog_right2.png")));
        leftAnimation = new Animation(0.25f, new Sprite(new Texture("frog_left1.png")), new Sprite(new Texture("frog_left2.png")));
        upAnimation = new Animation(0.25f, new Sprite(new Texture("frog_up1.png")), new Sprite(new Texture("frog_up2.png")));
        downAnimation = new Animation(0.25f, new Sprite(new Texture("frog_down1.png")), new Sprite(new Texture("frog_down2.png")));
    }

    //desplaza la tabla en el eje x
    public void move(Vector2 movement){
        movement.scl(SPEED);
        position.add(movement);
    }

    public void render(SpriteBatch batch){

        batch.draw(currentFrame, position.x, position.y);
    }

    public void update(float dt){
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("outofbounds.wav"));
        //calcula el tiempo para cargar el frame que sigue
        stateTime += dt;

        //carga el frame segun la posicion y momento del juego
        switch(state){
            case RIGHT:
                currentFrame = rightAnimation.getKeyFrame(stateTime, true);
                break;
            case LEFT:
                currentFrame = leftAnimation.getKeyFrame(stateTime, true);
                break;
            case UP:
                currentFrame = upAnimation.getKeyFrame(stateTime, true);
                break;
            case DOWN:
                currentFrame = downAnimation.getKeyFrame(stateTime, true);
                break;
            default:
                currentFrame = upAnimation.getKeyFrame(0, true);
        }

        //comprueba los limites de la pantalla
        if(position.x <= 0) {
            sound.play();
            position.x = 0;
        }
        if((position.x + currentFrame.getRegionWidth()) >= 1280){
            position.x = 1280 - currentFrame.getRegionWidth();
            sound.play();
        }

        if(position.y <= 0) {
            position.y = 0;
            sound.play();
        }
        if((position.y + currentFrame.getRegionWidth()) >= 720){
            position.y = 720 - currentFrame.getRegionWidth();
            sound.play();
        }

    }
}
