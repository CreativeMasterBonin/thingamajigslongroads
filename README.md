Thingamajigs 2: Long Roads
---

This is an addon mod for Thingamajigs 2.

Long Roads adds asphalt, road markings with paintbrushes and data-driven dynamic road signs.


<br><br>
Contributing? Easy steps ahead.

Just clone and open the project in your IDE of choice, and let gradle setup everything for you. Then run `build` to get a copy of the mod to test in your `build/libs` directory.

To run a test of MC with the mod and dependencies, run the application `Client`, and not the gradle `runClient` as it allows hotswapping when wanted. You need to run those tasks in `Debug` mode to allow advanced changes such as hotswapping.

The `build.gradle` file has a line in it called `jvmArgument("-XX:+AllowEnhancedClassRedefinition")` which enables hotswapping when using a `jbr-XX` java version.
<br><br><br><br>
NOT AN OFFICIAL MINECRAFT PRODUCT. NOT AFFILIATED WITH MOJANG STUDIOS OR MICROSOFT.