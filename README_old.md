
# Barista 
**A gradle plugin for Android testing**

   

>  Project name: barista-plugin
>     Author: Tsiskomichelis Stelios - 2018 
>     Email: stsiskomixelis@aueb.gr

**0.  Overview**
    
**1. How to use**

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
   
   d) When you run **any **android test**** (basically when the *assembleDebugAndroidTest* gradle task is trigered) the plugin should deploy a HTTP server container right before the execution of tests in the emulator.
     

> For the time given the listening URI is http://localhost:8040. Later build should allow the user to determin at least listening port.
>      To ensure proper functionality, visiting http://localhost:8040/barista/status should promt the message  *"Hello World from Jersey Servlet Container"*
>      To shutdown the server use http://localhost:8040/barista/kill request

After successfully deploying the barista plugin this message should appear in the build output of your android project 

    ...
    :app:assembleDebugAndroidTest
	:app:deployTestDispacherServerTask
    Deploying Server at: http://localhost:8040/barista/
  Now there is a HTTP Server container ready to accept REST requests from the barista library module.

**2.  How it works**
    
        - Workflow
            1. Barista plugin task checks if target project is Android. Check based on imported plugins and packages
            2. If it is an Android Project, check in the gradle task graph if there is a task named "assembleDebugAndroidTest"
               This indicates that a test is about to takee place (find more ddocumentation).

            3. 
            4.
            5.
            
        - How android emulator communicates with the host machine http server    
            AVD emulators : 10.0.2.2 (https://developer.android.com/studio/run/emulator-networking)
            Many factors can affect the communication between the emulator and the host machine such as a local firewall.
            
            
            Genymotion  emulators: 10.0.3.2 (Found in stackoverflow commnets https://stackoverflow.com/questions/6760585/accessing-localhostport-from-android-emulator)
            Not yet tested. 
            TO SEARCH: Is Genymotion a popular choise for android testing among devs?