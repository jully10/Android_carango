package br.com.jusuzuki.carango.adapter;

import android.content.Context;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.jusuzuki.carango.R;
import br.com.jusuzuki.carango.fragment.utils.ConstantesUtils;
import br.com.jusuzuki.carango.listener.OnclickListener;
import br.com.jusuzuki.carango.model.Carro;

/**
 * Created by logonrm on 08/04/2017.
 */

public class CarroListaAdapter extends RecyclerView.Adapter<CarroListaAdapter.CarroViewHolder> {

    private final List <Carro> carros ;
    private final Context context ;
    private OnclickListener onclickListener;

    // construtor
    public CarroListaAdapter (Context context, List<Carro> carros, OnclickListener onclickListener){
        this.carros =  carros;
        this.context = context;
        this.onclickListener = onclickListener;

    }


    @Override
    public CarroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater. from (context).inflate(R.layout.item_carro, parent, false ) ;
        return new CarroViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(final CarroViewHolder holder, final int position) {

        Carro carro = carros.get(position);
        holder.txt_nome_carro.setText(carro.getNome());
        holder.txt_desc_carro.setText(carro.getDescricao());
        Picasso.with(context)
                .load(ConstantesUtils.URL_FOTO + carro.getUrlImagem())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.img_carro);
        if(onclickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onclickListener.onClick(holder.itemView,position);
                }
            });
        }
    }

    public  Carro getItem (int position){
        return carros.get(position);
    }
    @Override
    public int getItemCount() {
        return carros.size();
    }

    public static class CarroViewHolder extends RecyclerView.ViewHolder{

        public ImageView img_carro;
        public TextView txt_nome_carro;
        public TextView txt_desc_carro;

       public CarroViewHolder (View itemView){
           super(itemView);

           img_carro = (ImageView)itemView.findViewById(R.id.img_carro);
           txt_nome_carro = (TextView)itemView.findViewById(R.id.txt_nome_carro);
           txt_desc_carro = (TextView)itemView.findViewById(R.id.txt_desc_carro);

       }


    }

}
