/* #AppInitDev -> Photo Utility Hub */



@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        includeBuild("build-logic")
        gradlePluginPortal()
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        maven("https://jitpack.io") { name = "JitPack" }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        maven("https://androidx.dev/storage/compose-compiler/repository") {
            name = "Compose Compiler Snapshots"
            content { includeGroup("androidx.compose.compiler") }
        }
        mavenCentral()
        maven("https://jitpack.io") { name = "JitPack" }
        maven("https://oss.sonatype.org/content/repositories/snapshots/") {
            name = "Sonatype Snapshots"
            mavenContent {
                snapshotsOnly()
            }
        }
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "ImageToolbox"

include(":app")

include(":feature:main")
include(":feature:pick-color")
include(":feature:image-stitch")
include(":core:filters")
include(":feature:filters")
include(":feature:draw")
include(":feature:erase-background")
include(":feature:single-edit")
include(":feature:pdf-tools")
include(":feature:resize-convert")
include(":feature:palette-tools")
include(":feature:delete-exif")
include(":feature:compare")
include(":feature:weight-resize")
include(":feature:image-preview")
include(":feature:cipher")
include(":feature:limits-resize")
include(":feature:crop")
include(":feature:load-net-image")
include(":feature:recognize-text")
include(":feature:watermarking")
include(":feature:gradient-maker")
include(":feature:gif-tools")
include(":feature:apng-tools")
include(":feature:zip")
include(":feature:jxl-tools")
include(":feature:media-picker")
include(":feature:quick-tiles")
include(":feature:settings")
include(":feature:easter-egg")
include(":feature:svg-maker")
include(":feature:format-conversion")
include(":feature:document-scanner")
include(":feature:scan-qr-code")
include(":feature:image-stacking")
include(":feature:image-splitting")
include(":feature:color-tools")
include(":feature:webp-tools")
include(":feature:noise-generation")
include(":feature:collage-maker")
include(":feature:libraries-info")
include(":feature:markup-layers")
include(":feature:base64-tools")
include(":feature:checksum-tools")
include(":feature:mesh-gradients")
include(":feature:edit-exif")
include(":feature:image-cutting")
include(":feature:audio-cover-extractor")
include(":feature:library-details")
include(":feature:wallpapers-export")
include(":feature:ascii-art")
include(":feature:ai-tools")
include(":feature:color-library")

include(":feature:root")

include(":core:settings")
include(":core:resources")
include(":core:data")
include(":core:domain")
include(":core:ui")
include(":core:di")
include(":core:crash")
include(":core:ksp")
include(":core:utils")

include(":lib:neural-tools")
include(":lib:collages")
include(":lib:ascii")
include(":lib:opencv-tools")
include(":lib:documentscanner")
include(":lib:snowfall")
include(":lib:curves")
include(":lib:dynamic-theme")
include(":lib:palette")
include(":lib:modalsheet")
include(":lib:qrose")
include(":lib:cropper")
include(":lib:colors")
include(":lib:gesture")
include(":lib:image")
include(":lib:zoomable")

include(":benchmark")