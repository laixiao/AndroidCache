package com.cache.main.Test;

import com.cache.main.R;
import com.cache.main.lib.ACache;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SaveStringActivity extends Activity {

	private EditText mEt_string_input;
	private TextView mTv_string_res;
	private ACache mCache;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save_string);
		// 初始化控件
		mEt_string_input = (EditText) findViewById(R.id.et_string_input);
		mTv_string_res = (TextView) findViewById(R.id.tv_string_res);
		
		mCache = ACache.get(this);
	}



	/**
	 * 点击save事件
	 * 
	 * @param v
	 */
	public void save(View v) {
		if (mEt_string_input.getText().toString().trim().length() == 0) {
			Toast.makeText(
					this,
					"Cuz u input is a nullcharacter ... So , when u press \"read\" , if do not show any result , plz don't be surprise",
					Toast.LENGTH_SHORT).show();
		}
		mCache.put("testString", mEt_string_input.getText().toString());
	}

	/**
	 * 点击read事件
	 * 
	 * @param v
	 */
	public void read(View v) {
		String testString = mCache.getAsString("testString");
		if (testString == null) {
			Toast.makeText(this, "String cache is null ...", Toast.LENGTH_SHORT)
					.show();
			mTv_string_res.setText(null);
			return;
		}
		mTv_string_res.setText(testString);
	}

	/**
	 * 点击clear事件
	 * 
	 * @param v
	 */
	public void clear(View v) {
		mCache.remove("testString");
	}

}
