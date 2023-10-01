package UniversalCardFix.patches.cards.blue;

import UniversalCardFix.Utils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.blue.Melter;

public class MelterPatch {
    @SpirePatch(clz = Melter.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(Melter __instance) {
            __instance.baseDamage = 11;
        }
    }
}
