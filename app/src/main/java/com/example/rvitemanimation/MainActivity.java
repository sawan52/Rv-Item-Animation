package com.example.rvitemanimation;

import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView newsRecyclerView;
    NewsAdapter newsAdapter;
    List<NewsItem> mData;
    FloatingActionButton fabSwitcher;
    boolean isDark = false;
    ConstraintLayout rootLayout;
    EditText searchInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // lets make this activity on fullscreen

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        // hide the action bar

        getSupportActionBar().hide();

        // initialize all fields

        fabSwitcher = findViewById(R.id.fab_switcher);
        rootLayout = findViewById(R.id.root_layout);
        searchInput = findViewById(R.id.search_input);

        newsRecyclerView = findViewById(R.id.news_recycler_view);
        mData = new ArrayList<>();

        // load theme state so that when user again open the app he/she get their previous saved state...

        isDark = getThemeStatePref();
        if (isDark) {
            // dark theme is on

            searchInput.setBackgroundResource(R.drawable.search_input_dark_style);
            rootLayout.setBackgroundColor(getResources().getColor(R.color.black));
        } else {
            // light theme is on

            searchInput.setBackgroundResource(R.drawable.search_input_style);
            rootLayout.setBackgroundColor(getResources().getColor(R.color.white));
        }

        // this is the dummy data

        mData.add(new NewsItem("Rafael Hoekstra", "Once upon a time, there was an enterprise named Goldilocks. She visited a tent in the woods in search of the perfect blockchain porridge- one that was both fast.", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Pierluigi Paganini", "Security experts at Group-IB have detected the activity of Gustuff a mobile Android Trojan, which includes potential targets of customers in leading international banks.", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Eleni Digalaki", "This is an excerpt from a story delivered exclusively to Business Insider Intelligence Fintech Briefing subscribers. To receive the full story plus.", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Georgi Georgiev", "The Bitcoin price volatility in March is approaching its lowest levels on record, which could precede the next prolonged bull run, says one analyst.", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Samantha Chang", "Russian billionaire Vladimir Potanin wants to launch cryptocurrency tokens pegged to the precious metal palladium. Potanin also wants to establish a regulatory framework.", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Yogita Khatri", "Switzerland’s finance watchdog has found that the cryptocurrency mining firm Envion AG, which raised millions through an initial coin offering (ICO).", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Rafael Hoekstra", "Once upon a time, there was an enterprise named Goldilocks. She visited a house in the woods in search of the perfect blockchain porridge- one that was both fast.", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Pierluigi Paganini", "Security experts at Group-IB have detected the activity of Gustuff a mobile Android Trojan, which includes potential targets of customers in leading international banks.", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Eleni Digalaki", "This is an excerpt from a story delivered exclusively to Business Insider Intelligence Fintech Briefing subscribers. To receive the full story plus.", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Georgi Georgiev", "The Bitcoin price volatility in March is approaching its lowest levels on record, which could precede the next prolonged bull run, says one analyst.", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Samantha Chang", "Russian billionaire Vladimir Potanin wants to launch cryptocurrency tokens pegged to the precious metal palladium. Potanin also wants to establish a regulatory framework.", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Yogita Khatri", "Switzerland’s finance watchdog has found that the cryptocurrency mining firm Envion AG, which raised millions through an initial coin offering (ICO).", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Rafael Hoekstra", "Once upon a time, there was an enterprise named Goldilocks. She visited a house in the woods in search of the perfect blockchain porridge- one that was both fast.", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Pierluigi Paganini", "Security experts at Group-IB have detected the activity of Gustuff a mobile Android Trojan, which includes potential targets of customers in leading international banks.", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Eleni Digalaki", "This is an excerpt from a story delivered exclusively to Business Insider Intelligence Fintech Briefing subscribers. To receive the full story plus.", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Georgi Georgiev", "The Bitcoin price volatility in March is approaching its lowest levels on record, which could precede the next prolonged bull run, says one analyst.", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Samantha Chang", "Russian billionaire Vladimir Potanin wants to launch cryptocurrency tokens pegged to the precious metal palladium. Potanin also wants to establish a regulatory framework.", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Yogita Khatri", "Switzerland’s finance watchdog has found that the cryptocurrency mining firm Envion AG, which raised millions through an initial coin offering (ICO).", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Rafael Hoekstra", "Once upon a time, there was an enterprise named Goldilocks. She visited a house in the woods in search of the perfect blockchain porridge- one that was both fast.", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Pierluigi Paganini", "Security experts at Group-IB have detected the activity of Gustuff a mobile Android Trojan, which includes potential targets of customers in leading international banks.", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Eleni Digalaki", "This is an excerpt from a story delivered exclusively to Business Insider Intelligence Fintech Briefing subscribers. To receive the full story plus.", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Georgi Georgiev", "The Bitcoin price volatility in March is approaching its lowest levels on record, which could precede the next prolonged bull run, says one analyst.", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Samantha Chang", "Russian billionaire Vladimir Potanin wants to launch cryptocurrency tokens pegged to the precious metal palladium. Potanin also wants to establish a regulatory framework.", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Yogita Khatri", "Switzerland’s finance watchdog has found that the cryptocurrency mining firm Envion AG, which raised millions through an initial coin offering (ICO).", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Rafael Hoekstra", "Once upon a time, there was an enterprise named Goldilocks. She visited a house in the woods in search of the perfect blockchain porridge- one that was both fast.", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Pierluigi Paganini", "Security experts at Group-IB have detected the activity of Gustuff a mobile Android Trojan, which includes potential targets of customers in leading international banks.", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Eleni Digalaki", "This is an excerpt from a story delivered exclusively to Business Insider Intelligence Fintech Briefing subscribers. To receive the full story plus.", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Georgi Georgiev", "The Bitcoin price volatility in March is approaching its lowest levels on record, which could precede the next prolonged bull run, says one analyst.", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Samantha Chang", "Russian billionaire Vladimir Potanin wants to launch cryptocurrency tokens pegged to the precious metal palladium. Potanin also wants to establish a regulatory framework.", "2019-03-28", R.drawable.userphoto));
        mData.add(new NewsItem("Yogita Khatri", "Switzerland’s finance watchdog has found that the cryptocurrency mining firm Envion AG, which raised millions through an initial coin offering (ICO).", "2019-03-28", R.drawable.userphoto));


        newsAdapter = new NewsAdapter(this, mData, isDark);
        newsRecyclerView.setAdapter(newsAdapter);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        // floating action for enabling Dark Mode
        fabSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isDark = !isDark;
                if (isDark) {

                    // set the rootLayout( ConstraintLayout in activity_main.xml file ) in black color...
                    searchInput.setBackgroundResource(R.drawable.search_input_dark_style);
                    rootLayout.setBackgroundColor(getResources().getColor(R.color.black));
                } else {

                    searchInput.setBackgroundResource(R.drawable.search_input_style);
                    rootLayout.setBackgroundColor(getResources().getColor(R.color.white));
                }

                // now set the each container of an Adapter to also in Dark Mode
                newsAdapter = new NewsAdapter(getApplicationContext(), mData, isDark);
                newsRecyclerView.setAdapter(newsAdapter);
                saveThemeStatePref(isDark);

            }
        });

        // added a listener on searchInput EditText
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                newsAdapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    // now save the preferred state of the app, so that when user again open the app they can see their previous state
    private void saveThemeStatePref(boolean isDark) {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isDark", isDark);
        editor.commit();

    }


    private boolean getThemeStatePref() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPref", MODE_PRIVATE);
        boolean isDark = pref.getBoolean("isDark", false);
        return isDark;

    }

}
