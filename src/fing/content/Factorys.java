package fing.content;

import mindustry.content.*;
import mindustry.content.Liquids;
import mindustry.ctype.UnlockableContent;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.content.TechTree;

public class Factorys {
    public static Block GiantAlloyFactory;
    public static Block MixedPlastaniumFactory;
    public static Block PlastaniumPulverizer;

    public static void load() {
        //巨型高压合金工厂
        GiantAlloyFactory = new GenericCrafter("Giant-alloy-factory") {{
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
            liquidCapacity = 80f;
            hasLiquids = true;
            consumePower(55f);
            researchCostMultiplier = 2f;
        }};
        addToNode(Blocks.surgeSmelter, () -> node(GiantAlloyFactory));

        //混合塑钢工厂
        MixedPlastaniumFactory = new GenericCrafter("Mixed-Plastanium-factory") {{
            requirements(
                    Category.crafting,
                    ItemStack.with(
                            Items.lead, 120,
                            Items.graphite, 60,
                            Items.silicon, 120,
                            Items.metaglass,60,
                            Items.titanium, 200,
                            Items.plastanium, 120,
                            Items.surgeAlloy, 50
                    )
            );
            size = 3;
            craftTime = 180f;
            outputItem = new ItemStack(Items.plastanium, 6);
            consumeItem(
                    Items.titanium, 6
            );
            itemCapacity = 12;
            consumeLiquid(
                    Liquids.water, 0.4f
            );
            liquidCapacity = 36f;
            hasLiquids = true;
            consumePower(12f);
        }};
        addToNode(Blocks.plastaniumCompressor, () -> node(MixedPlastaniumFactory));

        //塑料粉化工厂
        PlastaniumPulverizer = new GenericCrafter("plastanium-pulverizer") {{
            requirements(
                    Category.crafting,
                    ItemStack.with(
                            Items.copper, 30,
                            Items.silicon, 20,
                            Items.plastanium, 40,
                            Items.surgeAlloy, 20
                    )
            );
            size = 2;
            craftTime = 30f;
            outputItem = new ItemStack(Items.scrap, 3);
            consumeItems(
                    new ItemStack(Items.plastanium, 2)
            );
            itemCapacity = 10;
            consumePower(1f);
        }};
        addToNode(Blocks.pulverizer, () -> node(PlastaniumPulverizer));
    }

        private static TechTree.TechNode context;

        private static void addToNode(UnlockableContent parent, Runnable children) {
            context = TechTree.all.find(t -> t.content == parent);
            if (context != null) {
                children.run();
            } else {
                TechTree.nodeRoot("Giant Alloy Factory", GiantAlloyFactory, () -> {});
                TechTree.nodeRoot("Mixed Plastanium Factory", MixedPlastaniumFactory, () -> {});
                TechTree.nodeRoot("Plastanium Pulverizer", PlastaniumPulverizer, () -> {});
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
