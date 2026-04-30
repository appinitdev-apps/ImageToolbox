/* #AppInitDev -> Photo Utility Hub */



plugins {
    alias(libs.plugins.image.toolbox.library)
    alias(libs.plugins.image.toolbox.hilt)
    alias(libs.plugins.image.toolbox.compose)
}

android.namespace = "com.t8rin.imagetoolbox.core.ui"

dependencies {
    api(projects.core.resources)
    api(projects.core.domain)
    api(projects.core.utils)
    implementation(projects.core.di)
    implementation(projects.core.settings)

    // Navigation
    api(libs.decompose)
    api(libs.decomposeExtensions)

    //AndroidX
    api(libs.activityCompose)
    api(libs.splashScreen)
    api(libs.appCompat)
    api(libs.androidx.documentfile)

    //Konfetti
    //api(libs.konfetti.compose)

    //Coil
    api(libs.coil)
    api(libs.coilCompose)
    api(libs.coilGif)
    api(libs.coilSvg)
    api(libs.coilNetwork)
    api(libs.ktor)

    //Modules
    api(libs.toolbox.uCrop)
    api(projects.lib.cropper)
    api(projects.lib.dynamicTheme)
    api(projects.lib.colors)
    api(projects.lib.gesture)
    api(projects.lib.image)
    api(projects.lib.modalsheet)
    api(projects.lib.zoomable)
    api(projects.lib.snowfall)
    api(libs.toolbox.histogram)

    api(libs.reorderable)

    api(libs.shadowGadgets)
    api(libs.shadowsPlus)

    api(libs.kotlinx.collections.immutable)

    api(libs.fadingEdges)
    api(libs.scrollbar)

    implementation(libs.datastore.preferences.android)
    implementation(libs.datastore.core.android)
    api(libs.material)

    "marketImplementation"(platform(libs.firebase.bom))
    "marketImplementation"(libs.firebase.crashlytics)
    "marketImplementation"(libs.firebase.analytics)
    "marketImplementation"(libs.review.ktx)
    "marketImplementation"(libs.app.update)
    "marketImplementation"(libs.app.update.ktx)

    "marketImplementation"(libs.mlkit.document.scanner)
    "fossImplementation"(projects.lib.documentscanner)

    "marketImplementation"(libs.quickie.bundled)
    "fossImplementation"(libs.quickie.foss)
    implementation(libs.zxing.core)

    implementation(projects.lib.qrose)

    implementation(libs.jsoup)

    api(libs.androidliquidglass)
    api(libs.capsule)
    api(libs.squircle.shape)

    api(libs.evaluator)

    api(libs.flinger)
}