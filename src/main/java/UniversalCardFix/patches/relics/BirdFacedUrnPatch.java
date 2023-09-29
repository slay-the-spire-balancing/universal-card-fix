package UniversalCardFix.patches.relics;

import UniversalCardFix.UniversalCardFix;
import UniversalCardFix.Utils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.BirdFacedUrn;

public class BirdFacedUrnPatch {
    private static final int MAX_HEAL_TIMES = 6;

    @SpirePatch(clz = BirdFacedUrn.class, method = "getUpdatedDescription")
    public static class GetUpdatedDescription {
        public static String Replace(BirdFacedUrn __instance) {
            return __instance.DESCRIPTIONS[0] + 2 + __instance.DESCRIPTIONS[1] + " Up to #b" + MAX_HEAL_TIMES + " times per combat.";
        }
    }

    @SpirePatch(clz = BirdFacedUrn.class, method = "onUseCard")
    public static class OnUseCard {
        public static void Replace(BirdFacedUrn __instance, AbstractCard card, UseCardAction action) {
            if (UniversalCardFix.birdFacedUrnHealedTimes >= MAX_HEAL_TIMES) {
                return;
            }

            if (card.type == AbstractCard.CardType.POWER) {
                __instance.flash();
                Utils.relicAddToTop(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 2));
                Utils.relicAddToTop(new RelicAboveCreatureAction(AbstractDungeon.player, __instance));
                UniversalCardFix.birdFacedUrnHealedTimes++;
            }
        }
    }
}
