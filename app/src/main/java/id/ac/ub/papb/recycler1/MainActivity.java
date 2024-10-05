package id.ac.ub.papb.recycler1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv1;
    public static String TAG = "RV1";
    EditText input_NIM,input_nama;
    Button button_simpan;
    ArrayList<Mahasiswa> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv1 = findViewById(R.id.rv1);

        input_NIM = findViewById(R.id.input_Nim);
        input_nama = findViewById(R.id.input_nama);
        button_simpan = findViewById(R.id.button_simpan);

        data = getData();

        MahasiswaAdapter adapter = new MahasiswaAdapter(this, data);
        rv1.setAdapter(adapter);
        rv1.setLayoutManager(new LinearLayoutManager(this));

        button_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nim = input_NIM.getText().toString();
                String nama = input_nama.getText().toString();

                // Validasi input
                if (nim.isEmpty() || nama.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Harap isi NIM dan Nama", Toast.LENGTH_SHORT).show();
                } else {
                    // Tambahkan data baru ke ArrayList
                    Mahasiswa mahasiswaBaru = new Mahasiswa();
                    mahasiswaBaru.nim = nim;
                    mahasiswaBaru.nama = nama;

                    data.add(mahasiswaBaru);

                    // Beritahu adapter bahwa ada item baru
                    adapter.notifyItemInserted(data.size() - 1);

                    // Kosongkan input fields
                    input_NIM.setText("");
                    input_nama.setText("");

                    // Scroll RecyclerView ke posisi terakhir
                    rv1.scrollToPosition(data.size() - 1);
                }
            }
        });
    }

    public ArrayList getData() {
        ArrayList<Mahasiswa> data = new ArrayList<>();
        List<String> nim = Arrays.asList(getResources().getStringArray(R.array.nim));
        List<String> nama = Arrays.asList(getResources().getStringArray(R.array.nama));
        for (int i = 0; i < nim.size(); i++) {
            Mahasiswa mhs = new Mahasiswa();
            mhs.nim = nim.get(i);
            mhs.nama = nama.get(i);
            Log.d(TAG,"getData "+mhs.nim);
            data.add(mhs);
        }
        return data;
    }
}
//import android.os.Bundle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.ArrayList;
//
//public class MainActivity extends AppCompatActivity {
//
//    RecyclerView recyclerView;
//    ContactAdapter adapter;
//    ArrayList<Contact> contacts;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        recyclerView = findViewById(R.id.myRecyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        contacts = new ArrayList<>();
//        contacts.add(new Contact("John Doe", "+62-812345678", "john@example.com", ""));
//        contacts.add(new Contact("Jane Doe", "+62-812987654", "jane@example.com", ""));
//
//        adapter = new ContactAdapter(this, contacts);
//        recyclerView.setAdapter(adapter);
//    }
//}