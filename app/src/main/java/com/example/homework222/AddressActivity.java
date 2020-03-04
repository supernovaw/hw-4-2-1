package com.example.homework222;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class AddressActivity extends AppCompatActivity {
	private Spinner countriesSpinner;
	private Spinner citiesSpinner;
	private Spinner homesSpinner;
	private Button confirmButton;

	private ArrayAdapter<CharSequence> adapterCountries;
	private ArrayAdapter<CharSequence>[] adaptersCities;
	private ArrayAdapter<Integer> adapterNumbers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address);
		initViews();

		initSpinners();
		confirmButton.setOnClickListener(v -> confirm());
	}

	private void initViews() {
		countriesSpinner = findViewById(R.id.countriesSpinner);
		citiesSpinner = findViewById(R.id.citiesSpinner);
		homesSpinner = findViewById(R.id.homesSpinner);
		confirmButton = findViewById(R.id.confirmButton);
	}

	private void initSpinners() {
		adapterCountries = getAdapter(R.array.countries);
		ArrayAdapter<CharSequence> adapterRU = getAdapter(R.array.cities_RU);
		ArrayAdapter<CharSequence> adapterUA = getAdapter(R.array.cities_UA);
		ArrayAdapter<CharSequence> adapterBY = getAdapter(R.array.cities_BY);
		adaptersCities = new ArrayAdapter[]{adapterRU, adapterUA, adapterBY};

		Integer[] numbers = new Integer[50];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = i + 1;
		}
		adapterNumbers = new ArrayAdapter<>(this,
				android.R.layout.simple_spinner_dropdown_item, numbers);

		countriesSpinner.setAdapter(adapterCountries);
		citiesSpinner.setAdapter(adapterRU);
		homesSpinner.setAdapter(adapterNumbers);

		AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				switch (parent.getId()) {
					case R.id.countriesSpinner:
						citiesSpinner.setAdapter(adaptersCities[position]);
						homesSpinner.setSelection(0);
						break;
					case R.id.citiesSpinner:
						homesSpinner.setSelection(0);
						break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		};

		countriesSpinner.setOnItemSelectedListener(spinnerListener);
		citiesSpinner.setOnItemSelectedListener(spinnerListener);
	}

	private ArrayAdapter<CharSequence> getAdapter(int arrayId) {
		return ArrayAdapter.createFromResource(this,
				arrayId, android.R.layout.simple_spinner_dropdown_item);
	}

	private void confirm() {
		String country = (String) countriesSpinner.getSelectedItem();
		String city = (String) citiesSpinner.getSelectedItem();
		int home = (int) homesSpinner.getSelectedItem();
		String address = getString(R.string.confirm_toast, country, city, home);
		Toast.makeText(this, address, Toast.LENGTH_SHORT).show();
	}
}
