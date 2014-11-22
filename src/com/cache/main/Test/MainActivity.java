package com.cache.main.Test;

import com.cache.main.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

//主界面
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	// 1.String缓存
	public void string(View v) {
		startActivity(new Intent().setClass(this, SaveStringActivity.class));
	}

	// 2.JsonObject缓存
	public void jsonobject(View v) {
		startActivity(new Intent().setClass(this, SaveJsonObjectActivity.class));
	}

	// 3.JsonArray缓存
	public void jsonarray(View v) {
		startActivity(new Intent().setClass(this, SaveJsonArrayActivity.class));
	}

	// 4.Bitmap缓存
	public void bitmap(View v) {
		startActivity(new Intent().setClass(this, SaveBitmapActivity.class));
	}

	// 5.Media缓存
	public void media(View v) {
		startActivity(new Intent().setClass(this, SaveMediaActivity.class));
	}

	// 6.Drawable缓存
	public void drawable(View v) {
		startActivity(new Intent().setClass(this, SaveDrawableActivity.class));
	}

	// 7.Object對象缓存
	public void object(View v) {
		startActivity(new Intent().setClass(this, SaveObjectActivity.class));
	}

	// 8.代码作者
	public void about(View v) {
		startActivity(new Intent().setClass(this, AboutActivity.class));
	}

}
