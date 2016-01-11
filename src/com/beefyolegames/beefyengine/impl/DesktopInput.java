package com.beefyolegames.beefyengine.impl;

import com.beefyolegames.beefyengine.framework.Input;

import java.util.List;

/**
 * Created by Leo Febey on 3/01/2016.
 */
public class DesktopInput implements Input{
    @Override
    public boolean isKeyPressed(int keyCode) {
        return false;
    }

    @Override
    public boolean isKeyPressed(String keyCode) {
        return false;
    }

    @Override
    public boolean isTouchDown(int pointer) {
        return false;
    }

    @Override
    public int getTouchX(int pointer) {
        return 0;
    }

    @Override
    public int getTouchY(int pointer) {
        return 0;
    }

    @Override
    public float getAccelX() {
        return 0;
    }

    @Override
    public float getAccelY() {
        return 0;
    }

    @Override
    public float getAccelZ() {
        return 0;
    }

    @Override
    public List<KeyEvent> getKeyEvents() {
        return null;
    }

    @Override
    public List<PointerEvent> getPointerEvents() {
        return null;
    }
}
