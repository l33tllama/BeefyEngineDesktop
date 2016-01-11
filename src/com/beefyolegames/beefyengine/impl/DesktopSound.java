package com.beefyolegames.beefyengine.impl;

import com.beefyolegames.beefyengine.framework.Sound;

/**
 * Created by Leo Febey on 3/01/2016.
 */
public class DesktopSound implements Sound{

    private org.newdawn.slick.openal.Audio effect;


    public DesktopSound(org.newdawn.slick.openal.Audio effect){
        this.effect = effect;
    }

    @Override
    public void play(float volume) {
        effect.playAsSoundEffect(1.0f, 1.0f, false);
    }

    @Override
    public void dispose() {
        if(effect.isPlaying()){
            effect.stop();
        }
    }
}
