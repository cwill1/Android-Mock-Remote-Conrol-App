package com.example.cwill190.mockremotecontrolapp;

/**
 * Created by cwill190 on 10/25/2017.
 */
import android.widget.RadioGroup;
import android.app.Activity;
import android.widget.RadioButton;

public class channelSetter extends Activity{
    int channel = 0;

    int leftchannel = 0;
    String left = "";
    String middle = "";
    String right = "";
    RadioGroup rg = null;

    public channelSetter()
    {
        //rg = (RadioGroup)findViewById(R.id.radioSex);
        //String radiovalue = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();
    }

    public void setChannel(int _channel){
        this.channel = _channel;
    }

    public int getChannel(){
        return channel;
    }

    public String getRadioValue(){
       // String radiovalue = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();
        //left = radiovalue;
        return left;
    }

}
