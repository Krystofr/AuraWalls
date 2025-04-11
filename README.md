# 🖼️ Jetpack Compose Wallpaper App – Pexels API Client

A simple and elegant HD wallpaper browsing app built with **Kotlin** and **Jetpack Compose**, powered by the [Pexels API](https://www.pexels.com/api/). It supports high-quality wallpaper browsing by category, setting wallpapers, favoriting images, and even playing curated videos — all following a scalable **Clean Architecture** approach.

---

## 🚀 Features

- 🖼️ Browse stunning wallpapers across categories: Animals, Cars, Scenery, Nature, Fashion
- 🔎 Real-time search with Flow + Coroutines (as-you-type results)
- 🧑‍🎨 View image author details and open profile in Chrome Custom Tabs
- 🏞️ Set image as **home screen** or **lock screen** wallpaper
- 📤 Share images with other apps
- ❤️ Favorite wallpapers saved locally with Room
- 📹 Stream **popular videos** via ExoPlayer

---

## 🧱 Tech Stack & Libraries

| Layer         | Technology                                  |
|---------------|---------------------------------------------|
| Language      | Kotlin                                      |
| UI Toolkit    | Jetpack Compose                             |
| Architecture  | MVVM + Clean Architecture                   |
| Async         | Coroutines, Flow                            |
| Data Layer    | Retrofit, Room                              |
| DI            | Dagger-Hilt                                 |
| Media         | ExoPlayer                                   |
| Navigation    | Jetpack Navigation Compose                  |
| API           | [Pexels API](https://pexels.com/api)        |

---

## 🧠 Architecture Overview

This app follows **Clean Architecture** with distinct layers for:
- `data`: Retrofit service, Room DB, repository implementation
- `domain`: models and use cases
- `presentation`: Composables, ViewModels, navigation

## App Screenshots

### Start Screen
![Screenshot_20250411-172716](https://github.com/user-attachments/assets/6895118a-2281-4250-8d6d-9811652e06bf)

### Home Screen
![Screenshot_20250411-171106](https://github.com/user-attachments/assets/cf9f3e06-9952-479b-ad13-2a5583f98f70)

### Search Screen
![Screenshot_20250411-171301](https://github.com/user-attachments/assets/44b10110-b9f7-4388-8eaf-1da43522551e)

### Set Wallpaper
![Screenshot_20250411-171318](https://github.com/user-attachments/assets/e42d3687-1c14-45bd-b629-542f950582ba)
![Screenshot_20250411-171323](https://github.com/user-attachments/assets/4617e9ce-4871-4f9f-acfb-503b13259614)

### Author Profile
![Screenshot_20250411-171358](https://github.com/user-attachments/assets/2de48900-a978-4f25-b066-20b4f317e9a6)

### Account
![Screenshot_20250411-171454](https://github.com/user-attachments/assets/61758c99-028c-48bc-b737-4245ca0ce21b)
![Screenshot_20250411-171532](https://github.com/user-attachments/assets/9b6ff47b-91b4-4fe0-94b9-9ebe285596c0)



## 🛠️ Getting Started

### 1. Clone the repo
```bash
git clone https://github.com/Krystofr/AuraWalls.git
