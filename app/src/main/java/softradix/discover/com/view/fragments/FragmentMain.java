package softradix.discover.com.view.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import softradix.discover.com.R;
import softradix.discover.com.adapters.AdapterMain;
import softradix.discover.com.interfaces.OnCategoryClick;
import softradix.discover.com.view.activities.ActivitySlider;

/**
 * Created by softradix on 06/11/17.
 */

public class FragmentMain extends Fragment implements OnCategoryClick {

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

        // Making notification bar transparent
        /*if (Build.VERSION.SDK_INT >= 21) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }*/

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, view);

        adapterMain = new AdapterMain(getActivity(), this);
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

    @Override
    public void onCategoryClick() {
        /*FragmentSlider fragmentSlider = FragmentSlider.newInstance();
        FragmentTransaction fts = getActivity().getSupportFragmentManager().beginTransaction();
        fts.add(R.id.fragmentHolder, fragmentSlider);
        fts.addToBackStack(fragmentSlider.getClass().getSimpleName());
        fts.commit();*/
        startActivity(new Intent(getActivity(), ActivitySlider.class));
    }
}
