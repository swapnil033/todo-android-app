package com.example.myroomdemo.data.db.taskDao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myroomdemo.data.model.TodoModel;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertTask(TodoModel model);

    @Update
    void updateTask(TodoModel model);

    @Delete
    void deleteTask(TodoModel model);

    @Query("SELECT * FROM todoModel")
    List<TodoModel> showTasks();

}
