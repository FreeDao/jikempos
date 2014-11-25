package com.jike.pos;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import com.buybal.framework.utils.BluthPosManager;
import com.centerm.mpos.bluetooth.BluetoothController;
import com.centerm.mpos.bluetooth.BluetoothController.BluetoothSearchListener;
import com.centerm.mpos.bluetooth.BluetoothController.BluetoothStateListener;
import com.centerm.mpos.bluetooth.MposDevice;
import com.example.jike.R;
import com.jike.aty.BaseActivity;

public class AtyDeviceList extends BaseActivity implements
		android.view.View.OnClickListener {

	private ListView devicesList;

	private Button search;
	private BluthPosManager bluthManager;
	private ProgressDialog progress;
	private AlertDialog alertDialog;
	private ImageView iv_anim;
	private AnimationDrawable animationDrawable;
	private DialogMessage dialogMessage;
	private ArrayAdapter<String> mDevicesArrayAdapter;
	// private BluthPosManager bluthPosManager;

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case BluetoothController.STATE_CONNECTED:
				if (alertDialog != null && alertDialog.isShowing()) {
					alertDialog.dismiss();
				}
				if (alertDialog != null && alertDialog.isShowing()) {
					alertDialog.dismiss();
				}
				dialogMessage.dialogDismiss();
				setResult(RESULT_OK);
				finish();
				break;

			case BluetoothController.STATE_DISCONNECT:
				dialogMessage.dialogDismiss();
				if (progress != null && progress.isShowing())
					progress.dismiss();
				if (alertDialog != null && alertDialog.isShowing()) {
					alertDialog.dismiss();
				}
				break;
			case 1:
				search.setClickable(true);
				iv_anim.setVisibility(View.GONE);
				search.setVisibility(View.VISIBLE);
				animationDrawable.stop();
				break;

			}
		};
	};

	class searchListener implements BluetoothSearchListener {

		@Override
		public void onSearchDevice(MposDevice device) {
			if (device.getName() != null) {
				if (device.getName().indexOf("821") != -1
						|| device.getName().indexOf("666") != -1) {
					mDevicesArrayAdapter.add(device.getName() + "\n"
							+ device.getAddress());
					devicesList
							.setSelection(mDevicesArrayAdapter.getCount() - 1);
				}
			}
		}

		@Override
		public void onStopSearch() {
			Log.d("", "stop");
			handler.sendEmptyMessage(1);
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.bluetoothset);
		// bluthPosManager = app.getAdapter(BluthPosManager.class);
		// bluthPosManager.initBluthPos(getApplicationContext(), "bluthpos");
		mDevicesArrayAdapter = new ArrayAdapter<String>(this,
				R.layout.device_name);
		dialogMessage = new DialogMessage(this);
		initView();
		search.setClickable(false);
		search.setVisibility(View.GONE);
		BluthPosManager.controller.discovery(12, new searchListener());
		animationDrawable.start();
		iv_anim.setVisibility(View.VISIBLE);

	}

	private void initView() {
		bluthManager = new BluthPosManager(this);

		iv_anim = (ImageView) findViewById(R.id.iv_anim);
		iv_anim.setBackgroundResource(R.anim.network_anim);
		iv_anim.setVisibility(View.GONE);
		animationDrawable = (AnimationDrawable) iv_anim.getBackground();

		search = (Button) findViewById(R.id.search);
		search.setOnClickListener(this);

		devicesList = (ListView) findViewById(R.id.list_view1);
		devicesList.setAdapter(mDevicesArrayAdapter);

		devicesList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				try {
					BluthPosManager.controller.stopDiscovery();
					search.setVisibility(View.VISIBLE);
					search.setClickable(true);
					animationDrawable.stop();
					iv_anim.setVisibility(View.INVISIBLE);

					String info = ((TextView) view).getText().toString();
					String name = info.substring(0, info.length() - 18);
					cur_name = name;
					String address = info.substring(info.length() - 17);
					cur_address = address;
					showChooseDialog();
				} catch (Exception e) {
				}

			}
		});
	}

	public static String cur_address;
	String cur_name;

	class MyBluetoothListener implements BluetoothStateListener {

		@Override
		public void onStateChange(int state) {
			Message msg = new Message();
			switch (state) {
			case BluetoothController.STATE_CONNECTED:
				msg.what = BluetoothController.STATE_CONNECTED;
				break;
			case BluetoothController.STATE_CONNECTING:
				msg.what = BluetoothController.STATE_CONNECTING;
				break;
			case BluetoothController.STATE_DISCONNECT:
				msg.what = BluetoothController.STATE_DISCONNECT;
				break;
			}

			handler.sendMessage(msg);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.search:
			if (search.getText().toString().equals("重新搜索")) {
				search.setClickable(false);
				search.setVisibility(View.GONE);
				mDevicesArrayAdapter.clear();
				BluthPosManager.controller.discovery(12, new searchListener());
				animationDrawable.start();
				iv_anim.setVisibility(View.VISIBLE);
			}

			break;

		}
	}

	private Dialog mypDialog;
	private TextView title_text;
	private TextView title_msg;
	private Button left;
	private Button right;

	public void showChooseDialog() {
		View customFrame = null;
		customFrame = LayoutInflater.from(this).inflate(
				R.layout.device_conform_layout, null);
		title_text = (TextView) customFrame.findViewById(R.id.title_text);
		title_msg = (TextView) customFrame.findViewById(R.id.title_msg);
		title_text.setText("您选择连接的设备");
		title_msg.setText(cur_name);
		left = (Button) customFrame.findViewById(R.id.device_left_btn);
		right = (Button) customFrame.findViewById(R.id.device_rigth_btn);
		left.setOnClickListener(new LeftListener());
		right.setOnClickListener(new RightListener());
		android.view.ViewGroup.LayoutParams layoutParams = new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		mypDialog = new Dialog(this, R.style.My_Theme_Dialog_Alert2);
		mypDialog.addContentView(customFrame, layoutParams);
		mypDialog.setCancelable(false);
		mypDialog.setCanceledOnTouchOutside(false);
		try {
			mypDialog.show();
		} catch (Exception e) {
			Log.e("dialog_show", e + "");
		}

	}

	class LeftListener implements android.view.View.OnClickListener {

		@Override
		public void onClick(View v) {
			mypDialog.dismiss();
		}

	}

	class RightListener implements android.view.View.OnClickListener {

		@Override
		public void onClick(View v) {
			dialogMessage.noticeDialog("正在连接" + cur_name, 3, null);
			BluthPosManager.controller.connect(cur_address,
					new MyBluetoothListener());
			mypDialog.dismiss();
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		BluthPosManager.controller.stopDiscovery();
	}

}
