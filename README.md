<img src="https://github.com/laixiao/AndroidCache/blob/master/doc/1.png"></img>
#1.简介
* 1.AndroidCache 是一个为android制定的 轻量级的 开源缓存框架。轻量到只有一个java文件（由十几个类精简而来）。
  
* 2.支持的数据类型：
  > * 普通的字符串
  > * JsonObject
  > * JsonArray
  > * Bitmap
  > * Drawable
  > * 序列化的java对象
  > * byte数据

* 3.支持配置
  > * 可以配置缓存路径，缓存大小，缓存数量等。
  > * 可以设置缓存超时时间，缓存超时自动失效，并被删除。
  > * 支持多进程。
  
#2.使用（参看demo）
```java
public class SaveBitmapActivity extends Activity {
	private ImageView mIv_bitmap_res;
	private ACache mCache;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save_bitmap);
		// 初始化控件
		mIv_bitmap_res = (ImageView) findViewById(R.id.iv_bitmap_res);
		mCache = ACache.get(this);
	}
	//点击save事件
	public void save(View v) {
		Resources res = getResources();
		Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.img_test);
		mCache.put("testBitmap", bitmap);
	}

	//点击read事件
	public void read(View v) {
		Bitmap testBitmap = mCache.getAsBitmap("testBitmap");
		if (testBitmap == null) {
			Toast.makeText(this, "Bitmap cache is null ...", Toast.LENGTH_SHORT).show();
			mIv_bitmap_res.setImageBitmap(null);
			return;
		}
		mIv_bitmap_res.setImageBitmap(testBitmap);
	}

	//点击clear事件
	public void clear(View v) {
		mCache.remove("testBitmap");
	}
}
```
Android开发部落交流群：430049289
