package UniversalCardFix.patches.cards.purple;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.purple.Rushdown;

public class RushdownPatch {
    @SpirePatch(clz = Rushdown.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(Rushdown __instance) {
            __instance.baseMagicNumber = 1;
            __instance.magicNumber = 1;
        }
    }
}
