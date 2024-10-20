package fing;

import mindustry.mod.Mod;
import fing.factory.*;

public class Main extends Mod {

    @Override
    public void loadContent() {
        GiantAlloyFactory.load();   // 第二步：加载工厂
    }
}