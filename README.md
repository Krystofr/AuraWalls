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

## 🛠️ Getting Started

### 1. Clone the repo
```bash
git clone https://github.com/Krystofr/AuraWalls.git
