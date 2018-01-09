package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.FlappyC;

/**
 * Created by alexd on 06-Jan-18.
 */

public class GameOver extends State {

    private Texture background;
    private Texture gameover;

    public GameOver(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false,FlappyC.WIDTH/2,FlappyC.HEIGHT/2);
        gameover = new Texture("gameoverr.png");
        background =new Texture("4e61e31e9c436f94f54dcd73721f1eb0--gif-pictures--bit.jpg");

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(gameover,(FlappyC.WIDTH/2)-(gameover.getWidth()/2),FlappyC.HEIGHT/2);
        sb.draw(background,0,0, FlappyC.WIDTH,FlappyC.HEIGHT);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        gameover.dispose();
        System.out.print("GameOver Disposed");
    }
}
