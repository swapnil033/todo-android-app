package com.example.myroomdemo.data.db.helper;

import android.content.Context;
import android.util.Log;

import com.example.myroomdemo.data.db.TodoDatabase;
import com.example.myroomdemo.data.db.taskDao.DeleteTask;
import com.example.myroomdemo.data.db.taskDao.InsertTask;
import com.example.myroomdemo.data.db.taskDao.ShowTaskList;
import com.example.myroomdemo.data.db.taskDao.TaskDao;
import com.example.myroomdemo.data.db.taskDao.TaskListener;
import com.example.myroomdemo.data.db.taskDao.UpdateTask;
import com.example.myroomdemo.data.model.TodoModel;

import java.util.ArrayList;
import java.util.List;

public class TaskHelper {
    private static final String TAG = "TaskHelperTag";

    private final TaskDao taskDao;

    public TaskHelper(Context context){
        taskDao = TodoDatabase.getDbInstance(context).taskDao();
    }

    public void insertTask(TodoModel model, TaskHelperListener<Boolean> listener){
        new InsertTask(taskDao, model, new TaskListener<Boolean>() {
            @Override
            public void onSuccess(Boolean model) {
                listener.onSuccess(model);
            }

            @Override
            public void onFail(String message) {
                Log.d(TAG, "onFail: " + message);
            }
        }).execute();
    }

    public void deleteTask(TodoModel model, TaskHelperListener<Boolean> listener){
        new DeleteTask(taskDao, model, new TaskListener<Boolean>() {
            @Override
            public void onSuccess(Boolean model) {
                listener.onSuccess(model);
            }

            @Override
            public void onFail(String message) {
                Log.d(TAG, "onFail: " + message);
            }
        }).execute();
    }

    public void updateTask(TodoModel model, TaskHelperListener<Boolean> listener){
        new UpdateTask(taskDao, model, new TaskListener<Boolean>() {
            @Override
            public void onSuccess(Boolean model) {
                listener.onSuccess(model);
            }

            @Override
            public void onFail(String message) {
                Log.d(TAG, "onFail: " + message);
            }
        }).execute();
    }

    public void showTaskList(TaskHelperListener<List<TodoModel>> listener){
        new ShowTaskList(taskDao, new TaskListener<List<TodoModel>>() {
            @Override
            public void onSuccess(List<TodoModel> model) {
                listener.onSuccess(model);
            }

            @Override
            public void onFail(String message) {
                Log.d(TAG, "onFail: " + message);
            }
        }).execute();
    }
}
