package com.jetpack.submission.movietvshow.utils

import com.jetpack.submission.movietvshow.R
import com.jetpack.submission.movietvshow.core.response.*
import com.jetpack.submission.movietvshow.model.MovieModel
import com.jetpack.submission.movietvshow.model.TvShowModel

object Dummy {
    fun getDummyMovie(): List<MovieModel>{
        val listMovies = ArrayList<MovieModel>()

        listMovies.add(
            MovieModel(
                0,
                "Alita: Battle Angle (2019)",
                "14/02/2019",
                "Action, Science Fiction",
                "eng",
                7.3,
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "https://image.tmdb.org/t/p/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                false
            )
        )

        listMovies.add(
            MovieModel(
                1,
                "Aquaman (2018)",
                "21/12/2018",
                "Action, Adventure, Fantasy",
                "eng",
                6.9,
                "Once home to the most advanced civilization on Earth, Atlantis is now an " +
                        "underwater kingdom ruled by the power-hungry King Orm. With a vast army at " +
                        "his disposal, Orm plans to conquer the remaining oceanic people and then " +
                        "the surface world. Standing in his way is Arthur Curry, Orms half-human, " +
                        "half-Atlantean brother and true heir to the throne.",
                "https://image.tmdb.org/t/p/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                false
            )
        )

        listMovies.add(
            MovieModel(
                2,
                "Bohemian Rhapsody (2018)",
                "02/11/2018",
                "Drama, Music",
                "eng",
                7.9,
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass " +
                        "guitarist John Deacon take the music world by storm when they form the rock " +
                        " and roll band Queen in 1970. Hit songs become instant classics. When Mercury" +
                        " s increasingly wild lifestyle starts to spiral out of control, Queen soon " +
                        "faces its greatest challenge yet – finding a way to keep the band together " +
                        "amid the success and excess.",
                "https://image.tmdb.org/t/p/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                false
            )
        )

        listMovies.add(
            MovieModel(
                3,
                "Fantastic Beasts: The Crimes of Grindelwald (2018)",
                "16/11/2018",
                "Adventure, Fantasy, Drama",
                "eng",
                6.5,
                "Gellert Grindelwald has escaped imprisonment and has begun gathering " +
                        "followers to his cause—elevating wizards above all non-magical beings. The " +
                        "only one capable of putting a stop to him is the wizard he once called his" +
                        " closest friend, Albus Dumbledore. However, Dumbledore will need to seek" +
                        "help from the wizard who had thwarted Grindelwald once before, his former " +
                        "student Newt Scamander, who agrees to help, unaware of the dangers that lie " +
                        "ahead. Lines are drawn as love and loyalty are tested, even among the truest" +
                        " friends and family, in an increasingly divided wizarding world.",
                "https://image.tmdb.org/t/p/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                false
            )
        )

        listMovies.add(
            MovieModel(
                4,
                "Glass (2019)",
                "18/01/2019",
                "Thriller, Drama, Sci-fi",
                "eng",
                6.6,
                "In a series of escalating encounters, former security guard David Dunn uses " +
                        "his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who" +
                        " has twenty-four personalities. Meanwhile, the shadowy presence of Elijah " +
                        "Price emerges as an orchestrator who holds secrets critical to both men.",
                "https://image.tmdb.org/t/p/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                false
            )
        )

        listMovies.add(
            MovieModel(
                5,
                "How to Train Your Dragon: The Hidden World (2019)",
                "22/02/2019",
                "Animation, Family, Adventure",
                "eng",
                7.5,
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ " +
                        "discovery of an untamed, elusive mate draws the Night Fury away. When danger" +
                        " mounts at home and Hiccup’s reign as village chief is tested, both dragon " +
                        "and rider must make impossible decisions to save their kind.",
                "https://image.tmdb.org/t/p/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                false
            )
        )
        return listMovies
    }

