package com.cache.main.Test;

import com.cache.main.R;
import com.cache.main.lib.ACache;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class SaveDrawableActivity extends Activity {

	private ImageView mIv_drawable_res;

	private ACache mCache;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save_drawable);
		// 初始化控件
		mIv_drawable_res = (ImageView) findViewById(R.id.iv_drawable_res);

		mCache = ACache.get(this);
	}

	/**
	 * 点击save事件
	 * 
	 * @param v
	 */
	public void save(View v) {
		Resources res = getResources();
		Drawable drawable = res.getDrawable(R.drawable.img_test);
		mCache.put("testDrawable", drawable);
	}

	/**
	 * 点击read事件
	 * 
	 * @param v
	 */
	public void read(View v) {
		Drawable testDrawable = mCache.getAsDrawable("testDrawable");
		if (testDrawable == null) {
			Toast.makeText(this, "Drawable cache is null ...",
					Toast.LENGTH_SHORT).show();
			mIv_drawable_res.setImageDrawable(null);
			return;
		}
		mIv_drawable_res.setImageDrawable(testDrawable);
	}

	/**
	 * 点击clear事件
	 * 
	 * @param v
	 */
	public void clear(View v) {
		mCache.remove("testDrawable");
	}
}