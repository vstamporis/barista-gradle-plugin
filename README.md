Barista - A gradle plugin for android testing

Project name: barista-plugin
Author: Tsiskomichelis Stelios - 2018 
Email: stsiskomichelis@aueb.gr

0.  Overview

1. How to use
    a) Clone the project from the repository
    b) Install the plugin to the local maven repository using the command 'gradle build install'
    c) At any android project use the follwing .gradle configurations:

       >> Global build.gradle file
            
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

        
       >> Module specific build.gradle file (Your main android app module .gradle file)
        
        plugins{
            ...
              id 'gr.aueb.tsiskos.barista-plugin'
        }
        
     d) When you run any android test the plugin should deploy a HTTP server container right before the execution of tests in the emulator.
     
     IMPORTANT: Ignore the message that he server is listening to http://localhost:8080. For the time given the listening URI is http://localhost:8040
     To ensure proper functionality, visiting http://localhost:8040/barista/status should promt the message  "Hello World from Jersey Servlet Container"
     To shutdown the server use http://localhost:8040/barista/kill request