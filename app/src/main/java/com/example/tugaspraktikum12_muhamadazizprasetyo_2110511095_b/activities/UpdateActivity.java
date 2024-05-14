package com.example.tugaspraktikum12_muhamadazizprasetyo_2110511095_b.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tugaspraktikum12_muhamadazizprasetyo_2110511095_b.R;
import com.example.tugaspraktikum12_muhamadazizprasetyo_2110511095_b.db.DBHelper;
import com.example.tugaspraktikum12_muhamadazizprasetyo_2110511095_b.model.Member;

public class UpdateActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private EditText etName, etNim, etEmail, etGroupTo, etAppName;
    private Button btnSave;
    private Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);

        dbHelper = new DBHelper(this);

        etName = findViewById(R.id.edt_name);
        etNim = findViewById(R.id.edt_nim);
        etEmail = findViewById(R.id.edt_email);
        etGroupTo = findViewById(R.id.edt_group_to);
        etAppName = findViewById(R.id.edt_app_name);
        btnSave = findViewById(R.id.btn_submit);

        Intent intent = getIntent();
        member =  (Member) intent.getSerializableExtra("user");

        etName.setText(member.getName());
        etNim.setText(member.getNim());
        etEmail.setText(member.getEmail());
        etGroupTo.setText(member.getGroupTo());
        etAppName.setText(member.getAppName());

        btnSave.setOnClickListener((View v) -> {
            dbHelper.updateUser(member.getId(), etName.getText().toString(), etNim.getText().toString(), etEmail.getText().toString(), etGroupTo.getText().toString(), etAppName.getText().toString());
            Toast.makeText(UpdateActivity.this, "Updated berhasil!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}