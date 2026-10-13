package com.example.attendanceregister;

import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final ArrayList<Student> students = new ArrayList<>();
    private StudentAdapter adapter;

    private EditText etStudentName;
    private TextView tvSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etStudentName = findViewById(R.id.etStudentName);
        tvSummary = findViewById(R.id.tvSummary);
        ListView lvStudents = findViewById(R.id.lvStudents);
        TextView tvEmpty = findViewById(R.id.tvEmpty);

        adapter = new StudentAdapter(this, students, this::updateSummary);
        lvStudents.setAdapter(adapter);
        lvStudents.setEmptyView(tvEmpty);   // shown when the list has no students

        findViewById(R.id.btnAddStudent).setOnClickListener(v -> addStudent());
        findViewById(R.id.btnReset).setOnClickListener(v -> confirmReset());

        // Pressing "Done" on the keyboard also adds the student
        etStudentName.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addStudent();
                return true;
            }
            return false;
        });

        updateSummary();
    }

    private void addStudent() {
        String name = etStudentName.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter a student name", Toast.LENGTH_SHORT).show();
            return;
        }

        students.add(new Student(name));
        adapter.notifyDataSetChanged();
        etStudentName.setText("");
        updateSummary();
        Toast.makeText(this, name + " added", Toast.LENGTH_SHORT).show();
    }

    private void updateSummary() {
        int present = 0, absent = 0;
        for (Student s : students) {
            if (s.getStatus() == Student.PRESENT) present++;
            else if (s.getStatus() == Student.ABSENT) absent++;
        }
        tvSummary.setText("Total: " + students.size()
                + "   |   Present: " + present
                + "   |   Absent: " + absent);
    }

    private void confirmReset() {
        if (students.isEmpty()) {
            Toast.makeText(this, "The register is already empty", Toast.LENGTH_SHORT).show();
            return;
        }
        new AlertDialog.Builder(this)
                .setTitle("Reset Register")
                .setMessage("This will remove all students. Continue?")
                .setPositiveButton("Yes, reset", (dialog, which) -> {
                    students.clear();
                    adapter.notifyDataSetChanged();
                    updateSummary();
                    Toast.makeText(this, "Register cleared", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}