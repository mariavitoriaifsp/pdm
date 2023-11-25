package br.ifsp.pdm;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference BD = FirebaseDatabase.getInstance().getReference();

    private Button btnAdd;
    private ListView lstTreinos;
    private ActivityResultLauncher<Intent> startActivityForResultLauncher;
    private AdapterTreinos adapter;
    private ArrayList<Treino> treinos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity", "onCreate: Running");

        startActivityForResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    handleResult(result.getData());
                }
        );

        treinos = new ArrayList<>();
        adapter = new AdapterTreinos(this, treinos);
        lstTreinos = findViewById(R.id.lstTreinos);

        lstTreinos.setAdapter(adapter);

        DatabaseReference treinosRef = BD.child("treinos");
        treinosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                treinos.clear();

                for (DataSnapshot treinoSnapshot : snapshot.getChildren()) {
                    Treino treino = treinoSnapshot.getValue(Treino.class);
                    treinos.add(treino);
                }


                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error
                Log.e("Firebase", "Error getting data", error.toException());
            }
        });

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener( new EscutadorBotao());

    }

    private void handleResult(Intent data) {

        String nome = data.getStringExtra("nome");
        String desc = data.getStringExtra("desc");
        String peso = data.getStringExtra("peso");
        String idade = data.getStringExtra("idade");
        String sexo = data.getStringExtra("sexo");
        String dia = data.getStringExtra("dia");

        Treino novoTreino = new Treino(nome, desc, peso, idade, sexo, dia);
        DatabaseReference treinosRef = BD.child("treinos");
        String treinoId = treinosRef.push().getKey();
        treinosRef.child(treinoId).setValue(novoTreino);

        Log.d("MainActivity", "handleResult: New Treino added to Firebase");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("MainActivity", "handleResult: Handling result");

        if (resultCode == RESULT_OK) {
            handleResult(data);
        }
    }
    protected class EscutadorBotao implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(MainActivity.this, RegistraTreinoActivity.class);
            startActivityForResultLauncher.launch(i);
        }
    }
}