package br.senai.rn.agenda.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import br.senai.rn.agenda.R;
import br.senai.rn.agenda.daos.AlunoDAO;
import br.senai.rn.agenda.models.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private final String TITULO_APPBAR = "Novo Aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private Aluno aluno;
    private AlunoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno_formulario);
        setTitle(TITULO_APPBAR);
        inicializarComponentes();
        inicializarAluno();
        definirEventos();
    }

    private void inicializarComponentes() {
        campoNome = findViewById(R.id.activity_aluno_formulario_et_nome);
        campoTelefone = findViewById(R.id.activity_aluno_formulario_et_telefone);
        campoEmail = findViewById(R.id.activity_aluno_formulario_et_email);
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

    private void definirEventos() {}

    private void criarAluno() {
        aluno.setNome(campoNome.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setEmail(campoEmail.getText().toString());
    }

    private void salvarAluno() {
        dao.salvar(aluno);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_aluno_formulario_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuSelecionado = item.getItemId();

        switch (menuSelecionado) {
            case R.id.activity_aluno_formulario_menu_salvar:
                criarAluno();
                salvarAluno();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
