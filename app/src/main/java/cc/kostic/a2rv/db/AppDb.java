package cc.kostic.a2rv.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cc.kostic.a2rv.AppExecutors;

@Database(entities = {Slika.class}, version = 1)
public abstract class AppDb extends RoomDatabase {

	@VisibleForTesting
	public static String DB_NAME = "recycler_test";
	private static volatile AppDb DB_INSTANCE;
	private static final int NUMBER_OF_THREADS = 4;
	static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

	private final MutableLiveData<Boolean> isDatabaseCreated = new MutableLiveData<>();

	public abstract SlikaDao slikaDao();

	public static AppDb getInstance(final Context context) {
		if (DB_INSTANCE == null) {
			synchronized (AppDb.class) {
				if (DB_INSTANCE == null) {
					DB_INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
									AppDb.class, DB_NAME)
							.addCallback(dbCallback)
							.build();
				}
			}
		}
		return DB_INSTANCE;
	}




	private static final RoomDatabase.Callback dbCallback = new RoomDatabase.Callback() {
		@Override
		public void onCreate(@NonNull SupportSQLiteDatabase db) {
			super.onCreate(db);

			databaseWriteExecutor.execute(() -> {
				// Populate the database in the background.
				// If you want to start with more words, just add them.
				SlikaDao dao = DB_INSTANCE.slikaDao();
				dao.deleteAll();

				List<Slika> slike = AppDbDataGenerator.getDummySlike();
				dao.insert(slike);
			});
		}
	};


}
