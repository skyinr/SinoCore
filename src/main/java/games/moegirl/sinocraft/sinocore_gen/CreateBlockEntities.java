package games.moegirl.sinocraft.sinocore_gen;

import com.github.javaparser_new.StaticJavaParser;
import com.github.javaparser_new.ast.expr.Expression;
import lq2007.plugins.gradle_plugin.support.PluginContext;
import lq2007.plugins.gradle_plugin.support.PluginHelper;

import java.nio.file.Files;
import java.nio.file.Path;

public class CreateBlockEntities extends DeferredRegisterGenerator {

    private static final String PACKAGE_NAME = "games.moegirl.sinocraft.sinocore.common.blockentity";
    private static final String BLOCK_PATH = "games/moegirl/sinocraft/sinocore/common/block";

    public CreateBlockEntities() {
        super("BlockEntityType<?>",
                "net.minecraft.world.level.block.entity.BlockEntityType",
                "games.moegirl.sinocraft.sinocore.common.blockentity.ModBlockEntityRegister",
                "games.moegirl.sinocraft.sinocore.api.blockentity.ModBlockEntities",
                "BLOCK_ENTITIES");
    }

    @Override
    public void begin(PluginContext context, PluginHelper helper) throws Exception {
        super.begin(context, helper);
        registerUnit.addImport("games.moegirl.sinocraft.sinocore.common.block.ModBlockRegister");
    }

    @Override
    protected Expression buildRegisterInitializer(Path file, String className, String elementName, PluginHelper helper) {
        return StaticJavaParser.parseExpression(String.format(
                "() -> BlockEntityType.Builder.of(%s::new, ModBlockRegister.%s.get()).build(null)",
                className, helper.toUpperName(elementName)
        ));
    }

    @Override
    protected void addElement(Path file, PluginHelper helper, ElementAdder adder) throws Exception {
        String fileName = file.getFileName().toString();
        if (fileName.startsWith("BlockEntity") && fileName.endsWith(".java")) {
            String className = fileName.substring(0, fileName.length() - 5);
            String elementName = className.substring(11);
            String classPath = PACKAGE_NAME + "." + className;

            Path blockFile = helper.srcPath().resolve(BLOCK_PATH).resolve("Block" + elementName + ".java");
            if (Files.isRegularFile(blockFile)) {
                adder.add(className, elementName, classPath);
            } else {
                System.out.println("Find block entity " + className
                        + " but not found block games.moegirl.sinocraft.sinocore.common.block.Block" + elementName);
            }
        }
    }

    @Override
    public Path getLoopRoot(PluginHelper helper) {
        return helper.srcPath().resolve(PACKAGE_NAME.replace(".", "/"));
    }
}
