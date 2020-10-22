package com.tiagogameover.speedtraffic;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tiagogameover.speedtraffic.banco.DataAdapter;
import com.tiagogameover.speedtraffic.banco.DatabaseHandler;
import com.tiagogameover.speedtraffic.banco.ListDataModel;

import java.util.ArrayList;
import java.util.List;


@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class View_Historico extends Fragment  {
    private ListView listView;
    private ArrayList<ListDataModel> listdatamodel;
    private DataAdapter adapter;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.historico, container, false);
        listView = (ListView) v.findViewById(R.id.lista);
        listdatamodel = new ArrayList<>();

        DatabaseHandler db = new DatabaseHandler(getActivity());

        List<ListDataModel> users = db.getAllUsers();

        for (ListDataModel data : users) {
            ListDataModel item = new ListDataModel();
            item.setName(data.getName());
            item.setPhone(data.getPhone());
            item.setUpload(data.getUpload());
            item.setData(data.getData());
            listdatamodel.add(item);

        }

        adapter = new DataAdapter(listdatamodel, getActivity());
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {

                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        listdatamodel.remove(position);
                        adapter.notifyDataSetChanged();
                        return false;
                    }
                });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ListDataModel item = (ListDataModel) adapter.getItem(position);
                Snackbar.make(view, "Enviar Historico", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                // cria um intent
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                // define o email especifico pre definido
                String[] recipients = new String[] {
                        ""
                };
                // insere o email no extra
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
                // define um assunto
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Minha Velocidade");
                // define o conteúdo do email
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,"Minha Velocidade na "+item.getData()+"\n"+ item.getName()+"\n"+ item.getPhone()+"\n"+item.getUpload());
                // definido o tipo
                emailIntent.setType("text/plain");
                // inicia o intent
                startActivity(Intent.createChooser(emailIntent, "Enviar email..."));

            }
        });



        return v;
    }

}
