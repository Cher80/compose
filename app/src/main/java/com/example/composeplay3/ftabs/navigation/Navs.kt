package com.example.composeplay3.ftabs.navigation

sealed class Navs(var title: String, var icon: Int, var screenRoute: String) {

    data object ScreenBaseMain : Navs(
        title = "ScreenBaseMain",
        icon = androidx.core.R.drawable.ic_call_answer,
        screenRoute = "ScreenBaseMain"
    )

    data object ScreenBasePayments : Navs(
        title = "ScreenBasePayments",
        icon = androidx.core.R.drawable.ic_call_decline,
        screenRoute = "ScreenBasePayments"
    )

    data object ScreenBaseSettings : Navs(
        title = "ScreenBaseSettings",
        icon = androidx.core.R.drawable.ic_call_answer_video_low,
        screenRoute = "ScreenBaseSettings"
    )

    data object ScreenSecondProduct : Navs(
        title = "Box",
        icon = androidx.core.R.drawable.ic_call_answer,
        screenRoute = "screenBox"
    )

    data object Constraint : Navs(
        title = "Constraint",
        icon = androidx.core.R.drawable.ic_call_answer_video,
        screenRoute = "screenConstraint"
    )

    data object Row : Navs(
        title = "Row",
        icon = androidx.core.R.drawable.ic_call_answer_video,
        screenRoute = "screenRow"
    )

    data object Col : Navs(
        title = "Col",
        icon = androidx.core.R.drawable.ic_call_answer_video,
        screenRoute = "screenCol"
    )


    data object SheetOne : Navs(
        title = "SheetOne",
        icon = androidx.core.R.drawable.ic_call_answer_video,
        screenRoute = "sheetOne"
    )

    companion object {
        val tabs = listOf(
            ScreenBaseMain, ScreenBasePayments, ScreenBaseSettings
        )

        fun isTab(screenRoute: String): Boolean {
            return tabs.any {
                it.screenRoute == screenRoute
            }
        }
    }
}

var ii = 0
