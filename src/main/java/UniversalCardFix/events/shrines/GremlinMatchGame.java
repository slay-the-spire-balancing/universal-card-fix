package UniversalCardFix.events.shrines;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.*;

public class GremlinMatchGame extends AbstractImageEvent {
    public static final String ID = "Match and Keep!";
    private static final EventStrings eventStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static final String[] OPTIONS;
    private AbstractCard chosenCard;
    private AbstractCard hoveredCard;
    private boolean cardFlipped = false;
    private boolean gameDone = false;
    private boolean cleanUpCalled = false;
    private int attemptCount = 5;
    private CardGroup cards;
    private float waitTimer;
    private int cardsMatched;
    private CUR_SCREEN screen;
    private static final String MSG_2;
    private static final String MSG_3;
    private List<String> matchedCards;

    private boolean needFirstFlip = true;

    public GremlinMatchGame() {
        super(NAME, DESCRIPTIONS[2], "images/events/matchAndKeep.jpg");
        this.cards = new CardGroup(CardGroupType.UNSPECIFIED);
        this.waitTimer = 0.0F;
        this.cardsMatched = 0;
        this.screen = GremlinMatchGame.CUR_SCREEN.INTRO;
        this.cards.group = this.initializeCards();
        Collections.shuffle(this.cards.group, new Random(AbstractDungeon.miscRng.randomLong()));
        this.imageEventText.setDialogOption(OPTIONS[0]);
        this.matchedCards = new ArrayList();
    }

    private ArrayList<AbstractCard> initializeCards() {
        ArrayList<AbstractCard> retVal = new ArrayList();
        ArrayList<AbstractCard> retVal2 = new ArrayList();
        if (AbstractDungeon.ascensionLevel >= 15) {
            retVal.add(AbstractDungeon.getCard(CardRarity.RARE).makeCopy());
            retVal.add(AbstractDungeon.getCard(CardRarity.UNCOMMON).makeCopy());
            retVal.add(AbstractDungeon.getCard(CardRarity.COMMON).makeCopy());
            retVal.add(AbstractDungeon.returnRandomCurse());
            retVal.add(AbstractDungeon.returnRandomCurse());
        } else {
            retVal.add(AbstractDungeon.getCard(CardRarity.RARE).makeCopy());
            retVal.add(AbstractDungeon.getCard(CardRarity.UNCOMMON).makeCopy());
            retVal.add(AbstractDungeon.getCard(CardRarity.COMMON).makeCopy());
            retVal.add(AbstractDungeon.returnColorlessCard(CardRarity.UNCOMMON).makeCopy());
            retVal.add(AbstractDungeon.returnRandomCurse());
        }

        retVal.add(AbstractDungeon.player.getStartCardForEvent());
        Iterator var3 = retVal.iterator();

        AbstractCard c;
        while (var3.hasNext()) {
            c = (AbstractCard) var3.next();
            Iterator var5 = AbstractDungeon.player.relics.iterator();

            while (var5.hasNext()) {
                AbstractRelic r = (AbstractRelic) var5.next();
                r.onPreviewObtainCard(c);
            }

            retVal2.add(c.makeStatEquivalentCopy());
        }

        retVal.addAll(retVal2);

        for (var3 = retVal.iterator(); var3.hasNext(); c.target_y = c.current_y) {
            c = (AbstractCard) var3.next();
            c.current_x = (float) Settings.WIDTH / 2.0F;
            c.target_x = c.current_x;
            c.current_y = -300.0F * Settings.scale;
        }

        return retVal;
    }

    public void update() {
        super.update();
        this.cards.update();
        if (this.screen == GremlinMatchGame.CUR_SCREEN.PLAY) {
            this.updateControllerInput();
            this.updateMatchGameLogic();
        } else if (this.screen == GremlinMatchGame.CUR_SCREEN.CLEAN_UP) {
            if (!this.cleanUpCalled) {
                this.cleanUpCalled = true;
                this.cleanUpCards();
            }

            if (this.waitTimer > 0.0F) {
                this.waitTimer -= Gdx.graphics.getDeltaTime();
                if (this.waitTimer < 0.0F) {
                    this.waitTimer = 0.0F;
                    this.screen = GremlinMatchGame.CUR_SCREEN.COMPLETE;
                    GenericEventDialog.show();
                    this.imageEventText.updateBodyText(MSG_3);
                    this.imageEventText.clearRemainingOptions();
                    this.imageEventText.setDialogOption(OPTIONS[1]);
                }
            }
        }

        if (!GenericEventDialog.waitForInput) {
            this.buttonEffect(GenericEventDialog.getSelectedOption());
        }

    }

