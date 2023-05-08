# RickAndMortyApp

This application lists characters from the show "Rick and Morty" and provides details about them. The data is fetched using the API provided within the application.

## App Screenshots

<p align="center">
<img src="https://user-images.githubusercontent.com/45354919/236825697-0c342e24-791c-4bd3-acec-98cf7ea8544e.png" width="20%"/>
<img src="https://user-images.githubusercontent.com/45354919/236825749-c8e702db-dd3b-4463-a4b5-32cdec61cc9c.png" width="20%"/>
<img src="https://user-images.githubusercontent.com/45354919/236825817-61ee46a2-20c4-477f-b4f0-e5121cf7c979.png" width="20%"/>
<img src="https://user-images.githubusercontent.com/45354919/236825885-24d4fb38-318a-4d6d-8f0e-666224b303e0.png" width="20%"/>
<img src="https://user-images.githubusercontent.com/45354919/236825994-9aaee70f-c018-4f9b-9029-fb3828923f29.png" width="20%"/>
</p>

## Tech stack & Open-source Libraries
- Minimum SDK level 21
- 100% [Kotlin](https://kotlinlang.org/) based 
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
    -  A single-activity architecture, using the [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started) to manage fragment operations.
    - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
    - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform an action when lifecycle state changes
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
    - [Repository](https://developer.android.com/topic/architecture/data-layer) - Located in data layer that contains application data and business logic.
- [DataBinding](https://developer.android.com/topic/libraries/data-binding) - The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
- [Android Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Dependency Injection Library
- [Room](https://developer.android.com/training/data-storage/room) - Provides an abstraction layer over SQLite for database operations.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Glide](https://github.com/bumptech/glide) - Glide is a fast and efficient open source media management and image loading framework for Android that wraps media decoding, memory and disk caching, and resource pooling into a simple and easy to use interface.
- [Paging3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) - The Paging library helps you load and display pages of data from a larger dataset from local storage or over network efficiently. It has built-in support for handling data refreshes and infinitely scrolling lists. The latest version is Paging3 which is built upon Kotlin coroutines and offers a simpler API compared to its predecessor Paging2.
