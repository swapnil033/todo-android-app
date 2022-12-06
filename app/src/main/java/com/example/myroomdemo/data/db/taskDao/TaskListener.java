package com.example.myroomdemo.data.db.taskDao;

public interface TaskListener<T> {
    void onSuccess(T model);
    void onFail(String message);
}
