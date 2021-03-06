package cn.edu.gdut.dictationdemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import cn.edu.gdut.dictationdemo.databinding.FragmentContentBinding;

public class ContentFragment extends Fragment {

    final StringBuilder builder = new StringBuilder();

    public ContentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final MyViewModel myViewModel = new ViewModelProvider(requireActivity(), new SavedStateViewModelFactory(requireActivity().getApplication(), requireActivity())).get(MyViewModel.class);
        final FragmentContentBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_content, container, false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(requireActivity());

        if (savedInstanceState != null) {
            builder.append(savedInstanceState.getString("Answer"));
            binding.textView5.setText(builder.toString());
        }

        @SuppressLint("NonConstantResourceId")
        View.OnClickListener listener = v -> {
            switch (v.getId()) {
                case R.id.button0:
                    builder.append("0");
                    break;
                case R.id.button1:
                    builder.append("1");
                    break;
                case R.id.button2:
                    builder.append("2");
                    break;
                case R.id.button3:
                    builder.append("3");
                    break;
                case R.id.button4:
                    builder.append("4");
                    break;
                case R.id.button5:
                    builder.append("5");
                    break;
                case R.id.button6:
                    builder.append("6");
                    break;
                case R.id.button7:
                    builder.append("7");
                    break;
                case R.id.button8:
                    builder.append("8");
                    break;
                case R.id.button9:
                    builder.append("9");
                    break;
                case R.id.buttonClear:
                    builder.setLength(0);
                    break;
            }
            if (builder.length() == 0) {
                binding.textView5.setText(getString(R.string.input_indicator));
            } else {
                binding.textView5.setText(builder.toString());
            }
        };

        binding.button0.setOnClickListener(listener);
        binding.button1.setOnClickListener(listener);
        binding.button2.setOnClickListener(listener);
        binding.button3.setOnClickListener(listener);
        binding.button4.setOnClickListener(listener);
        binding.button5.setOnClickListener(listener);
        binding.button6.setOnClickListener(listener);
        binding.button7.setOnClickListener(listener);
        binding.button8.setOnClickListener(listener);
        binding.button9.setOnClickListener(listener);
        binding.buttonClear.setOnClickListener(listener);

        binding.buttonCommit.setOnClickListener(v -> {
            if (String.valueOf(myViewModel.getAnswer().getValue()).equals(builder.toString())) {
                myViewModel.answerCorrect();
                builder.setLength(0);
                binding.textView5.setText(R.string.correct_msg);
            } else {
                NavController controller = Navigation.findNavController(v);
                if (myViewModel.winflag) {
                    controller.navigate(R.id.action_contentFragment_to_winFragment);
                    myViewModel.winflag = false;
                    myViewModel.save();
                } else {
                    controller.navigate(R.id.action_contentFragment_to_loseFragment);
                }
            }
        });

        return binding.getRoot();
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Answer", builder.toString());
    }
}