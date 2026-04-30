/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.feature.filters.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.t8rin.imagetoolbox.core.filters.presentation.widget.FilterReorderSheet
import com.t8rin.imagetoolbox.core.filters.presentation.widget.addFilters.AddFiltersSheet
import com.t8rin.imagetoolbox.core.ui.utils.helper.isPortraitOrientationAsState
import com.t8rin.imagetoolbox.core.ui.utils.navigation.Screen
import com.t8rin.imagetoolbox.core.ui.widget.sheets.PickImageFromUrisSheet
import com.t8rin.imagetoolbox.feature.filters.presentation.components.addEditMaskSheet.AddEditMaskSheet
import com.t8rin.imagetoolbox.feature.filters.presentation.screenLogic.FiltersComponent

@Composable
internal fun FiltersContentSheets(
    component: FiltersComponent
) {
    val isPortrait by isPortraitOrientationAsState()

    if (component.filterType is Screen.Filter.Type.Basic) {
        val transformations by remember(component.basicFilterState, component.imageInfo) {
            derivedStateOf(component::getFiltersTransformation)
        }

        PickImageFromUrisSheet(
            transformations = transformations,
            visible = component.isPickImageFromUrisSheetVisible,
            onDismiss = component::hidePickImageFromUrisSheet,
            uris = component.basicFilterState.uris,
            selectedUri = component.basicFilterState.selectedUri,
            onUriPicked = component::updateSelectedUri,
            onUriRemoved = component::updateUrisSilently,
            columns = if (isPortrait) 2 else 4,
        )

        AddFiltersSheet(
            visible = component.isAddFiltersSheetVisible,
            onDismiss = component::hideAddFiltersSheet,
            previewBitmap = component.previewBitmap,
            onFilterPicked = component::addFilterNewInstance,
            onFilterPickedWithParams = component::addFilter,
            component = component.addFiltersSheetComponent,
            filterTemplateCreationSheetComponent = component.filterTemplateCreationSheetComponent
        )

        FilterReorderSheet(
            filterList = component.basicFilterState.filters,
            visible = component.isReorderSheetVisible,
            onDismiss = component::hideReorderSheet,
            onReorder = component::updateFiltersOrder
        )
    } else if (component.filterType is Screen.Filter.Type.Masking) {
        AddEditMaskSheet(
            visible = component.isAddFiltersSheetVisible,
            targetBitmapUri = component.maskingFilterState.uri,
            onMaskPicked = component::addMask,
            onDismiss = component::hideAddFiltersSheet,
            masks = component.maskingFilterState.masks,
            component = component.addMaskSheetComponent
        )

        MaskReorderSheet(
            maskList = component.maskingFilterState.masks,
            visible = component.isReorderSheetVisible,
            onDismiss = component::hideReorderSheet,
            onReorder = component::updateMasksOrder
        )
    }
}