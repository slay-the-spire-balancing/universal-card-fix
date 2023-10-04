package UniversalCardFix.patches.cards.blue;

import UniversalCardFix.Utils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.blue.Claw;

public class ClawPatch {
    @SpirePatch(clz = Claw.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(Claw __instance) {
            __instance.baseDamage = 4;
            __instance.baseMagicNumber = 2;
            __instance.magicNumber = 2;
        }
    }

    @SpirePatch(clz = Claw.class, method = "upgrade")
    public static class Upgrade {
        public static void Replace(Claw __instance) {
            if (!__instance.upgraded) {
                Utils.cardUpgradeName(__instance);
                Utils.cardUpgradeDamage(__instance, 2);
                Utils.cardUpgradeMagicNumber(__instance, 1);
            }
        }
    }
}
