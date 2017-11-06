package softradix.discover.com.view.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.Bind;
import butterknife.ButterKnife;
import softradix.discover.com.R;

/**
 * Created by softradix on 06/11/17.
 */

public class FragmentSlider extends Fragment {

    public static FragmentSlider newInstance() {
        FragmentSlider fragmentSlider = new FragmentSlider();
        return fragmentSlider;
    }

    private MyViewPagerAdapter myViewPagerAdapter;
    private TextView[] dots;
    int[] myImageList = new int[]{R.drawable.sliding_image1, R.drawable.sliding_image2, R.drawable.sliding_image3,
            R.drawable.sliding_image4, R.drawable.sliding_image5, R.drawable.sliding_image6, R.drawable.sliding_image7,
            R.drawable.sliding_image8, R.drawable.sliding_image9, R.drawable.sliding_image10
            , R.drawable.sliding_image11, R.drawable.sliding_image12, R.drawable.sliding_image13,
            R.drawable.sliding_image14, R.drawable.sliding_image15, R.drawable.sliding_image16};

    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.layoutDots)
    LinearLayout dotsLayout;
    @Bind(R.id.btn_skip)
    Button btnSkip;
    @Bind(R.id.btn_next)
    Button btnNext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        View view = inflater.inflate(R.layout.fragment_slider, container, false);

        ButterKnife.bind(this, view);

        // adding bottom dots
        addBottomDots(0);

        // making notification bar transparent
        changeStatusBarColor();

        myViewPagerAdapter = new MyViewPagerAdapter(getActivity());
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(+1);
                if (current < myImageList.length) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {

                }
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[myImageList.length];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(getActivity());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(ContextCompat.getColor(getActivity(), android.R.color.transparent));
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(ContextCompat.getColor(getActivity(), android.R.color.transparent));
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }


    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == myImageList.length - 1) {
                // last page. make button text to GOT IT
                btnNext.setText(getString(R.string.start));
                btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        Context context;

        public MyViewPagerAdapter(Context context) {
            this.context = context;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.layout_sliding, container, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.sliding_image);
            ImageView ivCrossIcon = (ImageView) view.findViewById(R.id.iv_cross_icon);

            Glide.with(context).load("").placeholder(myImageList[position])
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade()
                    .into(imageView);

            ivCrossIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackClick();
                }
            });

            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return myImageList.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    void onBackClick(){
        FragmentTransaction fts = getActivity().getSupportFragmentManager().beginTransaction();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 1) {
            fragmentManager.popBackStackImmediate();
            fts.commit();
        } else {
            getActivity().finish();
        }
    }
}