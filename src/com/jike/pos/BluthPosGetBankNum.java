package com.jike.pos;

import java.util.HashMap;
import java.util.Map;

import com.buybal.framework.bean.BaseBean;
import com.buybal.framework.bean.CardBean;
import com.buybal.framework.constant.Constant;
import com.buybal.framework.handler.PosHandler;
import com.buybal.framework.utils.BluthPosManager;
import com.buybal.framework.utils.EncryptManager;
import com.buybal.framework.utils.InterfaceUtil;
import com.example.jike.R;
import com.google.gson.Gson;
import com.jike.app.BaseApplication;
import com.jike.aty.BaseRequestParams;
import com.jike.aty.RequestParams4CheckBalance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class BluthPosGetBankNum extends Activity implements
		View.OnClickListener {
	private BaseApplication app;
	private ImageView action_bar_left;
	private BluthPosManager bluthPosManager;
	private String Ksn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bluthpos_swiper);
		init();
		if (!app.getIsLink()) {
			startActivityForResult(new Intent(this, AtyDeviceList.class), 0);
		} else {
			bluthPosManager.startSwipe("10.00", "search");
			bluthPosManager.initBluthPos(app, "", handler);
			Toast.makeText(this, "蓝牙已连接", Toast.LENGTH_SHORT).show();
		}
	}

	private void init() {
		action_bar_left = (ImageView) findViewById(R.id.action_bar_left);
		action_bar_left.setOnClickListener(this);
		app = (BaseApplication) getApplication();
		bluthPosManager = (app.getAdapter(BluthPosManager.class));
		// bluthPosManager=new BluthPosManager(this);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			app.setLink(true);
		}
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.action_bar_left:
			finish();
			break;
		}
	}

	private PosHandler handler = new PosHandler() {

		@Override
		protected void onOutTime(String msg) {
			Toast.makeText(BluthPosGetBankNum.this, msg, 0).show();
			setResult(RESULT_CANCELED);
			bluthPosManager = null;
		}

		@Override
		protected void onError(String errMsg) {
			int a = 0;
			Toast.makeText(BluthPosGetBankNum.this, errMsg, 0).show();
			setResult(RESULT_CANCELED);
			bluthPosManager = null;
		}

		@Override
		protected void OnReadCardInfo(String cardInfo) {
			System.out.println("读卡成功" + cardInfo);
			Toast.makeText(app, cardInfo, 2).show();
			Intent in = new Intent();
			in.putExtra("ksn", cardInfo);
			setResult(RESULT_OK, in);
			// bluthPosManager = null;
			BluthPosGetBankNum.this.finish();
		}

		@Override
		protected void onGetKsn(String ksn) {
			// TODO Auto-generated method stub
			System.out.println("ksn........." + ksn);
			Ksn = ksn;
			// checkBoxBind(ksn);
			// bluthPosManager.startSwipe();

			// getworkKey(Ksn);
		}

		@Override
		protected void onStartSwip(String msg) {
			// TODO Auto-generated method stub
			// if("Hx1000".equals(postype))
			// {
			// hx1000ma.SwipPinZuhe();
			// }
			bluthPosManager.startGetBankNum();
		}

		@Override
		protected void onCompleteSwip(CardBean cardBean) {
			// TODO Auto-generated method stub
			// Toast.makeText(BluthPosGetBankNum.this, "刷卡成功，进行交易", 0).show();
			// cardbean = cardBean;
			bluthPosManager.calMac(cardBean.getMaskedPAN(),
					cardBean.getPsamPin(), "", cardBean.getEncTracks(),
					cardBean.getPsamTrack(), Ksn, "M00000000000660");
		}
     
		@Override  
		protected void onGetMacValue(String macValue) {
			// TODO Auto-generated method stub
			bluthPosManager.displayTer("", "");
		}

		@Override
		protected void onFiled55Error(String result) {
			// TODO Auto-generated method stub
		}

		@Override
		protected void onCancleSwiped(String result) {
			// TODO Auto-generated method stub
			// finish();
		}
	};

}
