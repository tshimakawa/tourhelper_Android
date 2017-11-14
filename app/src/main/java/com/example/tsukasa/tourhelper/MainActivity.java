package com.example.tsukasa.tourhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import static com.example.tsukasa.tourhelper.R.id.textView;

public class MainActivity extends AppCompatActivity {

    private String[] destList = {"観光地","美術館","遊園地","動物園","水族館","神社","寺"};
    private String destination = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner dest_spinner = (Spinner)findViewById(R.id.placeList);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,destList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dest_spinner.setSelection(1);

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
    }
}
