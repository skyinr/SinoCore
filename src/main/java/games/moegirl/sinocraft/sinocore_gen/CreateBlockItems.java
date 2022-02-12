package games.moegirl.sinocraft.sinocore_gen;

import com.github.javaparser_new.StaticJavaParser;
import com.github.javaparser_new.ast.expr.Expression;
import lq2007.plugins.gradle_plugin.support.PluginContext;
import lq2007.plugins.gradle_plugin.support.PluginHelper;

import java.nio.file.Path;

public class CreateBlockItems extends DeferredRegisterGenerator {

    private static final String PACKAGE_NAME = "games.moegirl.sinocraft.sinocore.common.block";

    public CreateBlockItems() {
        super("Item", "net.minecraft.world.item.Item",
                "games.moegirl.sinocraft.sinocore.common.block.ModBlockItemRegister",
                "ITEMS");
    }

    @Override
    public void begin(PluginContext context, PluginHelper helper) throws Exception {
        super.begin(context, helper);
        registerUnit.addImport("games.moegirl.sinocraft.sinocore.api.item.TabSinoCore");
        registerUnit.addImport("net.minecraft.world.item.ItemNameBlockItem");
    }

    @Override
    protected Expression buildRegisterInitializer(Path file, String className, String elementName, PluginHelper helper) {
        // () -> new ItemNameBlockItem(ModBlockRegister.[ELEMENT_NAME].get(), new Item.Properties().tab(TabSinoCore.INSTANCE))
        return StaticJavaParser.parseExpression(String.format(
                "() -> new ItemNameBlockItem(ModBlockRegister.%s.get(), new Item.Properties().tab(TabSinoCore.INSTANCE))",
                helper.toUpperName(elementName)
        ));
    }

    @Override
    protected void addElement(Path file, PluginHelper helper, ElementAdder adder) throws Exception {
        String fileName = file.getFileName().toString();
        if (fileName.startsWith("Block") && fileName.endsWith(".java")) {
            String className = fileName.substring(0, fileName.length() - 5);
            String elementName = className.substring(5);
            String classPath = PACKAGE_NAME + "." + className;
            adder.add(className, elementName, classPath);
        }
    }

    @Override
    public Path getLoopRoot(PluginHelper helper) {
        return helper.srcPath().resolve(PACKAGE_NAME.replace(".", "/"));
    }
}
