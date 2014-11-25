package com.jike.aty;

import com.example.jike.R;
import com.jike.pos.AtyDeviceList;
import com.jike.pos.BluthPosGetBankNum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends Activity implements View.OnClickListener {
	Button bt_search, bt_play;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		bt_search = (Button) findViewById(R.id.bt_search);
		bt_play = (Button) findViewById(R.id.bt_play);
		bt_search.setOnClickListener(this);
		bt_play.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		Intent intent = new Intent(new Intent(this, BluthPosGetBankNum.class));
		switch (arg0.getId()) {
		case R.id.bt_search:
			intent.putExtra("code", 0);
			break;
		case R.id.bt_play:
			intent.putExtra("code", 1);
			break;
		}
		startActivity(intent);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

	}
}
