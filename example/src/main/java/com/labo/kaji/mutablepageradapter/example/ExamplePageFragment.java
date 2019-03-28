package com.labo.kaji.mutablepageradapter.example;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ExamplePageFragment extends Fragment {

    private static final boolean TRACE_LIFECYCLE = BuildConfig.DEBUG;
    private static int sSerialNumber = 0;

    public static ExamplePageFragment newInstance(Bundle args) {
        ExamplePageFragment fragment = new ExamplePageFragment();
        if (args == null) {
            args = new Bundle();
        }
        int color = Color.rgb((int) Math.floor(Math.random() * 128) + 64,
                (int) Math.floor(Math.random() * 128) + 64,
                (int) Math.floor(Math.random() * 128) + 64);
        args.putInt("color", color);
        args.putInt("serial", sSerialNumber);
        args.putLong("time", System.currentTimeMillis());
        args.putString("title", "#" + sSerialNumber);
        sSerialNumber++;
        fragment.setArguments(args);
        return fragment;
    }

    public static void resetSerialNumber() {
        sSerialNumber = 0;
    }

    public void setPageIndex(int pageIndex) {
        getArguments().putInt("pageIndex", pageIndex);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (TRACE_LIFECYCLE) {
            LogUtil.D(getClass(), "onCreateView:" + getArguments().getInt("serial"));
        }
        return inflater.inflate(R.layout.fragment_example, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (TRACE_LIFECYCLE) {
            LogUtil.D(getClass(), "onActivityCreated:" + getArguments().getInt("serial"));
        }
        super.onActivityCreated(savedInstanceState);

        getView().findViewById(R.id.button1).setOnClickListener((ExampleActivity) getActivity());
        getView().findViewById(R.id.button2).setOnClickListener((ExampleActivity) getActivity());
        getView().findViewById(R.id.button3).setOnClickListener((ExampleActivity) getActivity());
        getView().findViewById(R.id.button4).setOnClickListener((ExampleActivity) getActivity());
        getView().findViewById(R.id.button5).setOnClickListener((ExampleActivity) getActivity());
        getView().findViewById(R.id.button6).setOnClickListener((ExampleActivity) getActivity());

        getView().findViewById(R.id.layout1).setBackgroundColor(getArguments().getInt("color"));

        StringBuilder string = new StringBuilder();
        string.append("Page ").append(getArguments().getInt("pageIndex"));
        string.append(" / Fragment#").append(getArguments().getInt("serial"));

        TextView textView = (TextView) getView().findViewById(R.id.textView1);
        textView.setText(string);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (TRACE_LIFECYCLE) {
            LogUtil.D(getClass(), "onCreate:" + getArguments().getInt("serial"));
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        if (TRACE_LIFECYCLE) {
            LogUtil.D(getClass(), "onInflate:" + getArguments().getInt("serial"));
        }
        super.onInflate(activity, attrs, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (TRACE_LIFECYCLE) {
            LogUtil.D(getClass(), "onViewCreated:" + getArguments().getInt("serial"));
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        if (TRACE_LIFECYCLE) {
            LogUtil.D(getClass(), "onAttach:" + getArguments().getInt("serial"));
        }
        super.onAttach(activity);
    }

    @Override
    public void onResume() {
        if (TRACE_LIFECYCLE) {
            LogUtil.D(getClass(), "onResume:" + getArguments().getInt("serial"));
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        if (TRACE_LIFECYCLE) {
            LogUtil.D(getClass(), "onPause:" + getArguments().getInt("serial"));
        }
        super.onPause();
    }

    @Override
    public void onDetach() {
        if (TRACE_LIFECYCLE) {
            LogUtil.D(getClass(), "onDetach:" + getArguments().getInt("serial"));
        }
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        if (TRACE_LIFECYCLE) {
            LogUtil.D(getClass(), "onDestroyView:" + getArguments().getInt("serial"));
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        if (TRACE_LIFECYCLE) {
            LogUtil.D(getClass(), "onDestroy:" + getArguments().getInt("serial"));
        }
        super.onDestroy();
    }

}
