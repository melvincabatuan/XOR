package io.github.melvincabatuan.xor;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends Activity {

    private boolean switch1 = false;
    private boolean switch2 = false;
    private int outputDisplay = 0;
    private TextView tv;

    static {
        try {
            System.loadLibrary("NeuralNet");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // User interface
        tv = (TextView) findViewById(R.id.textViewOut);
        final Switch s1 = (Switch) findViewById(R.id.switch1);

        //set the switch to OFF
        s1.setChecked(false);
        //attach a listener to check for changes in state
        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    switch1 = true;
                } else {
                    switch1 = false;
                }

                updateDisplay();

            }
        });


        final Switch s2 = (Switch) findViewById(R.id.switch2);
        //set the switch to OFF
        s2.setChecked(false);
        //attach a listener to check for changes in state
        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    switch2 = true;
                } else {
                    switch2 = false;
                }

                updateDisplay();

            }
        });

        updateDisplay();
    }


    public native int xor(boolean input1, boolean input2);

    private void updateDisplay(){
        /// Native call of XOR
        outputDisplay = xor(switch1, switch2);
        Log.d("updateDisplay()", "" + outputDisplay);

        if (outputDisplay == 1) {
            tv.setText("TRUE");
        } else {
            tv.setText("FALSE");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
