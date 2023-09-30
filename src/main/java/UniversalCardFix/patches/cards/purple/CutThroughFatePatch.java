package UniversalCardFix.patches.cards.purple;

import UniversalCardFix.Utils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.purple.CutThroughFate;

public class CutThroughFatePatch {
    @SpirePatch(clz = CutThroughFate.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(CutThroughFate __instance) {
            __instance.baseDamage = 6;
        }
    }

    @SpirePatch(clz = CutThroughFate.class, method = "upgrade")
    public static class Upgrade {
        public static void Replace(CutThroughFate __instance) {
            if (!__instance.upgraded) {
                Utils.cardUpgradeName(__instance);
                Utils.cardUpgradeDamage(__instance, 3);
            }
        }
    }

}
