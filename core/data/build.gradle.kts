/* #AppInitDev -> Photo Utility Hub */



plugins {
    alias(libs.plugins.image.toolbox.library)
    alias(libs.plugins.image.toolbox.hilt)
}

android.namespace = "com.t8rin.imagetoolbox.core.data"

dependencies {
    api(libs.coil)
    api(libs.coilNetwork)
    api(libs.ktor)
    api(libs.ktor.logging)
    implementation(libs.coilGif)
    implementation(libs.coilSvg)
    implementation(libs.trickle)

    implementation(libs.androidx.compose.ui.graphics)

    api(libs.datastore.preferences.android)
    api(libs.datastore.core.android)

    implementation(libs.avif.coder.coil) {
        exclude(module = "com.github.awxkee:avif-coder")
    }
    implementation(libs.avif.coder)
    implementation(libs.jxl.coder.coil) {
        exclude(module = "com.github.awxkee:jxl-coder")
    }
    implementation(libs.jxl.coder)

    implementation(libs.aire)
    implementation(libs.jpegli.coder)

    implementation(libs.moshi)
    implementation(libs.moshi.adapters)

    api(libs.androidx.documentfile)

    implementation(libs.toolbox.gifConverter)
    implementation(libs.toolbox.exif)
    implementation(libs.tiffdecoder)
    implementation(libs.toolbox.qoiCoder)
    implementation(libs.toolbox.jp2decoder)
    implementation(libs.toolbox.awebp)
    implementation(libs.toolbox.psd)
    implementation(libs.toolbox.apng)
    implementation(libs.toolbox.djvuCoder)
    implementation(libs.pdfbox)
    implementation(libs.trickle)

    implementation(projects.core.domain)
    implementation(projects.core.resources)

    implementation(projects.core.filters)

    implementation(projects.core.settings)
    implementation(projects.core.di)
    api(projects.core.utils)
}