package br.com.jusuzuki.carango.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import br.com.jusuzuki.carango.R;
import br.com.jusuzuki.carango.adapter.CarroListaAdapter;
import br.com.jusuzuki.carango.api.CarroAPI;
import br.com.jusuzuki.carango.fragment.utils.ConstantesUtils;
import br.com.jusuzuki.carango.listener.OnclickListener;
import br.com.jusuzuki.carango.model.Carro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */



public class CarrosFragment extends Fragment {

    private RecyclerView recyclerView;
    private CarroListaAdapter carroListaAdapter;
    private String tipoCarros;

    @Override
    public void onCreate (@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            this.tipoCarros = getArguments().getString(ConstantesUtils.CHAVE_TIPO);
        }

    }
    public CarrosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_carros, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager((getActivity())));
        recyclerView.setHasFixedSize(true);
        return view;
    }

    @Override
    public void onViewCreated (View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        carregaDados();
    }
    private void carregaDados(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesUtils.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CarroAPI api = retrofit.create(CarroAPI.class);
        Call<List<Carro>> call = api.buscarCarros(tipoCarros);
        call.enqueue(new Callback<List<Carro>>() {
            @Override
            public void onResponse(Call<List<Carro>> call, Response<List<Carro>> response) {
                carroListaAdapter =  new CarroListaAdapter(getContext(),response.body(),cliqueNaLista);
                recyclerView.setAdapter(carroListaAdapter);
            }

            @Override
            public void onFailure(Call<List<Carro>> call, Throwable t) {
                Toast.makeText(getContext(),"Ops! Deu ruim! tente novamente mais tarde!", Toast.LENGTH_LONG).show();

            }
        });
    }
    private OnclickListener cliqueNaLista = new OnclickListener() {
        @Override
        public void onClick(View v, int position) {
            Carro carroClicado = carroListaAdapter.getItem(position);
            Toast.makeText(getContext(),
                    carroClicado.getNome(),
                    Toast.LENGTH_SHORT).show();
        }
    };
}
