package com.example.cwill190.mockremotecontrolapp;

import android.widget.RelativeLayout;
import android.widget.TextView;
import android.graphics.Color;
import java.text.DecimalFormat;
import android.util.Log;
/**
 * Created by cwill190 on 10/10/2017.
 */

import android.widget.SeekBar;
public class Remote {

    TextView Power;
    Integer Channel = 0;

    Integer Volume = 50;
    String mode = "On";
    SeekBar volume = null;
    TextView channelStatus = null;
    TextView speakerVolume = null;
    Boolean TVon = true;
    RelativeLayout main = null;
    TextView channel = null;
    String ch = null;
    DecimalFormat fmt = new DecimalFormat("000");


    //Configure Channel
    Integer CFCChannel = 0;
    Integer ConfigureLeftChannel = 0;
    Integer ConfigureMiddleChannel = 0;
    Integer ConfigureRightChannel = 0;

    String Configurech = null;



    public Remote(){}

    public Integer getChannel(){
        return Channel;

    }

    public Integer getCFCChannel(){
        return CFCChannel;

    }


    public void turnOffSetUp(SeekBar volume_, TextView channelStatus_, TextView speakerVolume_, RelativeLayout main_){
        this.volume = volume_;
        this.channelStatus = channelStatus_;
        this.speakerVolume = speakerVolume_;
        this.main = main_;

    }
    public void turnOff(){
        volume.setEnabled(false);
        channelStatus.setText("");
        channelStatus.setEnabled(false);
        speakerVolume.setText("");
        main.setBackgroundColor(Color.parseColor("#d3d3d3"));
        TVon = false;
    }
    public void turnOn(){
        volume.setEnabled(true);
        channelStatus.setEnabled(true);
        TVon = true;
        main.setBackgroundColor(Color.parseColor("#e303fd13"));
    }




    public Integer setChannel(TextView channel_){
        this.channel = channel_;
        String ch = (String)channel.getText();
        Channel = Integer.parseInt(ch);
        return Channel;
    }
    public void Channel_UP(TextView channel){
        if(TVon){
            channel.setText(fmt.format(Integer.toString(Channel)));

        }
    }
    public void setChannelConfiguration(Integer Channel, TextView channel){
        this.CFCChannel = Channel;

        if(TVon) {

            if (channel.getText().length() == 0 || channel.getText().length() >= 3) {
                Integer chan = Channel;

                channel.setText(Integer.toString(Channel));

            }

            else {
                if (channel.getText().length() != 3) {
                    channel.append(Integer.toString(Channel));
                    Configurech = channel.getText().toString();
                    this.CFCChannel = Integer.parseInt(Configurech);
                }
                else{
                    Configurech = channel.getText().toString();
                    this.CFCChannel = Integer.parseInt(Configurech);
                }
            }
        }
        else{
            channel.setText("");
        }
    }




    public void setChannel(Integer Channel, TextView channel){
        this.Channel = Channel;

            if(TVon) {

                if (channel.getText().length() == 0 || channel.getText().length() >= 3) {
                    Integer chan = Channel;

                    channel.setText(Integer.toString(Channel));

                }

                else {
                    if (channel.getText().length() != 3) {
                        channel.append(Integer.toString(Channel));
                        ch = channel.getText().toString();
                        this.Channel = Integer.parseInt(ch);
                    }
                    else{
                        ch = channel.getText().toString();
                        this.Channel = Integer.parseInt(ch);
                        Log.d("MainActivity",ch);
                    }
                }
            }
            else{
                channel.setText("");
            }
    }

    public Integer getVolume(){
        return Volume;
    }

    public void setVolume(Integer Volume){
        this.Volume = Volume;
    }

    public void setVolume(Integer Volume, TextView displayedVolume){
        this.Volume = Volume;
        displayedVolume.setText(Integer.toString(Volume));
    }

    public String getPowerStatus(){
        return mode;
    }

    public void setPowerStatus(TextView Power, String mode){
        this.Power = Power;
        this.mode = mode;
        Power.setText(mode);
    }

    public void setPowerStatus(String mode){
        this.mode = mode;
        Power.setText(mode);
    }
}
