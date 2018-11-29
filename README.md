# Retrofit-LiveData-Adapter
![badge](https://img.shields.io/badge/version-0.0.1-brightgreen.svg?style=plastic)

Adapter for Retrofit 2 that allows using LiveData objects as return a return type for API definitions

LiveData is part of the Android Jetpack :rocket: Architecture components, and using it with Retrofit is not that hard 
(but google doesn't bothers about explaining how to :unamused:), so you can use add this library to your project to
define an adapter. (like that RxJava adapter, yay!).

The source code is inside the **library** folder / module.

# How do I use it?
Simple. First, replace all your ``Call<T>`` references with ``LiveData<ApiResult<T>>`` references in your API definitions interface class.

This means, from:

**MyAwesomeAPIService.kt**

    interface MyAwesomeAPIService {
      @GET("/mtndew/flavors")
      fun fetchMtnDewFlavors():Call<List<MtnDewCan>>
    }

To:

**MyAwesomeAPIService.kt**

    interface MyAwesomeAPIService {
      @GET("/mtndew/flavors")
      fun fetchMtnDewFlavors():LiveData<ApiResult<List<MtnDewCan>>>
    }

Then, add the adapter to your retrofit instance.

**YourRetrofitDependencyInjectorOfSomeSort.kt**

	Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(LiveDataAdapter.Factory()) // <-- Define the LiveData adapter Factory here!
            .build()
            .create(MyAwesomeAPIService::class.java)

## Whoa there! 🤠 What is that **ApiResult** class? 🤔

The **[ApiResult.kt class](https://github.com/AlfredoBejarano/Retrofit-LiveData-Adapter/blob/master/library/src/main/java/me/alfredobejarano/retrofitadapters/data/ApiResult.kt)**
is a model class provided in the library too, it contains your response object inside its body property, a code is also contained and an error string is also there
somewhere in the class if you want some of that **spicy error handling** (that you should **definitely have**), it comes as a String.

What to do with that error string?, You can print it as it is, it can be a JSON/XML string too! _(if the server is responding with custom JSON errors, **that it should be**)_ so
you can use [GSON](https://github.com/google/gson), [Jackson](https://github.com/FasterXML/jackson) or whatever parsing tool you want to handle the custom error response error or something like that.

You can also use it to poke your eyes out but, why would you? _(actually you can't, it is a String, is not a physical object you dummy!)_

## So... I don't want to use this **ApiResult** class of yours

You can set the return type of your call as a <b>LiveData</b> containing your response object type, the value will be set as <b>null</b> if <b>any exception happens</b> during the call.

![dangerous](https://i.ibb.co/hYVvcST/dangerous.png)

_You know how dangerous that technique can be, right?_

Handling the error of your API call only because you got a <b>null</b> result is <b>a really bad idea!</b> as you don't have any information of what went wrong, (and the customer care deparment is going to love you when a user calls with an <b>oopsie, something wrong happened!</b> message and you don't know what happened!)

Anyway, if you really want to (or you are lazy enough to), just set the return type to LiveData.

# TLDR, MAY I HAVE SOME ADAPTER, BRÖTHER?

Yeah sure, add it to your project like this:

``implementation 'me.alfredobejarano.retrofitadapters:livedata:0.0.1'``

Enjoy! :happy:

_Made with :heart: by **@AlfredoBejarano**_
