package UniversalCardFix;

import basemod.TopPanelItem;
import com.badlogic.gdx.graphics.Texture;

public class TopPanel extends TopPanelItem {
    private static final Texture IMG = new Texture("UniversalCardFix/ucf.png");
    public static final String ID = "UniversalCardFix:TopPanel";

    public TopPanel() {
        super(IMG, ID);
    }

    @Override
    protected void onClick() {
    }
}
