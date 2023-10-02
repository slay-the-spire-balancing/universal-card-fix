package UniversalCardFix;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.logging.log4j.Logger;

public class Utils {
    public static void relicAddToTop(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToTop(action);
    }

    public static void relicAddToBot(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToBottom(action);
    }

    private static void invoke(Object obj, Class<?> clz, String m, Object... args) {
        Method method = null;
        try {
            method = clz.getDeclaredMethod(m);
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

    public static <A> A getField(Object obj, Class<?> clz, String name) {
        Field field = null;
        A result = null;
        try {
            field = clz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        field.setAccessible(true);
        try {
            result = (A) field.get(obj);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static <A> void setField(Object obj, Class<?> clz, String name, A value) {
        Field field = null;
        try {
            field = obj.getClass().getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        field.setAccessible(true);
        try {
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cardUpgradeName(AbstractCard card) {
        invoke(card, AbstractCard.class, "upgradeName");
    }

    public static void cardCreateCardImage(AbstractCard card) {
        invoke(card, AbstractCard.class, "createCardImage");
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

    public static void corruptionCostModify(Logger logger, AbstractCard card) {
        logger.info("modifying: " + card.name);
        logger.info("costs before: cost = " + card.cost + ", costForTurn = " + card.costForTurn);
        card.modifyCostForCombat(-1);
        logger.info("costs after: cost = " + card.cost + ", costForTurn = " + card.costForTurn);
    }
}
