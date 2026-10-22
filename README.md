# Word Counter

A lightweight Android writing utility for counting characters, non-whitespace characters, words and manually separated lines. Includes native Android clipboard Paste and Copy, Clear, a responsive dark interface and full offline support.

## Download the Android APK

Open the [Android build workflow](https://github.com/Averydimbulb79/word_counter/actions/workflows/android-apk.yml), select the latest successful run and download the **WordCounter-Android-APK** artifact. Unzip it and install `app-debug.apk` on your Android device. GitHub sign-in may be required to access workflow artifacts.

If no run appears, choose **Run workflow** from the workflow page. The APK is a debug build intended for personal installation, not a Play Store release.

## Counting rules

- Characters: Unicode code points, including whitespace.
- Without whitespace: characters excluding spaces, tabs and line breaks.
- Words: sequences separated by whitespace, including punctuation attached to a word.
- Lines: explicit newline-separated lines, including empty lines. Word wrapping on screen does not create a new line.

## Build locally

Requires JDK 17, Android SDK Platform 35 and Gradle 8.9.

```bash
gradle assembleDebug
```

APK output: `app/build/outputs/apk/debug/app-debug.apk`.

## Version

1.0.0. All text processing is local; the app requests no internet permission.
