package br.ifsp.pdm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterTreinos extends ArrayAdapter<Treino> {
    private ArrayList<Treino> treinos;
    private Context context;

    public AdapterTreinos(Context context, ArrayList<Treino> treinos) {
        super(context, R.layout.activity_treino_item, treinos);
        this.context = context;
        this.treinos = treinos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View itemView = li.inflate(R.layout.activity_treino_item, parent, false);
        TextView lblNome = itemView.findViewById(R.id.lblNome);
        TextView lblPeso = itemView.findViewById(R.id.lblPeso);
        TextView lblIdade = itemView.findViewById(R.id.lblIdade);
        TextView lblSexo = itemView.findViewById(R.id.lblSexo);
        TextView lblDia = itemView.findViewById(R.id.lblDia);
        TextView lblDesc = itemView.findViewById(R.id.lblDesc);

        lblNome.setText(treinos.get(position).getNome());
        lblPeso.setText(treinos.get(position).getPeso());
        lblIdade.setText(treinos.get(position).getIdade());
        lblSexo.setText(treinos.get(position).getSexo());
        lblDia.setText(treinos.get(position).getDia());
        lblDesc.setText(treinos.get(position).getDesc());

        return itemView;
    }
}