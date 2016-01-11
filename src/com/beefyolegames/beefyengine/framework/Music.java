package com.beefyolegames.beefyengine.framework;

/**
 * Created by Leo Febey on 30/12/2015.
 */
public interface Music {
    public void play();

    public void stop();

    public void pause();

    public void setLooping(boolean looping);

    public void setVolume(float volume);

    public boolean isPlaying();

    public boolean isStopped();

    public boolean isLooping();

    public void dispose();
}
