package com.example.simpletestbluetoo;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

/**
 * Created by Acer on 2017/3/25.
 */

public class BlueToothPopwindow extends PopupWindow {
    private View mView;
    private Activity mContext;

    private Button mButtonShowDevices;
    private Button mButtonStartBluetooth;
    private Button mButtonConnetctDevice;

    public BlueToothPopwindow(Activity context, View.OnClickListener itemsOnclick) {
        super(context);
        mContext = context;
        initView(mContext, itemsOnclick);
    }

    private void initView(final Activity context, View.OnClickListener itemsOnclick) {
        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = mInflater.inflate(R.layout.bluetooth_popwindow, null);

        Log.d("init", "init");
        mButtonShowDevices = (Button)mView.findViewById(R.id.button_showdevices);
        mButtonShowDevices.setOnClickListener(itemsOnclick);
        mButtonStartBluetooth = (Button)mView.findViewById(R.id.button_startbluetooth);
        mButtonStartBluetooth.setOnClickListener(itemsOnclick);
        mButtonConnetctDevice = (Button)mView.findViewById(R.id.button_connectdevices);
        mButtonConnetctDevice.setOnClickListener(itemsOnclick);

        this.setContentView(mView);
        this.setHeight(ActionBar.LayoutParams.WRAP_CONTENT);
        this.setWidth(ActionBar.LayoutParams.WRAP_CONTENT);

        this.setFocusable(true);
        this.setTouchable(true);

        ColorDrawable dw = new ColorDrawable(0x000000);
        this.setBackgroundDrawable(dw);
    }
}
