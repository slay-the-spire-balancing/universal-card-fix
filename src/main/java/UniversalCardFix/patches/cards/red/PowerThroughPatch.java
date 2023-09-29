package UniversalCardFix.patches.cards.red;

import UniversalCardFix.Utils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.red.PowerThrough;

public class PowerThroughPatch {
    @SpirePatch(clz = PowerThrough.class, method = SpirePatch.CONSTRUCTOR)
    public static class PowerThroughConstructorPatch {
        public static void Postfix(PowerThrough __instance) {
            __instance.baseBlock = 13;
        }
    }

    @SpirePatch(clz = PowerThrough.class, method = "upgrade")
    public static class PowerThroughUpgradePatch {
        public static void Replace(PowerThrough __instance) {
            if (!__instance.upgraded) {
                Utils.cardUpgradeName(__instance);
                Utils.cardUpgradeBlock(__instance, 5);
            }
        }
    }
}
