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

    private static void invoke(String m, Object obj, Object... args) {
        Method method = null;
        try {
            method = AbstractCard.class.getDeclaredMethod(m);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        method.setAccessible(true);
        try {
            method.invoke(obj, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cardUpgradeName(AbstractCard card) {
        invoke("upgradeName", card);
    }

    public static void cardCreateCardImage(AbstractCard card) {
        invoke("createCardImage", card);
    }

    public static void cardUpgradeBlock(AbstractCard card, int amount) {
        card.baseBlock += amount;
        card.upgradedBlock = true;
    }

    public static void cardUpgradeDamage(AbstractCard card, int amount) {
        card.baseDamage += amount;
        card.upgradedDamage = true;
    }

    public static void cardUpgradeMagicNumber(AbstractCard card, int amount) {
        card.baseMagicNumber += amount;
        card.magicNumber = card.baseMagicNumber;
        card.upgradedMagicNumber = true;
    }

}
