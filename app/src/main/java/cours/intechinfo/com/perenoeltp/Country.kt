package cours.intechinfo.com.perenoeltp

data class Country(val name: String,
                   val childCount: Int,
                   var giftsCount: Int = (childCount * 0.6).toInt())