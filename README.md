# SinoCore / 华夏核心
Core library of SinoCraft Mods.  
华夏系列 Mod 核心库。  

[![](https://jitpack.io/v/SinoCraftProject/SinoCore.svg)](https://jitpack.io/#SinoCraftProject/SinoCore)


Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency

```groovy
dependencies {
    implementation fg.deobf('com.github.SinoCraftProject:SinoCore:main-SNAPSHOT')
}
```

