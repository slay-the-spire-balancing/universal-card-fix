package UniversalCardFix.patches.actions.common;

import UniversalCardFix.Utils;

import com.megacrit.cardcrawl.core.Settings;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import org.apache.logging.log4j.Logger;

public class AbstractPlayerPatch {
    @SpirePatch(
        clz = AbstractPlayer.class,
        method = "onCardDrawOrDiscard"
    )
    public static class OnCardDrawOrDiscard {
        public static void Prefix(AbstractPlayer __instance, Logger ___logger) {
            for (AbstractPower p : __instance.powers) {
                p.onDrawOrDiscard();
            }
            for (AbstractRelic r : __instance.relics) {
                r.onDrawOrDiscard();
            }
            if (__instance.hasPower("Corruption")) {
                for (AbstractCard c : __instance.hand.group) {
                    if (c.type != AbstractCard.CardType.SKILL || c.costForTurn == 0) continue;
                    Utils.corruptionCostModify(___logger, c);
                }
            }
            __instance.hand.applyPowers();
            __instance.hand.glowCheck();

            return;
        }
    }

    @SpirePatch(
        clz = AbstractPlayer.class,
        method = "useCard"
    )
    public static class UseCard {
        public static void Replace(AbstractPlayer __instance, AbstractCard c, AbstractMonster monster, int energyOnUse) {
            if (c.type == AbstractCard.CardType.ATTACK) {
                __instance.useFastAttackAnimation();
            }
            c.calculateCardDamage(monster);
            if (c.cost == -1 && EnergyPanel.totalCount < energyOnUse && !c.ignoreEnergyOnUse) {
                c.energyOnUse = EnergyPanel.totalCount;
            }
            if (c.cost == -1 && c.isInAutoplay) {
                c.freeToPlayOnce = true;
            }
            c.use(__instance, monster);
            AbstractDungeon.actionManager.addToBottom(new UseCardAction(c, monster));
            if (!c.dontTriggerOnUseCard) {
                __instance.hand.triggerOnOtherCardPlayed(c);
            }
            __instance.hand.removeCard(c);
            __instance.cardInUse = c;
            c.target_x = Settings.WIDTH / 2;
            c.target_y = Settings.HEIGHT / 2;
            // Patched here; removes free energy use based on Corruption status
            if (!(c.costForTurn <= 0 || c.freeToPlay() || c.isInAutoplay)) {
                __instance.energy.use(c.costForTurn);
            }
        }
    }
}
