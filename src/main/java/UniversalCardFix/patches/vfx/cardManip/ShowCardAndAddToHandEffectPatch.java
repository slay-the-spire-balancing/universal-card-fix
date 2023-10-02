package UniversalCardFix.patches.actions.common;

import UniversalCardFix.Utils;

import com.megacrit.cardcrawl.core.Settings;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowCardAndAddToHandEffectPatch {
    public static final Logger logger = LogManager.getLogger(ShowCardAndAddToHandEffectPatch.class.getName());

    private static void corruptionCostModify(AbstractCard c) {
        Utils.corruptionCostModify(logger, c);
    }

    @SpirePatch(
        clz = ShowCardAndAddToHandEffect.class,
        method = SpirePatch.CONSTRUCTOR,
        paramtypez={
            AbstractCard.class,
            float.class,
            float.class
        }
    )
    public static class Constructor1 {
        public static void Postfix(ShowCardAndAddToHandEffect __instance, AbstractCard card, float offsetX, float offsetY) {
            logger.info("Running ShowCardAndAddToHandEffect Constructor1 Postfix");
            if (AbstractDungeon.player.hasPower("Corruption") && card.type == AbstractCard.CardType.SKILL) {
                card.costForTurn = card.cost; // reset costForTurn
                corruptionCostModify(card);
            }
        }
    }

    @SpirePatch(
        clz = ShowCardAndAddToHandEffect.class,
        method = SpirePatch.CONSTRUCTOR,
        paramtypez={
            AbstractCard.class
        }
    )
    public static class Constructor2 {
        public static void Postfix(ShowCardAndAddToHandEffect __instance, AbstractCard card) {
            logger.info("Running ShowCardAndAddToHandEffect Constructor2 Postfix");
            if (AbstractDungeon.player.hasPower("Corruption") && card.type == AbstractCard.CardType.SKILL) {
                card.costForTurn = card.cost; // reset costForTurn
                corruptionCostModify(card);
            }
        }
    }
}
