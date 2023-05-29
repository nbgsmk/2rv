package cc.kostic.a2rv.ui.db;

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

import cc.kostic.a2rv.AppExecutors;

@Database(entities = {Slika.class, User.class}, version = 1)
public abstract class AppDb_Room extends RoomDatabase {
	public abstract SlikaDao slikaDao();
	public abstract UserDao userDao();


	@VisibleForTesting
	public static String DB_NAME = "recycler_test";
	private static volatile AppDb_Room DB_INSTANCE;
	public static final AppExecutors executors = new AppExecutors();
	private final MutableLiveData<Boolean> isDatabaseCreated = new MutableLiveData<>();

	public abstract SlikaDao uredjajDao();
	public abstract UserDao dugmeDao();

	public static AppDb_Room getInstance(final Context context, final AppExecutors executors) {
		if (DB_INSTANCE == null) {
			synchronized (AppDb_Room.class) {
				if (DB_INSTANCE == null) {
					DB_INSTANCE = buildDatabase(context.getApplicationContext(), executors);
					DB_INSTANCE.updateDatabaseCreated(context.getApplicationContext());
				}
			}
		}
		return DB_INSTANCE;
	}


	/**
	 * Build the database. {@link Builder#build()} only sets up the database configuration and
	 * creates a new instance of the database.
	 * The SQLite database is only created when it's accessed for the first time.
	 */
	private static AppDb_Room buildDatabase(final Context appContext, final AppExecutors executors) {
		return Room.databaseBuilder(appContext, AppDb_Room.class, DB_NAME)
				.addCallback(new Callback() {
					@Override
					public void onCreate(@NonNull SupportSQLiteDatabase db) {
						super.onCreate(db);
						executors.diskIO().execute(() -> {
							AppDbDataGenerator.delay_inDebugMode();     // Add a delay to simulate a long-running operation
							// Generate the data for pre-population
							AppDb_Room database = AppDb_Room.getInstance(appContext, executors);
							List<Slika> uredjajList = AppDbDataGenerator.getDummyUredjajs();

							insertData(database, uredjajList);
							// notify that the database was created and it's ready to be used
							database.setDatabaseCreated();
						});
					}
				})
				.setQueryExecutor(executors.diskIO())           // STOPSHIP - vidi Dugme i Uredjaj repo - obrisi()
				.setTransactionExecutor(executors.diskIO())     // STOPSHIP - vidi Dugme i Uredjaj repo - obrisi()
				.fallbackToDestructiveMigration()               // STOPSHIP
				.fallbackToDestructiveMigrationOnDowngrade()    // STOPSHIP
				// .addMigrations(MIGRATION_1_2)                // STOPSHIP
				.build();
	}




	/**
	 * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
	 */
	private void updateDatabaseCreated(final Context context) {
		if (context.getDatabasePath(DB_NAME).exists()) {
			setDatabaseCreated();
		}
	}

	private void setDatabaseCreated(){
		isDatabaseCreated.postValue(true);
	}

	public LiveData<Boolean> getDatabaseCreated() {
		return isDatabaseCreated;
	}


	private static void insertData(final AppDb_Room database, final List<Slika> slike) {
		database.runInTransaction(() -> {
			database.slikaDao().insert(slike);
		});
	}



}
