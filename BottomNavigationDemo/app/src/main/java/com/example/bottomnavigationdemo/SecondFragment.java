package com.example.bottomnavigationdemo;

import androidx.lifecycle.ViewModelProvider;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SecondFragment extends Fragment {

    private SecondViewModel mViewModel;
    private ImageView imageView;

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);
        imageView = view.findViewById(R.id.imageView);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SecondViewModel.class);
        // TODO: Use the ViewModel

        imageView.setScaleX(mViewModel.scaleX);
        imageView.setScaleY(mViewModel.scaleY);


        ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(imageView, "scaleX", 0,0);
        ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(imageView, "scaleY", 0,0);
        objectAnimatorX.setDuration(500);
        objectAnimatorY.setDuration(500);

//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playTogether(objectAnimatorX, objectAnimatorY);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!objectAnimatorX.isRunning()) {
                    objectAnimatorX.setFloatValues(imageView.getScaleX(), imageView.getScaleX() + .1f);
                    objectAnimatorY.setFloatValues(imageView.getScaleY(), imageView.getScaleY() + .1f);

                    mViewModel.scaleX += .1f;
                    mViewModel.scaleY += .1f;

                    objectAnimatorX.start();
                    objectAnimatorY.start();
                }
            }
        });
    }

}