package UniversalCardFix.patches.cards.purple;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.purple.CutThroughFate;

public class CutThroughFatePatch {
    @SpirePatch(clz = CutThroughFate.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(CutThroughFate __instance) {
            __instance.baseMagicNumber = 1;
            __instance.magicNumber = 1;
        }
    }
}
