package UniversalCardFix.patches.helpers;

import UniversalCardFix.events.shrines.GremlinMatchGame;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.helpers.EventHelper;

public class EventHelperPatch {
    @SpirePatch(clz = EventHelper.class, method = "getEvent")
    public static class GetEvent {
        public static AbstractEvent Postfix(AbstractEvent __result, String key) {
            switch (key) {
                case "Match and Keep!":
                    return new GremlinMatchGame();
            }
            return __result;
        }
    }
}
