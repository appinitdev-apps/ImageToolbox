/* #AppInitDev -> Photo Utility Hub */



package com.t8rin.imagetoolbox.core.ui.widget.controls.selection

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import com.t8rin.imagetoolbox.core.resources.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.AddPhotoAlt
import com.t8rin.imagetoolbox.core.resources.icons.File
import com.t8rin.imagetoolbox.core.resources.icons.MiniEdit
import com.t8rin.imagetoolbox.core.resources.shapes.CloverShape
import com.t8rin.imagetoolbox.core.ui.utils.content_pickers.Picker
import com.t8rin.imagetoolbox.core.ui.utils.content_pickers.rememberFilePicker
import com.t8rin.imagetoolbox.core.ui.utils.content_pickers.rememberImagePicker
import com.t8rin.imagetoolbox.core.ui.utils.helper.ContextUtils.rememberFilename
import com.t8rin.imagetoolbox.core.ui.widget.dialogs.OneTimeImagePickingDialog
import com.t8rin.imagetoolbox.core.ui.widget.image.Picture
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.preferences.PreferenceItemOverload

@Composable
fun ImageSelector(
    value: Any?,
    onValueChange: (Uri) -> Unit,
    title: String = stringResource(id = R.string.image),
    subtitle: String?,
    modifier: Modifier = Modifier,
    autoShadowElevation: Dp = 1.dp,
    color: Color = MaterialTheme.colorScheme.surfaceContainerLow,
    shape: Shape = ShapeDefaults.large,
    contentScale: ContentScale = ContentScale.Crop
) {
    val imagePicker = rememberImagePicker(onSuccess = onValueChange)

    var showOneTimeImagePickingDialog by rememberSaveable {
        mutableStateOf(false)
    }

    PreferenceItemOverload(
        title = title,
        subtitle = subtitle,
        onClick = imagePicker::pickImage,
        onLongClick = {
            showOneTimeImagePickingDialog = true
        },
        autoShadowElevation = autoShadowElevation,
        startIcon = {
            Picture(
                contentScale = contentScale,
                model = value,
                shape = CloverShape,
                modifier = Modifier.size(48.dp),
                error = {
                    Icon(
                        imageVector = Icons.TwoTone.AddPhotoAlt,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CloverShape)
                            .background(
                                color = MaterialTheme.colorScheme.secondaryContainer
                                    .copy(0.5f)
                                    .compositeOver(color)
                            )
                            .padding(8.dp)
                    )
                }
            )
        },
        endIcon = {
            Icon(
                imageVector = Icons.Rounded.MiniEdit,
                contentDescription = stringResource(R.string.edit)
            )
        },
        modifier = modifier,
        shape = shape,
        containerColor = color,
        drawStartIconContainer = false
    )

    OneTimeImagePickingDialog(
        onDismiss = { showOneTimeImagePickingDialog = false },
        picker = Picker.Single,
        imagePicker = imagePicker,
        visible = showOneTimeImagePickingDialog
    )
}

@Composable
fun FileSelector(
    value: String?,
    onValueChange: (Uri) -> Unit,
    title: String = stringResource(id = R.string.pick_file),
    subtitle: String?,
    modifier: Modifier = Modifier,
    autoShadowElevation: Dp = 1.dp,
    color: Color = MaterialTheme.colorScheme.surfaceContainerLow,
    shape: Shape = ShapeDefaults.large
) {
    val pickFileLauncher = rememberFilePicker(onSuccess = onValueChange)

    PreferenceItemOverload(
        title = title,
        subtitle = if (subtitle == null && value != null) {
            rememberFilename(value.toUri())
        } else subtitle,
        onClick = pickFileLauncher::pickFile,
        autoShadowElevation = autoShadowElevation,
        startIcon = {
            Picture(
                contentScale = ContentScale.Crop,
                model = value,
                shape = CloverShape,
                modifier = Modifier.size(48.dp),
                error = {
                    Icon(
                        imageVector = Icons.TwoTone.File,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CloverShape)
                            .background(
                                MaterialTheme.colorScheme.secondaryContainer
                                    .copy(0.5f)
                                    .compositeOver(color)
                            )
                            .padding(8.dp)
                    )
                }
            )
        },
        endIcon = {
            Icon(
                imageVector = Icons.Rounded.MiniEdit,
                contentDescription = stringResource(R.string.edit)
            )
        },
        modifier = modifier,
        shape = shape,
        containerColor = color,
        drawStartIconContainer = false
    )
}