package UniversalCardFix.patches.cards.purple;

import UniversalCardFix.Utils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.purple.MentalFortress;

public class MentalFortressPatch {
    @SpirePatch(clz = MentalFortress.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(MentalFortress __instance) {
            __instance.baseMagicNumber = 3;
            __instance.magicNumber = 3;
        }
    }

    @SpirePatch(clz = MentalFortress.class, method = "upgrade")
    public static class Upgrade {
        public static void Postfix(MentalFortress __instance) {
            if (!__instance.upgraded) {
                Utils.cardUpgradeName(__instance);
                Utils.cardUpgradeMagicNumber(__instance, 1);
            }
        }
    }
}
