package com.example.repaircalculate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class SetPricesActivity extends AppCompatActivity {

    private EditText flatCosmeticPriceEditText;
    private EditText flatCapitalPriceEditText;
    private EditText flatRoomPriceEditText;
    private EditText houseCosmeticPriceEditText;
    private EditText houseCapitalPriceEditText;
    private EditText houseRoomPriceEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_prices);

        flatCosmeticPriceEditText = findViewById(R.id.flat_cosmetic_price_edit_text);
        flatCapitalPriceEditText = findViewById(R.id.flat_capital_price_edit_text);
        flatRoomPriceEditText = findViewById(R.id.flat_room_price_edit_text);
        houseCosmeticPriceEditText = findViewById(R.id.house_cosmetic_price_edit_text);
        houseCapitalPriceEditText = findViewById(R.id.house_capital_price_edit_text);
        houseRoomPriceEditText = findViewById(R.id.house_room_price_edit_text);

        // Отображаем текущие цены
        flatCosmeticPriceEditText.setText(String.format(Locale.getDefault(), "%.2f", Prices.getFlatCosmeticPrice()));
        flatCapitalPriceEditText.setText(String.format(Locale.getDefault(), "%.2f", Prices.getFlatCapitalPrice()));
        flatRoomPriceEditText.setText(String.format(Locale.getDefault(), "%.2f", Prices.getFlatRoomPrice()));
        houseCosmeticPriceEditText.setText(String.format(Locale.getDefault(), "%.2f", Prices.getHouseCosmeticPrice()));
        houseCapitalPriceEditText.setText(String.format(Locale.getDefault(), "%.2f", Prices.getHouseCapitalPrice()));
        houseRoomPriceEditText.setText(String.format(Locale.getDefault(), "%.2f", Prices.getHouseRoomPrice()));

        Button savePricesButton = findViewById(R.id.save_prices_button);
        savePricesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePrices();
            }
        });
    }

    private void savePrices() {
        // Сохраняем цены в SharedPreferences
        SharedPreferences preferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat("flat_cosmetic_price", Float.parseFloat(flatCosmeticPriceEditText.getText().toString().replace(",", ".")));
        editor.putFloat("flat_capital_price", Float.parseFloat(flatCapitalPriceEditText.getText().toString().replace(",", ".")));
        editor.putFloat("flat_room_price", Float.parseFloat(flatRoomPriceEditText.getText().toString().replace(",", ".")));
        editor.putFloat("house_cosmetic_price", Float.parseFloat(houseCosmeticPriceEditText.getText().toString().replace(",", ".")));
        editor.putFloat("house_capital_price", Float.parseFloat(houseCapitalPriceEditText.getText().toString().replace(",", ".")));
        editor.putFloat("house_room_price", Float.parseFloat(houseRoomPriceEditText.getText().toString().replace(",", ".")));
        editor.apply();

        // Обновляем цены в классе Prices
        Prices.setFlatCosmeticPrice(Float.parseFloat(flatCosmeticPriceEditText.getText().toString().replace(",", ".")));
        Prices.setFlatCapitalPrice(Float.parseFloat(flatCapitalPriceEditText.getText().toString().replace(",", ".")));
        Prices.setFlatRoomPrice(Float.parseFloat(flatRoomPriceEditText.getText().toString().replace(",", ".")));
        Prices.setHouseCosmeticPrice(Float.parseFloat(houseCosmeticPriceEditText.getText().toString().replace(",", ".")));
        Prices.setHouseCapitalPrice(Float.parseFloat(houseCapitalPriceEditText.getText().toString().replace(",", ".")));
        Prices.setHouseRoomPrice(Float.parseFloat(houseRoomPriceEditText.getText().toString().replace(",", ".")));

        // Показываем уведомление об успешном сохранении
        Toast.makeText(this, "Цены успешно сохранены", Toast.LENGTH_SHORT).show();

        // Обновляем отображение цен
        flatCosmeticPriceEditText.setText(String.format(Locale.getDefault(), "%.2f", Prices.getFlatCosmeticPrice()));
        flatCapitalPriceEditText.setText(String.format(Locale.getDefault(), "%.2f", Prices.getFlatCapitalPrice()));
        flatRoomPriceEditText.setText(String.format(Locale.getDefault(), "%.2f", Prices.getFlatRoomPrice()));
        houseCosmeticPriceEditText.setText(String.format(Locale.getDefault(), "%.2f", Prices.getHouseCosmeticPrice()));
        houseCapitalPriceEditText.setText(String.format(Locale.getDefault(), "%.2f", Prices.getHouseCapitalPrice()));
        houseRoomPriceEditText.setText(String.format(Locale.getDefault(), "%.2f", Prices.getHouseRoomPrice()));
    }
}