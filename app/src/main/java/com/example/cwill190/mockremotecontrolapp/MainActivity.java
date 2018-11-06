package com.example.cwill190.mockremotecontrolapp;

import android.widget.CompoundButton;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.Button;
import android.view.View;
import android.graphics.Color;
import java.text.DecimalFormat;
import android.content.Intent;
import android.app.Activity;

public class MainActivity extends Activity  {
    //controls text




    Button _ABC = null;
    Button _NBC = null;
    Button _CBS = null;
    TextView _currentChannelStatus = null;

    //
    int ABC = 0;
    int NBC = 0;
    int CBS = 0;


    private static final int ASK_QUESTION = 100;

    Remote remote = new Remote();
    static private final String TAG = "MainActivity";
    private static int INSTANCE_COUNTER = 0;
    private int instanceID;
    private int counter = 0;

    Integer currentChannel_ = 0;
    java.text.DecimalFormat fmt = new java.text.DecimalFormat("000");
    String numbersSaved = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout main = (RelativeLayout) findViewById(R.id.relativeLayout);
        Switch power = (Switch) findViewById(R.id.switch2);

        power.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(remote.getPowerStatus() == "On"){
                    remote.setPowerStatus("Off");
                    remote.turnOff();
                }
                else{
                    remote.setPowerStatus("On");
                    remote.turnOn();
                }
            }
        });


        SeekBar volume = (SeekBar) findViewById(R.id.seekbar);

        Button switchToDVR = (Button)findViewById(R.id.SwitchToDVR);
        switchToDVR.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(remote.getPowerStatus()=="On") {
                    Intent intent = new Intent(MainActivity.this, MyDVR.class);
                    startActivity(intent);
                }

            }
        });

        Button switchToConfigure = (Button)findViewById(R.id.Configure);
        switchToConfigure.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(remote.getPowerStatus()=="On") {
                    Intent intent = new Intent(MainActivity.this, Configure.class);
                    Button configure = findViewById(R.id.Configure);

                    intent.putExtra("Question", configure.getText());

                    startActivityForResult(intent, ASK_QUESTION);

                }

            }
        });

        final TextView volumeStatus = (TextView) findViewById(R.id.SpeakerVolumeStatus);
        final TextView currentChannelStatus = (TextView) findViewById(R.id.Current_ChannelStatus);_currentChannelStatus = currentChannelStatus;
        final Button zero = (Button) findViewById(R.id.Button0);
        final Button one = (Button) findViewById(R.id.Button1);
        final Button two = (Button) findViewById(R.id.Button2);
        final Button three = (Button) findViewById(R.id.Button3);
        final Button four = (Button) findViewById(R.id.Button4);
        final Button five = (Button) findViewById(R.id.Button5);
        final Button six = (Button) findViewById(R.id.Button6);
        final Button seven = (Button) findViewById(R.id.Button7);
        final Button eight = (Button) findViewById(R.id.Button8);
        final Button nine = (Button) findViewById(R.id.Button9);
        final Button channel_up = (Button) findViewById(R.id.Channel_UP);
        final Button channel_down = (Button) findViewById(R.id.Channel_DOWN);

        final Button ABC = (Button) findViewById(R.id.ABC);_ABC = ABC;
        final Button NBC = (Button) findViewById(R.id.NBC);_NBC = NBC;
        final Button CBS = (Button) findViewById(R.id.CBS);_CBS = CBS;

        //setup
        remote.turnOffSetUp(volume,currentChannelStatus,volumeStatus, main);


        Button.OnClickListener listener = new View.OnClickListener(){
            public void onClick(View v){

                Button button = (Button)v;

                CharSequence text = button.getText();
                String value = text.toString();


                switch(button.getId()){
                    case R.id.Button0: remote.setChannel(0,currentChannelStatus);break;
                    case R.id.Button1: remote.setChannel(1,currentChannelStatus);break;
                    case R.id.Button2: remote.setChannel(2,currentChannelStatus);break;
                    case R.id.Button3: remote.setChannel(3,currentChannelStatus);break;
                    case R.id.Button4: remote.setChannel(4,currentChannelStatus);break;
                    case R.id.Button5: remote.setChannel(5,currentChannelStatus);break;
                    case R.id.Button6: remote.setChannel(6,currentChannelStatus);break;
                    case R.id.Button7: remote.setChannel(7,currentChannelStatus);break;
                    case R.id.Button8: remote.setChannel(8,currentChannelStatus);break;
                    case R.id.Button9: remote.setChannel(9,currentChannelStatus);break;
                    case R.id.ABC:currentChannelStatus.setText("");remote.setChannel(getABC(),currentChannelStatus);break;
                    case R.id.NBC:currentChannelStatus.setText("");remote.setChannel(getNBC(),currentChannelStatus);break;
                    case R.id.CBS:currentChannelStatus.setText("");remote.setChannel(getCBS(),currentChannelStatus);break;

                    case R.id.Channel_UP:{
                        if(remote.Channel == 999) {
                            //remote.Channel = 0;
                            currentChannelStatus.setText(fmt.format(999));
                            break;
                        }
                        else{
                            currentChannelStatus.setText("");
                            remote.Channel += 1;
                            remote.setChannel(remote.getChannel(), currentChannelStatus);
                            break;
                        }
                    }
                    //case "CH+":remote.Channel_UP(currentChannelStatus);
                    case R.id.Channel_DOWN:{
                        if(remote.Channel <= 0) {
                            //remote.Channel = 0;
                            currentChannelStatus.setText(fmt.format(0));
                            break;
                        }
                        else{
                            currentChannelStatus.setText("");
                            remote.Channel -= 1;
                            remote.setChannel(remote.getChannel(), currentChannelStatus);
                            break;
                        }
                    }
                    default:

                        break;
                }
            }

        };


        volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b){
                Log.d(TAG,"onProgressedChanged");
                remote.setVolume(i, volumeStatus);


            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStopTrackingTouch");
            }
        });


        final TextView powerStatus = (TextView) findViewById(R.id.TV_PowerStatus);
        remote.setPowerStatus(powerStatus,"On");

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
        ABC.setOnClickListener(listener);
        NBC.setOnClickListener(listener);
        CBS.setOnClickListener(listener);
        channel_up.setOnClickListener(listener);
        channel_down.setOnClickListener(listener);

        power.setOnClickListener(listener);
    }
