/**
 * Using the Java programming language, you are to design, build, test and deploy a color picker Android app.
 * The color picker is based on hue, saturation and value/brightness (HSV).
 *
 * @author Michel Toutain (tout0004@algonquinlive.com)
 */
package com.algonquincollege.tout0004.hsvcolorpicker;

import android.app.Activity;
import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import model.HSVModel;

public class MainActivity extends Activity implements Observer, SeekBar.OnSeekBarChangeListener {

    private static final String ABOUT_DIALOG_TAG = "About Dialog";
    //private static final String LOG_TAG = "HSV";

    //Instance Variables
    private AboutDialogFragment mAboutDialogFragment;
    private TextView mColorSwatch;
    private HSVModel mModel;
    private SeekBar mHueSB;
    private SeekBar mSaturationSB;
    private SeekBar mValueSB;

    private TextView mHueTV;
    private TextView mSaturationTV;
    private TextView mValueTV;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle action bar item clicks here
        int id = item.getItemId();

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate a new HSV model
        // Initialize the model hue (min), saturation (min), value (min)
        mModel = new HSVModel();
        mModel.setHSV(HSVModel.MIN_HSV, HSVModel.MIN_HSV, HSVModel.MIN_HSV);

        // The Model is observing this Controller (class MainActivity implements Observer)
        mModel.addObserver(this);

        // reference each View
        mColorSwatch = (TextView) findViewById(R.id.colorSwatch);
        mHueSB = (SeekBar) findViewById(R.id.hueSB);
        mSaturationSB = (SeekBar) findViewById(R.id.saturationSB);
        mValueSB = (SeekBar) findViewById(R.id.valueSB);

        mHueTV = (TextView) findViewById(R.id.hue);
        mSaturationTV = (TextView) findViewById(R.id.saturation);
        mValueTV = (TextView) findViewById(R.id.valueBrightness);

        // set the domain (i.e. max) for each component
        mHueSB.setMax(HSVModel.MAX_HUE);
        mSaturationSB.setMax(100);
        mValueSB.setMax(100);


        // register the event handler for each <SeekBar>
        mHueSB.setOnSeekBarChangeListener(this);
        mSaturationSB.setOnSeekBarChangeListener(this);
        mValueSB.setOnSeekBarChangeListener(this);


        //    Color Swatch <TextView> :: onLongClick( )
//    Display the current color's HSV values. Use a Toast message (short) to display the following
//    formatted message: H: <hue>° S: <saturation>% V: <value>%. Where <hue>, <saturation>, and <value>
//    are the HSV values. For example, H: 240° S: 100% V: 100%

        mColorSwatch.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                int hue = mModel.getHue();
                float saturation = mModel.getSaturation();
                saturation = saturation * 100;
                float valueBrightness = mModel.getValueBrightness();
                valueBrightness = valueBrightness * 100;
                String HSV = "Kirby's HSV value is H: " + hue + "\u00B0 S: " + (int) saturation + "% V: " + (int) valueBrightness + "%";
                Toast.makeText(getApplicationContext(), HSV, Toast.LENGTH_SHORT).show();
                return false;
            }

        });
        // initialize the View to the values of the Model
        this.updateView();
    }


    public void changeColorSwatch(View v) {


        switch (v.getId()) {
            case R.id.blackButton:
                mModel.asBlack();
                break;
            case R.id.redButton:
                mModel.asRed();
                break;
            case R.id.limeButton:
                mModel.asLime();
                break;
            case R.id.blueButton:
                mModel.asBlue();
                break;
            case R.id.yellowButton:
                mModel.asYellow();
                break;
            case R.id.cyanButton:
                mModel.asCyan();
                break;
            case R.id.magentaButton:
                mModel.asMagenta();
                break;
            case R.id.silverButton:
                mModel.asSilver();
                break;
            case R.id.grayButton:
                mModel.asGray();
                break;
            case R.id.maroonButton:
                mModel.asMaroon();
                break;
            case R.id.oliveButton:
                mModel.asOlive();
                break;
            case R.id.greenButton:
                mModel.asGreen();
                break;
            case R.id.purpleButton:
                mModel.asPurple();
                break;
            case R.id.tealButton:
                mModel.asTeal();
                break;
            case R.id.navyButton:
                mModel.asNavy();
                break;
        }

        //ge the updated HSV values to display in the toast 
        int hue = mModel.getHue();
        float saturation = mModel.getSaturation();
        saturation = saturation * 100;
        float valueBrightness = mModel.getValueBrightness();
        valueBrightness = valueBrightness * 100;
        String HSV = "H: " + hue + "\u00B0 S: " + (int) saturation + "% V: " + (int) valueBrightness + "%";
        Toast.makeText(getApplicationContext(), HSV, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        // Did the user cause this event?
        // YES > continue
        // NO  > leave this method
        if (fromUser == false) {
            return;
        }

        // Determine which <SeekBark> caused the event (switch + case)
        // GET the SeekBar's progress, and SET the model to it's new value
        switch (seekBar.getId()) {
            case R.id.hueSB:
                mModel.setHue(mHueSB.getProgress());
                String hue = getResources().getString(R.string.hueProgress, progress).toUpperCase() + "\u00B0";
                mHueTV.setText(hue);
                break;

            case R.id.saturationSB:
                float saturation = mSaturationSB.getProgress();
                saturation = saturation / 100;
                mModel.setSaturation(saturation);
                String sat = getResources().getString(R.string.saturationProgress, progress).toUpperCase() + "%";
                mSaturationTV.setText(sat);
                break;

            case R.id.valueSB:
                float valueBrightness = mValueSB.getProgress();
                valueBrightness = valueBrightness / 100;
                mModel.setValueBrightness(valueBrightness);
                String value = getResources().getString(R.string.valueProgress, progress).toUpperCase() + "%";
                mValueTV.setText(value);
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // No-Operation
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()) {
            case R.id.hueSB:
                mHueTV.setText(getResources().getString(R.string.hue));
                break;
            case R.id.saturationSB:
                mSaturationTV.setText(getResources().getString(R.string.sat));
                break;
            case R.id.valueSB:
                mValueTV.setText(getResources().getString(R.string.value));
                break;
        }
    }


    // The Model has changed state!
    // Refresh the View to display the current values of the Model.
    @Override
    public void update(Observable observable, Object data) {
        this.updateView();
    }

    private void updateHueSB() {
        mHueSB.setProgress(mModel.getHue());
    }

    private void updateSaturationSB() {
        float sat = mModel.getSaturation();
        sat = sat * 100;
        mSaturationSB.setProgress((int) sat);
    }

    private void updateValueSB() {
        float valueBrightness = mModel.getValueBrightness();
        valueBrightness = valueBrightness * 100;
        mValueSB.setProgress((int) valueBrightness);
    }

    private void updateColorSwatch() {
        //GET the model's H,S,V values, and SET the background colour of the swatch <TextView>
        float[] hsv = {mModel.getHue(), mModel.getSaturation(), mModel.getValueBrightness()};
        mColorSwatch.setBackgroundColor(Color.HSVToColor(hsv));

    }


    // synchronize each View component with the Model
    public void updateView() {
        this.updateColorSwatch();
        this.updateHueSB();
        this.updateSaturationSB();
        this.updateValueSB();
    }
}
