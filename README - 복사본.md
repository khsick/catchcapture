

# CatchCapture(캐치캡쳐)
CatchCapture(캐치캡쳐) (CatchCapture) using `screenshotDetection ,ananas lib`

(https://raw.githubusercontent.com/akexorcist/Android-ScreenshotDetection/master/Images/screenshot_001.gif)
(https://github.com/iamutkarshtiwari/Ananas/blob/master/static/main_menu.gif?raw=true)

## Usage
```java
ShotWatch mShotWatch;


void onCreate() {
	Listener listener = new Listener() {
		public void onScreenShotTaken(ScreenShotData data) {
			textView.setText(data.getFileName());
			imageView.setImageURI(URI.parse(data.getPath());
		}
	}

	mShotWatch = new ShotWatch(getContentResolver(), listener);
}

// Register to begin receive event
void onResume() {
	mShotWatch.register();
}

// Don't forget to unregister when apps goes to background
void onPause() {
	mShotWatch.unregister();
}
```

## Permission
`android.permission.READ_EXTERNAL_STORAGE`

In Android M, you need to handle runtime permission.

## Gradle
`compile 'com.abangfadli.shotwatch:shotwatch:1.0.4'`
