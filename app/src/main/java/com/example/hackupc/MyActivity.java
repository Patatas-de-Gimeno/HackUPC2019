package com.example.hackupc;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_layout);
        // db=AppDatabase.getAppDatabase(getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.tv_main_list);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        // AQUÍ SE RECUPERAN LOS DATOS DE LA BBDD SEGUN EL TIPO DE OBJETO

        recyclerView.setAdapter(mAdapter);
    }

}

