package com.example.myroomdemo.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.example.myroomdemo.R;
import com.example.myroomdemo.data.db.helper.TaskHelper;
import com.example.myroomdemo.data.db.helper.TaskHelperListener;
import com.example.myroomdemo.data.model.TodoModel;
import com.example.myroomdemo.databinding.DialogAddTaskBinding;

public class ShowDialog {
    private final Context context;
    private final TaskHelper taskHelper;
    private final ShowDialogListener listener;

    private DialogAddTaskBinding binding;
    private TodoModel todoModel = new TodoModel();

    public ShowDialog(Context context, TaskHelper taskHelper, ShowDialogListener listener){
        this.context = context;
        this.taskHelper = taskHelper;
        this.listener = listener;

        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_add_task, null, false);

        showDialogBox();
    }

    public void populateFields(TodoModel todoModel){
        this.todoModel = todoModel;
        binding.edTask.setText(todoModel.getTask());
    }

    private void showDialogBox() {
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels + 0.90);

        Dialog dialog = new Dialog(context);
        dialog.setContentView(binding.getRoot());
        dialog.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.edTask.getText().toString().isEmpty())
                    return;

                todoModel.setTask(binding.edTask.getText().toString());
                taskHelper.insertTask(todoModel, new TaskHelperListener<Boolean>() {
                    @Override
                    public void onSuccess(Boolean model) {
                        listener.onSubmit(todoModel);
                        dialog.dismiss();
                    }
                });
            }
        });

        dialog.show();
    }


    public interface ShowDialogListener{
        void onSubmit(TodoModel todoModel);
    }
}
