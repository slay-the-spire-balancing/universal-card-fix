package UniversalCardFix.patches.powers;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.NightmarePower;

public class NightmarePowerPatch {
    @SpirePatch(
        clz = NightmarePower.class,
        method = SpirePatch.CONSTRUCTOR
    )
    public static class Constructor {
        public static void Postfix(NightmarePower __instance, AbstractCreature owner, int cardAmt, AbstractCard copyMe, AbstractCard ___card) {
            ___card.isEthereal = true;
            ___card.rawDescription = "Ethereal. NL " + ___card.rawDescription;
        }
    }
}
