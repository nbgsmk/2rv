package cc.kostic.a2rv;

import android.app.Application;

import cc.kostic.a2rv.db.AppDb_Room;

public class BasicApp extends Application {

	private AppExecutors appExecutors;

	@Override
	public void onCreate() {
		super.onCreate();
		appExecutors = new AppExecutors();


	}

	public AppDb_Room getDatabase() {
		return AppDb_Room.getInstance(this, appExecutors);
	}

}
