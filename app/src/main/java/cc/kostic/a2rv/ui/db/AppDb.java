package cc.kostic.a2rv.ui.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Slika.class, User.class}, version = 1)
public abstract class AppDb extends RoomDatabase {
	public abstract SlikaDao slikaDao();
	public abstract UserDao userDao();

}
