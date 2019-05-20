package br.senai.rn.agenda.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.senai.rn.activities.R;
import br.senai.rn.agenda.daos.AlunoDAO;
import br.senai.rn.agenda.models.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private final String TITULO_APPBAR = "Novo Aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private Button botaoSalvar;
    private Aluno aluno;
    private AlunoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR);
        inicializarComponentes();
        inicializarAluno();
        definirEventos();
    }

    private void inicializarComponentes() {
        campoNome = findViewById(R.id.activity_formulario_aluno_et_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_et_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_et_email);
        botaoSalvar = findViewById(R.id.activity_formulario_aluno_bt_salvar);
    }

    private void inicializarAluno() {
        dao = new AlunoDAO();
        if (getIntent().hasExtra("aluno")) {
            aluno = (Aluno) getIntent().getSerializableExtra("aluno");
            campoNome.setText(aluno.getNome());
            campoTelefone.setText(aluno.getTelefone());
            campoEmail.setText(aluno.getEmail());
        } else {
            aluno = new Aluno();
        }
    }

    private void definirEventos() {
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarAluno();
                salvarAluno();
            }
        });
    }

    private void criarAluno() {
        aluno.setNome(campoNome.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setEmail(campoEmail.getText().toString());
    }

    private void salvarAluno() {
        dao.salvar(aluno);
        finish();
    }

}