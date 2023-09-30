package UniversalCardFix.patches.cards.green;

import UniversalCardFix.Utils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.green.HeelHook;

public class HeelHookPatch {
    @SpirePatch(clz = HeelHookPatch.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(HeelHook __instance) {
            __instance.baseDamage = 8;
        }
    }
}
