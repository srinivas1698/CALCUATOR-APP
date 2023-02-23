package edu.sjsu.android.project1srinivasraochavan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import edu.sjsu.android.project1srinivasraochavan.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.cal.setOnClickListener(this::calculate);
        binding.uninstall.setOnClickListener(this::uninstall);
        setRateLabel(binding.seekBar.getProgress());
        binding.seekBar.setOnSeekBarChangeListener((SeekbarAdaptor) (seekBar, i, b) -> setRateLabel(i));
        binding.principle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                binding.result.setText(getString(R.string.prompt));
            }
        });
    }

    void setRateLabel(int progress)
    {
        double value = progress / 10.0;
        binding.rate.setText(getString(R.string.rateLabel,
                value));
    }

    public void calculate(View view) {
        String principleString = binding.principle.getText().toString();
        if (!inputValidation(principleString)) {
            //Toast warning
            Toast.makeText(this, getString(R.string.error) , Toast.LENGTH_SHORT).show();
            return;
        }
        double P = Double.parseDouble(principleString);
        double J = binding.seekBar.getProgress() / 10.0;
        int N = getNumOfYears();
        double T = binding.checkBox.isChecked() ? (0.1 * P) : 0.0;
        double result = Calculator.calculate(P, J, N, T);
        Locale current = Locale.getDefault();
        NumberFormat format = NumberFormat.getCurrencyInstance();
        String resultString = format.format(result);
        binding.result.setText(getString(R.string.monthlyResult,resultString));
    }
    boolean inputValidation(String input)
    {
        if(input.isEmpty())
        {
            binding.result.setText(getString(R.string.error_empty,getString(R.string.prompt)));
            return false;
        }
        if(input.equals("."))
        {
            binding.result.setText(getString(R.string.error));
            return false;
        }
        BigDecimal num=new BigDecimal(input);
        if(num.scale() > 2)
        {
            binding.result.setText(getString(R.string.error_digits,getString(R.string.prompt)));
            return false;
        }
        return true;
    }
    public void uninstall(View view) {
        Intent delete = new Intent(Intent.ACTION_DELETE,
                Uri.parse("package:" + getPackageName()));
        startActivity(delete);
    }

    private int getNumOfYears() {
        if (binding.radio15.isChecked())
            return 15;
        else if (binding.radio20.isChecked())
            return 20;
        else
            return 30;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId() == R.id.Lan)
        {
            Intent intent=new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}