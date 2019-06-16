package com.example.giovanni.giovanni.recyclerviewcheckbox;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.pojo.Persona;
import java.util.ArrayList;
import java.util.List;

public class MaxCheckBoxSearchFragment extends Fragment implements MaxCheckBoxSearchAdapter.OnItemViewClicked {

    private View view;
    private RelativeLayout content;
    private SwitchCompat switchCompat;
    private EditText editSearch;
    private ImageView closeAction;
    private RecyclerView recyclerView;
    private Button button;
    private TextView noResult;

    private List<Persona> list;
    private List<Persona> filtered;
    private List<Persona> checkedList;
    private MaxCheckBoxSearchAdapter adapter;

    private String sentence;

    public static int THRESHOLD = 3;
    private boolean isOpen = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_search_checkbox_max, container, false);
        content = view.findViewById(R.id.content);
        switchCompat = view.findViewById(R.id.switch_compat);
        editSearch = view.findViewById(R.id.edittext_search);
        closeAction = view.findViewById(R.id.close_action);
        recyclerView = view.findViewById(R.id.search_checkbox_recyclerview);
        button = view.findViewById(R.id.button_enabled);
        noResult = view.findViewById(R.id.no_result);

        list = init();
        checkedList = new ArrayList<>();
        adapter = new MaxCheckBoxSearchAdapter(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.setList(list);
        adapter.notifyDataSetChanged();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        switchCompat.setOnClickListener(view1 -> {

            closeAction.setVisibility(View.GONE);
            editSearch.setText("");
            startAnimation();

            if (switchCompat.isChecked()) {
                view.findViewById(R.id.edittext_search).requestFocus();
                recyclerView.setVisibility(View.VISIBLE);
            } else {
                view.findViewById(R.id.edittext_search).clearFocus();
                recyclerView.setVisibility(View.GONE);
            }
        });

        editSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (editSearch.getText().length() > 0) {
                    closeAction.setVisibility(View.VISIBLE);
                    filter();
                } else if (editSearch.getText().length() == 0) {
                    closeAction.setVisibility(View.GONE);
                    resetList();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        closeAction.setOnClickListener(v -> {
            editSearch.setText("");
            resetList();
        });

        button.setOnClickListener(v -> resetList());
    }

    @Override
    public boolean onItemClicked(Persona persona, boolean isChecked) {

        if (isChecked && checkedList.size() < THRESHOLD) {
            checkedList.add(persona);
            button.setEnabled(checkedList.size() > 0 && checkedList.size() <= THRESHOLD);
            return true;
        } else if (!isChecked) {
            checkedList.remove(persona);
            button.setEnabled(checkedList.size() > 0 && checkedList.size() <= THRESHOLD);
            return true;
        } else if (checkedList.size() == THRESHOLD) {
            Toast.makeText(getContext(), "Seleziona al massimo 3 elementi.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void filter() {

        // editSearch.clearFocus();
        if (sentence != null && sentence.equalsIgnoreCase(editSearch.getText().toString()))
            return;
        sentence = editSearch.getText().toString();
        if (sentence.length() == 0) {
            sentence = null;
            clearFilteredList();
            noResult.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            adapter.setList(list);
            adapter.notifyDataSetChanged();
            return;
        }
        clearFilteredList();
        if (list == null || list.size() == 0) {
            recyclerView.setVisibility(View.GONE);
            noResult.setVisibility(View.VISIBLE);
            noResult.setText(R.string.no_list);
            return;
        }
        for (Persona persona : list) {
            if (persona == null)
                continue;
            if ((persona.getMsisdn() != null && persona.getMsisdn().toLowerCase().contains(sentence.toLowerCase()))
                    || (persona.getNome() != null && persona.getNome().toLowerCase().contains(sentence.toLowerCase()))) {
                filtered.add(persona);
            }
        }
        if (filtered == null || filtered.size() == 0) {
            recyclerView.setVisibility(View.GONE);
            noResult.setVisibility(View.VISIBLE);
        } else {
            noResult.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            adapter.setList(filtered);
            adapter.notifyDataSetChanged();
        }
    }

    private void clearFilteredList() {
        if (filtered == null)
            filtered = new ArrayList<>();
        else
            filtered.clear();
    }

    private void resetList() {
        checkedList = new ArrayList<>();
        list = init();
        adapter.setList(list);
        adapter.notifyDataSetChanged();
        button.setEnabled(false);
        noResult.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void startAnimation() {

        long TRANSITION_TIME = 275;

        if (!isOpen) {

            int centerX = (int) switchCompat.getX() + switchCompat.getWidth()/2;
            int centerY = (int) switchCompat.getY() + switchCompat.getHeight()/2;
            int startRadius = 0;
            int endRadius = (int) Math.hypot((double) view.findViewById(R.id.container).getWidth(),
                                            (double) view.findViewById(R.id.container).getHeight());

            Animator anim = ViewAnimationUtils.createCircularReveal(content, centerX, centerY, (float) startRadius, (float) endRadius);
            anim.setDuration(TRANSITION_TIME);
            anim.start();

            content.setVisibility(View.VISIBLE);
            isOpen = true;
        } else {
            int centerX = (int) switchCompat.getX() + switchCompat.getWidth()/2;
            int centerY = (int) switchCompat.getY() + switchCompat.getHeight()/2;
            int startRadius = Math.max(view.findViewById(R.id.container).getWidth(),
                                        view.findViewById(R.id.container).getHeight());
            int endRadius = 0;

            Animator anim = ViewAnimationUtils.createCircularReveal(content, centerX, centerY, (float) startRadius, (float) endRadius);

            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {}

                @Override
                public void onAnimationEnd(Animator animator) {
                    content.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {}

                @Override
                public void onAnimationRepeat(Animator animator) {}
            });

            anim.setDuration(TRANSITION_TIME);
            anim.start();

            isOpen = false;
        }
    }

    public List<Persona> init() {

        List<Persona> list = new ArrayList<>();
        list.add(new Persona("Giovanni", "Petito", "3331582355", false));
        list.add(new Persona("Raffaele", "Petito", "3802689011", false));
        list.add(new Persona("Teresa", "Petito", "3343540536", false));
        list.add(new Persona("Salvatore", "Pragliola", "3384672609", false));
        list.add(new Persona("Angelina", "Basile", "3334392578", false));
        list.add(new Persona("Vincenzo", "Petito", "3666872262", false));
        list.add(new Persona("Giovanni", "Basile", "3884723340", false));
        list.add(new Persona("Marco", "Basile", "3892148853", false));
        list.add(new Persona("Antonio", "D'Ascia", "3315605694", false));
        list.add(new Persona("Giovanni", "D'Ascia", "3331711437", false));
        list.add(new Persona("Mariano", "Pinto", "3397016728", false));
        list.add(new Persona("Pasquale", "Amato", "3665917760", false));
        list.add(new Persona("Francesco", "Mongiello", "3299376402", false));
        list.add(new Persona("Gianluigi", "Santillo", "3386124867", false));
        list.add(new Persona("Daniele", "Musacchia", "3494977374", false));

        return list;
    }
}