    fun getDummyTvShow(): List<TvShowModel>{
        val listTvShow = ArrayList<TvShowModel>()

        listTvShow.add(
            TvShowModel(
                0,
                "Alita: Battle Angle (2019)",
                "14/02/2019",
                "Action, Science Fiction",
                "eng",
                7.3,
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "https://image.tmdb.org/t/p/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                false
            )
        )

        listTvShow.add(
            TvShowModel(
                1,
                "Aquaman (2018)",
                "21/12/2018",
                "Action, Adventure, Fantasy",
                "eng",
                6.9,
                "Once home to the most advanced civilization on Earth, Atlantis is now an " +
                        "underwater kingdom ruled by the power-hungry King Orm. With a vast army at " +
                        "his disposal, Orm plans to conquer the remaining oceanic people and then " +
                        "the surface world. Standing in his way is Arthur Curry, Orms half-human, " +
                        "half-Atlantean brother and true heir to the throne.",
                "https://image.tmdb.org/t/p/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                false
            )
        )

        listTvShow.add(
            TvShowModel(
                2,
                "Bohemian Rhapsody (2018)",
                "02/11/2018",
                "Drama, Music",
                "eng",
                7.9,
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass " +
                        "guitarist John Deacon take the music world by storm when they form the rock " +
                        " and roll band Queen in 1970. Hit songs become instant classics. When Mercury" +
                        " s increasingly wild lifestyle starts to spiral out of control, Queen soon " +
                        "faces its greatest challenge yet – finding a way to keep the band together " +
                        "amid the success and excess.",
                "https://image.tmdb.org/t/p/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                false
            )
        )

        listTvShow.add(
            TvShowModel(
                3,
                "Fantastic Beasts: The Crimes of Grindelwald (2018)",
                "16/11/2018",
                "Adventure, Fantasy, Drama",
                "eng",
                6.5,
                "Gellert Grindelwald has escaped imprisonment and has begun gathering " +
                        "followers to his cause—elevating wizards above all non-magical beings. The " +
                        "only one capable of putting a stop to him is the wizard he once called his" +
                        " closest friend, Albus Dumbledore. However, Dumbledore will need to seek" +
                        "help from the wizard who had thwarted Grindelwald once before, his former " +
                        "student Newt Scamander, who agrees to help, unaware of the dangers that lie " +
                        "ahead. Lines are drawn as love and loyalty are tested, even among the truest" +
                        " friends and family, in an increasingly divided wizarding world.",
                "https://image.tmdb.org/t/p/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                false
            )
        )

        return listTvShow
    }

    fun generateDetailMovie() : List<MovieDetail>{
        val movie = ArrayList<MovieDetail>()
        val genres = ArrayList<Genre>()
        genres.add(Genre(
            1,
            "Action"
        ))

        movie.add(MovieDetail(
            0,
            "Mortal Kombat",
            "2021-04-07",
            7.8,
            "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
            "https://image.tmdb.org/t/p/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
            genres,
            "en",


            ))
        return movie
    }

    fun generateDetailTv() : List<TvShowDetail>{
        val tv = ArrayList<TvShowDetail>()
        val genres = ArrayList<Genre>()
        genres.add(Genre(
            1,
            "Action"
        ))

        tv.add(TvShowDetail(
            0,
            "Alita: Battle Angle (2019)",
            "14/02/2019",
            7.3,
            "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
            "https://image.tmdb.org/t/p/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
            genres,
            "en",
            )
        )

        return tv
    }

    fun generateMovieResponse() : List<Movie>{
        val movies = ArrayList<Movie>()

        movies.add(Movie(460465,
            "Mortal Kombat",
            "2021-04-07",
            7.8,
            "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
            "https://image.tmdb.org/t/p/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg"))

        movies.add(Movie(460465,
            "Mortal Kombat",
            "2021-04-07",
            7.8,
            "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
            "https://image.tmdb.org/t/p/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg"))

        movies.add(Movie(460465,
            "Mortal Kombat",
            "2021-04-07",
            7.8,
            "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
            "https://image.tmdb.org/t/p/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg"))

        movies.add(Movie(460465,
            "Mortal Kombat",
            "2021-04-07",
            7.8,
            "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
            "https://image.tmdb.org/t/p/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg"))

        movies.add(Movie(460465,
            "Mortal Kombat",
            "2021-04-07",
            7.8,
            "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
            "https://image.tmdb.org/t/p/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg"))

        movies.add(Movie(460465,
            "Mortal Kombat",
            "2021-04-07",
            7.8,
            "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
            "https://image.tmdb.org/t/p/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg"))

        return movies
    }

    fun generateTvShowResponse() : List<TvShow>{
        val tvShows = ArrayList<TvShow>()

        tvShows.add(
            TvShow(88396,
                "The Falcon and the Winter Soldier",
                "2021-03-19",
                7.9,
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "https://image.tmdb.org/t/p/6kbAMLteGO8yyewYau6bJ683sw7.jpg",

            ))

        tvShows.add(
            TvShow(88396,
                "The Falcon and the Winter Soldier",
                "2021-03-19",
                7.9,
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "https://image.tmdb.org/t/p/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            ))

        tvShows.add(
            TvShow(88396,
                "The Falcon and the Winter Soldier",
                "2021-03-19",
                7.9,
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "https://image.tmdb.org/t/p/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            ))

        tvShows.add(
            TvShow(88396,
                "The Falcon and the Winter Soldier",
                "2021-03-19",
                7.9,
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "https://image.tmdb.org/t/p/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            ))

        return tvShows
    }

    fun getDetailTv() : TvShowModel {
        return TvShowModel(
            0,
            "Alita: Battle Angle (2019)",
            "14/02/2019",
            "Action",
            "en",
            7.3,
            "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
            "https://image.tmdb.org/t/p/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
            false
        )
    }

    fun getDetailMovie() : MovieModel{
        return MovieModel(
            0,
            "Mortal Kombat",
            "2021-04-07",
            "Action",
            "en",
            7.8,
            "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
            "https://image.tmdb.org/t/p/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
            false
        )
    }
}