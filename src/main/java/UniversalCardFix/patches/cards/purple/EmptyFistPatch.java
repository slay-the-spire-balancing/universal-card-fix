package UniversalCardFix.patches.cards.purple;

import UniversalCardFix.Utils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.purple.EmptyFist;

public class EmptyFistPatch {
    @SpirePatch(clz = EmptyFist.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(EmptyFist __instance) {
            __instance.baseDamage = 8;
        }
    }

    @SpirePatch(clz = EmptyFist.class, method = "upgrade")
    public static class Upgrade {
        public static void Replace(EmptyFist __instance) {
            if (!__instance.upgraded) {
                Utils.cardUpgradeName(__instance);
                Utils.cardUpgradeDamage(__instance, 4);
            }
        }
    }
}
