#Trakt.tv
This project contains case study challenge for Software Engineer (Android) role of Trivago GmbH.

##Tasks:
- Connect with [Trakt.tv](http://docs.trakt.apiary.io/)
- List of currently 10 most popular [Movies](http://docs.trakt.apiary.io/#reference/movies/popular)
- Load more and scrollable list of movies ( handle pagination)
- Search [Movies](http://docs.trakt.apiary.io/#reference/search)
- Show Result with title, release, overview and a picture.
- Load more and scrollable list of search results movies (handle pagination) 


##Used Features:
- [Retrofit 2](http://square.github.io/retrofit/)
- [Dagger 2](http://google.github.io/dagger/)
- [RxJava/RxAndroid](https://github.com/ReactiveX/RxAndroid)
- [Robotium](https://github.com/RobotiumTech/robotium)*
- [Picasso](http://square.github.io/picasso/)
- [GSON](https://github.com/google/gson)
- [RecyclerView,CardView](https://developer.android.com/training/material/lists-cards.html)
- [AppCompat v24](https://developer.android.com/topic/libraries/support-library/features.html)

## Supported Device :
I don't have much time to handle multi android version support, But I think it must be OK on Android 5+.
As I tested on Nexus series it would be Ok and works as well as possible !
CAUTION: Some Functionality might not work properly in Android version lower than 5 !!!

## Installation:
You can download APK (app/app-release.apk) file from this [link](https://bitbucket.org/LordK1/trakt.tv/raw/ece7ef2413c7a3235993daa655af5928ace511aa/app/app-release.apk),
 
Or make it from source as below :

    git clone git@bitbucket.org:LordK1/trakt.tv.git 
    // or git clone https://LordK1@bitbucket.org/LordK1/trakt.tv.git
    cd Trakt.tv
    ./gradlew clean
    ./gradlew packageDebug
    adb install app/build/outputs/apk/app-debug.apk


##Demonstration:

In this simple sample I just try to use most up to dated technologies, third parties and libraries in android development,
As an Android developer you always must be aware of what's going on, some momentary solutions
cause some big issues in the future.<br/>
* I'm so sorry, but I really want to make Test Modules with Robotium or Essperso but I didn't have sufficient time. :"(

### It's my pleasure to hear your feedbacks :")
