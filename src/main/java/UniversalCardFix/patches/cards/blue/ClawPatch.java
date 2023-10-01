package UniversalCardFix.patches.cards.blue;

import UniversalCardFix.Utils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.blue.Claw;

public class ClawPatch {
    @SpirePatch(clz = Claw.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(Claw __instance) {
            __instance.baseDamage = 5;
            __instance.baseMagicNumber = 3;
            __instance.magicNumber = 3;
        }
    }

    @SpirePatch(clz = Claw.class, method = "upgrade")
    public static class Upgrade {
        public static void Replace(Claw __instance) {
            if (!__instance.upgraded) {
                Utils.cardUpgradeName(__instance);
                Utils.cardUpgradeDamage(__instance, 1);
            }
        }
    }
}
