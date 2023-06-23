package com.vimalcvs.localization;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vimalcvs.localization.databinding.ActivityMainBinding;
import com.vimalcvs.localization.databinding.DialogLanguageBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.dialogButton.setOnClickListener(v -> switchLanguage());
    }


    public void switchLanguage() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this, R.style.Localization_Dialog);
        builder.setCancelable(true);

        DialogLanguageBinding binding = DialogLanguageBinding.inflate(LayoutInflater.from(this));
        builder.setView(binding.getRoot());

        TextView select = binding.tvSelect;
        TextView cancel = binding.tvCancel;
        RecyclerView recyclerView = binding.recyclerView;
        View up = binding.viewUp;
        View down = binding.viewDown;

        ArrayList<LangModel> language = new ArrayList<>();
        language.add(new LangModel(Constant.LANG_ID_DEFAULT, Constant.LANG_NAME_DEFAULT, Constant.LANG_CODE_DEFAULT));
        language.add(new LangModel(Constant.LANG_ID_ENGLISH, Constant.LANG_NAME_ENGLISH, Constant.LANG_CODE_ENGLISH));
        language.add(new LangModel(Constant.LANG_ID_HINDI, Constant.LANG_NAME_HINDI, Constant.LANG_CODE_HINDI));
        language.add(new LangModel(Constant.LANG_ID_GUJARATI, Constant.LANG_NAME_GUJARATI, Constant.LANG_CODE_GUJARATI));
        language.add(new LangModel(Constant.LANG_ID_PUNJABI, Constant.LANG_NAME_PUNJABI, Constant.LANG_CODE_PUNJABI));
        language.add(new LangModel(Constant.LANG_ID_MALAYALAM, Constant.LANG_NAME_MALAYALAM, Constant.LANG_CODE_MALAYALAM));
        language.add(new LangModel(Constant.LANG_ID_TAMIL, Constant.LANG_NAME_TAMIL, Constant.LANG_CODE_TAMIL));
        language.add(new LangModel(Constant.LANG_ID_TELUGU, Constant.LANG_NAME_TELUGU, Constant.LANG_CODE_TELUGU));
        language.add(new LangModel(Constant.LANG_ID_KANNADA, Constant.LANG_NAME_KANNADA, Constant.LANG_CODE_KANNADA));
        language.add(new LangModel(Constant.LANG_ID_ODIA, Constant.LANG_NAME_ODIA, Constant.LANG_CODE_ODIA));
        language.add(new LangModel(Constant.LANG_ID_BENGALI, Constant.LANG_NAME_BENGALI, Constant.LANG_CODE_BENGALI));
        language.add(new LangModel(Constant.LANG_ID_ASSAMESE, Constant.LANG_NAME_ASSAMESE, Constant.LANG_CODE_ASSAMESE));
        language.add(new LangModel(Constant.LANG_ID_MAARTHI, Constant.LANG_NAME_MAARTHI, Constant.LANG_CODE_MAARTHI));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LanguageAdapter mAdapter = new LanguageAdapter(language);
        recyclerView.setAdapter(mAdapter);

        RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean isAtTop = !recyclerView.canScrollVertically(-1);
                boolean isAtBottom = !recyclerView.canScrollVertically(1);

                up.setVisibility(isAtBottom ? View.VISIBLE : View.GONE);
                down.setVisibility(isAtTop ? View.VISIBLE : View.GONE);
            }
        };
        recyclerView.addOnScrollListener(scrollListener);


        String currentLanguage = LocaleHelper.getLanguage(MainActivity.this);
        int initialSelection = Constant.LANG_ID_DEFAULT;
        if (currentLanguage.equalsIgnoreCase(Constant.LANG_CODE_ENGLISH)) {
            initialSelection = Constant.LANG_ID_ENGLISH;
        } else if (currentLanguage.equalsIgnoreCase(Constant.LANG_CODE_HINDI)) {
            initialSelection = Constant.LANG_ID_HINDI;
        } else if (currentLanguage.equalsIgnoreCase(Constant.LANG_CODE_GUJARATI)) {
            initialSelection = Constant.LANG_ID_GUJARATI;
        } else if (currentLanguage.equalsIgnoreCase(Constant.LANG_CODE_PUNJABI)) {
            initialSelection = Constant.LANG_ID_PUNJABI;
        } else if (currentLanguage.equalsIgnoreCase(Constant.LANG_CODE_MALAYALAM)) {
            initialSelection = Constant.LANG_ID_MALAYALAM;
        } else if (currentLanguage.equalsIgnoreCase(Constant.LANG_CODE_TAMIL)) {
            initialSelection = Constant.LANG_ID_TAMIL;
        } else if (currentLanguage.equalsIgnoreCase(Constant.LANG_CODE_TELUGU)) {
            initialSelection = Constant.LANG_ID_TELUGU;
        } else if (currentLanguage.equalsIgnoreCase(Constant.LANG_CODE_KANNADA)) {
            initialSelection = Constant.LANG_ID_KANNADA;
        } else if (currentLanguage.equalsIgnoreCase(Constant.LANG_CODE_ODIA)) {
            initialSelection = Constant.LANG_ID_ODIA;
        } else if (currentLanguage.equalsIgnoreCase(Constant.LANG_CODE_BENGALI)) {
            initialSelection = Constant.LANG_ID_BENGALI;
        } else if (currentLanguage.equalsIgnoreCase(Constant.LANG_CODE_ASSAMESE)) {
            initialSelection = Constant.LANG_ID_ASSAMESE;
        } else if (currentLanguage.equalsIgnoreCase(Constant.LANG_CODE_MAARTHI)) {
            initialSelection = Constant.LANG_ID_MAARTHI;
        }

        mAdapter.setSelectedPosition(initialSelection);
        mAdapter.setOnItemClickListener((langModel, position) -> mAdapter.setSelectedPosition(position));

        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        select.setOnClickListener(ok -> {
            int selectedPosition = mAdapter.getSelectedPosition();
            if (selectedPosition != -1) {
                LangModel selectedLang = language.get(selectedPosition);
                LocaleHelper.setLocale(MainActivity.this, selectedLang.code);
                recreate();
            }
            dialog.dismiss();
        });
        cancel.setOnClickListener(cancels -> dialog.dismiss());
        dialog.show();
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(LocaleHelper.onAttach(context));
    }
}

