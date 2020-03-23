package com.example.giovanni.giovanni.viewpagertablayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.giovanni.giovanni.R;

public class FragmentCalcolatrice extends Fragment {

    private TextView tParziale;
    private TextView tFinale;
    private TextView tMemory;

    private String expression;
    private String first;
    private String second;
    private double result;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calcolatrice, container, false);

        tParziale = view.findViewById(R.id.textParziale);
        tFinale = view.findViewById(R.id.textFinale);
        tMemory = view.findViewById(R.id.textMemory);

        view.findViewById(R.id.button0).setOnClickListener(v -> {
            expression = tParziale.getText().toString();
            if (!tParziale.getText().toString().equals("")) {
                expression = expression + "0";
                tParziale.setText(expression);
            } else {
                tParziale.setText("0");
                expression = tParziale.getText().toString();
            }
        });

        // ascoltatoreClick(b0)

        view.findViewById(R.id.button1).setOnClickListener(v -> {
            expression = tParziale.getText().toString();
            if (!tParziale.getText().toString().equals("")) {
                expression = expression + "1";
                tParziale.setText(expression);
            } else {
                tParziale.setText("1");
                expression = tParziale.getText().toString();
            }
        });

        view.findViewById(R.id.button2).setOnClickListener(v -> {
            expression = tParziale.getText().toString();
            if (!tParziale.getText().toString().equals("")) {
                expression = expression + "2";
                tParziale.setText(expression);
            } else {
                tParziale.setText("2");
                expression = tParziale.getText().toString();
            }
        });

        view.findViewById(R.id.button3).setOnClickListener(v -> {
            expression = tParziale.getText().toString();
            if (!tParziale.getText().toString().equals("")) {
                expression = expression + "3";
                tParziale.setText(expression);
            } else {
                tParziale.setText("3");
                expression = tParziale.getText().toString();
            }
        });

        view.findViewById(R.id.button4).setOnClickListener(v -> {
            expression = tParziale.getText().toString();
            if (!tParziale.getText().toString().equals("")) {
                expression = expression + "4";
                tParziale.setText(expression);
            } else {
                tParziale.setText("4");
                expression = tParziale.getText().toString();
            }
        });

        view.findViewById(R.id.button5).setOnClickListener(v -> {
            expression = tParziale.getText().toString();
            if (!tParziale.getText().toString().equals("")) {
                expression = expression + "5";
                tParziale.setText(expression);
            } else {
                tParziale.setText("5");
                expression = tParziale.getText().toString();
            }
        });

        view.findViewById(R.id.button6).setOnClickListener(v -> {
            expression = tParziale.getText().toString();
            if (!tParziale.getText().toString().equals("")) {
                expression = expression + "6";
                tParziale.setText(expression);
            } else {
                tParziale.setText("6");
                expression = tParziale.getText().toString();
            }
        });

        view.findViewById(R.id.button7).setOnClickListener(v -> {
            expression = tParziale.getText().toString();
            if (!tParziale.getText().toString().equals("")) {
                expression = expression + "7";
                tParziale.setText(expression);
            } else {
                tParziale.setText("7");
                expression = tParziale.getText().toString();
            }
        });

        view.findViewById(R.id.button8).setOnClickListener(v -> {
            expression = tParziale.getText().toString();
            if (!tParziale.getText().toString().equals("")) {
                expression = expression + "8";
                tParziale.setText(expression);
            } else {
                tParziale.setText("8");
                expression = tParziale.getText().toString();
            }
        });

        view.findViewById(R.id.button9).setOnClickListener(v -> {
            expression = tParziale.getText().toString();
            if (!tParziale.getText().toString().equals("")) {
                expression = expression + "9";
                tParziale.setText(expression);
            } else {
                tParziale.setText("9");
                expression = tParziale.getText().toString();
            }
        });

        view.findViewById(R.id.buttonPiu).setOnClickListener(v -> {
            expression = tParziale.getText().toString();
            if (!tParziale.getText().toString().equals("")) {
                if (!expression.contains("+") && !expression.contains("-") &&
                        !expression.contains("*") && !expression.contains("/")) {
                    expression = expression + "+";
                    tParziale.setText(expression);
                }
            } else {
                expression = tParziale.getText().toString();
            }
        });

        view.findViewById(R.id.buttonMeno).setOnClickListener(v -> {
            expression = tParziale.getText().toString();
            if (!tParziale.getText().toString().equals("")) {
                if (!expression.contains("+") && !expression.contains("-") &&
                        !expression.contains("*") && !expression.contains("/")) {
                    expression = expression + "-";
                    tParziale.setText(expression);
                }
            } else {
                expression = tParziale.getText().toString();
            }
        });

        view.findViewById(R.id.buttonPer).setOnClickListener(v -> {
            expression = tParziale.getText().toString();
            if (!tParziale.getText().toString().equals("")) {
                if (!expression.contains("+") && !expression.contains("-") &&
                        !expression.contains("*") && !expression.contains("/")) {
                    expression = expression + "*";
                    tParziale.setText(expression);
                }
            } else {
                expression = tParziale.getText().toString();
            }
        });

        view.findViewById(R.id.buttonDiviso).setOnClickListener(v -> {
            expression = tParziale.getText().toString();
            if (!tParziale.getText().toString().equals("")) {
                if (!expression.contains("+") && !expression.contains("-") &&
                        !expression.contains("*") && !expression.contains("/")) {
                    expression = expression + "/";
                    tParziale.setText(expression);
                }
            } else {
                expression = tParziale.getText().toString();
            }
        });

        view.findViewById(R.id.buttonPunto).setOnClickListener(v -> {
            expression = tParziale.getText().toString();
            if (!tParziale.getText().toString().equals("")) {
                if (!expression.contains("+") || !expression.contains("-") || !expression.contains("*") || !expression.contains("/")) {
                    expression = expression + ".";
                    tParziale.setText(expression);
                }
            } else {
                expression = tParziale.getText().toString();
            }
        });

        view.findViewById(R.id.buttonC).setOnClickListener(v -> {
            tParziale.setText("");
            tFinale.setText("");
            expression = "";
        });

        view.findViewById(R.id.buttonMC).setOnClickListener(v -> tMemory.setText(" "));

        view.findViewById(R.id.buttonMS).setOnClickListener(v -> tMemory.setText(tFinale.getText().toString()));

        view.findViewById(R.id.buttonMR).setOnClickListener(v -> {
            if (!tMemory.getText().toString().equals(" ")) {
                String parziale = tParziale.getText().toString() + tMemory.getText().toString();
                tParziale.setText(parziale);
                expression = tParziale.getText().toString();
            }
        });

        view.findViewById(R.id.buttonUguale).setOnClickListener(v -> {
            tParziale.setText("");

            if (expression.contains("+")) {
                first = expression.substring(0, expression.indexOf("+"));
                second = expression.substring(expression.indexOf("+") + 1);
                result = Double.parseDouble(first) + Double.parseDouble(second);
                expression = String.valueOf(result);
            }
            if (expression.contains("-")) {
                first = expression.substring(0, expression.indexOf("-"));
                second = expression.substring(expression.indexOf("-") + 1);
                result = Double.parseDouble(first) - Double.parseDouble(second);
                expression = String.valueOf(result);
            }
            if (expression.contains("*")) {
                first = expression.substring(0, expression.indexOf("*"));
                second = expression.substring(expression.indexOf("*") + 1);
                result = Double.parseDouble(first) * Double.parseDouble(second);
                expression = String.valueOf(result);
            }
            if (expression.contains("/")) {
                first = expression.substring(0, expression.indexOf("/"));
                second = expression.substring(expression.indexOf("/") + 1);
                result = Double.parseDouble(first) / Double.parseDouble(second);
                expression = String.valueOf(result);
            }

            tFinale.setText(expression);
        });

        return view;
    }
}