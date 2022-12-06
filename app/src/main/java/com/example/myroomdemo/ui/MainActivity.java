package com.example.myroomdemo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.myroomdemo.R;
import com.example.myroomdemo.ui.adapter.TodoAdapter;
import com.example.myroomdemo.data.db.helper.TaskHelper;
import com.example.myroomdemo.data.db.helper.TaskHelperListener;
import com.example.myroomdemo.data.model.TodoModel;
import com.example.myroomdemo.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private TaskHelper taskHelper;
    private TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initView();
        setUpListener();
    }

    private void setUpListener() {
        binding.rvTodoList.setAdapter(adapter);
        populateList();

        binding.addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialog dialog = new ShowDialog(MainActivity.this, taskHelper, new ShowDialog.ShowDialogListener() {
                    @Override
                    public void onSubmit(TodoModel todoModel) {
                        populateList();
                    }
                });
            }
        });
    }

    private void initView() {
        taskHelper = new TaskHelper(this);
        adapter = new TodoAdapter(adapterListener());
    }

    private void populateList() {
        taskHelper.showTaskList(new TaskHelperListener<List<TodoModel>>() {
            @Override
            public void onSuccess(List<TodoModel> model) {
                adapter.setList(model);
            }
        });
    }

    private TodoAdapter.TodoAdapterListener adapterListener(){
       return new TodoAdapter.TodoAdapterListener() {
           @Override
           public void onClick(TodoModel todoModel) {

           }

           @Override
           public void onDelete(TodoModel todoModel) {
               taskHelper.deleteTask(todoModel, new TaskHelperListener<Boolean>() {
                   @Override
                   public void onSuccess(Boolean model) {
                       populateList();
                   }
               });
           }

           @Override
           public void onUpdate(TodoModel todoModel) {
               ShowDialog dialog = new ShowDialog(MainActivity.this, taskHelper, new ShowDialog.ShowDialogListener() {
                   @Override
                   public void onSubmit(TodoModel todoModel) {
                       taskHelper.updateTask(todoModel, new TaskHelperListener<Boolean>() {
                           @Override
                           public void onSuccess(Boolean model) {
                               populateList();
                           }
                       });
                   }
               });
               dialog.populateFields(todoModel);
           }
       };
    }
}