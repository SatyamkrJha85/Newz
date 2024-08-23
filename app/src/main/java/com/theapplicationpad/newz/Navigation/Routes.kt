package com.theapplicationpad.newz.Navigation

sealed class Routes (val routes: String){
    object Home:Routes("Home")
    object Search:Routes("Search")
    object Save:Routes("Save")
    object Details : Routes("Details/{url}") // Define route with argument

}

