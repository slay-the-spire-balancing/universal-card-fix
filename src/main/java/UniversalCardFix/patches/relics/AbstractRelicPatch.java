package UniversalCardFix.patches.relics;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class AbstractRelicPatch {
    @SpirePatch(
        clz = AbstractRelic.class,
        method = "getPrice"
    )
    public static class GetPrice {
        // Shop Relic Price Revert:
        public static int Replace(AbstractRelic __instance) {
            // int[] table = {-1, 300, 150, 250, 300, 400, 999, 150}; // default
            // public static enum RelicTier {
            // DEPRECATED,
            // STARTER,
            // COMMON,
            // UNCOMMON,
            // RARE,
            // SPECIAL,
            // BOSS,
            // SHOP;
            int[] table = {-1, 300, 150, 250, 300, 400, 999, 200};
            return table[__instance.tier.ordinal()];
            // Cant use the old code because of a very confusing crash when you hit the shop screen
            // switch (__instance.tier) {
            //     case STARTER: {
            //         return 300;
            //     }
            //     case COMMON: {
            //         return 150;
            //     }
            //     case UNCOMMON: {
            //         return 250;
            //     }
            //     case RARE: {
            //         return 300;
            //     }
            //     case SHOP: {
            //         return 150;
            //     }
            //     case SPECIAL: {
            //         return 400;
            //     }
            //     case BOSS: {
            //         return 999;
            //     }
            //     case DEPRECATED: {
            //         return -1;
            //     }
            // }
            // return -1;
        }
    }
}
