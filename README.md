# 📋 Student Attendance Register

A simple, elegant Android app for taking class attendance. Add students, mark each one **Present** or **Absent**, and watch the totals update live, all in a calm, clean interface.

---

## 👥 Group Members

| Name | Role |
|------|------|
| **Chogo Andrew** | Lead Developer |
| **Steven Nderi** | Documentation Consultant |

---

## ✨ Features

- **Add students:** type a name and tap Add to place the student in the register.
- **Mark attendance:** each student has pill-style **Present** and **Absent** buttons.
- **Live summary:** Total, Present and Absent counts update instantly as you mark students.
- **Status at a glance:** every row has an initial-letter avatar and a colour-coded status chip (unmarked, present, absent).
- **Reset register:** clear everything with one tap, protected by a confirmation dialog so nothing is lost by accident.
- **Empty state:** a friendly message shows when no students have been added yet.
- **Calm, premium UI:** a soft teal, sage and rose palette on card-based layouts, locked to light mode for a consistent look.

---

## 🛠️ Tech Stack

| Area | Technology |
|------|------------|
| Language | Java |
| Platform | Android |
| IDE | Android Studio |
| Build system | Gradle |
| UI layouts | XML (LinearLayout, FrameLayout) |
| UI components | Material Components: `MaterialCardView`, `TextInputLayout`, `MaterialAlertDialogBuilder` |
| List display | `ListView` with a custom `ArrayAdapter` |
| Styling | Custom vector icons, shape/selector drawables, colour state lists |
| Data storage | In-memory (no database) |

---

## 📁 Project Structure

```
app/src/main/
├── java/com/example/attendanceregister/
│   ├── MainActivity.java      # Screen logic: add, summary, reset
│   ├── Student.java           # Student model and status constants
│   └── StudentAdapter.java    # Row binding, styling and status callbacks
└── res/
    ├── layout/
    │   ├── activity_main.xml  # Main screen
    │   └── item_student.xml   # Single student row
    ├── drawable/              # Icons, avatar, chip and pill backgrounds
    ├── color/
    │   └── pill_text.xml      # Pill text colour selector
    └── values/
        └── colors.xml         # Colour palette
```

---

## 🚀 Getting Started

1. Clone the repository:
   ```bash
   git clone <your-repository-url>
   ```
2. Open the project in **Android Studio**.
3. Let Gradle sync finish.
4. Run the app on an emulator or a connected Android device.

---

## 📌 Notes

- Attendance data lives in memory only, so it clears when the app is closed.
- Possible future improvements: persistent storage (SQLite or Room), exporting reports, and date-based registers.
