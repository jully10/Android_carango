package br.com.jusuzuki.carango.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import br.com.jusuzuki.carango.fragment.CarrosFragment;
import br.com.jusuzuki.carango.fragment.utils.ConstantesUtils;

/**
 * Created by logonrm on 08/04/2017.
 */

public class TabsAdapter extends FragmentStatePagerAdapter {

    public static final int TOTAL_ABAS = 2;
    public static final int POSICAO_ABA_CLASSICOS = 0;
    public static final int POSICAO_ABA_ESPORTIVO = 1;

     public TabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Bundle args = new Bundle();

        switch (position) {
            case POSICAO_ABA_CLASSICOS:
                args.putString(ConstantesUtils.CHAVE_TIPO, "classicos");
                break;
            case POSICAO_ABA_ESPORTIVO:
                args.putString(ConstantesUtils.CHAVE_TIPO, "esportivos");
                break;

        }
        Fragment fragment = new CarrosFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return TOTAL_ABAS;
    }
}
