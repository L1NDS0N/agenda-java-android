package br.senai.rn.agenda.daos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.senai.rn.agenda.models.Aluno;

public class AlunoDAO {

    private static Long contadorDeIds = 1L;
    private static List<Aluno> alunos = new ArrayList<>();

    public void salvar(Aluno aluno) {
        if (aluno.getId() == null) {
            aluno.setId(contadorDeIds);
            alunos.add(aluno);
            contadorDeIds++;
        } else {
            int posicao = alunos.indexOf(aluno);
            alunos.set(posicao, aluno);
        }
    }

    public List<Aluno> obterTodos() {
        List<Aluno> copiaAlunos = new ArrayList<>(alunos);
        Collections.sort(copiaAlunos);
        return copiaAlunos;
    }

    public void remover(Aluno aluno) {
        alunos.remove(aluno);
    }

}
