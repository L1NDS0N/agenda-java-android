package br.senai.rn.agenda.daos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.senai.rn.agenda.models.Aluno;

public class AlunoDAO implements Serializable {

    private static List<Aluno> alunos = new ArrayList<>(Arrays.asList(new Aluno("Lindson", "986326467", "l1nds0n@hotmail.com")));


    public void salvar(Aluno aluno) {
        alunos.add(aluno);
    }

    public List<Aluno> obterTodos() {
        return new ArrayList<>(alunos);
    }


}
