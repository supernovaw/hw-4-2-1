package com.example.homework222;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class TasksActivity extends AppCompatActivity {
	private static final DateFormat DATE_FORMAT = SimpleDateFormat.getDateInstance();

	private RadioButton startRadioButton, finishRadioButton;
	private TextView startTextView, finishTextView;
	private Button confirmButton;
	private CalendarView calendarView;

	private GregorianCalendar dateStart, dateFinish;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tasks);
		initViews();

		initDates();
		View.OnClickListener radioListener = this::proceedRadioButton;
		startRadioButton.setOnClickListener(radioListener);
		finishRadioButton.setOnClickListener(radioListener);

		confirmButton.setOnClickListener(v -> confirmClick());
	}

	private void initViews() {
		startRadioButton = findViewById(R.id.startRadioButton);
		finishRadioButton = findViewById(R.id.finishRadioButton);

		startTextView = findViewById(R.id.startTextView);
		finishTextView = findViewById(R.id.finishTextView);

		confirmButton = findViewById(R.id.confirmButton);

		calendarView = findViewById(R.id.calendarView);
	}

	private void initDates() {
		calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
			GregorianCalendar cal = new GregorianCalendar(year, month, dayOfMonth);
			if (startRadioButton.isChecked()) {
				dateStart = cal;
			} else {
				dateFinish = cal;
			}
		});

		GregorianCalendar calendarSel = new GregorianCalendar();
		calendarSel.setTimeInMillis(calendarView.getDate());
		dateStart = dateFinish = calendarSel;
		String selString = DATE_FORMAT.format(calendarSel.getTimeInMillis());
		startTextView.setText(selString);
		finishTextView.setText(selString);
	}

	private void proceedRadioButton(View v) {
		boolean first = v.getId() == R.id.startRadioButton;
		startRadioButton.setChecked(first);
		finishRadioButton.setChecked(!first);

		startTextView.setVisibility(first ? View.INVISIBLE : View.VISIBLE);
		finishTextView.setVisibility(first ? View.VISIBLE : View.INVISIBLE);

		GregorianCalendar date = first ? dateFinish : dateStart,
				otherDate = first ? dateStart : dateFinish;
		String dateString = DATE_FORMAT.format(date.getTimeInMillis());
		if (first) {
			finishTextView.setText(dateString);
		} else {
			startTextView.setText(dateString);
		}
		calendarView.setDate(otherDate.getTimeInMillis());
	}

	private void confirmClick() {
		long start = dateStart.getTimeInMillis(), finish = dateFinish.getTimeInMillis();
		if (start > finish) {
			Toast.makeText(this, R.string.wrong_order, Toast.LENGTH_LONG).show();
			return;
		}
		String startString = DATE_FORMAT.format(start), finishString = DATE_FORMAT.format(finish);
		String out = getString(R.string.chosen_dates_format, startString, finishString);
		Toast.makeText(this, out, Toast.LENGTH_SHORT).show();
	}
}
