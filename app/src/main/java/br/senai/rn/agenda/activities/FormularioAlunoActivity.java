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
    private AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR);
        inicializarComponentes();
        definirEventos();
    }

    private void inicializarComponentes() {
        campoNome = findViewById(R.id.activity_formulario_aluno_et_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_et_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_et_email);
        botaoSalvar = findViewById(R.id.activity_formulario_aluno_bt_salvar);

        Aluno aluno = (Aluno) getIntent().getSerializableExtra("aluno");
        if (aluno != null){
        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        campoEmail.setText(aluno.getEmail());
        }

    }

    private void definirEventos() {
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno alunoCriado = criarAluno();
                salvarAluno(alunoCriado);
            }
        });
    }

    private Aluno criarAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();
        return new Aluno(nome, telefone, email);
    }

    private void salvarAluno(Aluno aluno) {
        dao.salvar(aluno);
        finish();
    }

}
