package com.example.simpletestbluetoo;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
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

public class Texiao extends Thefirst {
    TextView mTextViewState3_1;
    TextView mTextViewReceive01;
    Button mButton1;
    Button mButton2;
    Button mButton3;
    Button mButton4;
    Button mButton5;
    Button mButton6;
    Button mButton0;
    ScrollView mScrollView21;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.te_xiao);

        mButton1 = (Button) findViewById(R.id.button3_01);
        mButton2 = (Button) findViewById(R.id.button3_02);
        mButton3 = (Button) findViewById(R.id.button3_03);
        mButton4 = (Button) findViewById(R.id.button3_04);
        mButton5 = (Button) findViewById(R.id.button3_05);
        mButton6 = (Button) findViewById(R.id.button3_06);
        mButton0 = (Button) findViewById(R.id.button3_00);

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mButton5.setOnClickListener(this);
        mButton6.setOnClickListener(this);
        mButton0.setOnClickListener(this);

        mTextViewState3_1 = (TextView) findViewById(R.id.textview_3_2);
        mTextViewReceive01 = (TextView) findViewById(R.id.textview_receive3);

        mScrollView21 = (ScrollView) findViewById(R.id.scrollview3);

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

    }




public void onClick(View view) {
    super.onClick(view);
}





    // 客户端与服务器建立连接成功后，用ConnectedThread收发数据




}
