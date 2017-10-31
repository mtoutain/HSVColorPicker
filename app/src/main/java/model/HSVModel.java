package model;

import java.util.Observable;

/**
 * Created by Mitch on 2017-10-25.
 */

public class HSVModel extends Observable {

    // CLASS VARIABLES
    public static final Integer MAX_HUE = 360;
    public static final float MAX_SATURATION = 1;
    public static final float MAX_VALUE = 1;
    public static final Integer MIN_HSV = 0;

    // INSTANCE VARIABLES
    private Integer hue;
    private float saturation;
    private float valueBrightness;

    /**
     * No argument constructor.
     * <p>
     * Instantiate a new instance of this class, and
     * initialize H, S, B, to min values for black.
     */
    public HSVModel() {
        this(MAX_HUE, MAX_SATURATION, MAX_VALUE);
    }

    public HSVModel(Integer hue, float saturation, float valueBrightness) {
        super();

        this.hue = hue;
        this.saturation = saturation;
        this.valueBrightness = valueBrightness;

    }

    public void asBlack() {
        this.setHSV(MIN_HSV, MIN_HSV, MIN_HSV);
    }

    public void asRed() {
        this.setHSV(MIN_HSV, MAX_SATURATION, MAX_VALUE);
    }

    public void asLime() {
        this.setHSV(120, MAX_SATURATION, MAX_VALUE);
    }

    public void asBlue() {
        this.setHSV(240, MAX_SATURATION, MAX_VALUE);
    }

    public void asYellow() {
        this.setHSV(60, MAX_SATURATION, MAX_VALUE);
    }

    public void asCyan() {
        this.setHSV(180, MAX_SATURATION, MAX_VALUE);
    }

    public void asMagenta() {
        this.setHSV(300, MAX_SATURATION, MAX_VALUE);
    }

    public void asSilver() {
        this.setHSV(MIN_HSV, MIN_HSV, 0.75f);
    }

    public void asGray() {
        this.setHSV(MIN_HSV, MIN_HSV, 0.5f);
    }

    public void asMaroon() {
        this.setHSV(MIN_HSV, MAX_SATURATION, 0.5f);
    }

    public void asOlive() {
        this.setHSV(60, MAX_SATURATION, 0.5f);
    }

    public void asGreen() {
        this.setHSV(120, MAX_SATURATION, 0.5f);
    }

    public void asPurple() {
        this.setHSV(300, MAX_SATURATION, 0.5f);
    }

    public void asTeal() {
        this.setHSV(180, MAX_SATURATION, 0.5f);
    }

    public void asNavy() {
        this.setHSV(240, MAX_SATURATION, 0.5f);
    }

    //GETTERS AND SETTERS

    public Integer getHue() {
        return hue;
    }

    public void setHue(Integer hue) {

        this.hue = hue;
        this.updateObservers();
    }

    public float getSaturation() {
        return saturation;
    }

    public void setSaturation(float saturation) {
        this.saturation = saturation;
        this.updateObservers();
    }

    public float getValueBrightness() {
        return valueBrightness;
    }

    public void setValueBrightness(float valueBrightness) {
        this.valueBrightness = valueBrightness;
        this.updateObservers();
    }

    // Convenient setter: set H, S, V
    public void setHSV(Integer hue, float saturation, float valueBrightness) {
        this.hue = hue;
        this.saturation = saturation;
        this.valueBrightness = valueBrightness;

        this.updateObservers();
    }

    // the model has changed!
    // broadcast the update method to all registered observers
    private void updateObservers() {
        this.setChanged();              //sets the dirt flag on the data
        this.notifyObservers();         //broadcasts to all listeners
        //these two methods come from the observable class
    }


}
