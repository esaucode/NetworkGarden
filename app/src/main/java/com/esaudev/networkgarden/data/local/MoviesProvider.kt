package com.esaudev.networkgarden.data.local

import com.esaudev.networkgarden.R
import com.esaudev.networkgarden.domain.model.Movie
import com.esaudev.networkgarden.domain.model.MovieCatalog

object MoviesProvider {

    private val moviesForYou = listOf(
        Movie(
            name = "Pesadillas",
            imageRes = R.drawable.ic_featured_1
        ),
        Movie(
            name = "Aquaman",
            imageRes = R.drawable.ic_featured_2
        ),
        Movie(
            name = "The Terminator",
            imageRes = R.drawable.ic_featured_3
        ),
        Movie(
            name = "Jojo Rabbit",
            imageRes = R.drawable.ic_featured_4
        ),
        Movie(
            name = "Maquinas mortales",
            imageRes = R.drawable.ic_featured_5
        )
    )

    private val moviesForPremier = listOf(
        Movie(
            name = "X-men",
            imageRes = R.drawable.ic_premier_1
        ),
        Movie(
            name = "IT",
            imageRes = R.drawable.ic_premier_2
        ),
        Movie(
            name = "El niño",
            imageRes = R.drawable.ic_premier_3
        ),
        Movie(
            name = "Kung fu panda",
            imageRes = R.drawable.ic_premier_4
        ),
        Movie(
            name = "1917",
            imageRes = R.drawable.ic_premier_5
        )
    )

    val movieCatalog = listOf(
        MovieCatalog(
            catalog = "Recomendados para ti",
            movies = moviesForYou
        ),
        MovieCatalog(
            catalog = "Ultimos lanzamientos",
            movies = moviesForPremier
        )
    )

}