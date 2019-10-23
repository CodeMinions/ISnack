package me.codeminions.factory.data.model

import me.codeminions.factory.data.bean.SnackInfo

data class SnackInfoModel(var ingredient: String,       // 成分名
                          var quality: String,          // 含量
                          var day: String = "...")      // 日摄入


fun snackInfoUnpack(snackInfo: SnackInfo): List<SnackInfoModel> {

    val list = ArrayList<SnackInfoModel>()
    val fields = snackInfo.javaClass.declaredFields

    for (field in fields) {
        field.isAccessible = true

        val name = field.name

        if (field.type.toString() != "class java.lang.String" || field[snackInfo] == null || field[snackInfo] == 0)
            continue
        val value = field[snackInfo] as String
        // 对数据进行筛选,为空或为0不加入列表

        list.add(SnackInfoModel(translation(name), value))
    }
    return list
}

fun translation(atom: String): String {
    when (atom) {
        "salt" -> return "盐"
        "sugar" -> return "糖"
        "carbon_water" -> return "碳水化合物"
        "heat_quantity" -> return "能量"
        "protein" -> return "蛋白质"
        "fat" -> return "脂肪"
        "dietary_fiber" -> return "膳食纤维"
        "Na" -> return "钠"
        "vitaminD" -> return "维生素D"
        "Ca" -> return "钙"
    }
    return "未知信息"
}
