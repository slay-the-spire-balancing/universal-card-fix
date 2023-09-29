package UniversalCardFix;

import basemod.BaseMod;
import basemod.interfaces.OnStartBattleSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

@SpireInitializer
public class UniversalCardFix implements PostInitializeSubscriber, OnStartBattleSubscriber {
    public static int birdFacedUrnHealedTimes = 0;
    public static boolean pocketWatchFirstTurn = false;

    public UniversalCardFix() {
        BaseMod.subscribe(this);
    }


    public static void initialize() {
        UniversalCardFix modInitializer = new UniversalCardFix();
    }


    @Override
    public void receivePostInitialize() {
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        birdFacedUrnHealedTimes = 0;
        pocketWatchFirstTurn = true;
    }
}
