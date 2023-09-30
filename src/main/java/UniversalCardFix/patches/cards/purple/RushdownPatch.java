package UniversalCardFix.patches.cards.purple;

import UniversalCardFix.Utils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.purple.Rushdown;

public class RushdownPatch {
    @SpirePatch(clz = Rushdown.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(Rushdown __instance) {
            __instance.baseMagicNumber = 1;
            __instance.magicNumber = 1;
        }
    }

    @SpirePatch(clz = Rushdown.class, method = "upgrade")
    public static class Upgrade {
        public static void Replace(Rushdown __instance) {
            if (!__instance.upgraded) {
                Utils.cardUpgradeName(__instance);
                Utils.cardUpgradeMagicNumber(__instance, 1);
            }
        }
    }
}
