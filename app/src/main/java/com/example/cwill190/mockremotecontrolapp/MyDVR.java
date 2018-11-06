package com.example.cwill190.mockremotecontrolapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by cwill190 on 10/23/2017.
 */

public class MyDVR extends Activity{
    private static final String TAG = "MyDVR";
    private static int INSTANCE_COUNTER = 0;

    private int instanceID;
    private int counter = 0;
    String mode = "stopped";
    Boolean PowerStatus = true;
    RelativeLayout main = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dvr);

        //instanceID = ++INSTANCE_COUNTER;
        //Log.d(TAG, "onCreate() instanceID=" + instanceID);
        final TextView textViewPowerStatus = (TextView) findViewById(R.id.DVRPowerState);
        final TextView actionState = (TextView) findViewById(R.id.DVRTextViewState);

        final Button play = (Button) findViewById(R.id.ButtonPlay);
        final Button stop= (Button) findViewById(R.id.ButtonStop);
        final Button pause = (Button) findViewById(R.id.ButtonPause);
        final Button fastForward = (Button) findViewById(R.id.ButtonFastForward);
        final Button fastRewind = (Button) findViewById(R.id.ButtonFastRewind);
        final Button record = (Button) findViewById(R.id.ButtonRecord);

        final RelativeLayout main = (RelativeLayout) findViewById(R.id.relativeLayoutDVR);
        turnOnDVR(textViewPowerStatus, actionState, main);

        //copied_start
        Switch power = (Switch) findViewById(R.id.switch3);
        power.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(PowerStatus == true){
                    turnOffDVR(textViewPowerStatus, actionState, main);

                }
                else{
                    turnOnDVR(textViewPowerStatus, actionState, main);
                }


            }
        });
        //copied_end

        Button SwitchToTV = (Button) findViewById(R.id.ButtonSwitchToTV);
        SwitchToTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(PowerStatus){
                    Intent intent = new Intent(MyDVR.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });


        Button.OnClickListener listener = new View.OnClickListener(){
            public void onClick(View v){

                Button button = (Button)v;

                CharSequence text = button.getText();
                String value = text.toString();
               Log.d("MyDVR",value);
                switch(value){
                    case "Play": play(actionState);break;
                    case "Stop": stop(actionState);break;
                    case "Pause":pause(actionState); break;
                    case "Fast Forward":fastForward(actionState); break;
                    case "Fast Rewind": fastRewind(actionState);break;
                    case "Record":record(actionState); break;
                    default:break;
                }
            }

        };




        play.setOnClickListener(listener);
        stop.setOnClickListener(listener);
        pause.setOnClickListener(listener);
        fastForward.setOnClickListener(listener);
        fastRewind.setOnClickListener(listener);
        record.setOnClickListener(listener);
        power.setOnClickListener(listener);
    }
/*
    @Override
    protected void onStart() {
        super.onStart();
        //The activity is about to become visible.
        Log.d(TAG, "onStart() counter=" + ++counter);
        //TextView title = (TextView) findViewById(R.id.title);
        //title.setText("Activity B [" + instanceID + "-" + counter + "]");
        mode = "Playing";

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
*/

    public void play(TextView actionState){
        if(PowerStatus == true) {
            mode = "Playing";
            actionState.setText("Playing");
        }
    }
    public void stop(TextView actionState){
        if(PowerStatus == true) {
            mode = "Stop";
            actionState.setText("Stopped");
        }
    }

    public void pause(TextView actionState){
        if(mode == "Playing"&& PowerStatus == true){
            actionState.setText("Paused");
            mode = "Paused";
        }
    }
    public void fastForward(TextView actionState){
        if(mode == "Playing" && PowerStatus == true){
            actionState.setText("Fast Forwarding..");
            mode = "Fast Forward";
        }
    }
    public void fastRewind(TextView actionState){
        if(mode == "Playing" && PowerStatus == true){
            actionState.setText("Fast Rewinding..");
            mode = "Paused";
        }
    }

    public void record(TextView actionState){
        if(mode == "Stop" && PowerStatus == true){
            actionState.setText("Recording..");
            mode = "Record";
        }
    }

    public boolean getPowerStatus(){
        return PowerStatus;
    }
    public void turnOnDVR(TextView powerStatus, TextView ActionState, RelativeLayout main){
        powerStatus.setText("On");
        ActionState.setText("Stopped");
        PowerStatus = true;
        main.setBackgroundColor(Color.parseColor("#e3fd6005"));
    }

    public void turnOffDVR(TextView powerStatus, TextView ActionState, RelativeLayout main){
        powerStatus.setText("Off");
        ActionState.setText("");
        PowerStatus = false;
        main.setBackgroundColor(Color.parseColor("#d3d3d3"));
    }


}
