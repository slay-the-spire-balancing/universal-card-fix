package UniversalCardFix.patches.actions.common;

import UniversalCardFix.Utils;

import com.megacrit.cardcrawl.core.Settings;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.UUID;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ApplyPowerActionPatch {
    public static final Logger logger = LogManager.getLogger(ApplyPowerActionPatch.class.getName());

    private static void corruptionCostModify(AbstractCard c) {
        Utils.corruptionCostModify(logger, c);
    }

    public static class Costs {
        public int cost;
        public int costForTurn;

        public Costs(int cost, int costForTurn) {
            this.cost = cost;
            this.costForTurn = costForTurn;
        }

        public void resetCosts(AbstractCard card) {
            card.cost = this.cost;
            card.costForTurn = this.costForTurn;
        }
    }

    @SpirePatch(
        clz = ApplyPowerAction.class,
        method = SpirePatch.CONSTRUCTOR,
        paramtypez={
            AbstractCreature.class,
            AbstractCreature.class,
            AbstractPower.class,
            int.class,
            boolean.class,
            AbstractGameAction.AttackEffect.class
        }
    )
    public static class Constructor {
        private static HashMap<UUID, Costs> handSkills = new HashMap<UUID, Costs>();
        private static HashMap<UUID, Costs> drawSkills = new HashMap<UUID, Costs>();
        private static HashMap<UUID, Costs> discardSkills = new HashMap<UUID, Costs>();
        private static HashMap<UUID, Costs> exhaustSkills = new HashMap<UUID, Costs>();

        public static void Prefix(ApplyPowerAction __instance, AbstractCreature target, AbstractCreature source, AbstractPower powerToApply, int stackAmount, boolean isFast, AbstractGameAction.AttackEffect effect) {
            if (powerToApply.ID.equals("Corruption")) {
                logger.info("ApplyPowerActionPatch: Corruption Prefix");

                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    if (c.type != AbstractCard.CardType.SKILL) continue;
                    handSkills.put(c.uuid, new Costs(c.cost, c.costForTurn));
                }
                for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                    if (c.type != AbstractCard.CardType.SKILL) continue;
                    drawSkills.put(c.uuid, new Costs(c.cost, c.costForTurn));
                }
                for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                    if (c.type != AbstractCard.CardType.SKILL) continue;
                    discardSkills.put(c.uuid, new Costs(c.cost, c.costForTurn));
                }
                for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
                    if (c.type != AbstractCard.CardType.SKILL) continue;
                    exhaustSkills.put(c.uuid, new Costs(c.cost, c.costForTurn));
                }
            }
        }

        public static void Postfix(ApplyPowerAction __instance, AbstractCreature target, AbstractCreature source, AbstractPower powerToApply, int stackAmount, boolean isFast, AbstractGameAction.AttackEffect effect) {
            if (powerToApply.ID.equals("Corruption")) {
                logger.info("ApplyPowerActionPatch: Corruption Postfix");

                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    if (c.type != AbstractCard.CardType.SKILL) continue;
                    Costs costs = handSkills.get(c.uuid);
                    costs.resetCosts(c);
                    corruptionCostModify(c);
                }
                for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                    if (c.type != AbstractCard.CardType.SKILL) continue;
                    Costs costs = drawSkills.get(c.uuid);
                    costs.resetCosts(c);
                    corruptionCostModify(c);
                }
                for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                    if (c.type != AbstractCard.CardType.SKILL) continue;
                    Costs costs = discardSkills.get(c.uuid);
                    costs.resetCosts(c);
                    corruptionCostModify(c);
                }
                for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
                    if (c.type != AbstractCard.CardType.SKILL) continue;
                    Costs costs = exhaustSkills.get(c.uuid);
                    costs.resetCosts(c);
                    corruptionCostModify(c);
                }
            }
        }
    }
/*
             if (powerToApply.ID.equals("Corruption")) {
                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    if (c.type != AbstractCard.CardType.SKILL) continue;
                    corruptionCostModify(c);
                }
                for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                    if (c.type != AbstractCard.CardType.SKILL) continue;
                    corruptionCostModify(c);
                }
                for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                    if (c.type != AbstractCard.CardType.SKILL) continue;
                    corruptionCostModify(c);
                }
                for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
                    if (c.type != AbstractCard.CardType.SKILL) continue;
                    corruptionCostModify(c);
                }
            }
*/
/*
    @SpirePatch(
        clz = ApplyPowerAction.class,
        method = SpirePatch.CONSTRUCTOR,
        paramtypez={
            AbstractCreature.class,
            AbstractCreature.class,
            AbstractPower.class,
            int.class,
            boolean.class,
            AbstractGameAction.AttackEffect.class
        }
    )
    public static class Constructor {
        public static void Prefix(ApplyPowerAction __instance, AbstractCreature target, AbstractCreature source, AbstractPower powerToApply, int stackAmount, boolean isFast, AbstractGameAction.AttackEffect effect, AbstractPower ___powerToApply, float ___startingDuration, float ___duration) {
            logger.info("Running Corruption Constructor Prefix");
            ___startingDuration = Settings.FAST_MODE ? 0.1f : (isFast ? Settings.ACTION_DUR_FASTER : Settings.ACTION_DUR_FAST);
            //__instance.setValues(target, source, stackAmount);
            __instance.target = target;
            __instance.source = source;
            __instance.amount = stackAmount;
            ___duration = ___startingDuration;
            ___powerToApply = powerToApply;
            if (AbstractDungeon.player.hasRelic("Snake Skull") && source != null && source.isPlayer && target != source && powerToApply.ID.equals("Poison")) {
                AbstractDungeon.player.getRelic("Snake Skull").flash();
                ++___powerToApply.amount;
                ++__instance.amount;
            }

            __instance.actionType = AbstractGameAction.ActionType.POWER;
            __instance.attackEffect = effect;
            if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                ___duration = 0.0f;
                ___startingDuration = 0.0f;
                __instance.isDone = true;
            }

            if (powerToApply.ID.equals("Corruption")) {
                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    if (c.type != AbstractCard.CardType.SKILL) continue;
                    corruptionCostModify(c);
                }
                for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                    if (c.type != AbstractCard.CardType.SKILL) continue;
                    corruptionCostModify(c);
                }
                for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                    if (c.type != AbstractCard.CardType.SKILL) continue;
                    corruptionCostModify(c);
                }
                for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
                    if (c.type != AbstractCard.CardType.SKILL) continue;
                    corruptionCostModify(c);
                }
            }

            return;
        }
    }
*/
}
