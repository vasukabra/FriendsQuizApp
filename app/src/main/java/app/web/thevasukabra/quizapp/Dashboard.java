package app.web.thevasukabra.quizapp;

import static app.web.thevasukabra.quizapp.MainActivity.list;

import android.content.Intent;
import android.os.Bundle;

import android.util.TypedValue;
import android.view.View;

import android.widget.LinearLayout;

import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Dashboard extends AppCompatActivity {
    List<ModelClass> allqueslist;
    int index = 0;
    ModelClass modelClass;
    TextView card_question , optiona , optionb , optionc ,optiond;
    CardView cardoa, cardob, cardoc , cardod;
    LinearLayout nextBtn;
    TextView exit;
    public static int correctCount = 0;
    int wrongCount = 0;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Hooks();
        exit = findViewById(R.id.ic_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        allqueslist = list;
        Collections.shuffle(allqueslist);
        modelClass = list.get(index);
        resetcolor();
        setAllData();
        nextBtn.setClickable(false);
        setAds();

    }



    private void setAllData(){
        card_question.setText(modelClass.getQuestion());
        card_question.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f);
        optiona.setText(modelClass.getoA());
        optiona.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f);
        optionb.setText(modelClass.getoB());
        optionb.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f);
        optionc.setText(modelClass.getoC());
        optionc.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f);
        optiond.setText(modelClass.getoD());
        optiond.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f);
    }

    private void Hooks(){
        cardoa = findViewById(R.id.card_a);
        cardob = findViewById(R.id.card_b);
        cardoc = findViewById(R.id.cardc);
        cardod = findViewById(R.id.card_d);
        card_question = findViewById(R.id.card_question);
        optiona = findViewById(R.id.card_optiona);
        optionb = findViewById(R.id.card_optionb);
        optionc = findViewById(R.id.card_optionc);
        optiond = findViewById(R.id.card_optiond);
        nextBtn = findViewById(R.id.next_ll);



    }
    public void correct(CardView cardView){
        cardView.setCardBackgroundColor(getResources().getColor(R.color.green));
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correctCount++;
                enableButton();
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(Dashboard.this);
                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
                            if (index<list.size()-1) {
                                index++;
                                modelClass = list.get(index);
                                setAllData();
                                resetcolor();
                            }
                            else{
                                gameWon();
                            }
                            mInterstitialAd = null;
                            setAds();

                        }
                    });
                } else {
                    if (index<list.size()-1) {
                        index++;
                        modelClass = list.get(index);
                        setAllData();
                        resetcolor();
                    }
                    else{
                        gameWon();
                    }
                }


            }
        });

    }
     public void wrong(CardView cardView) {
         cardView.setCardBackgroundColor(getResources().getColor(R.color.red));
         nextBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 enableButton();
                 if (mInterstitialAd != null) {
                     mInterstitialAd.show(Dashboard.this);
                     mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                         @Override
                         public void onAdDismissedFullScreenContent() {
                             super.onAdDismissedFullScreenContent();
                             if (index < list.size() - 1) {
                                 index++;
                                 modelClass = list.get(index);
                                 setAllData();
                                 resetcolor();
                             } else {
                                 gameWon();
                             }
                             mInterstitialAd = null;
                             setAds();

                         }
                     });
                 } else {
                     if (index < list.size() - 1) {
                         index++;
                         modelClass = list.get(index);
                         setAllData();
                         resetcolor();
                     } else {
                         gameWon();
                     }
                 }


             }
         });
     }
    public void gameWon(){
        Intent intent = new Intent(Dashboard.this, Wonactivity.class);
        intent.putExtra("correct",correctCount);
        intent.putExtra("wrong", wrongCount);
        startActivity(intent);


    }
    public void optionAClick(View view){
        disbleButton();
        nextBtn.setClickable(true);
        if (modelClass.getoA().equals(modelClass.getAns())){
//            cardoa.setCardBackgroundColor(getResources().getColor(R.color.green));
            correct(cardoa);


//            if (index < list.size()-1){
//                correct(cardoa);
//            }
//            else{
//                gameWon();
//            }
            }

        else{
            wrong(cardoa);


        }
    }
    public void optionBClick(View view){
        disbleButton();
        nextBtn.setClickable(true);
        if (modelClass.getoB().equals(modelClass.getAns())){
//            cardoa.setCardBackgroundColor(getResources().getColor(R.color.green));
            correct(cardob);


//            if (index < list.size()-1){
//                correct(cardoa);
//            }
//            else{
//                gameWon();
//            }
        }

        else{
            wrong(cardob);


        }
    }
    public void optionCClick(View view){
        disbleButton();
        nextBtn.setClickable(true);
        if (modelClass.getoC().equals(modelClass.getAns())){
//            cardoa.setCardBackgroundColor(getResources().getColor(R.color.green));
            correct(cardoc);


//            if (index < list.size()-1){
//                correct(cardoa);
//            }
//            else{
//                gameWon();
//            }
        }

        else{
            wrong(cardoc);


        }
    }
    public void optionDClick(View view){
        disbleButton();
        nextBtn.setClickable(true);

        if (modelClass.getoD().equals(modelClass.getAns())){
//            cardoa.setCardBackgroundColor(getResources().getColor(R.color.green));
            correct(cardoa);

//            if (index < list.size()-1){
//                correct(cardoa);
//            }
//            else{
//                gameWon();
//            }
        }

        else{
            wrong(cardod);


        }
    }
    public void resetcolor(){
        cardoa.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardob.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardoc.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardod.setCardBackgroundColor(getResources().getColor(R.color.white));
    }
    public void enableButton(){
        cardoa.setClickable(true);
        cardob.setClickable(true);
        cardoc.setClickable(true);
        cardod.setClickable(true);
    }
    public void disbleButton(){
        cardoa.setClickable(false);
        cardob.setClickable(false);
        cardoc.setClickable(false);
        cardod.setClickable(false);
    }
    public void setAds(){
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,getString(R.string.adsunitid), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        mInterstitialAd = null;
                    }
                });
    }
}