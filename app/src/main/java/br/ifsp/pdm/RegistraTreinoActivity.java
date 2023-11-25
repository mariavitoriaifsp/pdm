package br.ifsp.pdm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistraTreinoActivity extends AppCompatActivity {

    private EditText txtNome;
    private EditText txtDesc;
    private EditText txtSexo;
    private EditText txtIdade;
    private EditText txtPeso;
    private RadioGroup radioGroup;
    private RadioButton radSeg;
    private RadioButton radTer;
    private RadioButton radQua;
    private RadioButton radQui;
    private RadioButton radSex;
    private RadioButton radSab;


    private Button btnCadastra;
    private Button btnCancela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user_form);

        txtNome = findViewById(R.id.txtNome);
        txtDesc = findViewById(R.id.txtDesc);
        txtSexo = findViewById(R.id.txtSexo);
        txtIdade = findViewById(R.id.txtIdade);
        txtPeso = findViewById(R.id.txtPeso);

        radioGroup = findViewById(R.id.radioGroup);
        radSeg = findViewById(R.id.radSeg);
        radTer = findViewById(R.id.radTer);
        radQua = findViewById(R.id.radQua);
        radQui = findViewById(R.id.radQui);
        radSex = findViewById(R.id.radSex);
        radSab = findViewById(R.id.radSab);



        btnCadastra = findViewById(R.id.btnCadastra);
        btnCancela = findViewById(R.id.btnCancela);

        btnCadastra.setOnClickListener(new escutadoBtnCadastra());
        btnCancela.setOnClickListener(new escutadoBtnCancela());
    }

    public class escutadoBtnCadastra implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent i = new Intent();
            String nome = txtNome.getText().toString().trim();
            String peso = txtPeso.getText().toString().trim();
            String idade = txtIdade.getText().toString().trim();
            String sexo = txtSexo.getText().toString().trim();
            String desc = txtDesc.getText().toString().trim();

            if (nome.equals("") | peso.equals("") | idade.equals("") | sexo.equals("") | desc.equals("")) {
                Toast.makeText(RegistraTreinoActivity.this, "Formulário incompleto, preencha todos os campos e tente novamente.", Toast.LENGTH_LONG).show();
            } else {
                i.putExtra("nome", nome);
                i.putExtra("peso", peso);
                i.putExtra("idade", idade);
                i.putExtra("sexo", sexo);
                i.putExtra("desc", desc);


                int radSelect = radioGroup.getCheckedRadioButtonId();

                if (radSelect != -1) {
                    if (radSelect == R.id.radSeg) {
                        i.putExtra("dia", "Segunda");
                    } else if (radSelect == R.id.radTer) {
                        i.putExtra("dia", "Terça");
                    } else if (radSelect == R.id.radQua) {
                        i.putExtra("dia", "Quarta");
                    } else if (radSelect == R.id.radQui) {
                        i.putExtra("dia", "Quinta");
                    } else if (radSelect == R.id.radSex) {
                        i.putExtra("dia", "Sexta");
                    } else if (radSelect == R.id.radSab) {
                        i.putExtra("dia", "Sabado");
                    }
                    setResult(RESULT_OK, i);
                    finish();
                } else {
                    Toast.makeText(RegistraTreinoActivity.this, "Formulário incompleto, Selecione um dia da semana.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    public class escutadoBtnCancela implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent i = new Intent();
            setResult(RESULT_CANCELED, i);
            finish();
        }
    }
}
