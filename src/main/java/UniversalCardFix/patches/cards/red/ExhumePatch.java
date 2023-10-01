package UniversalCardFix.patches.cards.red;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Exhume;

public class ExhumePatch {
    @SpirePatch(clz = Exhume.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(Exhume __instance) {
            // HEALING removes it from the random card generation pool.
            __instance.tags.add(AbstractCard.CardTags.HEALING);
        }
    }
}
