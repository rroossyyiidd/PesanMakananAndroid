package com.rosyid.pesanmakananandroid;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputData extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dataHelper;
    Button btnSimpan;
    String edit;
    EditText nama, makanan, notelp, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        nama = (EditText) findViewById(R.id.inputNama);
        makanan = (EditText) findViewById(R.id.inputMakanan);
        notelp = (EditText) findViewById(R.id.inputNotelp);
        alamat = (EditText) findViewById(R.id.inputAlamat);

        btnSimpan = (Button) findViewById(R.id.buttonSimpan);

        //databaseHelper
        dataHelper = new DataHelper(this);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dataHelper.getWritableDatabase();

                edit = nama.getText().toString();
                edit = makanan.getText().toString();
                edit = notelp.getText().toString();
                edit = alamat.getText().toString();

                if (edit.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Kolom tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    db.execSQL("insert into biodata('pelanggan', 'makanan', 'nomor', 'alamat') values('" +
                            nama.getText().toString() + "','" + makanan.getText().toString() + "','" + notelp.getText().toString() + "','" + alamat.getText().toString() + "')");

                    Toast.makeText(getApplicationContext(), "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    finish();
                }
                DataPesanan.da.RefreshList();
            }
        });
    }
}
