package fing.content;

import mindustry.content.*;
import mindustry.ctype.UnlockableContent;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.liquid.LiquidRouter;
import mindustry.content.TechTree;

public class Liquids {
    public static Block alloytank;
    public static Block alloyContainer;
    public static Block DenseCompressionAlloyTank;

    public static void load() {
        alloyContainer = new LiquidRouter("alloy-container") {{
            requirements(
                    Category.liquid,
                    ItemStack.with(
                            Items.metaglass, 10,
                            Items.surgeAlloy, 20
                    )
            );
            size = 2;
            liquidCapacity = 3000;
        }};
        addToNode(Blocks.liquidTank,() -> node(alloyContainer));

        alloytank = new LiquidRouter("alloy-tank") {{
            requirements(
                    Category.liquid,
                    ItemStack.with(
                            Items.metaglass, 25,
                            Items.phaseFabric,15,
                            Items.surgeAlloy, 50
                    )
            );
            size = 3;
            liquidCapacity = 7000;
        }};
        addToNode(alloyContainer,() -> node(alloytank));

        DenseCompressionAlloyTank = new LiquidRouter("dense-compression-alloy-tank") {{
            requirements(
                    Category.liquid,
                    ItemStack.with(
                            Items.metaglass, 1000,
                            Items.phaseFabric,1500,
                            Items.surgeAlloy, 2000
                    )
            );
            size = 2;
            liquidCapacity = 20000;
            health = 5000;
        }};
        addToNode(alloytank,() -> node(DenseCompressionAlloyTank ));
    }

    private static TechTree.TechNode context;

    private static void addToNode(UnlockableContent parent, Runnable children) {
        context = TechTree.all.find(t -> t.content == parent);
        if (context != null) {
            children.run();
        } else {
            TechTree.nodeRoot("AlloyContainer", alloyContainer, () -> {});
            TechTree.nodeRoot("AlloyTank", alloytank, () -> {});
            TechTree.nodeRoot("DenseCompressionAlloyTank", DenseCompressionAlloyTank, () -> {});
        }
    }

    private static void node(UnlockableContent content, Runnable children) {
        TechTree.TechNode prev = context;
        context = new TechTree.TechNode(context, content, content.researchRequirements());
        children.run();
        context = prev;
    }

    private static void node(UnlockableContent content) {
        node(content, () -> {});
    }
}
