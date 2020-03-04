package com.example.homework222;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NotesActivity extends AppCompatActivity {
	private static final String NOTE_KEY = "note_text";

	private EditText editText;
	private Button clearButton;
	private Button saveButton;

	private SharedPreferences noteSP;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notes);
		initViews();
		noteSP = getPreferences(MODE_PRIVATE);

		clearButton.setOnClickListener(v -> clear());
		saveButton.setOnClickListener(v -> save());
		if (noteSP.contains(NOTE_KEY))
			editText.setText(noteSP.getString(NOTE_KEY, null));
	}

	private void initViews() {
		editText = findViewById(R.id.editText);
		clearButton = findViewById(R.id.clearButton);
		saveButton = findViewById(R.id.saveButton);
	}

	private void clear() {
		editText.setText(null);
	}

	private void save() {
		noteSP.edit().putString(NOTE_KEY, editText.getText().toString()).apply();
		Toast.makeText(this, R.string.saved_toast, Toast.LENGTH_SHORT).show();
	}
}
