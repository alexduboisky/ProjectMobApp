package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.FlappyC;

import java.awt.Dialog;

/**
 * Created by alexd on 06-Jan-18.
 */

public class GameOver extends State {

    private Texture background;
    private Texture gameover;
    private BitmapFont text,prevText;
    private int record;
    public GameOver(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false,FlappyC.WIDTH/2,FlappyC.HEIGHT/2);
        gameover = new Texture("gameover.png");
        background =new Texture("4e61e31e9c436f94f54dcd73721f1eb0--gif-pictures--bit.jpg");
        text = new BitmapFont();
        prevText = new BitmapFont();
        text.setColor(Color.WHITE);
        prevText.setColor(Color.WHITE);

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
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background,0,0);
        sb.draw(gameover,camera.position.x- gameover.getWidth()/2,camera.position.y);
        record= writeRecord(PlayState.tubeCount);
        text.draw(sb,"Score: "+PlayState.tubeCount,200,400);
        prevText.draw(sb,"Best Score: "+record,200,440);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        gameover.dispose();
        System.out.print("GameOver Disposed");
    }
    private int writeRecord(int record)
    {
        int prevRecord=0;
        FileHandle handle = Gdx.files.external("record.txt");
        if(handle.exists()) {
            prevRecord = Integer.valueOf(handle.readString());
        }
        if(prevRecord<record) {
            handle.writeString("" + record, false);
            return record;
        }else{
        return prevRecord;}
    }
}
