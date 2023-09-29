package UniversalCardFix.patches.cards.red;

import UniversalCardFix.Utils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.red.Shockwave;

public class ShockwavePatch {
    @SpirePatch(clz = Shockwave.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(Shockwave __instance) {
            __instance.baseMagicNumber = 2;
            __instance.magicNumber = 2;
        }
    }

    @SpirePatch(clz = Shockwave.class, method = "upgrade")
    public static class Upgrade {
        public static void Replace(Shockwave __instance) {
            if (!__instance.upgraded) {
                Utils.cardUpgradeName(__instance);
                Utils.cardUpgradeMagicNumber(__instance, 2);
            }
        }
    }
}
