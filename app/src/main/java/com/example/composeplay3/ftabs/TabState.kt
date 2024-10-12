package com.example.composeplay3.ftabs

import com.example.composeplay3.ftabs.navigation.Navs

data class TabState(
    val nav: Navs,
    val selected: Boolean
)