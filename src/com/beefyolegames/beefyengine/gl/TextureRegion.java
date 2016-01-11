package com.beefyolegames.beefyengine.gl;

/**
 * Created by Leo Febey on 3/01/2016.
 */
public class TextureRegion {
    public final float u1, u2;
    public final float v1, v2;
    Texture texture;

    public TextureRegion(Texture texture, float x, float y, float width,
                         float height) {
        this.u1 = x / texture.getWidth();
        this.v1 = y / texture.getHeight();
        this.u2 = this.u1 + width / texture.getWidth();
        this.v2 = this.v1 + height / texture.getHeight();
        this.texture = texture;
    }
}
