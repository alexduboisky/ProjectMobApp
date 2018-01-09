package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.FlappyC;
import com.mygdx.game.Sprites.Tube;
import com.mygdx.game.Sprites.Unicorn;
import com.badlogic.gdx.utils.Array;


/**
 * Created by alexd on 06-Jan-18.
 */

public class PlayState extends State {

    public static int tubeCount =0;

    private static final int TUBE_WIDTH=212;

    private static final int TUBE_SPACING=125;
    private static final int TUBE_COUNT=4;
    private static final int GROUND_Y_OFFSET=-60;



    private Unicorn unicorn;
    private Texture ground;
    private  Texture bg;
    private Vector2 groundPos1,groundPos2;

    private Array<Tube> tubes;
    //private com.badlogic.gdx.utils.Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        tubeCount = 0;
        unicorn = new Unicorn(50,300);
        bg = new Texture("4e61e31e9c436f94f54dcd73721f1eb0--gif-pictures--bit.jpg");
        camera.setToOrtho(false, FlappyC.WIDTH/2,FlappyC.HEIGHT/2);
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(camera.position.x-camera.viewportWidth/2,GROUND_Y_OFFSET);
        groundPos2 = new Vector2((camera.position.x-camera.viewportWidth/2)+ground.getWidth(),GROUND_Y_OFFSET);
        tubes = new Array<Tube>();

        for (int i=0;i<TUBE_COUNT;i++){
            tubes.add(new Tube(i*(TUBE_SPACING + TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            unicorn.jump();
        }
    }

    Timer time;

    @Override
    public void update(float dt) {
        handleInput();
        unicorn.update(dt);
        updateGround();
        camera.position.x=unicorn.getPosition().x+80;
        boolean firstEnter = true;

        time = new Timer();
        time.start();
        for (int i=0;i<tubes.size;i++){

            Tube tube=tubes.get(i);
            if(camera.position.x - (camera.viewportWidth/2)>tube.getPosTopTube().x+tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x+((TUBE_WIDTH+TUBE_SPACING)*TUBE_COUNT));
            }
            if (tube.collides(unicorn.getBounds())){
                gsm.set(new GameOver(gsm));



            }
            if(unicorn.getPosition().y<150)
                gsm.set(new GameOver(gsm));
            if (unicorn.getPosition().x>tube.getPosTopTube().x&&unicorn.getPosition().x<tube.getPosTopTube().x+212) {
                if(firstEnter) {
                    tubeCount++;
                    firstEnter = false;
                }


            }else firstEnter=true;
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg,camera.position.x - (camera.viewportWidth/2),0);
        sb.draw(unicorn.getUnicorn(),unicorn.getPosition().x,unicorn.getPosition().y);
        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosBottomTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
        }

        sb.draw(ground,groundPos1.x,groundPos1.y);
        sb.draw(ground,groundPos2.x,groundPos2.y);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        unicorn.dispose();
        ground.dispose();
        for (Tube tube:tubes){
            tube.dispose();
        }
        System.out.print("PlayState Disposed");
    }

    private void updateGround(){
        if(camera.position.x-(camera.viewportWidth/2)>groundPos1.x+ground.getWidth())
            groundPos1.add(ground.getWidth()*2,0);
        if(camera.position.x-(camera.viewportWidth/2)>groundPos2.x+ground.getWidth())
            groundPos2.add(ground.getWidth()*2,0);
    }
}
