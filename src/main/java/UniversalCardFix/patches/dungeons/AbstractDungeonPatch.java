package UniversalCardFix.patches.dungeons;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class AbstractDungeonPatch {
    @SpirePatch(clz = AbstractDungeon.class, method = "initializeSpecialOneTimeEventList")
    public static class InitializeSpecialOneTimeEventList {
        public static void Postfix(AbstractDungeon __instance) {
            AbstractDungeon.specialOneTimeEventList.remove("SecretPortal");
        }
    }
}
