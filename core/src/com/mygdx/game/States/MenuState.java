package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.FlappyC;

/**
 * Created by alexd on 06-Jan-18.
 */

public class MenuState extends State {

    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false,FlappyC.WIDTH/2,FlappyC.HEIGHT/2);
        background =new Texture("4e61e31e9c436f94f54dcd73721f1eb0--gif-pictures--bit.jpg");
        playBtn = new Texture("kKZ8t1j.jpg");
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
        sb.draw(background,0,0, FlappyC.WIDTH,FlappyC.HEIGHT);
        sb.draw(playBtn,(FlappyC.WIDTH/2)-(playBtn.getWidth()/2),FlappyC.HEIGHT/2);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        System.out.print("MenuState Disposed");
    }
}
