package UniversalCardFix.patches.cards.colorless;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.HandOfGreed;

public class HandOfGreedPatch {
    @SpirePatch(clz = HandOfGreed.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(HandOfGreed __instance) {
            // HEALING removes it from the random card generation pool.
            __instance.tags.add(AbstractCard.CardTags.HEALING);
        }
    }
}
