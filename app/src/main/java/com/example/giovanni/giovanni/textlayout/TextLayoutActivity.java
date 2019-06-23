package com.example.giovanni.giovanni.textlayout;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.giovanni.giovanni.R;

public class TextLayoutActivity extends AppCompatActivity {

    private TextView tHello;
    private TextView tComeStai;
    private EditText eNome;
    private String nome;

    private EditText eNumero;
    private TextView tNumero;
    private String numero;

    private RelativeLayout relativeVariable;
    private float density;

    private ImageView bart;
    private RelativeLayout homerContainer;
    private RelativeLayout bartContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_layout);

        tHello = findViewById(R.id.text_hello);
        tComeStai = findViewById(R.id.text_come_stai);
        eNome = findViewById(R.id.edit_nome);
        Button bInvioNome = findViewById(R.id.button_invio_nome);

        TextView longText = findViewById(R.id.long_text);
        longText.setText(R.string.long_text);
        longText.setAllCaps(true); // Il metodo setAllCaps() rende maiuscolo il contenuto di una TextView.

        eNumero = findViewById(R.id.edit_numero);
        tNumero = findViewById(R.id.text_numero);
        Button bInvioNumero = findViewById(R.id.button_invio_numero);

        String helloWorld = getResources().getString(R.string.hello_world);
        String hellodUpper =
                helloWorld.substring(0, 1).toUpperCase() +
                helloWorld.substring(1, 6) +
                helloWorld.substring(6, 7).toUpperCase() +
                helloWorld.substring(7);

        tHello.setText(hellodUpper);
        tHello.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18); // setTextSize() setta la size programmaticamente.

        bInvioNome.setOnClickListener(v -> {
            nome = eNome.getText().toString();
            if(!nome.equals("")) {
                String hello = "CIAO " + nome.toUpperCase() + "!";
                String howAreYou = "come stai?";
                tHello.setText(hello);
                tComeStai.setText(howAreYou);
                eNome.setText("");
            }
        });

        bInvioNumero.setOnClickListener(v -> {
            numero = eNumero.getText().toString();
            tNumero.setText(formatCount(numero));
        });

        relativeVariable = findViewById(R.id.relative_variable);
        Button buttonSetHeight = findViewById(R.id.button_set_height);

        // Le due linee di codice successive settano programmaticamente l'altezza di una vista.
        density = getResources().getDisplayMetrics().density;
        relativeVariable.getLayoutParams().height = Math.round((float) 30 * density);

        buttonSetHeight.setOnClickListener(v ->
                relativeVariable.getLayoutParams().height = Math.round((float) 30 * density));

        LinearLayout linearMarginBottom = findViewById(R.id.linear_margin_bottom);
        LinearLayout linearMargin = findViewById(R.id.linear_margin);

        // Il codice seguente setta programmaticamente il marginBottom di un LinearLayout contenuto all'interno di un
        // RelativeLayout.
        // A causa della variazione della densità dei pixel dello schermo del dispositivo, è opportuno utilizzare
        // sempre l'unità DIP per impostare il margine a livello di codice:
        Resources resources1 = getResources();
        float pxMarginBottom = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, resources1.getDisplayMetrics());

        RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) linearMarginBottom.getLayoutParams();
        params1.bottomMargin = (int) pxMarginBottom;
        linearMarginBottom.setLayoutParams(params1);

        // Il codice seguente setta programmaticamente tutti i margini di un LinearLayout contenuto all'interno di un
        // RelativeLayout.
        Resources resources = getResources();
        float pxLeftMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, resources.getDisplayMetrics());
        float pxTopMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, resources.getDisplayMetrics());
        float pxRightMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, resources.getDisplayMetrics());
        float pxBottomMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, resources.getDisplayMetrics());

        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        params1.setMargins(Math.round(pxLeftMargin), Math.round(pxTopMargin), Math.round(pxRightMargin), Math.round(pxBottomMargin));
        linearMargin.setLayoutParams(params2);

        homerContainer = findViewById(R.id.homer_container);
        bartContainer = findViewById(R.id.bart_container);
        bart = findViewById(R.id.bart);
        Button buttonHomer = findViewById(R.id.button_homer);
        Button buttonBart = findViewById(R.id.button_bart);

        buttonHomer.setOnClickListener(v -> {
            homerContainer.setPivotX(0);
            homerContainer.animate().scaleX(0).setDuration(2000);
        });

        buttonBart.setOnClickListener(v -> {
            ScaleAnimation scaleAnimation = new ScaleAnimation((float)1.0, (float)1.0, (float)1.0, (float)0.0);
            scaleAnimation.setFillAfter(true);
            scaleAnimation.setDuration(2000);
            bart.startAnimation(scaleAnimation);
            bartContainer.startAnimation(scaleAnimation);
        });
    }

    private String formatCount(String string) {
        char[] array = string.toCharArray();
        String formatted = "";
        int index = 0;
        if(array.length > 0) {
            for(int i = array.length - 1; i >= 0; i --) {
                char c = array[i];
                formatted = c + formatted;
                index ++;
                if(index == 3) {
                    index = 0;
                    formatted = "." + formatted;
                }
            }
            if(formatted.startsWith(".")) {
                formatted = formatted.substring(1);
            }
        }
        return formatted;
    }
}