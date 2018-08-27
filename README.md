Backing App created as a part of Udacity [Android Developer Nanodegree Program](https://www.udacity.com/course/android-developer-nanodegree-by-google--nd801).

![alt text](http://images.vfl.ru/ii/1535398414/9e3d21fd/23081697.jpg)

## Used Libraries
Dependency Injections:
* [Dagger 2](https://github.com/google/dagger)
* [ButterKnife](https://github.com/JakeWharton/butterknife)
___
HTTP:
* [Retrofit 2](https://github.com/square/retrofit) with [RxJava adapter](https://github.com/square/retrofit/tree/master/retrofit-adapters/rxjava2) and [Gson converter](https://github.com/square/retrofit/tree/master/retrofit-converters/gson)
* [OkHttp](https://github.com/square/okhttp)
___
React:
* [RxJava 2](https://github.com/ReactiveX/RxJava)
___
Other:
* [Retrolambda](https://github.com/evant/gradle-retrolambda)
* [Glide](https://github.com/bumptech/glide)
* [ExoPlayer](https://github.com/google/ExoPlayer)

## Covers
This App features covers the following aspects:
* MediaPlayer/Exoplayer to display videos.
* Widget.
* Fragments (lifecycle / navigation / bugs).
* Responsive design that works on phones and tablets.

## Rubric

### General App Usage
- [x] App should display recipes from provided network resource.
- [x] App should allow navigation between individual recipes and recipe steps.
- [x] App uses RecyclerView and can handle recipe steps that include videos or images.
- [x] App conforms to common standards found in the Android Nanodegree General Project Guidelines.

### Components and Libraries
- [x] Application uses Master Detail Flow to display recipe steps and navigation between them.
- [x] Application uses Exoplayer to display videos.
- [x] Application properly initializes and releases video assets when appropriate.
- [x] Application should properly retrieve media assets from the provided network links. It should properly handle network requests.
- [x] Application makes use of Espresso to test aspects of the UI.
- [x] Application sensibly utilizes a third-party library to enhance the app's features. That could be helper library to interface with Content Providers if you choose to store the recipes, a UI binding library to avoid writing findViewById a bunch of times, or something similar.

### Homescreen Widget
- [x] Application has a companion homescreen widget.
- [x] Widget displays ingredient list for desired recipe.
