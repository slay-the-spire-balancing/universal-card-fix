package UniversalCardFix.patches.cards.red;

import UniversalCardFix.Utils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.red.Rampage;

public class RampagePatch {
    @SpirePatch(clz = Rampage.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(Rampage __instance) {
            __instance.baseDamage = 9;
        }
    }

    @SpirePatch(clz = Rampage.class, method = "upgrade")
    public static class Upgrade {
        public static void Replace(Rampage __instance) {
            if (!__instance.upgraded) {
                Utils.cardUpgradeName(__instance);
                Utils.cardUpgradeMagicNumber(__instance, 4);
            }
        }
    }

}
