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

public class Thefirst extends AppCompatActivity implements View.OnClickListener {
    public static final int RECV_VIEW = 0;
    public static final int NOTICE_VIEW = 1;
    protected BlueToothPopwindow mBlueToothPopwindow = null;
    Button mButton001;
    Button mButton002;
    TextView mTextViewState1;
    TextView mTextViewReceive1;
    Context mContext = this;
    Activity mActivity = this;
    ScrollView mScrollView1;
    Button mButtonClear1;
    Button mButtonBlueTooth1;


    protected BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    protected ConnectThread mConnectThread = null;
    protected ConnectedThread mConnectedThread = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.the_first);

        mButton001 = (Button)findViewById(R.id.button1_01);
        mButton002 = (Button)findViewById(R.id.button1_02);
        mButtonClear1 = (Button)findViewById(R.id.button_clear1);
        mButtonClear1.setOnClickListener(this);
        mButtonBlueTooth1 = (Button)findViewById(R.id.button_bluetooth1);
        mButtonBlueTooth1.setOnClickListener(this);
        mTextViewState1 = (TextView)findViewById(R.id.textview_state1);
        mTextViewReceive1 = (TextView)findViewById(R.id.textview_receive1);
        mScrollView1 = (ScrollView)findViewById(R.id.scrollview1);

        mButton001.setOnClickListener(this);
        mButton002.setOnClickListener(this);

        if (mBluetoothAdapter == null) {
            return;
        }

        if (!mBluetoothAdapter.isEnabled()) {
            mTextViewState1.setText("蓝牙未开启");
        }
        else {
            mTextViewState1.setText("蓝牙已开启");
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_clear1: {
                mTextViewReceive1.setText("");}
                break;

            case R.id.button_bluetooth1: {
                if (mBlueToothPopwindow != null && mBlueToothPopwindow.isShowing()) {
                    mBlueToothPopwindow.dismiss();
                } else {
                    mBlueToothPopwindow = new BlueToothPopwindow(mActivity, itemsOnClick);
                    mBlueToothPopwindow.showAsDropDown(view, 0, 5);
                }}
                break;

            case R.id.button1_01: {
                Intent intent1=new Intent(Thefirst.this,Zhaoming.class);
                startActivity(intent1);}
                break;

            case R.id.button1_02: {
                Intent intent2=new Intent(Thefirst.this,Texiao.class);
                startActivity(intent2);}
                break;

            case R.id.button3_01:
                sendMessage("texiao1\n");break;

            case R.id.button3_02:
                sendMessage("texiao2\n");break;

            case R.id.button3_03:
                sendMessage("texiao3\n");break;

            case R.id.button3_04:
                sendMessage("texiao4\n");break;

            case R.id.button3_05:
                sendMessage("texiao5\n");break;

            case R.id.button3_06:
                sendMessage("texiao6\n");break;

            case R.id.button3_00:
                sendMessage("texiao0\n");break;



        }
    }

    public void sendMessage(String chr) {

                /* send message*/
        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            Toast.makeText(this, "蓝牙未开启", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mConnectedThread == null) {
            Toast.makeText(this, "未连接设备", Toast.LENGTH_SHORT).show();
            return;
        }
        mConnectedThread.write(chr.getBytes());
        //Toast.makeText(this, "send" + chr + "<<end", Toast.LENGTH_SHORT).show();
        return;
    }

    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        public void onClick (View v) {
            switch (v.getId()) {
                case R.id.button_showdevices:
//                    Toast.makeText(mContext, "show", Toast.LENGTH_SHORT).show();
                    if (mBluetoothAdapter != null) {
                        if (!mBluetoothAdapter.isEnabled()) {
                            Toast.makeText(mContext, "蓝牙未开启", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        // 查询配对设备
                        List<String> devices = new ArrayList<String>();
                        Set<BluetoothDevice> bondedDevices = mBluetoothAdapter.getBondedDevices();
                        for (BluetoothDevice device : bondedDevices) {
                            devices.add(device.getName() + "-" + device.getAddress());
                        }
                        StringBuilder text = new StringBuilder();
                        for (String device : devices) {
                            text.append(device + "\n");
                        }
                        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.button_startbluetooth:
//                    Toast.makeText(mContext, "start", Toast.LENGTH_SHORT).show();
                    if (mBluetoothAdapter != null) {
                        //开启蓝牙
                        int REQUEST_ENABLE_BT = 1;
                        if (!mBluetoothAdapter.isEnabled()) {
                            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                            startActivityForResult(intent, REQUEST_ENABLE_BT);
                            mTextViewState1.setText("开启蓝牙成功");
                        } else {
                            Toast.makeText(mContext, "蓝牙已开启", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case R.id.button_connectdevices:
//                    Toast.makeText(mContext, "connect", Toast.LENGTH_SHORT).show();
                    if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
                        Toast.makeText(mContext, "蓝牙未开启", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    // 查询配对设备 建立连接，只能连接第一个配对的设备
                    List<String> devices = new ArrayList<String>();
                    Set<BluetoothDevice> bondedDevices = mBluetoothAdapter.getBondedDevices();
                    for (BluetoothDevice device : bondedDevices) {
                        mConnectThread = new ConnectThread(device);
                        mConnectThread.start();
                        break;
                    }
                    break;
            }
        }
    };

    protected class ConnectThread extends Thread {
        private final String MY_UUID = "00001101-0000-1000-8000-00805F9B34FB";
        private final BluetoothSocket socket;
        private final BluetoothDevice device;
        public ConnectThread(BluetoothDevice device) {
            this.device = device;
            BluetoothSocket tmp = null;
            try {
                tmp = device.createRfcommSocketToServiceRecord(UUID.fromString(MY_UUID));
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.socket = tmp;
        }
        public void run() {
            mBluetoothAdapter.cancelDiscovery();
            try {
                socket.connect();
                mConnectedThread = new Thefirst.ConnectedThread(socket);
                mConnectedThread.start();
            } catch (IOException e) {
                try {
                    socket.close();
                } catch (IOException ee) {
                    ee.printStackTrace();
                }
                return;
            }
//manageConnectedSocket(socket);
        }
        public void cancel() {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // 客户端与服务器建立连接成功后，用ConnectedThread收发数据
    protected class ConnectedThread extends Thread {
        private BluetoothSocket socket;
        private final InputStream inputStream;
        private final OutputStream outputStream;
        public ConnectedThread(BluetoothSocket socket) {
            this.socket = socket;
            InputStream input = null;
            OutputStream output = null;
            try {
                input = socket.getInputStream();
                output = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.inputStream = input;
            this.outputStream = output;
        }
        public void run() {
            StringBuilder recvText = new StringBuilder();
            byte[] buff = new byte[1024];
            int bytes;
            Bundle tmpBundle = new Bundle();
            Message tmpMessage = new Message();
            tmpBundle.putString("notice", "连接成功");
            tmpMessage.what = NOTICE_VIEW;
            tmpMessage.setData(tmpBundle);
            handler.sendMessage(tmpMessage);
            while (true) {
                try {
                    bytes = inputStream.read(buff);
                    String str = new String(buff, "ISO-8859-1");
                    str = str.substring(0, bytes);
// 收到数据，单片机发送上来的数据以"#"结束，这样手机知道一条数据发送结束
//Log.e("read", str);
                    if (!str.endsWith("#")) {
                        recvText.append(str);
                        continue;
                    }
                    recvText.append(str.substring(0, str.length() - 1)); // 去除'#'
                    Bundle bundle = new Bundle();
                    Message message = new Message();
                    bundle.putString("recv", recvText.toString());
                    message.what = RECV_VIEW;
                    message.setData(bundle);
                    handler.sendMessage(message);
                    recvText.replace(0, recvText.length(), "");
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
        public void write(byte[] bytes) {
            try {
                outputStream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void cancel() {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected android.os.Handler handler = new android.os.Handler() {
        public void handleMessage(Message msg) {
            Bundle bundle = null;
            switch (msg.what) {
                case RECV_VIEW:

                    bundle = msg.getData();
                    String recv = bundle.getString("recv");
                    mTextViewReceive1.append(recv + "\n");
                    mScrollView1.fullScroll(ScrollView.FOCUS_DOWN); // 滚动到底部
//                    if (recv.isEmpty() || recv.contains(" ") || recv.contains("#")) {
//                        break;
//                    }

                    break;
                case NOTICE_VIEW:
                    bundle = msg.getData();
                    String notice = bundle.getString("notice");
                    mTextViewState1.setText(notice);
                    break;
                default:
                    break;
            }
        }
    };

}
