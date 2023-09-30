package UniversalCardFix.patches.relics;

import UniversalCardFix.Utils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.relics.PaperCrane;

public class PaperCranePatch {
    public static final float WEAK_PERCENTAGE = 0.34f;

    @SpirePatch(clz = PaperCrane.class, method = "getUpdatedDescription")
    public static class GetUpdatedDescription {
        public static String Replace(PaperCrane __instance) {
            return "Enemies with #yWeak deal #b34% less damage rather than #b25%.";
        }
    }
}
