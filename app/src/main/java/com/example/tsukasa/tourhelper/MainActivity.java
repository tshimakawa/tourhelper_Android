package com.example.tsukasa.tourhelper;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.StringTokenizer;

import static com.example.tsukasa.tourhelper.R.id.endtimeView;
import static com.example.tsukasa.tourhelper.R.id.starttimeView;
import static com.example.tsukasa.tourhelper.R.id.textView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String[] destList = {"観光地","美術館","遊園地","動物園","水族館","神社","寺"};
    private String destination = "美術館";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner dest_spinner = (Spinner)findViewById(R.id.placeList);
        Button starttimeButton = (Button)findViewById(R.id.starttimeButton);
        Button endtimeButton = (Button)findViewById(R.id.endtimeButton);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,destList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dest_spinner.setSelection(2);
        // spinner に adapter をセット
        dest_spinner.setAdapter(arrayAdapter);
        // リスナーを登録
        dest_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //　アイテムが選択された時
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int position, long id) {
                Spinner spinner = (Spinner)parent;
                String item = (String)spinner.getSelectedItem();
                destination = item;
            }

            //　アイテムが選択されなかった
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        starttimeButton.setOnClickListener(this);
        endtimeButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        if(v!=null){
            switch(v.getId()){
                case R.id.starttimeButton:
                    showTimePicker("starttimeView");
                    break;
                case R.id.endtimeButton:
                    showTimePicker("endtimeView");
                    break;
                default:
                    break;
            }
        }
    }
    public void showTimePicker(String textname){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int viewId = getResources().getIdentifier(textname, "id", getPackageName());
        final TextView timeText = (TextView)findViewById(viewId);
        TimePickerDialog dialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        timeText.setText(String.format("%02d:%02d", hourOfDay,minute));
                    }
                },
                hour,minute,true);
        dialog.show();
    }
}
