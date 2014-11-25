package com.jike.app;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import com.buybal.framework.bean.BaseBean;
import com.buybal.framework.handler.CrashHandler;
import com.buybal.framework.utils.BluthPosManager;
import com.buybal.framework.utils.BoxManager;
import com.buybal.framework.utils.EncryptManager;
import com.buybal.framework.utils.InterfaceUtil;
import com.buybal.framework.utils.hx1000Manager;
import com.jike.aty.BaseActivity;
import com.jike.receiver.NetChangeReceiver;

import android.app.ActivityManager;
import android.app.Application;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;
import android.util.Log;

public class BaseApplication extends Application {
	private static final String TAG = BaseApplication.class.getSimpleName();
	// Activity管理者
	private ActivityManager activityManager;
	private CrashHandler crashHandler;
	// 容器
	private HashMap<Integer, Stack<BaseActivity>> taskMap = null;
	// 数据
	private BaseBean baseBean;
	// 网络状态广播
	private NetChangeReceiver netChangeReceiver = null;
	// 加密管理者
	private EncryptManager encryptManager;
	// 盒子管理者
	private BoxManager boxManager;
	// 蓝牙pos管理者
	private BluthPosManager bluthPosManager;
	private hx1000Manager hxManager;
	// 设置
	private SharedPreferences preferences;
	private boolean isLink;

	// private DatabaseHelper databaseHelper;

	public BaseBean getBaseBean() {
		return baseBean;
	}

	public void logout() {
		this.baseBean.setLogin(false);
		this.baseBean.setUserName("");
		this.baseBean.setToken("");
	}

	@Override
	public void onCreate() {
		super.onCreate();
		//
		setLink(false);
		activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		taskMap = new HashMap<Integer, Stack<BaseActivity>>();
		// 添加异常捕获类（假如异常，会提示异常，并退出）
		crashHandler = this.getAdapter(CrashHandler.class);
		// 初始化全局变量
		this.baseBean = new BaseBean();
		this.baseBean.setAppType(InterfaceUtil.getAppType());
		this.baseBean.setAppOs(InterfaceUtil.getAppOs());
		this.baseBean.setAppVersion(InterfaceUtil.getAppVersion(this));
		this.baseBean.setImei(InterfaceUtil.getIMEI(this));
		this.baseBean.setImsi(InterfaceUtil.getIMSI(this));
		this.baseBean.setDeviceSN(InterfaceUtil.getDeviceSN(this));
		this.baseBean.setDeviceType(InterfaceUtil.getDeviceType());
		this.baseBean.setMac(InterfaceUtil.getMAC(this));
		this.baseBean.setIp(InterfaceUtil.getLocalIpAddress());
		this.baseBean.setCookie("");
		if (Log.isLoggable(TAG, Log.DEBUG)) {
			Log.d(TAG, baseBean.toString());
		}
		// 网络状态
		registerNetChangeReceiver();
	}

	@Override
	public void onTerminate() {
		baseBean = null;
		if (netChangeReceiver != null) {
			unregisterReceiver(netChangeReceiver);
		}
		super.onTerminate();
	}

	/**
	 * 注册网络监听
	 */
	private void registerNetChangeReceiver() {
		netChangeReceiver = new NetChangeReceiver();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(netChangeReceiver, intentFilter);
	}

	/**
	 * 代理模式
	 * 
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public synchronized <T> T getAdapter(Class<T> clazz) {

		if (BoxManager.class == clazz) {
			if (boxManager == null) {
				boxManager = new BoxManager();
			}
			return clazz.cast(boxManager);
		}

		if (BluthPosManager.class == clazz) {
			if (bluthPosManager == null) {
				bluthPosManager = new BluthPosManager(getApplicationContext());
			}
			return clazz.cast(bluthPosManager);
		}
//		if (hx1000Manager.class == clazz) {
//			if (hxManager == null) {
//				hxManager = new hx1000Manager();
//			}
//			return clazz.cast(hxManager);
//		}
		if (EncryptManager.class == clazz) {
			if (encryptManager == null) {
				encryptManager = new EncryptManager();
			}
			return clazz.cast(encryptManager);
		}
		if (CrashHandler.class == clazz) {
			if (crashHandler == null) {
				crashHandler = new CrashHandler(this);
			}
			return clazz.cast(crashHandler);
		}
		// if (DatabaseHelper.class == clazz) {
		// if (databaseHelper == null) {
		// databaseHelper = new DatabaseHelper(this);
		// }
		// return clazz.cast(databaseHelper);
		// }

		if (SharedPreferences.class == clazz) {
			if (preferences == null) {
				preferences = PreferenceManager
						.getDefaultSharedPreferences(this);
			}
			return clazz.cast(preferences);
		}

		return null;
	}

	public void pushToStack(BaseActivity activity) {
		int currentTaskId = getCurrentTaskId();
		if (!taskMap.containsKey(currentTaskId)) {
			taskMap.put(currentTaskId, new Stack<BaseActivity>());
		}
		Stack<BaseActivity> stack = taskMap.get(currentTaskId);
		stack.add(activity);
	}

	public int getCurrentTaskId() {
		List<ActivityManager.RunningTaskInfo> runningTasks = activityManager
				.getRunningTasks(1);
		ActivityManager.RunningTaskInfo runningTask = runningTasks.get(0);
		return runningTask.id;
	}

	public void removeFromStack(BaseActivity activity) {
		Stack<BaseActivity> stack = taskMap.get(getCurrentTaskId());
		if (stack != null) {
			stack.remove(activity);
		}
	}

	public Stack<BaseActivity> getCurrentTask() {
		return taskMap.get(getCurrentTaskId());
	}

	public boolean getIsLink() {
		return isLink;
	}

	public void setLink(boolean isLink) {
		this.isLink = isLink;
	}

}
