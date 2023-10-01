package UniversalCardFix.patches.cards.purple;

import UniversalCardFix.Utils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.megacrit.cardcrawl.cards.purple.Halt;

public class HaltPatch {
    // Apply Powers Code:
    // this.baseBlock += 6 + this.timesUpgraded * 4;
    // this.baseMagicNumber = this.baseBlock;
    // super.applyPowers();
    // this.magicNumber = this.block;
    // this.isMagicNumberModified = this.isBlockModified;
    // this.baseBlock -= 6 + this.timesUpgraded * 4;
    // super.applyPowers();
    @SpirePatch(
        clz = Halt.class,
        method = "applyPowers"
    )
    public static class ApplyPowers1 {
        @SpireInsertPatch(
            rloc=1
        )
        public static void Insert(Halt __instance) {
            __instance.baseBlock -= 2;
        }
    }
    @SpirePatch(
        clz = Halt.class,
        method = "applyPowers"
    )
    public static class ApplyPowers2 {
        @SpireInsertPatch(
            rloc=6
        )
        public static void Insert(Halt __instance) {
            __instance.baseBlock += 2;
        }
    }


    @SpirePatch(clz = Halt.class, method = "upgrade")
    public static class Upgrade {
        public static void Replace(Halt __instance) {
            if (!__instance.upgraded) {
                Utils.cardUpgradeName(__instance);
                Utils.cardUpgradeBlock(__instance, 1);
                // Dont ask me why they do it this way
                __instance.baseMagicNumber = __instance.baseBlock + 6 + __instance.timesUpgraded * 2;
                __instance.upgradedMagicNumber = __instance.upgradedBlock;
            }
        }
    }
}
