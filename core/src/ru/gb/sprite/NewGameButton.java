package ru.gb.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.gb.base.BaseButton;
import ru.gb.math.Rect;
import ru.gb.screen.GameScreen;

public class NewGameButton extends BaseButton {
    private static final float HEIGHT = 0.05f;
    private static final float PADDING = 0.3f;

    private final GameScreen gameScreen;

    public NewGameButton(TextureAtlas atlas, GameScreen gameScreen) {
        super(atlas.findRegion("button_new_game"));
        this.gameScreen = gameScreen;
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(HEIGHT);
        setBottom(worldBounds.getBottom() + PADDING);
    }

    @Override
    public void action() {
        gameScreen.reload();
    }
}
