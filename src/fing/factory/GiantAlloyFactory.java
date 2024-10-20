package fing.factory;

import mindustry.content.*;
import mindustry.ctype.UnlockableContent;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.content.TechTree;
import mindustry.world.draw.DrawMulti;
import mindustry.world.draw.DrawRegion;
import mindustry.world.draw.*;

public class GiantAlloyFactory {
    public static Block GiantAlloyFactory;

    public static void load() {
        // 1. 创建工厂
        GiantAlloyFactory = new GenericCrafter("Giant-alloy-factory") {{
            // 配置建造材料和研究条件
            requirements(
                    Category.crafting,
                    ItemStack.with(
                            Items.graphite, 160,
                            Items.silicon, 140,
                            Items.titanium, 200,
                            Items.plastanium, 120,
                            Items.surgeAlloy, 100,
                            Items.thorium, 200,
                            Items.phaseFabric, 100
                    )
            );

            // 其他配置
            size = 5;
            craftTime = 30f;
            outputItem = new ItemStack(Items.surgeAlloy, 25);
            consumeItems(
                    new ItemStack(Items.titanium, 8),
                    new ItemStack(Items.plastanium, 10),
                    new ItemStack(Items.silicon, 9),
                    new ItemStack(Items.sporePod, 6),
                    new ItemStack(Items.sand, 16)
            );
            itemCapacity = 50;
            consumeLiquid(Liquids.slag, 1f);
            liquidCapacity = 10f;
            hasLiquids = true;
            consumePower(55f);
            researchCostMultiplier = 2f;
        }};
        addToNode(Blocks.surgeSmelter, () -> node(GiantAlloyFactory));
    }

        private static TechTree.TechNode context;

        private static void addToNode(UnlockableContent parent, Runnable children) {
            context = TechTree.all.find(t -> t.content == parent);
            if (context != null) {
                children.run();
            } else {
                TechTree.nodeRoot("Giant Alloy Factory", GiantAlloyFactory, () -> {});
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