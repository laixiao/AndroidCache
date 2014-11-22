package com.cache.main.Test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cache.main.R;
import com.cache.main.lib.ACache;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;


public class SaveJsonArrayActivity extends Activity {

	private TextView mTv_jsonarray_original, mTv_jsonarray_res;
	private JSONArray jsonArray;

	private ACache mCache;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save_jsonarray);
		// 初始化控件
		mTv_jsonarray_original = (TextView) findViewById(R.id.tv_jsonarray_original);
		mTv_jsonarray_res = (TextView) findViewById(R.id.tv_jsonarray_res);

		mCache = ACache.get(this);
		jsonArray = new JSONArray();
		JSONObject yosonJsonObject = new JSONObject();

		try {
			yosonJsonObject.put("name", "Yoson");
			yosonJsonObject.put("age", 18);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		JSONObject michaelJsonObject = new JSONObject();
		try {
			michaelJsonObject.put("name", "Michael");
			michaelJsonObject.put("age", 25);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		jsonArray.put(yosonJsonObject);
		jsonArray.put(michaelJsonObject);

		mTv_jsonarray_original.setText(jsonArray.toString());
	}



	/**
	 * 点击save事件
	 * 
	 * @param v
	 */
	public void save(View v) {
		mCache.put("testJsonArray", jsonArray);
	}

	/**
	 * 点击read事件
	 * 
	 * @param v
	 */
	public void read(View v) {
		JSONArray testJsonArray = mCache.getAsJSONArray("testJsonArray");
		if (testJsonArray == null) {
			Toast.makeText(this, "JSONArray cache is null ...",
					Toast.LENGTH_SHORT).show();
			mTv_jsonarray_res.setText(null);
			return;
		}
		mTv_jsonarray_res.setText(testJsonArray.toString());
	}

	/**
	 * 点击clear事件
	 * 
	 * @param v
	 */
	public void clear(View v) {
		mCache.remove("testJsonArray");
	}
}
