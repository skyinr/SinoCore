package games.moegirl.sinocraft.sinocore_gen;

import com.github.javaparser_new.StaticJavaParser;
import com.github.javaparser_new.ast.expr.Expression;
import lq2007.plugins.gradle_plugin.support.PluginHelper;

import java.nio.file.Path;

public class CreateBlocks extends DeferredRegisterGenerator {

    private static final String PACKAGE_NAME = "games.moegirl.sinocraft.sinocore.common.block";

    public CreateBlocks() {
        super("Block", "net.minecraft.world.level.block.Block",
                "games.moegirl.sinocraft.sinocore.common.block.ModBlockRegister",
                "games.moegirl.sinocraft.sinocore.api.block.ModBlocks",
                "BLOCKS");
    }

    @Override
    protected Expression buildRegisterInitializer(Path file, String className, String elementName, PluginHelper helper) {
        return StaticJavaParser.parseExpression(className + "::new");
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
