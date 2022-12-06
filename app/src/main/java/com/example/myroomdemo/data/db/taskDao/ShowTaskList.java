package com.example.myroomdemo.data.db.taskDao;

import android.os.AsyncTask;

import com.example.myroomdemo.data.model.TodoModel;

import java.util.ArrayList;
import java.util.List;

public class ShowTaskList extends AsyncTask<Void, Boolean, List<TodoModel>> {
    private final TaskDao taskDao;
    public TaskListener<List<TodoModel>> listener;

    private String errorMessage = "";

    public ShowTaskList( TaskDao taskDao, TaskListener<List<TodoModel>> listener){
        this.taskDao = taskDao;
        this.listener = listener;
    }

    @Override
    protected List<TodoModel> doInBackground(Void... voids) {

        List<TodoModel> list = null;

        try{
            list = taskDao.showTasks();
        }catch (Exception e){
            errorMessage = e.getLocalizedMessage();
            return list;
        }
        return list;
    }

    @Override
    protected void onPostExecute(List<TodoModel> list) {
        super.onPostExecute(list);
        if (list == null)
            listener.onFail(errorMessage);
        else
            listener.onSuccess(list);
    }
}
