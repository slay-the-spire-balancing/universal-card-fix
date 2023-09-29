package UniversalCardFix.patches.relics;

import UniversalCardFix.Utils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.Pantograph;

import java.util.Iterator;

public class PantographPatch {
    private static final int HEAL_AMT = 15;

    @SpirePatch(clz = Pantograph.class, method = "getUpdatedDescription")
    public static class GetUpdatedDescription {
        public static String Replace(Pantograph __instance) {
            return __instance.DESCRIPTIONS[0] + HEAL_AMT + __instance.DESCRIPTIONS[1];
        }
    }

    @SpirePatch(clz = Pantograph.class, method = "atBattleStart")
    public static class AtBattleStart {
        public static void Replace(Pantograph __instance) {
            Iterator<AbstractMonster> var1 = AbstractDungeon.getMonsters().monsters.iterator();

            AbstractMonster m;
            do {
                if (!var1.hasNext()) {
                    return;
                }

                m = var1.next();
            } while (m.type != AbstractMonster.EnemyType.BOSS);

            __instance.flash();
            Utils.relicAddToTop(new HealAction(AbstractDungeon.player, AbstractDungeon.player, HEAL_AMT, 0.0F));
            Utils.relicAddToTop(new RelicAboveCreatureAction(AbstractDungeon.player, __instance));
        }
    }
}
