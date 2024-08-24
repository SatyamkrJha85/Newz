# 📰 News Application

![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-Android-brightgreen)
![Kotlin](https://img.shields.io/badge/Kotlin-1.5.31-blue)
![Hilt](https://img.shields.io/badge/Hilt-DI-blueviolet)
![Room](https://img.shields.io/badge/Room-Database-yellowgreen)

Welcome to the **News Application**, an Android app designed to keep you updated with the latest news articles from around the world. Built with the latest Android technologies, this app ensures a seamless and modern user experience.

## 📋 Table of Contents
- [Features](#features)
- [Screenshots & Video](#screenshots--video)
- [Architecture](#architecture)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## 🌟 Features
- **Browse News**: Discover news articles from a variety of sources.
- **Article Details**: Read in-depth articles with a user-friendly interface.
- **Search**: Quickly find news articles on specific topics.
- **Save & Share**: Bookmark favorite articles and share them with others.
- **Offline Access**: Access your saved articles even when you're offline.
- **Dark Mode**: Enjoy reading with a built-in dark mode option.

## 📸 Screenshots & Video

| Home Screen                         | Article Details                      | Search Screen                        |
|-------------------------------------|--------------------------------------|--------------------------------------|
| ![Newz](https://github.com/user-attachments/assets/ddc443ec-8565-42f9-8184-36a08e7f80df) | ![Newz](https://github.com/user-attachments/assets/ddc443ec-8565-42f9-8184-36a08e7f80df) | ![Newz](https://github.com/user-attachments/assets/ddc443ec-8565-42f9-8184-36a08e7f80df) |

### 📹 Demo Video
[Watch the demo video](https://github.com/user-attachments/assets/67c60d57-d6df-4db0-a741-4d9fec2d7e58)

## 🏛️ Architecture
The application follows the **MVVM (Model-View-ViewModel)** architecture pattern, ensuring a clean separation of concerns and ease of testing.

- **Model**: Handles data operations, including interaction with Room Database and network calls using Retrofit.
- **View**: Built with Jetpack Compose, the view layer displays the UI and observes the ViewModel for data changes.
- **ViewModel**: Acts as a bridge between the Model and View, managing UI-related data and handling user actions.


## 🛠️ Technologies Used
- **Kotlin**: The official programming language for Android.
- **Jetpack Compose**: Modern toolkit for building native UI.
- **Room**: SQLite abstraction library for persistent storage.
- **Hilt**: Dependency Injection library to manage dependencies.
- **Navigation Component**: Handles app navigation and deep linking.
- **Retrofit**: A type-safe HTTP client for network operations.
- **Coil**: An image loading library optimized for Android.

## 🧑‍💻 Installation
To set up the project locally, follow these steps:

1. **Clone the repository**:
    ```bash
    git clone https://github.com/SatyamkrJha85/Newz
    cd NewsApplication
    ```

2. **Open in Android Studio**:
    - Launch Android Studio.
    - Navigate to `File > Open` and select the cloned project directory.

3. **Build the project**:
    - Make sure you're using the latest version of Android Studio.
    - Build the project via `Build > Make Project`.

4. **Run the application**:
    - Select a connected device or emulator.
    - Click the `Run` button.

## 🚀 Usage
- **Browse News**: Scroll through the list of articles on the home screen.
- **Read Articles**: Tap on an article to view its details.
- **Search**: Use the search bar to find news on specific topics.
- **Bookmark & Share**: Save your favorite articles or share them with others.
- **Switch Themes**: Toggle between light and dark mode for a better reading experience.

## 🤝 Contributing
Contributions are always welcome! To contribute:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/AmazingFeature`).
3. Commit your changes (`git commit -m 'Add some amazing feature'`).
4. Push to the branch (`git push origin feature/AmazingFeature`).
5. Open a Pull Request.

Please make sure to update tests as appropriate.

## 📜 License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📞 Contact
For any questions or feedback, feel free to reach out:

- **Name**: Satyam Jha
- **LinkedIn**: [Satyam Jha](https://www.linkedin.com/in/satyamkrjha/)
- **GitHub**: [SatyamkrJha85](https://github.com/SatyamkrJha85)

---

Thank you for checking out the News Application! If you liked it, don't forget to give the repository a ⭐.



