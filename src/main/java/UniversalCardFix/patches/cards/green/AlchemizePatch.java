package UniversalCardFix.patches.cards.green;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.green.Alchemize;

public class AlchemizePatch {
    @SpirePatch(clz = Alchemize.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(Alchemize __instance) {
            __instance.isEthereal = true;
            __instance.rawDescription = "Ethereal. NL Obtain a random potion. NL Exhaust.";
            __instance.initializeDescription();
        }
    }
}
