package UniversalCardFix;

import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.OnStartBattleSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

@SpireInitializer
public class UniversalCardFix implements PostInitializeSubscriber, OnStartBattleSubscriber {
    public static int birdFacedUrnHealedTimes = 0;

    public UniversalCardFix() {
        BaseMod.subscribe(this);
    }


    public static void initialize() {
        UniversalCardFix modInitializer = new UniversalCardFix();
    }


    @Override
    public void receivePostInitialize() {
        ModPanel settingsPanel = new ModPanel();
        BaseMod.registerModBadge(
                ImageMaster.loadImage("UniversalCardFix/modBadge.png"),
                "UniversalCardFix",
                "vmService, aljce, chessai",
                "Community balance patch",
                settingsPanel
        );
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        birdFacedUrnHealedTimes = 0;
    }
}
