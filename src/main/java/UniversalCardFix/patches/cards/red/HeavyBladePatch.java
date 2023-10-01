package UniversalCardFix.patches.cards.red;

import UniversalCardFix.Utils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.red.HeavyBlade;

public class HeavyBladePatch {
    @SpirePatch(clz = HeavyBlade.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(HeavyBlade __instance) {
            __instance.baseDamage = 16;
        }
    }
}
