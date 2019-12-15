package com.example.giovanni.giovanni.offset;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giovanni.giovanni.R;

public class OffsetActivity extends AppCompatActivity {

    public AppBarLayout appBarLayout;
    public ViewPager viewPager;
    public TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offset);
        setBodyText();

        appBarLayout = findViewById(R.id.appBarLayout);

        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(new OffsetAdapter(getSupportFragmentManager()));

        tabLayout = findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(viewPager);

        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (verticalOffset == 0) {
                Toast.makeText(getApplicationContext(), "offset = 0", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressWarnings("deprecation")
    private void setBodyText() {
        TextView textView = findViewById(R.id.body_text);
        String text = "La nebbia a gl\'irti colli<br/>piovigginando sale,<br/>e sotto il maestrale<br/>" +
                "urla e biancheggia il mar;<br/><br/>ma per le vie del borgo<br/>dal ribollir de\' tini<br/>" +
                "va l\'aspro odor de i vini<br/>l\'anime a rallegrar.<br/><br/>Gira su\' ceppi accesi<br/>" +
                "lo spiedo scoppiettando:<br/>sta il cacciator fischiando<br/>su l\'uscio a rimirar<br/><br/>" +
                "tra le rossastre nubi<br/>stormi d\'uccelli neri,<br/>com\'esuli pensieri,<br/>nel vespero migrar.";
        textView.setText(Html.fromHtml(text));
    }
}