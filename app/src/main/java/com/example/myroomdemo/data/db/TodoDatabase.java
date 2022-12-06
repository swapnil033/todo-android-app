package com.example.myroomdemo.data.db;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myroomdemo.data.db.taskDao.TaskDao;
import com.example.myroomdemo.data.model.TodoModel;

@Database(entities = TodoModel.class, version = 1)
public abstract class TodoDatabase extends RoomDatabase {

    private static final String DB_NAME = "todo_db";
    private static final String DB_TAG = "todo_db_tag";
    private static final Object LOCK = new Object();
    private static TodoDatabase dbInstance;

    public abstract TaskDao taskDao();

    public static TodoDatabase getDbInstance(Context context){
        if (dbInstance == null) {
            synchronized (LOCK) {
                Log.d(DB_TAG, "Creating new database instance");
                dbInstance = Room.databaseBuilder(context.getApplicationContext(),
                                TodoDatabase.class, TodoDatabase.DB_NAME)
                        .build();
            }
        }
        Log.d(DB_TAG, "Getting the database instance");
        return dbInstance;
    }

}
