package UniversalCardFix.patches.cards.green;

import UniversalCardFix.Utils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.green.Alchemize;

public class AlchemizePatch {
    @SpirePatch(clz = Alchemize.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(Alchemize __instance) {
            __instance.isEthereal = true;
            __instance.rawDescription = "Ethereal. NL Obtain a random potion. NL Exhaust.";
            // This change is correct both in the game and when you click on it in the compendium
            // but is not correct on the main compendium change.
        }
    }
}
