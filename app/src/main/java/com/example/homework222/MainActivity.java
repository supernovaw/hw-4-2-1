package com.example.homework222;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initToolsList();
	}

	private void initToolsList() {
		ArrayList<ToolsArrayAdapter.Tool> list = new ArrayList<>();

		list.add(new ToolsArrayAdapter.Tool(R.drawable.kanji_kaku, R.string.notes, R.string.notes_description));
		list.add(new ToolsArrayAdapter.Tool(R.drawable.kanji_toki, R.string.tasks, R.string.tasks_description));
		list.add(new ToolsArrayAdapter.Tool(R.drawable.kanji_tokoro, R.string.address, R.string.address_description));

		ListView toolsList = findViewById(R.id.toolsList);
		toolsList.setAdapter(new ToolsArrayAdapter(this, list));

		toolsList.setOnItemClickListener((parent, view, position, id) -> {
			switch (position) {
				case 0: // notes
					startActivity(new Intent(this, NotesActivity.class));
					break;
				case 1: // organizer
					startActivity(new Intent(this, TasksActivity.class));
					break;
				case 2: // address picker
					startActivity(new Intent(this, AddressActivity.class));
					break;
			}
		});
	}
}
