package ru.gb.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.gb.base.BaseScreen;

public class MenuScreen extends BaseScreen {
    private Texture img;
    private Vector2 touch;
    private Vector2 v;
    private Vector2 position;
    private Vector2 direction;
    private Vector2 temp;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        touch = new Vector2();
        v = new Vector2();
        position = new Vector2(0, 0);
        direction = new Vector2();
        temp = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(img, position.x, position.y);
        temp.set(position);
        if (temp.sub(touch).len() > 1) {
            calculate();
        }
        batch.end();
    }

    private void calculate() {
        position.add(v);
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        Vector2 copy = touch.cpy();
        direction.set(copy.sub(position)).nor();
        v.set(direction);

        return false;
    }
}
