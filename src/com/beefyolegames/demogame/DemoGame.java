package com.beefyolegames.demogame;

import com.beefyolegames.beefyengine.framework.Screen;
import com.beefyolegames.beefyengine.impl.DesktopGame;
import com.beefyolegames.demogame.screens.GameScreen;

/**
 * Created by Leo Febey on 11/01/2016.
 */
public class DemoGame extends DesktopGame {
    public DemoGame(){
        super();
    }

    @Override
    public Screen getStartScreen(){
        return new GameScreen(this);
    }

}
