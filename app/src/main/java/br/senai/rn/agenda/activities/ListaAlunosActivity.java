package br.senai.rn.agenda.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import br.senai.rn.activities.R;
import br.senai.rn.agenda.daos.AlunoDAO;
import br.senai.rn.agenda.models.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    private final String TITULO_APPBAR = "Lista de Alunos";
    private ListView listaAlunos;
    private FloatingActionButton botaoAdicionar;
    private ArrayAdapter<Aluno> adapter;
    private AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        inicializarComponentes();
        definirEventos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear();
        adapter.addAll(dao.obterTodos());
    }

    private void inicializarComponentes() {
        dao = new AlunoDAO();
        listaAlunos = findViewById(R.id.activity_lista_alunos_lista);
        botaoAdicionar = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listaAlunos.setAdapter(adapter);

        registerForContextMenu(listaAlunos);

        dao.salvar(new Aluno("José", "112233", "jose@gmail"));
        dao.salvar(new Aluno("João", "445566", "joao@hotmail"));
        dao.salvar(new Aluno("Pedro", "778899", "pedro@yahoo"));
    }



    private void definirEventos() {
        botaoAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
                startActivity(intent);
            }
        });
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoSelecionado = (Aluno) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
                intent.putExtra("aluno", alunoSelecionado);
                startActivity(intent);
            }
        });
//        listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Aluno alunoSelecionado = (Aluno) parent.getItemAtPosition(position);
//                dao.remover(alunoSelecionado);
//                Toast.makeText(ListaAlunosActivity.this, alunoSelecionado + " foi removido", Toast.LENGTH_SHORT).show();
//                adapter.remove(alunoSelecionado);
//                return true;
//            }
//        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_aluno_menu, menu);
        menu.add("Deletar ");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int menuSelecionado = item.getItemId();

        if (menuSelecionado == R.id.activity_lista_alunos_menu_remover){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Aluno alunoSelecionado = adapter.getItem(menuInfo.position);
        dao.remover(alunoSelecionado);
        adapter.remove(alunoSelecionado);
        Toast.makeText(ListaAlunosActivity.this, alunoSelecionado + " foi removido", Toast.LENGTH_SHORT).show();
        }

        return super.onContextItemSelected(item);
    }
}
