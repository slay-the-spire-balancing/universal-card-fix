package UniversalCardFix.patches.powers;

import UniversalCardFix.Utils;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.powers.CorruptionPower;

import org.apache.logging.log4j.Logger;

public class CorruptionPowerPatch {
    @SpirePatch(clz = CorruptionPower.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(CorruptionPower __instance) {
            __instance.description = "#ySkills cost #b1 less. Whenever you play a #ySkill, #yExhaust it.";
        }
    }

    @SpirePatch(clz = CorruptionPower.class, method = "onCardDraw")
    public static class OnCardDraw {
        public static void Replace(CorruptionPower __instance, AbstractCard card) {
            if (card.type == AbstractCard.CardType.SKILL) {
                card.setCostForTurn(card.cost - 1);
            }

            return;
        }
    }
}
