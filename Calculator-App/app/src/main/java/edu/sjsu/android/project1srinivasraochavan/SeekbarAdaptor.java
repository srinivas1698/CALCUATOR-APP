package edu.sjsu.android.project1srinivasraochavan;

import android.widget.SeekBar;

public interface SeekbarAdaptor extends SeekBar.OnSeekBarChangeListener{
    @Override
    default void onStartTrackingTouch(SeekBar seekBar)
    {

    }
    @Override
    default void onStopTrackingTouch(SeekBar seekBar)
    {

    }
}
