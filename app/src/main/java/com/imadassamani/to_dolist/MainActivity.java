package com.imadassamani.to_dolist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<CheckBoxListView> listItem;
    CheckBoxListViewAdapter listAdapter;
    EditText editText;
    Button doneButton;
    CheckBoxListView data;
    SharedPreferences sp;
    String[] listString;
    String[] listDate;
    boolean[] listBoolean;
    Date date;
    String curDate;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        setSupportActionBar(toolbar);
        Resources r = this.getResources();
        date = Calendar.getInstance().getTime();
        curDate = (String) DateFormat.format("dd", date);
        sp = this.getPreferences(Context.MODE_PRIVATE);


        final RelativeLayout rl = findViewById(R.id.relativeLayout);

        final RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        final RelativeLayout.LayoutParams lpBtn = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        listView = findViewById(R.id.view1);
        listItem = new ArrayList<>();
        listAdapter = new CheckBoxListViewAdapter(this,
               R.layout.check_box_list_view, listItem);
        listView.setAdapter(listAdapter);
        listString = new String[20];
        listDate = new String[20];
        listBoolean = new boolean[20];
        for (int i = 0; i < 20; i++){
            /// TODO: Make log of everything added and removed
            listString[i] = sp.getString("item_" + i, "");
            listDate[i] = sp.getString("date_" + i, "");
            listBoolean[i] = sp.getBoolean("check_" + i, false);
            if (!listDate[i].equals(curDate)){
                 if (listBoolean[i])
                     listString[i] = "";
                 else{
                     Toast.makeText(this, listString[i] + " overdue.", Toast.LENGTH_SHORT).show();
                 }
            }
            if (!listString[i].isEmpty()){
                data = new CheckBoxListView(listString[i], false);
                listItem.add(data);
                listAdapter.notifyDataSetChanged();
            }
        }

        editText = new EditText(this);
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
                rl.addView(editText, lp);
                rl.addView(doneButton, lpBtn);
                editText.requestFocus();
                Toast.makeText(MainActivity.this, "Adding item..", Toast.LENGTH_SHORT).show();
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().equals("")){
                    data = new CheckBoxListView(editText.getText().toString(), false);
                    listItem.add(data);
                    listAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Item added", Toast.LENGTH_SHORT).show();
                    rl.removeView(doneButton);
                    rl.removeView(editText);
                }
            }
        });

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("testPreference", "Test Value");
        editor.apply();
    }
}




