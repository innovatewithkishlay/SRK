# FixMate - Modern Home Services App 🏠🛠️

FixMate is a comprehensive, UI-based Android application built using **Kotlin** and **Jetpack Compose**. It serves as a bridge between homeowners and professional service providers (plumbers, electricians, cleaners, etc.), featuring a robust multi-role architecture.

---

## 🎯 Problem Statement
Finding reliable, verified home service providers (plumbers, electricians, etc.) is often a fragmented and frustrating process for homeowners. Simultaneously, skilled professionals lack a centralized platform to manage their jobs and growth efficiently. 

**FixMate** solves this by providing a unified, multi-role ecosystem that connects users with trusted experts through a seamless, mobile-first experience, complete with booking management, analytics, and real-time communication.

---

The project is designed to showcase core Android development concepts, strictly following a standard academic syllabus (Units I-VI).

---

## 🚀 Key Features

### 👤 User Dashboard
Designed for a seamless service discovery and booking experience.
- **Dynamic Home Screen**: Category grids (Plumbing, Cleaning, etc.) and horizontal scrolling for popular services.
- **Smart Booking**: Integrated Date and Time pickers with service variation selection (Standard/Premium).
- **Interactive Maps**: Placeholder UI for tracking nearby service providers.
- **Real-time Chat**: Modern messaging interface for communicating with experts.
- **Profile Management**: Scoped storage-ready profile image selection UI.

### 🧑‍🔧 Service Provider Dashboard
Tailored for professionals to manage their workflow and earnings.
- **Live Stats**: Overview cards showing Total Jobs, Monthly Earnings, and User Ratings.
- **Job Management**: Tabbed interface (Pending vs. Completed) using a Horizontal Pager.
- **Request Handling**: Quick Accept/Reject functionality for incoming service requests.

### 🛠️ Admin Dashboard
A high-level command center for platform management.
- **System Overview**: Cards displaying aggregate data (Total Users, Providers, Bookings).
- **Navigation Drawer**: A slide-out menu for deep navigation into management sections.
- **Activity Logs**: Feed of recent platform events and alerts.

---

## 📚 Syllabus Units Implemented

### 🔥 Unit I: Compose UI Basics
- **Lazy Layouts**: `LazyColumn` for lists and `LazyVerticalGrid` for service categories.
- **Interactions**: `LaunchedEffect` for splash screen timing and `ExposedDropdownMenuBox` for spinners.
- **UI Elements**: Progress Indicators, Custom Star Rating Bars, and Nested Scrolling.

### 🔥 Unit II & III: Communications & Notifications
- **Intents**: Seamless navigation between screens and role flows.
- **Notifications**: Implementation of **Notification Channels** and **Pending Intents** for booking updates.
- **Scheduling**: `AlarmManager` integration for background reminders.

### 📅 Unit III: User Interaction
- **Dialogs**: Full integration of `DatePickerDialog` and `TimePickerDialog` for booking schedules.

### 🎨 Unit IV: Custom UI Components
- Developed a library of reusable components:
  - `CustomButton()`: Themed elevation and rounded corners.
  - `CustomTextField()`: Standardized input styling.
  - `CustomCard()`: Material 3 card with shadows.
  - `CustomRatingBar()`: Interactive star-based feedback UI.

### 💾 Unit V: Data & Permissions
- **DataStore**: Uses `Preferences DataStore` to persist the selected user role.
- **Permissions**: Runtime permission handling for Location and Notifications.
- **Storage**: Scoped storage dummy UI for profile image updates.

### 📊 Unit VI: Navigation & Paging
- **Navigation Component**: Type-safe routing using Compose Navigation.
- **Advanced UI**: `TabRow` + `HorizontalPager` and `ModalNavigationDrawer`.

---

## 🛠️ Tech Stack
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose (100% XML-free)
- **Design System**: Material Design 3
- **Architecture**: MVVM (Lightweight)
- **Data Persistence**: Preferences DataStore
- **Dependency Management**: Gradle Version Catalog (libs.versions.toml)

---

## 📂 Project Structure
```text
com.fixmate.app
├── data             # DataStore and Dummy Data Objects
├── navigation       # NavHost and Route Definitions
├── ui
│   ├── components   # Reusable Custom UI Components
│   ├── screens      # Dashboards (User, Provider, Admin)
│   └── theme        # Material 3 Color/Type/Theme tokens
└── utils            # Notifications, Alarms, and Helpers
```

---

## 🏁 Getting Started
1. Clone the repository.
2. Open in **Android Studio (Ladybug or newer)**.
3. Sync Gradle and run on an emulator/device with **API 24+**.

---

## 📝 License
This project is for educational purposes as part of the Android Development syllabus.
