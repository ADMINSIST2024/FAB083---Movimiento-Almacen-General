package com.example.fab083.ui.progres;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.view.Window;

import com.example.fab083.R;

public class ProgressDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar el layout con el ProgressBar
        View view = inflater.inflate(R.layout.fragment_progress_dialog, container, false);
        view.setBackgroundColor(Color.TRANSPARENT);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        progressBar.setIndeterminate(true);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Hacer que el diálogo sea no cancelable para bloquear la pantalla
        if (getDialog() != null) {
            getDialog().setCanceledOnTouchOutside(false);
            getDialog().setCancelable(false);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // Quitar título del diálogo
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }
}