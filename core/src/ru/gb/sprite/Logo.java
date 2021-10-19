package ru.gb.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.gb.base.Sprite;
import ru.gb.math.Rect;

public class Logo extends Sprite {
    private static final float V_LEN = 0.01f;

    private Vector2 v;
    private Vector2 touch;

    public Logo(Texture texture) {
        super(new TextureRegion(texture));
        v = new Vector2();
        touch = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight() / 6);
        pos.set(worldBounds.pos);
    }

    public void draw(SpriteBatch batch) {
        if (pos.dst(touch) > V_LEN) {
            pos.add(v);
        } else {
            pos.set(touch);
        }

        super.draw(batch);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.touch.set(touch);
        v.set(touch.cpy().sub(pos).setLength(V_LEN));
        return super.touchDown(touch, pointer, button);
    }
}
