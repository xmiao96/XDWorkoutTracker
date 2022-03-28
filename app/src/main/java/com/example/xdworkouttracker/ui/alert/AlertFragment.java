package com.example.xdworkouttracker.ui.alert;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.xdworkouttracker.databinding.FragmentAlertBinding;

public class AlertFragment extends Fragment {

    private FragmentAlertBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AlertViewModel dashboardViewModel =
                new ViewModelProvider(this).get(AlertViewModel.class);

        binding = FragmentAlertBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAlert;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}