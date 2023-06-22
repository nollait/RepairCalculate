package com.example.repaircalculate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Map;

public class SavedResultsActivity extends AppCompatActivity {

    private ListView resultsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_results);

        resultsListView = findViewById(R.id.resultsListView);

        // Получаем объект SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        // Получаем все сохраненные результаты
        Map<String, ?> allEntries = sharedPreferences.getAll();
        ArrayList<String> resultsList = new ArrayList<>();

        // Проходимся по всем сохраненным результатам и добавляем их в список
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String result = "Стоимость ремонта: " + entry.getValue() + " рублей";
            resultsList.add(result);
        }

        // Отображаем список результатов в ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, resultsList);
        resultsListView.setAdapter(adapter);
    }
}