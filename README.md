> [!CAUTION]
> This repository is no longer maintained and has been merged into our new monorepo: [https://github.com/FancyMcPlugins/fancyplugins](https://github.com/FancyMcPlugins/fancyplugins).
>
> For the latest updates, issues, and contributions, please visit the monorepo. This repo remains available for historical reference but will no longer receive updates.
>
> Read [this blog article](https://docs.fancyplugins.de/blog/why-monorepo/) to learn more about the reasons, why we switched to a monorepo.


# How to use

```kotlin
repositories {
    maven("https://repo.fancyplugins.de/releases/")
    // or
    maven("https://repo.fancyplugins.de/snapshots/")
}

dependencies {
    implementation("de.oliver:FancyLib:<version>")
}
```
