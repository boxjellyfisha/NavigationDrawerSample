package com.example.deer.navigationdrawersample

data class SelectionData(
        val iconResId: Int = 0,
        val titleResId: Int = 0,
        val url: String = "") {

    companion object {
        @JvmStatic
        fun getSelectionItem(resId: Int): SelectionData? {
            return when (resId) {
                R.id.nav_all -> {
                    SelectionData(
                            R.drawable.icon_all,
                            R.string.app_name,
                            "http://idolish7.com/")
                }
                R.id.nav_one -> {
                    SelectionData(
                            R.drawable.icon_1,
                            R.string.nav_title_1,
                            "http://idolish7.com/profile/iori/")
//                url = "http://idolish7.com/wp-content/themes/idolish/profile/iori.html"
                }
                R.id.nav_two -> {
                    SelectionData(
                            R.drawable.icon_2,
                            R.string.nav_title_2,
                            "http://idolish7.com/profile/nikaido/")
//                url = "http://idolish7.com/wp-content/themes/idolish/profile/nikaido.html"
                }
                R.id.nav_three -> {
                    SelectionData(
                            R.drawable.icon_3,
                            R.string.nav_title_3,
                            "http://idolish7.com/profile/mitsuki/")
//                url = "http://idolish7.com/wp-content/themes/idolish/profile/mitsuki.html"
                }
                R.id.nav_four -> {
                    SelectionData(
                            R.drawable.icon_4,
                            R.string.nav_title_4,
                            "http://idolish7.com/profile/yotsuba/")
//                url = "http://idolish7.com/wp-content/themes/idolish/profile/yotsuba.html"
                }
                R.id.nav_five -> {
                    SelectionData(
                            R.drawable.icon_5,
                            R.string.nav_title_5,
                            "http://idolish7.com/profile/osaka/")
//                url = "http://idolish7.com/wp-content/themes/idolish/profile/osaka.html"
                }
                R.id.nav_six -> {
                    SelectionData(
                            R.drawable.icon_6,
                            R.string.nav_title_6,
                            "http://idolish7.com/profile/rokuya/")
//                url = "http://idolish7.com/wp-content/themes/idolish/profile/rokuya.html"
                }
                R.id.nav_seven -> {
                    SelectionData(
                            R.drawable.icon_7,
                            R.string.nav_title_7,
                            "http://idolish7.com/profile/nanase/")
//                url = "http://idolish7.com/wp-content/themes/idolish/profile/nanase.html"
                }
                else -> null
            }
        }
    }
}
