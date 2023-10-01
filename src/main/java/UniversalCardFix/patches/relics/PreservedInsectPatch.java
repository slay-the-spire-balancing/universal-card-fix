package UniversalCardFix.patches.relics;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.PreservedInsect;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PreservedInsectPatch {
    public static float ELITE_HEALTH_MODIFIER_AMT = 0.20f;

    @SpirePatch(clz = PreservedInsect.class, method = "getUpdatedDescription")
    public static class GetUpdatedDescription {
        public static String Replace(PreservedInsect __instance) {
            return __instance.DESCRIPTIONS[0] + Math.round(100 * ELITE_HEALTH_MODIFIER_AMT) + __instance.DESCRIPTIONS[1];
        }
    }

    @SpirePatch(clz = PreservedInsect.class, method = "atBattleStart")
    public static class AtBattleStart {
        public static void Replace(PreservedInsect __instance) {
            if (AbstractDungeon.getCurrRoom().eliteTrigger) {
                __instance.flash();
                for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    int newHealth = (int)((float)m.maxHealth * (1.0f - ELITE_HEALTH_MODIFIER_AMT));
                    if (m.currentHealth <= newHealth) continue;
                    m.currentHealth = newHealth;
                    m.healthBarUpdatedEvent();
                }
                AbstractDungeon.actionManager.addToTop
                    ( (AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player
                    , (AbstractRelic)__instance)
                    );
            }
        }
    }
}