/*
    @Override
    protected void onStart() {
        super.onStart();
        //The activity is about to become visible.
        Log.d(TAG, "onStart() counter=" + ++counter);
        Intent intent = getIntent();

    }

    */
    public void setSavedChannel(Integer num, String location){
        switch(location){
            case "left": ABC = num; break;
            case "middle": NBC = num; break;
            case "right":CBS = num; break;
        }

    }
    public int getABC(){
        return ABC;
    }

    public int getCBS(){
        return CBS;
    }
    public int getNBC(){
        return NBC;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ASK_QUESTION) {
            if (resultCode == RESULT_OK) {
                CharSequence name =  data.getCharSequenceExtra("Answer");
                CharSequence result = data.getCharSequenceExtra("RadioB");
                CharSequence CFCResult = data.getCharSequenceExtra("CFCChannel");

                Log.d(TAG,result.toString());
               //if(result.equals("left")){
                    //_ABC.setText(name);
               //}
                Integer savedNum = Integer.parseInt(CFCResult.toString());
               switch(result.toString()){
                   case "left":_ABC.setText(name);setSavedChannel(savedNum,"left");break;
                   case "middle":_NBC.setText(name);setSavedChannel(savedNum,"middle");break;
                   case "right":_CBS.setText(name);setSavedChannel(savedNum,"right");break;
               }

                Toast.makeText(this, "Favorite Channel Successfully Changed", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Configurations Canceled", Toast.LENGTH_LONG).show();
            }
        }
    }
}
