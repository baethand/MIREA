package com.example.secondworkpractice;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.secondworkpractice.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    private MainFragmentListener listener;
    private FragmentMainBinding binding;
    private boolean isCalculatingCarat = false;
    private boolean isCalculatingGram = false;
    private boolean isCalculatingKg = false;
    private boolean isCalculatingCentner = false;
    private boolean isCalculatingTon = false;
    private boolean isCalculatingPound = false;
    private boolean isCalculatingOunce = false;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        View view = binding.getRoot();




        // Изменение меток EditText для соответствия единицам измерения массы
        binding.bit.setHint("Караты");
        binding.byteE.setHint("Граммы");
        binding.kbyte.setHint("Кг");
        binding.mbyte.setHint("Центнеры");
        binding.gbyte.setHint("Тонны");
        binding.tbyte.setHint("Фунты");
        binding.clear.setText("Очистить");

        binding.bit.addTextChangedListener(textWatcher);
        binding.byteE.addTextChangedListener(textWatcher);
        binding.kbyte.addTextChangedListener(textWatcher);
        binding.mbyte.addTextChangedListener(textWatcher);
        binding.gbyte.addTextChangedListener(textWatcher);
        binding.tbyte.addTextChangedListener(textWatcher);
        binding.ounce.addTextChangedListener(textWatcher);
        binding.clear.setOnClickListener(v -> clearAllFields());


        binding.buttonRef.setOnClickListener(v -> {
            Bundle data = new Bundle();
            data.putString("bitValue", binding.bit.getText().toString());
            data.putString("byteValue", binding.byteE.getText().toString());
            data.putString("kbyteValue", binding.kbyte.getText().toString());
            data.putString("mbyteValue", binding.mbyte.getText().toString());
            data.putString("gbyteValue", binding.gbyte.getText().toString());
            data.putString("tbyteValue", binding.tbyte.getText().toString());
            data.putString("ounceValue", binding.ounce.getText().toString());

            if (listener != null) {
                listener.onButtonClick(data);
            }
        });
        return view;
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            updateValues();
        }
    };

    private void updateValues() {
        if (binding.bit.isFocused()) {
            handleCaratConversion();
        } else if (binding.byteE.isFocused()) {
            handleGramConversion();
        } else if (binding.kbyte.isFocused()) {
            handleKgConversion();
        } else if (binding.mbyte.isFocused()) {
            handleCentnerConversion();
        } else if (binding.gbyte.isFocused()) {
            handleTonConversion();
        } else if (binding.tbyte.isFocused()) {
            handlePoundConversion();
        } else if (binding.ounce.isFocused()) {
            handleOunceConversion();
        }
    }
    private void handleCaratConversion() {
        if (isCalculatingCarat) {
            return;
        }

        isCalculatingCarat = true;

        try {
            String caratStr = binding.bit.getText().toString();
            if (TextUtils.isEmpty(caratStr)) {
                clearAllFields();
                return;
            }
            if (!TextUtils.isEmpty(caratStr)) {

                double carat = Double.parseDouble(caratStr);
                double gram = carat * 0.2;
                double kg = gram / 1000;
                double centner = kg / 100;
                double ton = centner / 10;
                double pound = gram * 0.00220462;
                double ounce = pound * 16;

                binding.byteE.setText(String.valueOf(gram));
                binding.kbyte.setText(String.valueOf(kg));
                binding.mbyte.setText(String.valueOf(centner));
                binding.gbyte.setText(String.valueOf(ton));
                binding.tbyte.setText(String.valueOf(pound));
                binding.ounce.setText(String.valueOf(ounce));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            isCalculatingCarat = false;
        }
    }

    private void handleGramConversion() {
        if (isCalculatingGram) {
            return;
        }

        isCalculatingGram = true;

        try {
            String gramStr = binding.byteE.getText().toString();
            if (TextUtils.isEmpty(gramStr)) {
                clearAllFields();
                return;
            }
            if (!TextUtils.isEmpty(gramStr)) {
                double gram = Double.parseDouble(gramStr);
                double carat = gram * 5;
                double kg = gram / 1000;
                double centner = kg / 100;
                double ton = centner / 10;
                double pound = gram * 0.00220462;
                double ounce = pound * 16;

                binding.bit.setText(String.valueOf(carat));
                binding.kbyte.setText(String.valueOf(kg));
                binding.mbyte.setText(String.valueOf(centner));
                binding.gbyte.setText(String.valueOf(ton));
                binding.tbyte.setText(String.valueOf(pound));
                binding.ounce.setText(String.valueOf(ounce));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            isCalculatingGram = false;
        }
    }

    private void handleKgConversion() {
        if (isCalculatingKg) {
            return;
        }

        isCalculatingKg = true;

        try {
            String kgStr = binding.kbyte.getText().toString();
            if (TextUtils.isEmpty(kgStr)) {
                clearAllFields();
                return;
            }
            if (!TextUtils.isEmpty(kgStr)) {
                double kg = Double.parseDouble(kgStr);
                double carat = kg * 5000;
                double gram = kg * 1000;
                double centner = kg / 100;
                double ton = centner / 10;
                double pound = kg * 2.20462;
                double ounce = pound * 16;

                binding.bit.setText(String.valueOf(carat));
                binding.byteE.setText(String.valueOf(gram));
                binding.mbyte.setText(String.valueOf(centner));
                binding.gbyte.setText(String.valueOf(ton));
                binding.tbyte.setText(String.valueOf(pound));
                binding.ounce.setText(String.valueOf(ounce));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            isCalculatingKg = false;
        }
    }

    private void handleCentnerConversion() {
        if (isCalculatingCentner) {
            return;
        }

        isCalculatingCentner = true;

        try {
            String centnerStr = binding.mbyte.getText().toString();
            if (TextUtils.isEmpty(centnerStr)) {
                clearAllFields();
                return;
            }
            if (!TextUtils.isEmpty(centnerStr)) {
                double centner = Double.parseDouble(centnerStr);
                double carat = centner * 500000;
                double gram = centner * 100000;
                double kg = centner * 1000;
                double ton = centner / 10;
                double pound = centner * 220.462;
                double ounce = pound * 16;

                binding.bit.setText(String.valueOf(carat));
                binding.byteE.setText(String.valueOf(gram));
                binding.kbyte.setText(String.valueOf(kg));
                binding.gbyte.setText(String.valueOf(ton));
                binding.tbyte.setText(String.valueOf(pound));
                binding.ounce.setText(String.valueOf(ounce));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            isCalculatingCentner = false;
        }
    }

    private void handleTonConversion() {
        if (isCalculatingTon) {
            return;
        }

        isCalculatingTon = true;

        try {
            String tonStr = binding.gbyte.getText().toString();
            if (TextUtils.isEmpty(tonStr)) {
                clearAllFields();
                return;
            }
            if (!TextUtils.isEmpty(tonStr)) {
                double ton = Double.parseDouble(tonStr);
                double carat = ton * 5000000;
                double gram = ton * 1000000;
                double kg = ton * 1000000;
                double centner = ton * 10;
                double pound = ton * 2204.62;
                double ounce = pound * 16;

                binding.bit.setText(String.valueOf(carat));
                binding.byteE.setText(String.valueOf(gram));
                binding.kbyte.setText(String.valueOf(kg));
                binding.mbyte.setText(String.valueOf(centner));
                binding.tbyte.setText(String.valueOf(pound));
                binding.ounce.setText(String.valueOf(ounce));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            isCalculatingTon = false;
        }
    }

    private void handlePoundConversion() {
        if (isCalculatingPound) {
            return;
        }

        isCalculatingPound = true;

        try {
            String poundStr = binding.tbyte.getText().toString();
            if (TextUtils.isEmpty(poundStr)) {
                clearAllFields();
                return;
            }
            if (!TextUtils.isEmpty(poundStr)) {
                double pound = Double.parseDouble(poundStr);
                double carat = pound * 453.592;
                double gram = pound * 453.592;
                double kg = pound / 2.20462;
                double centner = kg / 100;
                double ton = centner / 10;
                double ounce = pound * 16;

                binding.bit.setText(String.valueOf(carat));
                binding.byteE.setText(String.valueOf(gram));
                binding.kbyte.setText(String.valueOf(kg));
                binding.mbyte.setText(String.valueOf(centner));
                binding.gbyte.setText(String.valueOf(ton));
                binding.ounce.setText(String.valueOf(ounce));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            isCalculatingPound = false;
        }
    }
    private void handleOunceConversion() {
        if (isCalculatingOunce) {
            return;
        }

        isCalculatingOunce = true;

        try {
            String ounceStr = binding.ounce.getText().toString();
            if (TextUtils.isEmpty(ounceStr)) {
                clearAllFields();
                return;
            }
            if (!TextUtils.isEmpty(ounceStr)) {
                double ounce = Double.parseDouble(ounceStr);
                double carat = ounce * 141.747612;
                double gram = ounce * 28.3495;
                double kg = gram / 1000;
                double centner = kg / 100;
                double ton = centner / 10;
                double pound = ounce / 16;

                binding.bit.setText(String.valueOf(carat));
                binding.byteE.setText(String.valueOf(gram));
                binding.kbyte.setText(String.valueOf(kg));
                binding.mbyte.setText(String.valueOf(centner));
                binding.gbyte.setText(String.valueOf(ton));
                binding.tbyte.setText(String.valueOf(pound));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            isCalculatingOunce = false;
        }
    }


    private void clearAllFields() {
        binding.bit.setText("");
        binding.byteE.setText("");
        binding.kbyte.setText("");
        binding.mbyte.setText("");
        binding.gbyte.setText("");
        binding.tbyte.setText("");
        binding.ounce.setText("");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainFragmentListener) {
            listener = (MainFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement MainFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface MainFragmentListener {
        void onButtonClick(Bundle data);
    }
}
