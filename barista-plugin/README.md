
# Barista Instrumentation Framework
**A gradle plugin for Android testing**

 Tsiskomichelis Stelios - stsiskomixelis@aueb.gr - AUEB 2018 - 2019

---

Overview
---

Barista Plugin is a gradle plugin that works along with the [Barista Library].
 Both together offer a great tool for customizing android instrumentation tests. 
 More specific, it gives the developer an easy way to customize various emulator options 
 that are been applied to all the connected emulators at test time.   
	
    
Barista Plugin Installation
--

   a) Clone the project from the repository
   
   b) Install the plugin to the local maven repository using the command
     
     'gradlew build install'
    
   c) At any android project use the follwing .gradle configurations:

> The following configuration makes sure that gradle will look into the local
> maven repository to find the previous installed plugin. (Later will be
> available in global maven repo). It also sets the classpath of the
> plugin executable as an android build dependency.

   
*Global build.gradle file*
       

     buildscript{
                ...
                repositories{
                    ...
                    mavenLocal()
                }
            
            }
            ...
            dependencies{
                ....
                classpath 'gr.aueb.tsiskos:barista-plugin:1.0-SNAPSHOT'
            }

   

> In order to use the Barista Plugin just declare the usage of the
> plugin using the following code in your app specific .gradle file

  *Module specific build.gradle file (Your main android app module .gradle file)*
        
        plugins{
            ...
              id 'gr.aueb.tsiskos.barista-plugin'
        }
   
   d) When you run **any **android test**** (basically when the *assembleDebugAndroidTest* gradle task is trigered) 
   the plugin should deploy a HTTP server container right before the execution of tests in the emulator. This HTTP server 
   is alive until all the tests are finished. The default listening port of the server is **8040**.  In order to set a specific listening port you can use
   the below configuration structure inside your gradle.build file
   		
   		...
   		baristaSettings {
            	port = 8070
        	}	
        	...	
     

> Normally the http server closes automaticaly after each test build. However there may be cases where this is not happening 
correctly for various reasons. In such cases you have to shutdown the server manually by using http://localhost:8040/barista/kill request from your browser.

After successfully deploying the barista plugin this message should appear in the build output of your android project 

    ...
    :app:assembleDebugAndroidTest
	:app:deployTestDispacherServerTask
    Deploying Server at: http://localhost:8040/barista/
  Now there is a HTTP Server container ready to accept REST requests from the barista library module.



Barista Library Installation
--
The Barista library provides the developer a set of commands in the form of annotations 
that can be used at the instrumentation test in order to invoke adb commands at test time.

1. Clone the library files
2. Install the library to the local maven repository using
	````
	 gradle clean build publishToMavenLocal
	 ```` 
	 
	> Later will be available to the global repository.  
3. Add the library to the classpath.
  Inside the project build.gradle file
 	 ````
  	buildscript {
        repositories {
            ....
            
        }
        dependencies {
            classpath 'com.android.tools.build:gradle:3.2.1'
            classpath 'gr.aueb.android:barista-plugin:1.0-SNAPSHOT'
            classpath 'gr.aueb.android:barista:1.0'
        }
    }
  	````
4. Assign the Barista Instrumentation Test  Listener inside the module specific gradle.build file
	````
	android {
        compileSdkVersion 28
        defaultConfig {
            ...
            testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
            testInstrumentationRunnerArgument "listener", "gr.aueb.android.barista.core.BaristaRunListener"
        }
        ...
    }
  	````
5. Add the dependecies needed for Barista Library to work
		````
			dependencies {
				...
			
				//BARISTA DEPENDENCIES START
				androidTestImplementation 'gr.aueb.android:barista:2.0'
				implementation 'com.squareup.retrofit2:retrofit:2.4.0'
				implementation 'com.jakewharton.timber:timber:4.7.1'
				implementation 'com.squareup.retrofit2:converter-jackson:2.4.0'
				implementation 'com.squareup.okhttp3:okhttp:3.6.0'
				implementation 'com.squareup.okhttp3:logging-interceptor:3.6.0'
				//BARISTA DEPENDENCIES END
				...
			}  	
		````		
	> Later those dependecies will be included into the library.


How it works
--
    
- Workflow
	1. Barista plugin task checks if target project is Android. Check based on imported plugins and packages
	2. If it is an Android Project, check in the gradle task graph if there is a task named "assembleDebugAndroidTest"
	   This indicates that a test is about to take place

	3. Just be before the instrumentation tests run, The HTTP server is deployed and access tokens are shared among the connected emulators.
		This way each time a request arrives the server knows to which emulator a command should be executed.   
	4. At test time the Barista Run Listener parses the barista annotation of each test, 
	constructs an appropriate command requests and sends it to the server. 
	5. The server receives which command(s) to execute to which emulator and does so.
	6. The emulator now has the requested settings and the body of the instrumentation test is executed.
	7. After the completion of each test the emulator is reseted to the previous/default settings
	8. Step 4 is executed again until all tests are finished.
	9. When all tests are finished the server shuts down. 
	
- How android emulator communicates with the host machine http server    
	AVD emulators : 10.0.2.2 (https://developer.android.com/studio/run/emulator-networking)
	Many factors can affect the communication between the emulator and the host machine such as a local firewall.
	
	
	Genymotion  emulators: 10.0.3.2 (Found in stackoverflow commnets https://stackoverflow.com/questions/6760585/accessing-localhostport-from-android-emulator)
	Not yet tested. 
	TO SEARCH: Is Genymotion a popular choise for android testing among devs?

Supported Commands
--

#### Screen Options
#### GeoFix
#### Battery Options
#### Connectivity Options
#### Permission Provider



## How to use
---	