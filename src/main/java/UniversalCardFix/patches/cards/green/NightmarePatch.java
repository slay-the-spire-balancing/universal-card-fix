package UniversalCardFix.patches.cards.green;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Nightmare;

public class NightmarePatch {
    @SpirePatch(clz = Nightmare.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(Nightmare __instance) {
            // HEALING removes it from the random card generation pool.
            __instance.tags.add(AbstractCard.CardTags.HEALING);
            __instance.rawDescription = "Choose a card. NL Next turn, add !M! Ethereal copies of that card into your hand. Exhaust.";
            __instance.initializeDescription();
        }
    }
}
