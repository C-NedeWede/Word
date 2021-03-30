package cn.edu.gdut.dictationdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import cn.edu.gdut.dictationdemo.databinding.FragmentWinBinding;


public class WinFragment extends Fragment {
    public WinFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MyViewModel myViewModel = new ViewModelProvider(requireActivity(),new SavedStateViewModelFactory(requireActivity().getApplication(),requireActivity())).get(MyViewModel.class);
        FragmentWinBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_win, container, false);
        Toast.makeText(requireActivity(),R.string.winflag,Toast.LENGTH_SHORT).show();
        binding.buttonBack.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_winFragment_to_homeFragment));
        binding.setData(myViewModel);
        binding.setLifecycleOwner(requireActivity());
        return binding.getRoot();

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_result, container, false);
    }
}