package com.app.wiki;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recycleView;
    private int i;
    private TestAdapter adapter;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycleView = (RecyclerView) findViewById(R.id.recycle_view);
        recycleView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(mLayoutManager);

        List<DataModel> dataModels = SharedPreferenceManager.getInstance().getTransactionsFromKeeper();
        adapter = new TestAdapter(this, dataModels);
        recycleView.setAdapter(adapter);

        findViewById(R.id.tv_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferenceManager.getInstance().saveTransactionsInKeeper(null);
                finish();
            }
        });


        if (dataModels == null || dataModels.size() == 0) {
            openDialog();
        }
    }

    private void openDialog() {
        // create a Dialog component
        dialog = new Dialog(this);

        //tell the Dialog to use the dialog.xml as it's layout description
        dialog.setContentView(R.layout.dialog_ui);
        dialog.setTitle("Android Custom Dialog Box");

        final EditText txt = (EditText) dialog.findViewById(R.id.et_name);

        Button dialogButton = (Button) dialog.findViewById(R.id.btn_ok);

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                DataModel dataModel = new DataModel(txt.getText().toString(), "eqowieqwe2wfew.com");
                List<DataModel> dataModels = SharedPreferenceManager.getInstance().getTransactionsFromKeeper();
                dataModels.add(dataModel);
                SharedPreferenceManager.getInstance().saveTransactionsInKeeper(dataModels);

                adapter.setData(dataModel);
            }
        });

        dialog.show();
    }
}
