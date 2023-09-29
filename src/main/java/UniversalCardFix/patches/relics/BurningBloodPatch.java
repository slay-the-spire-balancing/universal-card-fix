package UniversalCardFix.patches.relics;

import UniversalCardFix.Utils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.BurningBlood;

public class BurningBloodPatch {
    private static final int HEALTH_AMT = 4;

    @SpirePatch(clz = BurningBlood.class, method = "getUpdatedDescription")
    static class GetUpdatedDescription {
        public static String Replace(BurningBlood __instance) {
            return __instance.DESCRIPTIONS[0] + HEALTH_AMT + __instance.DESCRIPTIONS[1];
        }
    }

    @SpirePatch(clz = BurningBlood.class, method = "onVictory")
    static class OnVictory {
        public static void Replace(BurningBlood __instance) {
            __instance.flash();
            Utils.relicAddToTop(new RelicAboveCreatureAction(AbstractDungeon.player, __instance));
            AbstractPlayer p = AbstractDungeon.player;
            if (p.currentHealth > 0) {
                p.heal(HEALTH_AMT);
            }
        }
    }
}
