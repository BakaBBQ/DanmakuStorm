import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by LBQ on 7/4/14.
 */
public class DanmakuGame extends Game {
    public SpriteBatch batch;
    public BitmapFont font;
    public void create(){

    }

    public void render() {
        super.render();
    }

    public void dispose(){
        batch.dispose();
        font.dispose();
    }
}
