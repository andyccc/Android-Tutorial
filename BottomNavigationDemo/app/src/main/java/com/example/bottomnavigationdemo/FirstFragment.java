package com.example.bottomnavigationdemo;

import androidx.lifecycle.ViewModelProvider;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FirstFragment extends Fragment {

    private FirstViewModel mViewModel;
    private ImageView imageView;

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.first_fragment, container, false);
        imageView = view.findViewById(R.id.imageView);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 只有当前fragment 数据状态会保留
//        mViewModel = new ViewModelProvider(this).get(FirstViewModel.class);
        // 所有activity中保留 requireActivity()
        mViewModel = new ViewModelProvider(requireActivity()).get(FirstViewModel.class);
        // TODO: Use the ViewModel

        imageView.setRotation(mViewModel.rotationPosition);

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0,0);
        objectAnimator.setDuration(500);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!objectAnimator.isRunning()) {
                    objectAnimator.setFloatValues(imageView.getRotation(), imageView.getRotation() + 100);
                    mViewModel.rotationPosition += 100;
                    objectAnimator.start();;
                }
            }
        });

    }

}