pluginManagement {
    includeBuild("build-logic")
}

include (":app")
rootProject.name = "Android Playground"
include(":core:data")
include(":data:cart")
include(":core:preferences")
include(":data:user")
include(":data:category")
include(":data:product")
include(":feature:cart")
include(":core:util")
include(":feature:home")
include(":data:announcement")
include(":feature:splash")
include(":core:logging")
include(":feature:login")
include(":domain:login")
include(":feature:category-list")
include(":feature:profile")
include(":feature:product-detail")
include(":feature:product-list")
include(":feature:product-detail-tabbed")
include(":feature:register")
include(":feature:search")
include(":core:database")
include(":domain:product")
include(":benchmark")
include(":core:session")
include(":core:remoteconfig")
include(":data:pdf")
include(":core:notification")
include(":core:validation")
include(":core:resources")
include(":core:ui:theme")
include(":core:ui:components")
include(":core:test")
