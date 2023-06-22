package com.example.repaircalculate;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

public class MainActivityTwo extends AppCompatActivity {

    private RadioButton flatRadioButton, houseRadioButton, cosmeticRadioButton, capitalRadioButton;
    private EditText areaEditText, roomsEditText;
    private Button calculateButton;
    private TextView resultTextView;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);

        flatRadioButton = findViewById(R.id.flatRadioButton);
        houseRadioButton = findViewById(R.id.houseRadioButton);
        cosmeticRadioButton = findViewById(R.id.cosmeticRadioButton);
        capitalRadioButton = findViewById(R.id.capitalRadioButton);
        areaEditText = findViewById(R.id.areaEditText);
        roomsEditText = findViewById(R.id.roomsEditText);
        calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);

        // Получаем объект SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получаем выбранный тип помещения
                boolean isFlat = flatRadioButton.isChecked();
                // Получаем выбранный тип ремонта
                boolean isCosmetic = cosmeticRadioButton.isChecked();
                // Получаем площадь помещения
                String areaStr = areaEditText.getText().toString();
                double area = Double.parseDouble(areaStr.isEmpty() ? "0" : areaStr);
                // Получаем количество комнат
                String roomsStr = roomsEditText.getText().toString();
                int rooms = Integer.parseInt(roomsStr.isEmpty() ? "0" : roomsStr);

                // Проверяем корректность введенных данных
                if (area <= 0) {
                    Toast.makeText(getApplicationContext(), "Ошибка: Некорректное значение площади помещения", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!flatRadioButton.isChecked() && !houseRadioButton.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Ошибка: Некорректный тип помещения", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!cosmeticRadioButton.isChecked() && !capitalRadioButton.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Ошибка: Некорректный тип ремонта", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rooms < 1) {
                    Toast.makeText(getApplicationContext(), "Ошибка: Некорректное значение количества комнат", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Рассчитываем стоимость ремонта
                double squareMeterPrice = 0;
                if (isFlat) {
                    if (isCosmetic) {
                        squareMeterPrice = Prices.getFlatCosmeticPrice();
                    } else {
                        squareMeterPrice = Prices.getFlatCapitalPrice();
                    }
                } else {
                    if (isCosmetic) {
                        squareMeterPrice = Prices.getHouseCosmeticPrice();
                    } else {
                        squareMeterPrice = Prices.getHouseCapitalPrice();
                    }
                }

                double roomPrice = 0;
                if (isFlat) {
                    roomPrice = Prices.getFlatRoomPrice();
                } else {
                    roomPrice = Prices.getHouseRoomPrice();
                }

                double cost = area * squareMeterPrice + rooms * roomPrice;

                // Генерируем уникальный ключ для сохранения результата в SharedPreferences
                String key = UUID.randomUUID().toString();

                // Сохраняем результат в SharedPreferences с уникальным ключом
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putFloat(key, (float) cost);
                editor.apply();

                // Отображаем результат
                resultTextView.setText(String.format("Стоимость ремонта: %.2f рублей", cost));
            }
        });
    }
}