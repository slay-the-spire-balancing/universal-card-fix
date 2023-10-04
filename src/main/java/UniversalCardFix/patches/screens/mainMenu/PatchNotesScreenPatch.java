package UniversalCardFix.patches.screens.mainMenu;

import UniversalCardFix.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.screens.mainMenu.PatchNotesScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class PatchNotesScreenPatch {
    public static final Logger logger = LogManager.getLogger(PatchNotesScreenPatch.class.getName());

    @SpirePatch(clz = PatchNotesScreen.class, method = "openLog")
    public static class OpenLog {
        public static void Replace(PatchNotesScreen __instance) {
            FileHandle fd = Gdx.files.internal("UniversalCardFix" + File.separator + "changelog.txt");
            String changeLog = fd.readString();
            FileHandle log = Utils.getField(__instance, PatchNotesScreen.class, "log");


            try {
                BufferedReader br = new BufferedReader(log.reader());
                Throwable var2 = null;

                try {
                    StringBuilder sb = new StringBuilder();
                    String line = "";

                    try {
                        line = br.readLine();
                    } catch (IOException var15) {
                        logger.error(var15);
                    }

                    while (line != null) {
                        sb.append(line);
                        sb.append(System.lineSeparator());
                        line = br.readLine();
                    }

                    sb.insert(0, changeLog + System.lineSeparator());
                    Utils.setField(__instance, PatchNotesScreen.class, "text", sb.toString());
                    br.close();
                } catch (Throwable var16) {
                    var2 = var16;
                    throw var16;
                } finally {
                    if (var2 != null) {
                        try {
                            br.close();
                        } catch (Throwable var14) {
                            var2.addSuppressed(var14);
                        }
                    } else {
                        br.close();
                    }

                }
            } catch (IOException var18) {
                logger.error(var18);
            }
        }
    }
}
