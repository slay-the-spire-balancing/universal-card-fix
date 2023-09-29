package UniversalCardFix.patches.relics;

import UniversalCardFix.Utils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.MeatOnTheBone;

public class MeatOnTheBonePatch {
    private static final int HEAL_AMT = 6;

    @SpirePatch(clz = MeatOnTheBone.class, method = "getUpdatedDescription")
    public static class GetUpdatedDescription {
        public static String Replace(MeatOnTheBone __instance) {
            return __instance.DESCRIPTIONS[0] + HEAL_AMT + __instance.DESCRIPTIONS[1];
        }
    }

    @SpirePatch(clz = MeatOnTheBone.class, method = "onTrigger")
    public static class OnTrigger {
        public static void Replace(MeatOnTheBone __instance) {
            AbstractPlayer p = AbstractDungeon.player;
            if ((float) p.currentHealth <= (float) p.maxHealth / 2.0F && p.currentHealth > 0) {
                __instance.flash();
                Utils.relicAddToTop(new RelicAboveCreatureAction(AbstractDungeon.player, __instance));
                p.heal(HEAL_AMT);
                __instance.stopPulse();
            }
        }
    }
}
