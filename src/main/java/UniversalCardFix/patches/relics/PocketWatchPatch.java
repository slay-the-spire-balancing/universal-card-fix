package UniversalCardFix.patches.relics;

import UniversalCardFix.Utils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.Pocketwatch;

public class PocketWatchPatch {
    private static final int AMT = 2;

    @SpirePatch(clz = Pocketwatch.class, method = "getUpdatedDescription")
    public static class GetUpdatedDescription {
        public static String Replace(Pocketwatch __instance) {
            return __instance.DESCRIPTIONS[0].replace("draw #b3", "draw #b" + AMT);
        }
    }

    @SpirePatch(clz = Pocketwatch.class, method = "atTurnStartPostDraw")
    public static class AtTurnStartPostDraw {
        public static void Replace(Pocketwatch __instance) {
            boolean firstTurn = Utils.getField(__instance, "firstTurn");

            if (__instance.counter <= 3 && !firstTurn) {
                Utils.relicAddToBot(new DrawCardAction(AbstractDungeon.player, 3));
            } else {
                Utils.setField(__instance, "firstTurn", false);
            }

            __instance.counter = 0;
            __instance.beginLongPulse();
        }
    }
}
