package com.beefyolegames.beefyengine.framework;

/**
 * Created by Leo Febey on 30/12/2015.
 */
public abstract class Screen {
    protected final Game game;

    public Screen(Game game) {
        // Get access to low-level modules of the Game interface for playing audio,
        // drawing graphics, receiving input, and file IO. Can also change screen.
        this.game = game;
    }

    public abstract void update(float deltaTime);

    public abstract void present(float deltaTime);

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();
}