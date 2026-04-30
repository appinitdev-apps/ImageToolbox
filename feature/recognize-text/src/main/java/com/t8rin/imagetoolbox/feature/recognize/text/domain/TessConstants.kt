/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.recognize.text.domain


internal object TessConstants {

    const val LANGUAGE_CODE = "%s.traineddata"

    const val TESSERACT_DATA_DOWNLOAD_URL_BEST =
        "https://github.com/tesseract-ocr/tessdata_best/raw/refs/heads/main/%s.traineddata"
    const val TESSERACT_DATA_DOWNLOAD_URL_STANDARD =
        "https://github.com/tesseract-ocr/tessdata/raw/refs/heads/main/%s.traineddata"
    const val TESSERACT_DATA_DOWNLOAD_URL_FAST =
        "https://github.com/tesseract-ocr/tessdata_fast/raw/refs/heads/main/%s.traineddata"

    const val KEY_PRESERVE_INTERWORD_SPACES = "preserve_interword_spaces"
    const val KEY_CHOP_ENABLE = "chop_enable"
    const val KEY_USE_NEW_STATE_COST = "use_new_state_cost"
    const val KEY_SEGMENT_SEGCOST_RATING = "segment_segcost_rating"
    const val KEY_ENABLE_NEW_SEGSEARCH = "enable_new_segsearch"
    const val KEY_LANGUAGE_MODEL_NGRAM_ON = "language_model_ngram_on"
    const val KEY_TEXTORD_FORCE_MAKE_PROP_WORDS = "textord_force_make_prop_words"
    const val KEY_EDGES_MAX_CHILDREN_PER_OUTLINE = "edges_max_children_per_outline"
}