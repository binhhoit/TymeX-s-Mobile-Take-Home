# TymeX’s Mobile Take Home

## Getting set up
+ Run on Android Studio version :
  Android Studio Hedgehog | 2023.1.1
  Build #Al-231.9392.1.2311.11076708, built on November 9, 2023
+ SDK: 34
+ Java SDK 17/13 (jvmTarget = 17)


## Overview how to used TymeX’s Mobile Take Home
* In the application have 3 screens:
    ```
    - Welcome Screen: load data and show logo app.
    - Dashboard Screen: shows all list user GitHub, can load more users if the user scrolls over 20 items.
    - Profile Details Screen: list detail info user (location, avatar, URL page, follower, following).
    ```  
* In the app auto cache data and show again when you open the next time (do not need internet).
* UnitTest I only write simply for `ViewModel`
* UITest only example page DashboardViewmodel, ProfileDetailsViewmodel.

## Architectures

* Single Activity (1 *Explicitly* Activity per project)
* MVVM (Model-View-ViewModel)
* DI using Koin

We use a simple architecture of `Fragment` -> `LifecycleObserver` -> `ViewModel` -> `Usecase`-> `Repo` -> `Service`

## Code Styles

This project uses KtLint, this can be run from the command line via `gradlew ktlintFormat`

## Testing
'TymeX’s Mobile Take Home' have taken ownership of UI tests, using Appium/Espresso (can using firebase test lab apply) . Unit tests should be written for `ViewModel`, and above. Fragments and Activities will not be test, so the least amount of code the better.

* Folders:
  ** `test` is used for unit tests that are not Android specific (jvm based tests)

* Naming:
  ** Unit tests - use backticks

* Running tests:
```
- When running tests via the ui (using the play button), make sure your Build Variant is in Debug mode.
- Open "build.gradle(app):119" You can click button play run all test into project and generated coverage report. 
**Commands to run project tests:
- For local unit tests: "./gradlew testStagingDebugUnitTest", this can also be access in the gradle sidebar.
```

## Data Type Objects
When modelling (above of all for model-objects coming from the server), try to keep `data` classes for simple
data holding structures. Try to keep data models inside a model / models folder, this will help Proguard. If not possible, please annotate with `@Keep`
#### Example
`data class Account(val id: String, val name: String)`

## UI declarations in XML
When declaring a new `@+id/`:

1. Keep it _lowercase_snake_case_
2. Add some information on it by prefixing it with what you're doing. (`@+id/feature1_id`, `@+id/feature2_id`)

## Logging
Only use `Timber` to log.

## Build Variants

We use: Debug, and Release. Release will make the app not debuggable and enable Proguard

## Build Flavors

We use Dev, Staging, and Prod


