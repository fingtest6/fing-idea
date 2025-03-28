package fing.nh;

import mindustry.content.*;
import mindustry.ctype.UnlockableContent;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.content.TechTree;
import newhorizon.content.*;

public class QuantumSporePodFactory {
    public static Block QuantumSporePodFactory;

    public static void load() {
        // 1. 创建工厂
        QuantumSporePodFactory = new GenericCrafter("quantum-spore-pod-factory") {{
            requirements (
                    Category.crafting,
                    ItemStack.with(
                            NHItems.zeta, 65,
                            Items.silicon, 75,
                            NHItems.juniorProcessor, 100,
                            NHItems.presstanium, 125,
                            NHItems.multipleSteel, 150
                    )
            );
            size = 2;
            craftTime = 60f;
            outputItem = new ItemStack(Items.sporePod, 2);
            consumeItems(
                new ItemStack(Items.sand, 1),
                new ItemStack(Items.coal, 1)
            );
            itemCapacity = 10;
            consumeLiquid(NHLiquids.quantumEntity, 0.1f);
            liquidCapacity = 10f;
            hasLiquids = true;
            consumePower(2f);
            researchCostMultiplier = 2f;
        }};
        addToNode(NHBlocks.multipleSteelFactory,() -> node(QuantumSporePodFactory) );
    }
        private static TechTree.TechNode context;

        private static void addToNode(UnlockableContent parent, Runnable children){
            context = TechTree.all.find(t -> t.content == parent);
            if (context != null) {
                children.run();
            } else {
                TechTree.nodeRoot("Quantum SporePod Factory",QuantumSporePodFactory, () -> {});
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
