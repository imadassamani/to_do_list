package com.example.to_dolist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    class MainData{
        CheckBox cb;
        TextView tv;
    }
    ListView listView;
    ArrayList<MainData> listItems;
    ArrayAdapter<MainData> listAdapter;
    EditText editText;
    Button doneButton;
    MainData data;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        setSupportActionBar(toolbar);
        Resources r = this.getResources();

        final RelativeLayout rl = findViewById(R.id.relativeLayout);

        final RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        final RelativeLayout.LayoutParams lpBtn = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        listView = findViewById(R.id.view1);
        listItems = new ArrayList<>();

        listAdapter = new ArrayAdapter<>(this,
               android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(listAdapter);

        data = new MainData();
        data.tv = new TextView(this);
        data.cb = new CheckBox(this);


        editText = new EditText(this);
        editText.setText("Herllo");
        editText.setId(View.generateViewId());

        doneButton = new Button(this);
        doneButton.setText("Add");
        doneButton.setId(View.generateViewId());

        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                16,
                r.getDisplayMetrics()
        );
        lp.addRule(RelativeLayout.BELOW, listView.getId());
        lp.setMargins(px,0,0,0);
        lpBtn.addRule(RelativeLayout.BELOW, listView.getId());
        lpBtn.addRule(RelativeLayout.RIGHT_OF, editText.getId());

        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Adding item..", Toast.LENGTH_SHORT).show();
                rl.addView(editText, lp);
                rl.addView(doneButton, lpBtn);
                editText.requestFocus();
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Item added", Toast.LENGTH_SHORT).show();
                    data.tv.setText("Hehe");
                    listItems.add(data);
                    listAdapter.notifyDataSetChanged();
                }
            }
        });

    }
}


