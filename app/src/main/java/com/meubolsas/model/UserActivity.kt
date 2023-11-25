package com.meubolsas.model


/** This class holds holds user activities in the app.
 * For example: add a new [Bag] to the wishlist or remove
 * one from it. It triggers automatically when the user
 * executes these actions. */
data class UserActivity(
    /** The [action] the user has just executed. */
    val action: String,
    /** The [date] the user has executed it (dd/mm/yyyy hh:mm). */
    val date: String,
)
