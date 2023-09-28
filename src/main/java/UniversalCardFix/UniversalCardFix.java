package UniversalCardFix;

import basemod.BaseMod;
import basemod.interfaces.PostInitializeSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

@SpireInitializer
public class UniversalCardFix implements PostInitializeSubscriber {

    public UniversalCardFix() {
        BaseMod.subscribe(this);
    }


    public static void initialize() {
        UniversalCardFix modInitializer = new UniversalCardFix();
    }


    @Override
    public void receivePostInitialize() {
    }

}
