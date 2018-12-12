## Jenkins setup on Ubuntu with HockeyApp

**Step 1: Install Jenkins on Ubuntu**
* We have to setup Java JDK first with root environment. If you are not setup please go this url https://www.liquidweb.com/kb/how-to-install-oracle-java-8-in-ubuntu-16-04/
* Go to this url to get information about jenkins setup https://jenkins.io/doc/book/installing/#debian-ubuntu
* Click on `latest stable Jenkins WAR file` to download war file in WAR section.
* Open terminal and go to downloaded war folder and Run this command `java -jar jenkins.war`.
  ```java
     username:~$ cd Downloads/  
     username:~/Downloads$ java -jar jenkins.war

     ```
     
* Uninstall Jenkins from Ubuntu
  ```java
  sudo apt-get remove --purge jenkins
  ```


**Step 2: Setup Jenkins Profile**
* Go to browser and open `localhost:8080`. Jenkins open login form with this
`/var/lib/jenkins/secrets/initialAdminPassword`
* Please open terminal and type this on terminal
```java
sudo cat /var/lib/jenkins/secrets/initialAdminPassword
```
and pressed enter. Terminal show password please copy terminal password and paste jenkins form and click setup.
* Now after that, Jenkins setup all plugin which have required. So please wait some time and after completed jenkins plugins  go to next.
* Now jenkins open form page. Please fill all field like `username`, `password`, and `email` ext. After that Jenkins open Home page where you have create job

**Step 3: Setup Jenkins Configure System**
* Go to `manage Jenkins` from jenkins home page. Your URL is `http://localhost:8080/manage`.
* Go to `Configure System`. Your URL is `http://localhost:8080/configure`.
* Go to `Global properties` point. Here you have to set Android Home and android SDK path. So please follow this Step.
  * Click on `Environment variables` checkbox and fill this field
  * `Name` - `ANDROID_HOME`
  * `Value` - `/home/umesh/Android/Sdk` // Your android sdk path
* Go to `Extended E-mail Notification` point.
  * Fill this field
  * `SMTP server` - `smtp.gmail.com`
  * `Default user E-mail suffix` - `@gmail.com`
* Go to `E-mail Notification` point
  * Fill this field
  * `SMTP server` - `smtp.gmail.com`
  * `Default user E-mail suffix` - `@gmail.com`
  * Click `Advanced...` on right side
  * Fill this field
  * Check Enable `Use SMTP Authentication`
  * `User Name` - `umesh.bsa@gmail.com` // Your Email-id
  * `Password` - `123456789` // Any string here, not required email password
  * Check Enable `Use SSL`
  * `SMTP Port` - `456`
  * `Reply-To Address` - noreply@gmail.com
  * `Charset` - `UTF-8`
  * `Test configuration by sending test e-mail`. please fill your email-id and  click to `Test configuration button` after some time you got message on your email-id.
  * Click apply and save
  
**Step 4: Setup Jenkins Global Tool Configuration**
* Go to `manage Jenkins` from jenkins home page. Your URL is `http://localhost:8080/manage`.
* Go to `Global Tool Configuration`. Your URL is `http://localhost:8080/configureTools/`.
* Now you have to setup JDK, Gradle and Git
* Setup JDK
  * Fill this field
  * `Name` - `JAVA_HOME`
  * `JAVA_HOME` - `/home/umesh/Documents/software/android-studio/jre` // your jdk path
* Setup git. Git is already setup. If not then follow this.
  * `Name` - `Default`
  * `Path to Git executable` - `git`
* Setup Gradle
  * `Name` - `Gradle 4.4`
  * Check Enable `Install automatically`
  * Set gradle version `Gradle 4.4`
* Click apply and save.

**Step 5: Create Job**
* Click on `New Item` from Jenkins Home Page.
* Now `Enter an item name` Example. `My First Android Project`
* Select `Freestyle project` and click `OK` button. Now your URL is `http://localhost:8080/job/My%20First%20Android%20Project/configure`
* **Fill all field on this project configure** 
  * Enter `Description` Example  `This is my first android project on jenkins`
  * Enable `Git`
    * `Repository URL` - `https://github.com/umeshbsa/android-setup-jenkins-  
    automation.git` // this is your git repository url.
    * `Credentials` - `jenkins password`
  * Build
    * Select `Invoke Gradle script` from `App build step` from dropdown.
    * Enable `Invoke Gradle` with `Gradle Version` - `Gradle 4.4
    * Enter `Task` - `clean build --stacktrace --debug`<br/>

  * **Jenkins Script in Task field**
    
    Script to create all debug apk: 
    ```java 
       clean assembleDebug --stacktrace
    ```
    Script to create all release apk: 

    ```java
      clean assembleRelease --stacktrace
    ```
    Script to create selected product flavor debug apk:
    ```java 
       clean assembleTest1Debug assembleTest2Debug --stacktrace
    ```
    Script to create one release apk:
    ```java
       clean assembleTest1Release --stacktrace
    ```
    
    <img src="https://github.com/umeshbsa/android-setup-jenkins-automation/blob/master/jenkins0.png"/><br/>
  * Post-build Actions
    * Select `Archive the artifacts` from `Add post-build action` dropdown  
    * `	Files to archive` - `**/*.apk` // It will be show error but don't worry.
    * Now select `Upload to HockeyApp` from `Add post-build action` dropdown 
    * First of all you add plugin HockeyApp in Jenkins so that follow this step.
    * Go to `Manage Jenkins` -> `Manage Plugin` -> `Available` -> `Search By HockeyApp Plugin` and install it.
    * Now Login with HockeyApp account
    * Now click on right side button. it will show `Account Settings` click on it.
    * Click `API Token` and create `API Token` and copy and paste on Jenkins. 
    * Add HockeyApp `API Token` - `46c642196f212ca00d32d87514fa98c8`
    * Enable `Upload App` from `Upload Method`
    * `	App File (.ipa, .app.zip, .apk)` - `**/*.apk`
    * Enable `Allow Download`<br/>
    <img src="https://github.com/umeshbsa/android-setup-jenkins-automation/blob/master/jenkins1.png"/><br/>
 * Apply and save.

**Step 6: Build Now**
 * Go to Jenkins Home page
 * Click on `Build Now`. It will take few minutes
 * If you want to see `Console`. Click on circle(0 #1) in `Build History`
 * After create APK file you can see suucess message in `Console`
 * ```java
   Build step 'Invoke Gradle script' changed build result to SUCCESS
   Archiving artifacts
   Uploading to HockeyApp...
   /var/lib/jenkins/workspace/My New Project/app/build/outputs/apk/debug/app-  
   debug.apk
   HockeyApp Upload Speed: 539.71Kbps
   /var/lib/jenkins/workspace/My New 
   Project/app/build/outputs/apk/release/app-release-unsigned.apk
   HockeyApp Upload Speed: 1.11Mbps
   Finished: SUCCESS
   ```
  * Now go to HockeyApp and you can see your APK.
  
  
#### Licence

    Copyright 2018 Umesh Kumar

    Licensed under the Apache License, Version 2.0 (the "License")
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
