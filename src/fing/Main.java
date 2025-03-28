package fing;

import fing.nh.QuantumSporePodFactory;
import mindustry.mod.Mod;
import fing.factory.*;

public class Main extends Mod {
    @Override
    public void loadContent() {
        GiantAlloyFactory.load();
        QuantumSporePodFactory.load();
    }
}