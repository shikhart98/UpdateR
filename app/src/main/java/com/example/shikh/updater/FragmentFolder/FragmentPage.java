package com.example.shikh.updater.FragmentFolder;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shikh.updater.R;

/**
 * Created by shikh on 21-03-2018.
 */

public class FragmentPage extends Fragment {
    View view;

    public FragmentPage() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.diary_page, container, false);
        return view;
    }
}
