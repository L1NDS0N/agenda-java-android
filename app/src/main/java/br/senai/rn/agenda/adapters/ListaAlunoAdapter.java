package br.senai.rn.agenda.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.senai.rn.agenda.R;
import br.senai.rn.agenda.models.Aluno;

public class ListaAlunoAdapter extends BaseAdapter {

    private List<Aluno> alunos;
    private Context context;

    public ListaAlunoAdapter(Context context) {
        this.context = context;
        this.alunos = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = criarView(parent);
        Aluno alunoEncontrado = getItem(position);
        vincularElementos(viewCriada, alunoEncontrado);
        return viewCriada;
    }

    private void vincularElementos(View view, Aluno aluno) {
        TextView nome = view.findViewById(R.id.item_aluno_nome);
        TextView telefone = view.findViewById(R.id.item_aluno_telefone);
        nome.setText(aluno.getNome());
        telefone.setText(aluno.getTelefone());
    }

    private View criarView(ViewGroup parent) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_aluno, parent, false);
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }

    public void update(List<Aluno> alunos) {
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

}
