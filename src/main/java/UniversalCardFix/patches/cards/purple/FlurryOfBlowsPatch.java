package UniversalCardFix.patches.cards.purple;

import UniversalCardFix.Utils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.purple.FlurryOfBlows;

public class FlurryOfBlowsPatch {
    @SpirePatch(clz = FlurryOfBlows.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(FlurryOfBlows __instance) {
            __instance.baseDamage = 3;
        }
    }

    @SpirePatch(clz = FlurryOfBlows.class, method = "upgrade")
    public static class Upgrade {
        public static void Replace(FlurryOfBlows __instance) {
            if (!__instance.upgraded) {
                Utils.cardUpgradeName(__instance);
                Utils.cardUpgradeDamage(__instance, 3);
            }
        }
    }
}
