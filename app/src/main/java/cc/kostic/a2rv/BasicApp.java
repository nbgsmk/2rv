package cc.kostic.a2rv;

import android.app.Application;

import cc.kostic.a2rv.db.AppDb;

public class BasicApp extends Application {

	private AppExecutors appExecutors;

	@Override
	public void onCreate() {
		super.onCreate();
		appExecutors = new AppExecutors();
	}

	public AppDb getDatabase() {
		return AppDb.getInstance(getApplicationContext());
	}

}
