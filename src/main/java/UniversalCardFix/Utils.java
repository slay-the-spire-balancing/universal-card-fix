package UniversalCardFix;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class Utils {
    public static void relicAddToTop(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToTop(action);
    }
}
