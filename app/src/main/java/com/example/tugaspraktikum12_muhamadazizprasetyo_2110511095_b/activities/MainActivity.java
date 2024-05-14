package com.example.tugaspraktikum12_muhamadazizprasetyo_2110511095_b.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tugaspraktikum12_muhamadazizprasetyo_2110511095_b.R;
import com.example.tugaspraktikum12_muhamadazizprasetyo_2110511095_b.db.DBHelper;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private EditText etName, etNim, etEmail, etGroupTo, etAppName;
    private Button btnSave, btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        etName = findViewById(R.id.edt_name);
        etNim = findViewById(R.id.edt_nim);
        etEmail = findViewById(R.id.edt_email);
        etGroupTo = findViewById(R.id.edt_group_to);
        etAppName = findViewById(R.id.edt_app_name);
        btnSave = findViewById(R.id.btn_submit);
        btnList = findViewById(R.id.btn_list);

        btnSave.setOnClickListener(v -> {
            if (etName.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Error: Nama langkap harus diisi!", Toast.LENGTH_SHORT).show();
            } else if (etNim.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Error: NIM harus diisi!", Toast.LENGTH_SHORT).show();
            } else if (etEmail.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Error: Email harus diisi!", Toast.LENGTH_SHORT).show();
            } else if (etGroupTo.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Error: Kelompok harus diisi!", Toast.LENGTH_SHORT).show();
            } else if (etAppName.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Error: Nama aplikasi harus diisi!", Toast.LENGTH_SHORT).show();
            } else {
                dbHelper.addUserDetail(etName.getText().toString(), etNim.getText().toString(), etEmail.getText().toString(), etGroupTo.getText().toString(), etAppName.getText().toString());
                etName.setText("");
                etNim.setText("");
                etEmail.setText("");
                etGroupTo.setText("");
                etAppName.setText("");
                Toast.makeText(MainActivity.this, "Simpan berhasil!", Toast.LENGTH_SHORT).show();
            }
        });

        btnList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListMembersActivity.class);
            startActivity(intent);
        });
    }
}