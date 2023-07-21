package com.example.composeplay3.ftabs

sealed class Navs(var title: String, var icon: Int, var screenRoute: String) {


    object BaseOne : Navs(
        title = "BaseOne",
        icon = androidx.core.R.drawable.ic_call_answer,
        screenRoute = "screenBaseOne"
    )

    object BaseTwo : Navs(
        title = "BaseTwo",
        icon = androidx.core.R.drawable.ic_call_answer,
        screenRoute = "screenBaseTwo"
    )

    object BaseThree : Navs(
        title = "BaseThree",
        icon = androidx.core.R.drawable.ic_call_answer,
        screenRoute = "screenBaseThree"
    )

    object Box : Navs(
        title = "Box",
        icon = androidx.core.R.drawable.ic_call_answer,
        screenRoute = "screenBox"
    )

    object Constraint : Navs(
        title = "Constraint",
        icon = androidx.core.R.drawable.ic_call_answer_video,
        screenRoute = "screenConstraint"
    )

    object Row : Navs(
        title = "Row",
        icon = androidx.core.R.drawable.ic_call_answer_video,
        screenRoute = "screenRow"
    )

    object Col : Navs(
        title = "Col",
        icon = androidx.core.R.drawable.ic_call_answer_video,
        screenRoute = "screenCol"
    )


    object SheetOne : Navs(
        title = "SheetOne",
        icon = androidx.core.R.drawable.ic_call_answer_video,
        screenRoute = "sheetOne"
    )
}

var ii = 0
