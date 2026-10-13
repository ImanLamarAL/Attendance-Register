package com.example.attendanceregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {

    /** Called whenever a student's status changes so the summary can refresh. */
    public interface OnStatusChangedListener {
        void onStatusChanged();
    }

    private final OnStatusChangedListener listener;

    public StudentAdapter(@NonNull Context context, @NonNull List<Student> students,
                          OnStatusChangedListener listener) {
        super(context, 0, students);
        this.listener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_student, parent, false);
        }

        final Student student = getItem(position);

        LinearLayout root = convertView.findViewById(R.id.itemRoot);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvStatus = convertView.findViewById(R.id.tvStatus);
        RadioGroup rgStatus = convertView.findViewById(R.id.rgStatus);

        tvName.setText(student.getName());

        // Remove the old listener first: views are recycled, so setting the
        // checked state below must not trigger the previous row's callback.
        rgStatus.setOnCheckedChangeListener(null);

        switch (student.getStatus()) {
            case Student.PRESENT:
                rgStatus.check(R.id.rbPresent);
                break;
            case Student.ABSENT:
                rgStatus.check(R.id.rbAbsent);
                break;
            default:
                rgStatus.clearCheck();
        }
        applyColours(student, root, tvStatus);

        rgStatus.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbPresent) {
                student.setStatus(Student.PRESENT);
            } else if (checkedId == R.id.rbAbsent) {
                student.setStatus(Student.ABSENT);
            }
            applyColours(student, root, tvStatus);
            if (listener != null) listener.onStatusChanged();
        });

        return convertView;
    }

    private void applyColours(Student student, LinearLayout root, TextView tvStatus) {
        int bg, textColor;
        String label;
        switch (student.getStatus()) {
            case Student.PRESENT:
                bg = R.color.present_bg;
                textColor = R.color.present_text;
                label = "Present";
                break;
            case Student.ABSENT:
                bg = R.color.absent_bg;
                textColor = R.color.absent_text;
                label = "Absent";
                break;
            default:
                bg = R.color.unmarked_bg;
                textColor = R.color.unmarked_text;
                label = "Not marked";
        }
        root.setBackgroundColor(ContextCompat.getColor(getContext(), bg));
        tvStatus.setTextColor(ContextCompat.getColor(getContext(), textColor));
        tvStatus.setText(label);
    }
}