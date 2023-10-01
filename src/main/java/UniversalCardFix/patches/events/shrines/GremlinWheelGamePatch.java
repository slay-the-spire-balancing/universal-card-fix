package UniversalCardFix.patches.events.shrines;

import UniversalCardFix.Utils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.shrines.GremlinWheelGame;

public class GremlinWheelGamePatch {
    @SpirePatch(clz = GremlinWheelGame.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(GremlinWheelGame __instance) {
            __instance.imageEventText.setDialogOption(GremlinWheelGame.OPTIONS[8]);
        }
    }

    @SpirePatch(clz = GremlinWheelGame.class, method = "buttonEffect")
    public static class ButtonEffect {
        public static SpireReturn<Void> Prefix(GremlinWheelGame __instance, int buttonPressed) {
            if (buttonPressed == 1) {
                Utils.invoke(__instance, AbstractImageEvent.class, "openMap");
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
}
