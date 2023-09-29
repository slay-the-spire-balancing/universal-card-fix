package UniversalCardFix.patches.relics;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.WhiteBeast;

public class WhiteBeastPatch {
    @SpirePatch(clz = WhiteBeast.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(WhiteBeast __instance) {
            __instance.tier = AbstractRelic.RelicTier.RARE;
        }
    }
}
