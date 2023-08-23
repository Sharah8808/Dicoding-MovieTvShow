package com.jetpack.submission.movietvshow.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.jetpack.submission.movietvshow.R
import com.jetpack.submission.movietvshow.utils.Dummy
import com.jetpack.submission.movietvshow.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    private val dummyMovie = Dummy.getDummyMovie()
    private val dummyTvShow = Dummy.getDummyTvShow()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearsDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0)
            )
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
            )
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_language)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_synopsis)).perform(ViewActions.scrollTo()).check(matches(isDisplayed()))
        onView(withId(R.id.img_bg_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.img_main_poster)).check(matches(isDisplayed()))

    }

    @Test
    fun loadTvShow(){
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tv_show))
            .check(matches(isDisplayed()))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0)
            )
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
            )
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_language)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_synopsis)).perform(ViewActions.scrollTo()).check(matches(isDisplayed()))
        onView(withId(R.id.img_bg_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.img_main_poster)).check(matches(isDisplayed()))
    }

    @Test
    fun loadListFavoriteMovies(){
        onView(withId(R.id.fab_favorite_movie)).perform(click())
        onView(withId(R.id.rv_fav_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))
    }

    @Test
    fun loadListFavoriteTvShow(){
        loadTvShow()
        onView(withId(R.id.fab_favorite_tvShow)).perform(click())
        onView(withId(R.id.rv_fav_tvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_tvShow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))
    }
}