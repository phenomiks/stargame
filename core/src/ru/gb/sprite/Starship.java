package ru.gb.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.gb.base.Sprite;
import ru.gb.math.Rect;

public class Starship extends Sprite {
    private static final float V_LEN = 0.007f;
    private static final float PADDING = 0.1f;

    private Rect worldBounds;
    private Vector2 v;
    private Vector2 touch;
    private boolean isKeyPressed;
    private boolean isTouched;

    public Starship(TextureRegion region) {
        super(region);
        v = new Vector2();
        touch = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(worldBounds.getHeight() / 6);
        pos.set(0, worldBounds.getBottom() + PADDING);
    }

    public void draw(SpriteBatch batch) {
        if ((isWidthLeftBorder() && v.x < 0)
                || (isWidthRightBorder() && v.x > 0)
                || (isHeightLeftBorder() && v.y < 0)
                || (isHeightRightBorder() && v.y > 0)) {
            isKeyPressed = false;
            isTouched = false;
        }

        if (isKeyPressed) {
            pos.add(v);
        } else if (isTouched) {
            if (pos.dst(touch) > V_LEN) {
                pos.add(v);
            } else {
                pos.set(touch);
                isTouched = false;
            }
        }

        super.draw(batch);
    }

    private boolean isWidthLeftBorder() {
        return pos.x < -(worldBounds.getWidth() / 2 - PADDING);
    }

    private boolean isWidthRightBorder() {
        return pos.x > (worldBounds.getWidth() / 2 - PADDING);
    }

    private boolean isHeightLeftBorder() {
        return pos.y < -(worldBounds.getHeight() / 2 - PADDING);
    }

    private boolean isHeightRightBorder() {
        return pos.y > (worldBounds.getHeight() / 2 - PADDING);
    }

    public boolean keyDown(int keycode) {
        if (keycode == 29) {
            isKeyPressed = true;
            v.set(-V_LEN, 0);
        } else if (keycode == 32) {
            isKeyPressed = true;
            v.set(V_LEN, 0);
        } else if (keycode == 51) {
            isKeyPressed = true;
            v.set(0, V_LEN);
        } else if (keycode == 47) {
            isKeyPressed = true;
            v.set(0, -V_LEN);
        }

        return false;
    }

    public boolean keyUp(int keycode) {
        isKeyPressed = false;
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        isTouched = true;
        this.touch.set(touch);
        v.set(touch.cpy().sub(pos).setLength(V_LEN));
        return super.touchDown(touch, pointer, button);
    }
}
