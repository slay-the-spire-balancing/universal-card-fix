package UniversalCardFix;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Utils {
    public static void relicAddToTop(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToTop(action);
    }

    public static void cardUpgradeName(AbstractCard card) {
        Method method = null;
        try {
            method = AbstractCard.class.getDeclaredMethod("upgradeName");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        method.setAccessible(true);
        try {
            method.invoke(card);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cardUpgradeBlock(AbstractCard card, int amount) {
        card.baseBlock += amount;
        card.upgradedBlock = true;
    }
}
