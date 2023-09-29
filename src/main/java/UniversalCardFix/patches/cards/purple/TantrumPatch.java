package UniversalCardFix.patches.cards.purple;

import UniversalCardFix.Utils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.Tantrum;

public class TantrumPatch {
    @SpirePatch(clz = Tantrum.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(Tantrum __instance) {
            __instance.rarity = AbstractCard.CardRarity.RARE;
            Utils.cardCreateCardImage(__instance);
        }
    }
}
