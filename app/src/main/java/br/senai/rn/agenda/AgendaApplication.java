package br.senai.rn.agenda;

import android.app.Application;

import br.senai.rn.agenda.daos.AlunoDAO;
import br.senai.rn.agenda.models.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criarAlunosDeTeste();
    }

    private void criarAlunosDeTeste() {
        AlunoDAO dao = new AlunoDAO();
        dao.salvar(new Aluno("José", "112233", "jose@gmail"));
        dao.salvar(new Aluno("João", "445566", "joao@hotmail"));
        dao.salvar(new Aluno("Pedro", "778899", "pedro@yahoo"));
    }

}
