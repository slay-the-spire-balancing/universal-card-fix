package UniversalCardFix.patches.cards.purple;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.purple.Conclude;

public class ConcludePatch {
    @SpirePatch(clz = Conclude.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(Conclude __instance) {
            __instance.baseDamage = 10;
        }
    }

}
