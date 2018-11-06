package com.example.cwill190.mockremotecontrolapp;

/**
 * Created by cwill190 on 10/24/2017.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.support.v7.app.ActionBarActivity;
import android.widget.Switch;
import java.text.DecimalFormat;
import android.widget.RadioGroup.OnCheckedChangeListener;


public class Configure extends AppCompatActivity {
    java.text.DecimalFormat fmt = new java.text.DecimalFormat("000");
    private static final String TAG = "Configure";
    private static int INSTANCE_COUNTER = 0;
    private static final int ASK_QUESTION = 100;
    private int instanceID;
    private int counter = 0;
    Remote remote = new Remote();
    CharSequence selectedRadioButtonText = null;
    RadioGroup someGroup = null;
    TextView channelStat = null;
    int left = 0;
    int middle = 0;
    int right = 0;
    channelSetter configureChan = new channelSetter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //radio




        super.onCreate(savedInstanceState);
        setContentView(R.layout.configurefavoritechannels);

        //instanceID = ++INSTANCE_COUNTER;
        //Log.d(TAG, "onCreate() instanceID=" + instanceID);
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioSex);
        final TextView currentChannelStatus = (TextView)findViewById(R.id.ChannelTextView);
        final TextView currentLabelStatus = (TextView)findViewById(R.id.LabelEditText);
        final Button zero = (Button) findViewById(R.id.ButtonConfigure0);
        final Button one = (Button) findViewById(R.id.ButtonConfigure1);
        final Button two = (Button) findViewById(R.id.ButtonConfigure2);
        final Button three = (Button) findViewById(R.id.ButtonConfigure3);
        final Button four = (Button) findViewById(R.id.ButtonConfigure4);
        final Button five = (Button) findViewById(R.id.ButtonConfigure5);
        final Button six = (Button) findViewById(R.id.ButtonConfigure6);
        final Button seven = (Button) findViewById(R.id.ButtonConfigure7);
        final Button eight = (Button) findViewById(R.id.ButtonConfigure8);
        final Button nine = (Button) findViewById(R.id.ButtonConfigure9);
        final Button channel_up = (Button) findViewById(R.id.ButtonConfigureCHplus);
        final Button channel_down = (Button) findViewById(R.id.ButtonConfigureCHminus);
        someGroup = radioGroup;
        channelStat = currentChannelStatus;
        channelStat.setText("0");

//copied_start

        Button.OnClickListener listener = new View.OnClickListener(){
            public void onClick(View v){

                Button button = (Button)v;

                CharSequence text = button.getText();
                String value = text.toString();

                switch(value){
                    case "0": remote.setChannelConfiguration(0,currentChannelStatus);break;
                    case "1": remote.setChannelConfiguration(1,currentChannelStatus);break;
                    case "2": remote.setChannelConfiguration(2,currentChannelStatus);break;
                    case "3": remote.setChannelConfiguration(3,currentChannelStatus);break;
                    case "4": remote.setChannelConfiguration(4,currentChannelStatus);break;
                    case "5": remote.setChannelConfiguration(5,currentChannelStatus);break;
                    case "6": remote.setChannelConfiguration(6,currentChannelStatus);break;
                    case "7": remote.setChannelConfiguration(7,currentChannelStatus);break;
                    case "8": remote.setChannelConfiguration(8,currentChannelStatus);break;
                    case "9": remote.setChannelConfiguration(9,currentChannelStatus);break;
                    case "CH+":{
                        if(remote.CFCChannel == 999) {
                            //remote.Channel = 0;
                            currentChannelStatus.setText(fmt.format(999));
                            break;
                        }
                        else{
                            currentChannelStatus.setText("");
                            remote.CFCChannel += 1;
                            remote.setChannelConfiguration(remote.getCFCChannel(), currentChannelStatus);
                            break;
                        }
                    }

                    //case "CH+":remote.Channel_UP(currentChannelStatus);
                     case "CH-":{
                        if(remote.CFCChannel <= 0) {
                            //remote.Channel = 0;
                            currentChannelStatus.setText(fmt.format(0));
                            break;
                        }
                        else{
                            currentChannelStatus.setText("");
                            remote.CFCChannel -= 1;
                            remote.setChannel(remote.getCFCChannel(), currentChannelStatus);
                            break;
                        }
                    }
                    default:break;
                }
            }

        };

        //copied_end
        zero.setOnClickListener(listener);
        one.setOnClickListener(listener);
        two.setOnClickListener(listener);
        three.setOnClickListener(listener);
        four.setOnClickListener(listener);
        five.setOnClickListener(listener);
        six.setOnClickListener(listener);
        seven.setOnClickListener(listener);
        eight.setOnClickListener(listener);
        nine.setOnClickListener(listener);
        channel_up.setOnClickListener(listener);
        channel_down.setOnClickListener(listener);

//copied
        //RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioSex);
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                int selectedRadioButtonID = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = (RadioButton) findViewById(selectedRadioButtonID);
                selectedRadioButtonText = selectedRadioButton.getText();
                Log.d(TAG, selectedRadioButtonText.toString());
            }
        });

        //copied_end
        Button save = (Button) findViewById(R.id.ButtonConfiguresave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedRadioButtonText != null && channelStat.getText() != null) {

                    EditText answer = (EditText) findViewById(R.id.LabelEditText);

                    Intent data = new Intent(Configure.this, MainActivity.class);

                    data.putExtra("Answer", answer.getText());
                    data.putExtra("RadioB", selectedRadioButtonText);
                    data.putExtra("CFCChannel", channelStat.getText());

                    //data.putExtra("RadioSelection", idx);

                    setResult(RESULT_OK, data);
                    finish();
                }
                else{
                    Intent intent = new Intent(Configure.this, MainActivity.class);
                    setResult(RESULT_CANCELED, intent);
                    finish();
                }
                //startActivity(data);
            }
        });


        Button cancel = (Button) findViewById(R.id.ButtonConfigurecancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setResult(RESULT_CANCELED);
               // finish();
                //Intent intent = new Intent(Configure.this, MainActivity.class);
                //startActivity(intent);

                Intent intent = new Intent(Configure.this, MainActivity.class);
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });




    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioLeft:
                if (checked)
                    Log.d(TAG, "left");
                    break;
            case R.id.radioMiddle:
                if (checked)
                    Log.d(TAG, "middle");
                    break;
            case R.id.radioRight:
                if (checked)
                    Log.d(TAG, "right");
                    break;
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        //The activity is about to become visible.
        Log.d(TAG, "onStart() counter=" + ++counter);
        //TextView title = (TextView) findViewById(R.id.title);
        //title.setText("Activity B [" + instanceID + "-" + counter + "]");

    }

    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
        Log.d(TAG, "onDestory()");
    }
    public CharSequence getChannelNumber(){
        CharSequence num = "0";
        if(channelStat.getText() != null){
            return channelStat.getText();
        }
        else return num;
    }
}
