package com.example.roomtester.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.example.roomtester.R;
import com.example.roomtester.databinding.FragmentAddDrinkBinding;
import com.example.roomtester.db.Drink;
import com.example.roomtester.db.DrinkDatabase;

public class AddDrinkFragment extends Fragment {
    public AddDrinkFragment() {
        // Required empty public constructor
    }

    DrinkDatabase database;
    FragmentAddDrinkBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddDrinkBinding.inflate(inflater, container, false);
        database = Room.databaseBuilder(getActivity(), DrinkDatabase.class, "Grades.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Add Drink");


        binding.seekBarAlcohol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.textViewAlcoholPercentage.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        binding.buttonAddDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double alcohol = binding.seekBarAlcohol.getProgress();
                double size = 1.0;
                if(binding.radioGroupDrinkSize.getCheckedRadioButtonId() == R.id.radioButton5oz){
                    size = 5.0;
                } else if(binding.radioGroupDrinkSize.getCheckedRadioButtonId() == R.id.radioButton12oz){
                    size = 12.0;
                }

                String type = "BEER";
                if(binding.radioGroupType.getCheckedRadioButtonId() == R.id.radioButtonWine){
                    type = "WINE";
                } else if(binding.radioGroupType.getCheckedRadioButtonId() == R.id.radioButtonShot){
                    type = "SHOT";
                }


                //Store the new drink to the database
                database.drinkDAO().insertAll(new Drink(alcohol, size, type));


                mListener.doneAddDrink();
            }
        });

        binding.buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancelAddDrink();
            }
        });

    }

    AddDrinkFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (AddDrinkFragmentListener) context;
    }

    public interface AddDrinkFragmentListener{
        void doneAddDrink();
        void cancelAddDrink();
    }

}