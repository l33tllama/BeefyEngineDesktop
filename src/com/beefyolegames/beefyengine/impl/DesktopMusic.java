package com.beefyolegames.beefyengine.impl;

import com.beefyolegames.beefyengine.framework.Music;
import org.newdawn.slick.openal.Audio;

/**
 * Created by Leo Febey on 3/01/2016.
 */
public class DesktopMusic implements Music {
    Audio streamingMusic;
    float pausedPosition;
    boolean looping;
    float volume;
    boolean paused = false;

    public DesktopMusic(Audio streamingMusic){
        this.streamingMusic = streamingMusic;
        looping = false;
        volume = 1.0f;
        pausedPosition = 0.0f;
    }

    @Override
    public void play() {
        if(paused){
            streamingMusic.setPosition(pausedPosition);
            paused = false;
        }
        streamingMusic.playAsMusic(1.0f, volume, looping);

    }

    @Override
    public void stop() {
        streamingMusic.stop();
    }

    @Override
    public void pause() {
        pausedPosition = streamingMusic.getPosition();
        streamingMusic.stop();
        paused = true;
    }

    @Override
    public void setLooping(boolean looping) {
        this.looping = looping;
    }

    @Override
    public void setVolume(float volume) {
        this.volume = volume;
    }

    @Override
    public boolean isPlaying() {
        return streamingMusic.isPlaying();
    }

    @Override
    public boolean isStopped() {
        return !streamingMusic.isPlaying();
    }

    @Override
    public boolean isLooping() {
        return looping;
    }

    @Override
    public void dispose() {
        // Android-specific stub
    }
}
