package UniversalCardFix.patches.helpers;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.monsters.exordium.GremlinNob;

public class MonsterHelperPatch {
    public static MonsterGroup getEncounter(String key) {
        switch (key) {
            case "Colosseum Nobs":
                return new MonsterGroup(new AbstractMonster[]{new GremlinNob(-270.0F, 15.0F), new GremlinNob(130.0F, 0.0F)});
            default:
                return null;
        }
    }

    @SpirePatch2(clz = MonsterHelper.class, method = "getEncounter", paramtypez = {String.class})
    static class GetEncounter {
        public static SpireReturn<MonsterGroup> Prefix(String key) {
            MonsterGroup monsterGroup = getEncounter(key);
            if (monsterGroup != null) {
                return SpireReturn.Return(monsterGroup);
            }
            return SpireReturn.Continue();
        }
    }
}
