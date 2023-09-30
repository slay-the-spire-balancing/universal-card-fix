package UniversalCardFix.patches.powers;

import UniversalCardFix.patches.relics.PaperCranePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.cards.DamageInfo;

public class WeakPowerPatch {
    @SpirePatch(clz = WeakPower.class, method = "atDamageGive")
    public static class AtDamageGive {
        public static float Replace(WeakPower __instance, float damage, DamageInfo.DamageType type) {
            if (type == DamageInfo.DamageType.NORMAL) {
                if (!__instance.owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Crane")) {
                    return damage * (1.0f - PaperCranePatch.WEAK_PERCENTAGE);
                }
                return damage * 0.75f;
            }
            return damage;
        }
    }

    @SpirePatch(clz = WeakPower.class, method = "updateDescription")
    public static class UpdateDescription {
        public static void Replace(WeakPower __instance) {
            int paperCranePercentage = Math.round(100 * PaperCranePatch.WEAK_PERCENTAGE);
            __instance.description = __instance.amount == 1 ? (__instance.owner != null && !__instance.owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Crane") ? __instance.DESCRIPTIONS[0] + paperCranePercentage + __instance.DESCRIPTIONS[1] + __instance.amount + __instance.DESCRIPTIONS[2] : __instance.DESCRIPTIONS[0] + 25 + __instance.DESCRIPTIONS[1] + __instance.amount + __instance.DESCRIPTIONS[2]) : (__instance.owner != null && !__instance.owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Crane") ? __instance.DESCRIPTIONS[0] + paperCranePercentage + __instance.DESCRIPTIONS[1] + __instance.amount + __instance.DESCRIPTIONS[3] : __instance.DESCRIPTIONS[0] + 25 + __instance.DESCRIPTIONS[1] + __instance.amount + __instance.DESCRIPTIONS[3]);
        }
    }
}