    private void updateControllerInput() {
        if (Settings.isControllerMode) {
            boolean anyHovered = false;
            int index = 0;

            for (Iterator var3 = this.cards.group.iterator(); var3.hasNext(); ++index) {
                AbstractCard c = (AbstractCard) var3.next();
                if (c.hb.hovered) {
                    anyHovered = true;
                    break;
                }
            }

            if (!anyHovered) {
                Gdx.input.setCursorPosition((int) ((AbstractCard) this.cards.group.get(0)).hb.cX,
                        Settings.HEIGHT - (int) ((AbstractCard) this.cards.group.get(0)).hb.cY);
            } else {
                float x;
                if (!CInputActionSet.up.isJustPressed() && !CInputActionSet.altUp.isJustPressed()) {
                    if (!CInputActionSet.down.isJustPressed() && !CInputActionSet.altDown.isJustPressed()) {
                        if (!CInputActionSet.left.isJustPressed() && !CInputActionSet.altLeft.isJustPressed()) {
                            if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
                                x = ((AbstractCard) this.cards.group.get(index)).hb.cX + 210.0F * Settings.scale;
                                if (x > 1375.0F * Settings.scale) {
                                    x = 640.0F * Settings.scale;
                                }

                                Gdx.input.setCursorPosition((int) x,
                                        Settings.HEIGHT - (int) ((AbstractCard) this.cards.group.get(index)).hb.cY);
                            }
                        } else {
                            x = ((AbstractCard) this.cards.group.get(index)).hb.cX - 210.0F * Settings.scale;
                            if (x < 530.0F * Settings.scale) {
                                x = 1270.0F * Settings.scale;
                            }

                            Gdx.input.setCursorPosition((int) x,
                                    Settings.HEIGHT - (int) ((AbstractCard) this.cards.group.get(index)).hb.cY);
                        }
                    } else {
                        x = ((AbstractCard) this.cards.group.get(index)).hb.cY - 230.0F * Settings.scale;
                        if (x < 175.0F * Settings.scale) {
                            x = 750.0F * Settings.scale;
                        }

                        Gdx.input.setCursorPosition((int) ((AbstractCard) this.cards.group.get(index)).hb.cX,
                                (int) ((float) Settings.HEIGHT - x));
                    }
                } else {
                    x = ((AbstractCard) this.cards.group.get(index)).hb.cY + 230.0F * Settings.scale;
                    if (x > 865.0F * Settings.scale) {
                        x = 290.0F * Settings.scale;
                    }

                    Gdx.input.setCursorPosition((int) ((AbstractCard) this.cards.group.get(index)).hb.cX,
                            (int) ((float) Settings.HEIGHT - x));
                }

                if (CInputActionSet.select.isJustPressed()) {
                    CInputActionSet.select.unpress();
                    InputHelper.justClickedLeft = true;
                }
            }

        }
    }

    private void cleanUpCards() {
        AbstractCard c;
        for (Iterator var1 = this.cards.group.iterator(); var1.hasNext(); c.target_y = -300.0F * Settings.scale) {
            c = (AbstractCard) var1.next();
            c.targetDrawScale = 0.5F;
            c.target_x = (float) Settings.WIDTH / 2.0F;
        }

    }

    private void updateMatchGameLogic() {
        if (this.waitTimer == 0.0F) {
            this.hoveredCard = null;
            Iterator<AbstractCard> var1 = this.cards.group.iterator();

            while (true) {
                while (var1.hasNext()) {
                    AbstractCard c = var1.next();
                    c.hb.update();

                    if (this.needFirstFlip && c.rarity != CardRarity.CURSE) {
                        this.needFirstFlip = false;
                        c.hb.hovered = true;
                        InputHelper.justClickedLeft = true;
                    }

                    if (this.hoveredCard == null && c.hb.hovered) {
                        c.drawScale = 0.7F;
                        c.targetDrawScale = 0.7F;
                        this.hoveredCard = c;
                        if (InputHelper.justClickedLeft && this.hoveredCard.isFlipped) {
                            InputHelper.justClickedLeft = false;
                            this.hoveredCard.isFlipped = false;
                            if (!this.cardFlipped) {
                                this.cardFlipped = true;
                                this.chosenCard = this.hoveredCard;
                            } else {
                                this.cardFlipped = false;
                                if (this.chosenCard.cardID.equals(this.hoveredCard.cardID)) {
                                    this.waitTimer = 1.0F;
                                    this.chosenCard.targetDrawScale = 0.7F;
                                    this.chosenCard.target_x = (float) Settings.WIDTH / 2.0F;
                                    this.chosenCard.target_y = (float) Settings.HEIGHT / 2.0F;
                                    this.hoveredCard.targetDrawScale = 0.7F;
                                    this.hoveredCard.target_x = (float) Settings.WIDTH / 2.0F;
                                    this.hoveredCard.target_y = (float) Settings.HEIGHT / 2.0F;
                                } else {
                                    this.waitTimer = 1.25F;
                                    this.chosenCard.targetDrawScale = 1.0F;
                                    this.hoveredCard.targetDrawScale = 1.0F;
                                }
                            }
                        }
                    } else if (c != this.chosenCard) {
                        c.targetDrawScale = 0.5F;
                    }
                }

                return;
            }
        } else {
            this.waitTimer -= Gdx.graphics.getDeltaTime();
            if (this.waitTimer < 0.0F && !this.gameDone) {
                this.waitTimer = 0.0F;
                if (this.chosenCard.cardID.equals(this.hoveredCard.cardID)) {
                    ++this.cardsMatched;
                    this.cards.group.remove(this.chosenCard);
                    this.cards.group.remove(this.hoveredCard);
                    this.matchedCards.add(this.chosenCard.cardID);
                    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(this.chosenCard.makeCopy(),
                            (float) Settings.WIDTH / 2.0F,
                            (float) Settings.HEIGHT / 2.0F));
                } else {
                    this.chosenCard.isFlipped = true;
                    this.hoveredCard.isFlipped = true;
                    this.chosenCard.targetDrawScale = 0.5F;
                    this.hoveredCard.targetDrawScale = 0.5F;
                }
                this.chosenCard = null;
                this.hoveredCard = null;

                --this.attemptCount;
                if (this.attemptCount == 0) {
                    this.gameDone = true;
                    this.waitTimer = 1.0F;
                }
            } else if (this.gameDone) {
                this.screen = GremlinMatchGame.CUR_SCREEN.CLEAN_UP;
            }
        }

    }

    protected void buttonEffect(int buttonPressed) {
        switch (this.screen) {
            case INTRO:
                switch (buttonPressed) {
                    case 0:
                        this.imageEventText.updateBodyText(MSG_2);
                        this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                        this.screen = GremlinMatchGame.CUR_SCREEN.RULE_EXPLANATION;
                        return;
                    default:
                        return;
                }
            case RULE_EXPLANATION:
                switch (buttonPressed) {
                    case 0:
                        this.imageEventText.removeDialogOption(0);
                        GenericEventDialog.hide();
                        this.screen = GremlinMatchGame.CUR_SCREEN.PLAY;
                        this.placeCards();
                        return;
                    default:
                        return;
                }
            case COMPLETE:
                logMetricObtainCards("Match and Keep!", this.cardsMatched + " cards matched", this.matchedCards);
                this.openMap();
        }

    }

    private void placeCards() {
        for (int i = 0; i < this.cards.size(); ++i) {
            ((AbstractCard) this.cards.group.get(i)).target_x =
                    (float) (i % 4) * 210.0F * Settings.xScale + 640.0F * Settings.xScale;
            ((AbstractCard) this.cards.group.get(i)).target_y =
                    (float) (i % 3) * -230.0F * Settings.yScale + 750.0F * Settings.yScale;
            ((AbstractCard) this.cards.group.get(i)).targetDrawScale = 0.5F;
            ((AbstractCard) this.cards.group.get(i)).isFlipped = true;
        }

    }

    public void render(SpriteBatch sb) {
        this.cards.render(sb);
        if (this.chosenCard != null) {
            this.chosenCard.render(sb);
        }

        if (this.hoveredCard != null) {
            this.hoveredCard.render(sb);
        }

        if (this.screen == GremlinMatchGame.CUR_SCREEN.PLAY) {
            FontHelper.renderSmartText(sb,
                    FontHelper.panelNameFont,
                    OPTIONS[3] + this.attemptCount,
                    780.0F * Settings.scale,
                    80.0F * Settings.scale,
                    2000.0F * Settings.scale,
                    0.0F,
                    Color.WHITE);
        }

    }

    public void renderAboveTopPanel(SpriteBatch sb) {
    }

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString("Match and Keep!");
        NAME = eventStrings.NAME;
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
        OPTIONS = eventStrings.OPTIONS;
        MSG_2 = DESCRIPTIONS[0];
        MSG_3 = DESCRIPTIONS[1];
    }

    private static enum CUR_SCREEN {
        INTRO, RULE_EXPLANATION, PLAY, COMPLETE, CLEAN_UP;

        private CUR_SCREEN() {
        }
    }
}
