package fing;

import fing.content.*;
import mindustry.mod.Mod;

public class Main extends Mod {
    @Override
    public void loadContent() {
        Factorys.load();
        Liquids.load();

        // 检测加载newhorizon相关的东西
        try {
            Class.forName("newhorizon.content.NHContent");
            fing.content.NHFactorys.load();
        } catch (ClassNotFoundException e) {
            System.out.println("[fing-idea]newhorizon not detected, skipping load Nfing-idea-HFactorys");
        } catch (NoClassDefFoundError err) {
            System.err.println("[fing-idea]Dependency error: " + err.getMessage());
        }
    }
}