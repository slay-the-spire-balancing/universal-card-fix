package UniversalCardFix.patches.cards;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class AbstractCardPatch {
    @SpirePatch(
            clz = AbstractCard.class,
            method = "makeStatEquivalentCopy"
    )
    public static class MakeStatEquivalentCopy {
        @SpireInsertPatch(
                rloc = 4,
                localvars = {"card"}
        )
        public static void Insert(AbstractCard __instance, AbstractCard card) {
            card.isEthereal = __instance.isEthereal;
            card.rawDescription = __instance.rawDescription;
            card.initializeDescription();
        }
    }
}
