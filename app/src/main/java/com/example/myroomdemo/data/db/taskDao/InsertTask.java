package com.example.myroomdemo.data.db.taskDao;

import android.os.AsyncTask;

import com.example.myroomdemo.data.model.TodoModel;

public class InsertTask extends AsyncTask<Void, Boolean, Boolean> {
    private final TaskDao taskDao;
    private final TodoModel model;
    public TaskListener<Boolean> listener;

    private String errorMessage = "";

    public InsertTask( TaskDao taskDao ,TodoModel model, TaskListener<Boolean> listener){
        this.taskDao = taskDao;
        this.model = model;
        this.listener = listener;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try{
            taskDao.insertTask(model);
        }catch (Exception e){
            errorMessage = e.getLocalizedMessage();
            return false;
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if (aBoolean)
            listener.onSuccess(aBoolean);
        else
            listener.onFail(errorMessage);
    }
}
