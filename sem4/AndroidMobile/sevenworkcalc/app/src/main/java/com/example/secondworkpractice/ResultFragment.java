package com.example.secondworkpractice;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ResultFragment extends Fragment {
    private SecondFragmentListener listener;

    public ResultFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        TextView bitValueTextView = view.findViewById(R.id.caratValueTextView);
        TextView byteValueTextView = view.findViewById(R.id.gramValueTextView);
        TextView kbyteValueTextView = view.findViewById(R.id.kilogramValueTextView);
        TextView mbyteValueTextView = view.findViewById(R.id.metric_tonValueTextView);
        TextView gbyteValueTextView = view.findViewById(R.id.short_tonValueTextView);
        TextView tbyteValueTextView = view.findViewById(R.id.poundValueTextView);
        TextView ounceValueTextView = view.findViewById(R.id.ounceValueTextView);
        Button backMainButton = view.findViewById(R.id.backMainButton);

        Bundle data = getArguments();
        if (data != null) {
            bitValueTextView.setText(data.getString("bitValue"));
            byteValueTextView.setText(data.getString("byteValue"));
            kbyteValueTextView.setText(data.getString("kbyteValue"));
            mbyteValueTextView.setText(data.getString("mbyteValue"));
            gbyteValueTextView.setText(data.getString("gbyteValue"));
            tbyteValueTextView.setText(data.getString("tbyteValue"));
            ounceValueTextView.setText(data.getString("ounceValue"));
        }


        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SecondFragmentListener) {
            listener = (SecondFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement SecondFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface SecondFragmentListener {
        void onBackButtonClick();
    }
}
