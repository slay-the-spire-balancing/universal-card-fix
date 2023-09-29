package UniversalCardFix.patches.cards.purple;

import UniversalCardFix.Utils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.purple.FollowUp;

public class FollowUpPatch {
    @SpirePatch(clz = FollowUp.class, method = "upgrade")
    public static class Upgrade {
        public static void Replace(FollowUp __instance) {
            if (!__instance.upgraded) {
                Utils.cardUpgradeName(__instance);
                Utils.cardUpgradeDamage(__instance, 3);
            }
        }
    }
}
