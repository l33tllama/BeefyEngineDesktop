package com.beefyolegames.beefyengine.impl;


import com.beefyolegames.beefyengine.framework.Game;
import com.beefyolegames.beefyengine.framework.Screen;

/**
 * Created by Leo Febey on 3/01/2016.
 */
public class DesktopScreen extends Screen{
    protected final DesktopGraphics glGraphics;
    protected final DesktopGame glGame;

    public DesktopScreen(Game game) {
        super(game);
        glGame = (DesktopGame) game;
        glGraphics = glGame.getGLGraphics();
    }

    @Override
    public void update(float deltaTime) {

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
