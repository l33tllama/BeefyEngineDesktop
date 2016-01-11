package com.beefyolegames.demogame.screens;

import com.beefyolegames.beefyengine.framework.Game;
import com.beefyolegames.beefyengine.framework.Screen;
import com.beefyolegames.beefyengine.gl.Camera2D;
import com.beefyolegames.beefyengine.gl.SpriteBatcher;
import com.beefyolegames.beefyengine.gl.Texture;
import com.beefyolegames.beefyengine.impl.DesktopGame;
import com.beefyolegames.beefyengine.math.Vector2;

/**
 * Created by Leo Febey on 11/01/2016.
 */
public class GameScreen extends Screen {

    Camera2D cam2d;
    SpriteBatcher batcher;
    Vector2 mousePoint;
    Texture testTex;
    DesktopGame game;

    public GameScreen(Game game){
        super(game);
        this.game = (DesktopGame) game;
        float frustumWidth = ((DesktopGame) game).getGLGraphics().getWidth();
        float frustumHeight = ((DesktopGame) game).getGLGraphics().getHeight();
        cam2d = new Camera2D(this.game, frustumWidth, frustumHeight );
        batcher = new SpriteBatcher(64);
    }

    @Override
    public void update(float deltaTime) {
        System.out.println("Dekta " + deltaTime);

    }

    @Override
    public void present(float deltaTime) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
