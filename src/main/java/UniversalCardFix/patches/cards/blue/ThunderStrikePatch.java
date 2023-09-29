package UniversalCardFix.patches.cards.blue;

import UniversalCardFix.Utils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.blue.ThunderStrike;

public class ThunderStrikePatch {
    @SpirePatch(clz = ThunderStrike.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(ThunderStrike __instance) {
            __instance.baseDamage = 9;
        }
    }

    @SpirePatch(clz = ThunderStrike.class, method = "upgrade")
    public static class Upgrade {
        public static void Replace(ThunderStrike __instance) {
            if (!__instance.upgraded) {
                Utils.cardUpgradeName(__instance);
                Utils.cardUpgradeDamage(__instance, 3);
            }
        }
    }
}
