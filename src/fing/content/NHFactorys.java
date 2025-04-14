package fing.content;

import mindustry.content.*;
import mindustry.ctype.UnlockableContent;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.content.TechTree;
import newhorizon.content.*;

public class NHFactorys {
    public static Block QuantumSporePodFactory;
    public static Block T2IrdryonFluidFactory;

    public static void load() {
        // 量子孢子生成器
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

        // 大型化工业液混合器
        T2IrdryonFluidFactory = new GenericCrafter("t2-irdryon-fluid-factory") {{
            requirements (
                    Category.crafting,
                    ItemStack.with(
                            Items.graphite,40,
                            Items.thorium,100,
                            Items.plastanium,90,
                            Items.surgeAlloy, 100,
                            NHItems.presstanium, 350,
                            NHItems.multipleSteel,120,
                            NHItems.seniorProcessor, 230,
                            NHItems.irayrondPanel, 150
                    )
            );
            size = 5;
            craftTime = 60f;
            outputLiquid = new LiquidStack(NHLiquids.irdryonFluid,0.7f);
            consumeItems(
                    new ItemStack(Items.lead, 8),
                    new ItemStack(Items.titanium, 8),
                    new ItemStack(Items.silicon, 12),
                    new ItemStack(Items.surgeAlloy,10 ),
                    new ItemStack(NHItems.zeta,12)
            );
            consumeLiquids(
                    new LiquidStack(NHLiquids.quantumEntity, 0.4f),
                    new LiquidStack(NHLiquids.zetaFluid,0.15f)
            );
            itemCapacity = 30;
            liquidCapacity = 40f;
            hasLiquids = true;
            consumePower(55f);
        }};
        addToNode(NHBlocks.irdryonFluidFactory,() -> node(T2IrdryonFluidFactory) );
    }
        private static TechTree.TechNode context;

        private static void addToNode(UnlockableContent parent, Runnable children){
            context = TechTree.all.find(t -> t.content == parent);
            if (context != null) {
                children.run();
            } else {
                TechTree.nodeRoot("Quantum SporePod Factory",QuantumSporePodFactory, () -> {});
                TechTree.nodeRoot("t2-irdryon-fluid-factory",T2IrdryonFluidFactory, () -> {});
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
