package com.cache.main.Test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.cache.main.R;
import com.cache.main.lib.ACache;

public class SaveMediaActivity extends Activity implements Runnable {
	private String mUrl = "http://lx.cdn.baidupcs.com/file/8639945c0006fd73649b9e959163368e?bkt=p2-qd-89&xcode=b25fc0b48493d06c5ca2f7b9d4857a2404574eb5be85dfa6837047dfb5e85c39&fid=3158529459-250528-516787845320425&time=1416218866&sign=FDTAXERB-DCb740ccc5511e5e8fedcff06b081203-mpL8vDjUI03CgF0Z9CaAuwhJRp4%3D&to=cb&fm=Nan,B,U,nc&sta_dx=8&sta_cs=67&sta_ft=flv&sta_ct=5&newver=1&newfm=1&flow_ver=3&expires=8h&rt=sh&r=728326479&mlogid=2491370994&vuk=3158529459&vbdid=3917168490&fin=%E4%BC%98%E9%85%B7%E7%BD%91-%E9%9D%92%E5%B9%B4%E5%B1%81%E7%9A%84%E6%98%A5%E8%BF%90%E6%BC%82%E6%B5%81%2008.flv&fn=%E4%BC%98%E9%85%B7%E7%BD%91-%E9%9D%92%E5%B9%B4%E5%B1%81%E7%9A%84%E6%98%A5%E8%BF%90%E6%BC%82%E6%B5%81%2008.flv";
	private static String CACHE_KEY = "file01";

	private TextView text;
	private ACache mCache;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save_file);

		text = (TextView) findViewById(R.id.text);

		mCache = ACache.get(this);
	}

	/**
	 * 点击save事件
	 * 
	 * @param v
	 */
	public void save(View v) {
		text.setText("Loading...");
		new Thread(this).start();
	}

	/**
	 * 点击read事件
	 * 
	 * @param v
	 */
	public void read(View v) {
		InputStream stream = null;
		try {
			stream = mCache.get(CACHE_KEY);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (stream == null) {
			Toast.makeText(this, "Bitmap cache is null ...", Toast.LENGTH_SHORT)
					.show();
			text.setText("file not found");
			return;
		}
		try {
			text.setText("file size: " + stream.available());
		} catch (IOException e) {
			text.setText("error " + e.getMessage());
		}
	}

	/**
	 * 点击clear事件
	 * 
	 * @param v
	 */
	public void clear(View v) {
		mCache.remove(CACHE_KEY);
	}

	@Override
	public void run() {
		OutputStream ostream = null;
		try {
			ostream = mCache.put(CACHE_KEY);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (ostream == null) {
			Toast.makeText(this, "Open stream error!", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		try {
			URL u = new URL(mUrl);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.connect();
			InputStream stream = conn.getInputStream();

			byte[] buff = new byte[1024];
			int counter;

			while ((counter = stream.read(buff)) > 0) {
				ostream.write(buff, 0, counter);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// cache update
				ostream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					text = (TextView) findViewById(R.id.text);
					text.setText("done...");
				}
			});
		}
	}
}
