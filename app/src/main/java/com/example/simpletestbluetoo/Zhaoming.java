package com.example.simpletestbluetoo;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by chenjunpeng on 2018/12/20.
 */

public class Zhaoming extends Thefirst {
    String str1="打开白灯";
    String str2="关闭白灯";
    String str3="打开黄灯";
    String str4="关闭黄灯";
    String str5="打开绿灯";
    String str6="关闭绿灯";
    String str7="打开蓝灯";
    String str8="关闭蓝灯";
    String str9="打开红灯";
    String str10="关闭红灯";
    TextView mTextViewState2_1;
    TextView mTextViewReceive01;
    TextView description;
    TextView description1;
    TextView description2;
    TextView description3;
    TextView description4;
    Button mButton01;
    Button mButton02;
    Button mButton03;
    Button mButton04;
    Button mButton05;
    ScrollView mScrollView31;
    SeekBar seekbar1;
    SeekBar seekbar2;
    SeekBar seekbar3;
    SeekBar seekbar4;
    SeekBar seekbar5;



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhao_ming);

        mButton01 = (Button)findViewById(R.id.button2_01);
        mButton02 = (Button)findViewById(R.id.button2_02);
        mButton03 = (Button)findViewById(R.id.button2_03);
        mButton04 = (Button)findViewById(R.id.button2_04);
        mButton05 = (Button)findViewById(R.id.button2_05);
        seekbar1 = (SeekBar)findViewById(R.id.seekBar2);
        seekbar2 = (SeekBar)findViewById(R.id.seekBar3);
        seekbar3 = (SeekBar)findViewById(R.id.seekBar4);
        seekbar4 = (SeekBar)findViewById(R.id.seekBar5);
        seekbar5 = (SeekBar)findViewById(R.id.seekBar6);

        mButton01.setOnClickListener(this);
        mButton02.setOnClickListener(this);
        mButton03.setOnClickListener(this);
        mButton04.setOnClickListener(this);
        mButton05.setOnClickListener(this);

        mButton01.setText(str1);
        mButton01.setTag(false);
        mButton02.setText(str3);
        mButton02.setTag(false);
        mButton03.setText(str5);
        mButton03.setTag(false);
        mButton04.setText(str7);
        mButton04.setTag(false);
        mButton05.setText(str9);
        mButton05.setTag(false);

        List<String> devices = new ArrayList<String>();
        Set<BluetoothDevice> bondedDevices = mBluetoothAdapter.getBondedDevices();
        for (BluetoothDevice device : bondedDevices) {
            mConnectThread = new ConnectThread(device);
            mConnectThread.start();
            break;
        }

        List<String> devices1 = new ArrayList<String>();
        Set<BluetoothDevice> bondedDevices1 = mBluetoothAdapter.getBondedDevices();
        for (BluetoothDevice device : bondedDevices1) {
            mConnectThread = new ConnectThread(device);
            mConnectThread.start();
            break;
        }

        mTextViewState2_1=(TextView)findViewById(R.id.textview_2_2);
        mTextViewReceive01 = (TextView)findViewById(R.id.textview_receive2);

        mScrollView31 = (ScrollView)findViewById(R.id.scrollview2);

        description=(TextView)findViewById(R.id.description);
        description1=(TextView)findViewById(R.id.description1);
        description2=(TextView)findViewById(R.id.description2);
        description3=(TextView)findViewById(R.id.description3);
        description4=(TextView)findViewById(R.id.description4);
        seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /**
             * 拖动条停止拖动的时候调用
             */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                description.setText("蓝灯亮度");
            }
            /**
            * 拖动条开始拖动的时候调用
             * */
             @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                description.setText("开始拖动");
            }
            /**
             * 拖动条进度改变的时候调用
             */
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                description.setText("当前亮度："+progress+"%");
                sendMessage(String.valueOf(progress)+'\n');
             }
            });
        seekbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                description1.setText("绿灯亮度");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                description1.setText("开始拖动");
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                description1.setText("当前亮度："+progress+"%");
                sendMessage(String.valueOf(progress+200)+'\n');
            }
        });
        seekbar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                description2.setText("黄灯亮度");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                description2.setText("开始拖动");
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                description2.setText("当前亮度："+progress+"%");
                sendMessage(String.valueOf(progress+400)+'\n');
            }
        });
        seekbar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                description3.setText("白灯亮度");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                description3.setText("开始拖动");
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                description3.setText("当前亮度："+progress+"%");
                sendMessage(String.valueOf(progress+600)+'\n');
            }
        });
        seekbar5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                description4.setText("红灯亮度");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                description4.setText("开始拖动");
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                description4.setText("当前亮度："+progress+"%");
                sendMessage(String.valueOf(progress+800)+'\n');
            }
        });
    }
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.button2_01: {
                boolean flag1 = (boolean) mButton01.getTag();
                if(!flag1){
                    mButton01.setText(str2);
                    mButton01.setTag(true);
                    sendMessage("white1\n"); break;
                }else{
                    mButton01.setText(str1);
                    mButton01.setTag(false);
                    sendMessage("white-1\n"); break;
                }
            }
            case R.id.button2_02: {
                boolean flag2 = (boolean) mButton02.getTag();
                if(!flag2){
                    mButton02.setText(str4);
                    mButton02.setTag(true);
                    sendMessage("yellow1\n"); break;
                }else{
                    mButton02.setText(str3);
                    mButton02.setTag(false);
                    sendMessage("yellow-1\n"); break;
                }
            }
            case R.id.button2_03: {
                boolean flag3 = (boolean) mButton03.getTag();
                if(!flag3){
                    mButton03.setText(str6);
                    mButton03.setTag(true);
                    sendMessage("green1\n"); break;
                }else{
                    mButton03.setText(str5);
                    mButton03.setTag(false);
                    sendMessage("green-1\n"); break;
                }
            }
            case R.id.button2_04: {
                boolean flag4 = (boolean) mButton04.getTag();
                if(!flag4){
                    mButton04.setText(str8);
                    mButton04.setTag(true);
                    sendMessage("blue1\n"); break;
                }else{
                    mButton04.setText(str7);
                    mButton04.setTag(false);
                    sendMessage("blue-1\n"); break;
                }
            }
            case R.id.button2_05: {
                boolean flag5 = (boolean) mButton05.getTag();
                if(!flag5){
                    mButton05.setText(str10);
                    mButton05.setTag(true);
                    sendMessage("red1\n"); break;
                }else{
                    mButton05.setText(str9);
                    mButton05.setTag(false);
                    sendMessage("red-1\n"); break;
                }
            }
        }
    }

}
