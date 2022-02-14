package games.moegirl.sinocraft.codegen;

import com.github.javaparser_new.StaticJavaParser;
import com.github.javaparser_new.ast.CompilationUnit;
import com.github.javaparser_new.ast.Modifier;
import com.github.javaparser_new.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser_new.ast.body.TypeDeclaration;
import com.github.javaparser_new.ast.nodeTypes.modifiers.NodeWithStaticModifier;
import lq2007.plugins.gradle_plugin.support.EnumLoopResult;
import lq2007.plugins.gradle_plugin.support.ISourcePlugin;
import lq2007.plugins.gradle_plugin.support.PluginContext;
import lq2007.plugins.gradle_plugin.support.PluginHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class CreateCapability implements ISourcePlugin {

    private static final String CAPABILITY_PKG = "games.moegirl.sinocraft.sinocore.api.capability";

    private CompilationUnit unit;
    private ClassOrInterfaceDeclaration declaration;

    private final Map<String, String> capNames = new HashMap<>();

    private Path srcPath = null;

    @Override
    public void begin(PluginContext context, PluginHelper helper) throws Exception {


        Path capabilityPath = srcPath(helper)
                .resolve(CAPABILITY_PKG.replace(".", "/"))
                .resolve("ModCapabilities.java");
        if (Files.isRegularFile(capabilityPath)) {
            unit = StaticJavaParser.parse(capabilityPath);
        } else {
            unit = new CompilationUnit(CAPABILITY_PKG);
            unit.setStorage(capabilityPath);
        }

        if (unit.getPrimaryType().isEmpty()) {
            unit.addClass(unit.getPrimaryTypeName().orElseThrow(IOException::new)).setPublic(true);
        }

        declaration = unit.getPrimaryType()
                .map(TypeDeclaration::asClassOrInterfaceDeclaration)
                .orElseThrow();
    }

    @Override
    public void each(Path file, PluginContext context, PluginHelper helper) throws Exception {
        String fileName = file.getFileName().toString();
        if (fileName.endsWith(".java") && fileName.startsWith("I")) {
            String className = fileName.substring(0, fileName.length() - 5);
            String fieldName = helper.toUpperName(className.substring(1));
            capNames.put(fieldName, className);
        }
    }

    @Override
    public EnumLoopResult finished(PluginContext context, PluginHelper helper) throws Exception {
        if (!capNames.isEmpty()) {
            declaration.getFields().stream()
                    .filter(NodeWithStaticModifier::isStatic)
                    .map(f -> f.getVariable(0).getNameAsString())
                    .forEach(capNames::remove);
        }

        if (!capNames.isEmpty()) {
            unit.addImport("net.minecraftforge.common.capabilities.Capability");
            unit.addImport("net.minecraftforge.common.capabilities.CapabilityManager");
            unit.addImport("net.minecraftforge.common.capabilities.CapabilityToken");

            // Capability<[className]> [fieldName] = CapabilityManager.get(new CapabilityToken<>() {});
            capNames.forEach((fieldName, className) -> declaration.addFieldWithInitializer(
                    "Capability<" + className + ">",
                    fieldName,
                    StaticJavaParser.parseExpression("CapabilityManager.get(new CapabilityToken<>() {})"),
                    Modifier.Keyword.PUBLIC, Modifier.Keyword.STATIC, Modifier.Keyword.FINAL));

            unit.getStorage().orElseThrow().save();
        }

        return EnumLoopResult.FINISHED;
    }

    @Override
    public Path getLoopRoot(PluginHelper helper) {
        return srcPath(helper).resolve(CAPABILITY_PKG.replace(".", "/"));
    }

    private Path srcPath(PluginHelper helper) {
        if (srcPath == null) {
            srcPath = helper.projectPath().resolve("/src/main/java/");
        }
        return srcPath;
    }
}
