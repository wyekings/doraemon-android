package com.wyekings.sunflower.ui.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.wyekings.sunflower.R

/**
 *  @author wangpt on 9/7/23
 */
enum class SunflowerPage(
    @StringRes val titleResId: Int,
    @DrawableRes val iconResId: Int,
) {
    MY_GARDEN(R.string.my_garden_title,R.drawable.ic_my_garden_active),
    PLANT_LIST(R.string.plant_list_title,R.drawable.ic_plant_list_active)
}