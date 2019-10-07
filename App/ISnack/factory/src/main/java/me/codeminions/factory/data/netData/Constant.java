package me.codeminions.factory.data.netData;

import java.util.Arrays;
import java.util.List;

import me.codeminions.factory.R;
import me.codeminions.factory.data.bean.Snack;

public class Constant {

    public static List<Snack> list = Arrays.asList(new Snack("乐事薯片", 8.1f),
            new Snack(1, "旺仔牛奶", 8.1f),
            new Snack(2, "士力架", 9.1f),
            new Snack(3, "巧克力", 9.8f),
            new Snack(4, "白色恋人", 3.1f),
            new Snack(5, "坚硬坚果", 2.1f),
            new Snack(6, "冰淇淋", 8.5f));

    public static List<Integer> imgs = Arrays.asList(R.drawable.le,
            R.drawable.wang,
            R.drawable.default_snack_pic,
            R.drawable.coo,
            R.drawable.white,
            R.drawable.guo,
            R.drawable.ice);
}
