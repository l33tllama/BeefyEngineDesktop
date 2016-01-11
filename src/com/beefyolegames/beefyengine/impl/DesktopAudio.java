package com.beefyolegames.beefyengine.impl;

import com.beefyolegames.beefyengine.framework.Audio;
import com.beefyolegames.beefyengine.framework.Music;
import com.beefyolegames.beefyengine.framework.Sound;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;

/**
 * Created by Leo Febey on 30/12/2015.
 */
public class DesktopAudio implements Audio{
    public DesktopAudio(){}

    @Override
    public Music newMusic(String filename) {
        String fileTypeString = filename.substring(filename.length() - 3);

        org.newdawn.slick.openal.Audio music;
        try{
            if(fileTypeString.contains("ogg")){
                music = AudioLoader.getStreamingAudio("OGG", ResourceLoader.getResource(filename));
                return new DesktopMusic(music);
            } else {
                throw new IOException("Filetype not supported for music: " + fileTypeString);
            }

        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Sound newSound(String filename) {
        String fileTypeString = filename.substring(filename.length() - 3);

        //Same name! D:
        org.newdawn.slick.openal.Audio effect;

        try {
            if(fileTypeString.contains(".wav")){
                effect = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(filename));
                return new DesktopSound(effect);
            } else if (fileTypeString.contains("ogg")){
                effect = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream(filename));
                return new DesktopSound(effect);
            } else {
                throw new IOException("Filetype not supported for sound effects: " + fileTypeString);
            }

        } catch (IOException e){
            e.printStackTrace();
        }
        return null;

    }
}
