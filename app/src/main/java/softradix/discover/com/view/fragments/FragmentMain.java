package softradix.discover.com.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import softradix.discover.com.R;
import softradix.discover.com.adapters.AdapterMain;

/**
 * Created by softradix on 06/11/17.
 */

public class FragmentMain extends Fragment {

    public static FragmentMain newInstance() {
        FragmentMain fragmentMain = new FragmentMain();
        return fragmentMain;
    }

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    AdapterMain adapterMain;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, view);

        adapterMain = new AdapterMain(getActivity());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterMain);
        recyclerView.setHasFixedSize(true);
        //Layout manager for Recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
