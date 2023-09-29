package UniversalCardFix.patches.cards.blue;


import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.blue.SelfRepair;

public class SelfRepairPatch {
    @SpirePatch(clz = SelfRepair.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(SelfRepair __instance) {
            __instance.baseMagicNumber = 6;
            __instance.magicNumber = 6;
        }
    }
}
