<h1 align="center">Library Management System</h1>

<p align="center">
  Android app (Java) that lets <strong>admins</strong> manage library inventory and <strong>users</strong> search &amp; issue books — all backed by Firebase.
</p>

---

## 🧐 Overview
The app provides two roles: **Admin** (add / remove / search books) and **User** (browse catalogue, issue books).  
Authentication, data storage and real-time updates are handled by **Firebase Authentication** and **Cloud Firestore**.:contentReference[oaicite:0]{index=0}

---

## ✨ Features

| Role  | Capability |
|-------|------------|
| Admin | • Login <br> • Add new books (title, author, ID, description) <br> • Remove books <br> • Search catalogue |
| User  | • Sign-up / Login <br> • View available books <br> • Search by title / author <br> • Issue selected books |
| Both  | • Real-time data sync <br> • Clean Material Design UI (RecyclerView lists):contentReference[oaicite:1]{index=1} |

---

## 🧰 Tech Stack

| Layer       | Tools / Libraries |
|-------------|------------------|
| Platform    | **Android** (API 31+) |
| Language    | **Java 8** |
| UI          | Android XML, Material Components |
| Auth        | **Firebase Auth** |
| Database    | **Cloud Firestore** |
| Storage     | Firebase Storage (for book images, if added later) |
| Build tool  | **Gradle 7.3.1** |
| Misc.       | RecyclerView, Navigation Component |

---

## 🛠 Setup
```bash
# 1 Clone the repo
git clone https://github.com/shubhpatel2197/Library-management-system.git
cd Library-management-system

# 2 Open the project in Android Studio (Flamingo or newer)

# 3 Create a Firebase project and add an Android app
#   • Download google-services.json
#   • Place it in app/ folder

# 4 Sync Gradle & run on an emulator / device
