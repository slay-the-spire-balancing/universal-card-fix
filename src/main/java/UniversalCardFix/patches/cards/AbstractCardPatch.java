package UniversalCardFix.patches.cards;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

import org.apache.logging.log4j.Logger;

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

    @SpirePatch(
        clz = AbstractCard.class,
        method = "modifyCostForCombat"
    )
    public static class ModifyCostForCombat {
        public static void Prefix(AbstractCard __instance, int amt, Logger ___logger) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            // this would be [2], but the patch shifts things down
            StackTraceElement caller = stackTrace[3];
            ___logger.info("className = " + caller.getClassName() + ", fileName = " + caller.getFileName() + ", methodName = " + caller.getMethodName() + ", lineNumber = " + caller.getLineNumber());
            ___logger.info("modifyCostForCombat amount: " + amt);
        }
    }
}
