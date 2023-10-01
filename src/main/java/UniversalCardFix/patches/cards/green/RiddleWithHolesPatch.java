package UniversalCardFix.patches.cards.green;

import UniversalCardFix.Utils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.green.RiddleWithHoles;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RiddleWithHolesPatch {
    @SpirePatch(clz = RiddleWithHoles.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(RiddleWithHoles __instance) {
            __instance.baseMagicNumber = 5;
            __instance.magicNumber = 5;
            __instance.rawDescription = "Deal !D! damage !M! times.";
            __instance.initializeDescription();
        }
    }

    @SpirePatch(clz = RiddleWithHoles.class, method = "use")
    public static class Use {
        public static void Replace(RiddleWithHoles __instance, AbstractPlayer p, AbstractMonster m) {
            for (int i = 0; i < __instance.magicNumber; ++i) {
                Utils.relicAddToBot
                    ( (AbstractGameAction)new DamageAction((AbstractCreature)m
                    , new DamageInfo((AbstractCreature)p, __instance.damage, __instance.damageTypeForTurn)
                    , AbstractGameAction.AttackEffect.SLASH_HORIZONTAL)
                    );
            }
        }
    }

    @SpirePatch(clz = RiddleWithHoles.class, method = "upgrade")
    public static class Upgrade {
        public static void Replace(RiddleWithHoles __instance) {
            if (!__instance.upgraded) {
                Utils.cardUpgradeName(__instance);
                Utils.cardUpgradeMagicNumber(__instance, 2);
            }
        }
    }
}
