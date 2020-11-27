package com.example.symwe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivityOnboarding extends AppCompatActivity {


    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicators;
    private MaterialButton buttonOnboardingAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_onboarding);

        layoutOnboardingIndicators = findViewById(R.id.layoutOnboardingIndicators);
        buttonOnboardingAction = findViewById(R.id.buttonOnboardingAction);


        setupOnboardingItems();

        ViewPager2 onboardingViewPager = findViewById(R.id.onboardingViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);

        setupOnboardingIndicators();
        setCurrentOnboardingIndicator(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });

        buttonOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()) {
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
                }else {
                    startActivity(new Intent(getApplicationContext(), HomePage.class));
                    finish();
                }
            }
        });
    }

    private void setupOnboardingItems(){
        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem itemWelcomeSymwe = new OnboardingItem();
        itemWelcomeSymwe.setTitle("Welcome to Symwe");
        itemWelcomeSymwe.setDescription("The Place to share your favorite music.");
        itemWelcomeSymwe.setImage(R.drawable.group);

        OnboardingItem itemMusicscreen = new OnboardingItem();
        itemMusicscreen.setTitle("Create Groups");
        itemMusicscreen.setDescription("Groups help you to get everything in your designer order.");
        itemMusicscreen.setImage(R.drawable.music);

        OnboardingItem itemSharescreen = new OnboardingItem();
        itemSharescreen.setTitle("Native Share");
        itemSharescreen.setDescription("Share music directly from your favorite sources on Symwe");
        itemSharescreen.setImage(R.drawable.share);


        onboardingItems.add(itemWelcomeSymwe);
        onboardingItems.add(itemMusicscreen);
        onboardingItems.add(itemSharescreen);

        onboardingAdapter = new OnboardingAdapter(onboardingItems);

    }

    private void setupOnboardingIndicators(){
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );

        layoutParams.setMargins(8, 0 , 8, 0);
        for (int i = 0; i < indicators.length; i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicators.addView(indicators[i]);
        }
    }

    private void setCurrentOnboardingIndicator(int index){
        int childCount = layoutOnboardingIndicators.getChildCount();
        for (int i = 0; i< childCount; i++){
            ImageView imageView = (ImageView) layoutOnboardingIndicators.getChildAt(i);
            if (i== index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_active)
                );
            }
            else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_inactive)
                );

            }
        }

        if (index == onboardingAdapter.getItemCount() - 1) {
            buttonOnboardingAction.setText("Finish");
        }else if(index == onboardingAdapter.getItemCount() - 2) {
            buttonOnboardingAction.setText("Sound Nice");
        }else {
            buttonOnboardingAction.setText("Go on");
        }

    }



}