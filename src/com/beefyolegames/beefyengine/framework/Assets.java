package com.beefyolegames.beefyengine.framework;

/**
 * Created by Leo Febey on 30/12/2015.
 */
public interface Assets {
    public void load();

    public void reload();

    public void playSound(Sound sound);

    public void unload();
}
