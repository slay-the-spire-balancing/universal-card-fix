package UniversalCardFix.patches.cards.purple;

import UniversalCardFix.Utils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.Crescendo;

public class CrescendoPatch {
    @SpirePatch(clz = Crescendo.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(Crescendo __instance) {
            __instance.rarity = AbstractCard.CardRarity.UNCOMMON;
            Utils.cardCreateCardImage(__instance);
        }
    }
}
