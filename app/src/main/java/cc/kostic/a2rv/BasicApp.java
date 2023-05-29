package cc.kostic.a2rv;

import android.app.Application;

import androidx.room.Room;

import cc.kostic.a2rv.ui.db.AppDb;
import cc.kostic.a2rv.ui.db.SlikaDao;
import cc.kostic.a2rv.ui.db.UserDao;

public class BasicApp extends Application {

	private static AppDb appDb;
	private static SlikaDao slikaDao;

	@Override
	public void onCreate() {
		super.onCreate();

		appDb = Room.databaseBuilder(getApplicationContext(), AppDb.class, "dva_risajklera").build();

	}


	public static AppDb getAppDb() {
		return appDb;
	}

	public static SlikaDao getSlikaDao() {
		return slikaDao;
	}
}
