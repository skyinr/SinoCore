package games.moegirl.sinocraft.sinocore_gen;

import com.github.javaparser_new.StaticJavaParser;
import com.github.javaparser_new.ast.CompilationUnit;
import com.github.javaparser_new.ast.Modifier;
import com.github.javaparser_new.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser_new.ast.body.TypeDeclaration;
import com.github.javaparser_new.ast.expr.Expression;
import com.github.javaparser_new.ast.expr.StringLiteralExpr;
import com.github.javaparser_new.ast.type.VoidType;
import lq2007.plugins.gradle_plugin.support.EnumLoopResult;
import lq2007.plugins.gradle_plugin.support.ISourcePlugin;
import lq2007.plugins.gradle_plugin.support.PluginContext;
import lq2007.plugins.gradle_plugin.support.PluginHelper;

import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class DeferredRegisterGenerator implements ISourcePlugin {

    private static final String MODID = "sinocore";

    protected CompilationUnit registerUnit;
    protected ClassOrInterfaceDeclaration registerDeclaration;

    @Nullable
    protected CompilationUnit elementUnit;
    @Nullable
    protected ClassOrInterfaceDeclaration elementDeclaration;

    private final String elementTypeName, elementTypeClass, registryName;
    private final String registerClassPath;

    @Nullable
    private final String elementHolderClassPath;

    public DeferredRegisterGenerator(String elementTypeName, String elementTypeClass,
                                     String registerClassPath, String elementHolderClassPath,
                                     String forgeRegistryName) {
        this.elementTypeName = elementTypeName;
        this.elementTypeClass = elementTypeClass;
        this.registerClassPath = registerClassPath;
        this.elementHolderClassPath = elementHolderClassPath;
        this.registryName = forgeRegistryName;
    }

    public DeferredRegisterGenerator(String elementTypeName, String elementTypeClass,
                                     String registerClassPath,
                                     String forgeRegistryName) {
        this.elementTypeName = elementTypeName;
        this.elementTypeClass = elementTypeClass;
        this.registerClassPath = registerClassPath;
        this.elementHolderClassPath = null;
        this.registryName = forgeRegistryName;
    }

    @Override
    public void begin(PluginContext context, PluginHelper helper) throws Exception {
        Path registerPath = helper.srcPath().resolve(registerClassPath.replace(".", "/") + ".java");
        registerUnit = getOrCreateUnit(registerPath, registerClassPath, helper);
        registerDeclaration = registerUnit.getPrimaryType()
                .map(TypeDeclaration::asClassOrInterfaceDeclaration)
                .orElseThrow(RuntimeException::new);

        registerUnit.addImport("net.minecraftforge.eventbus.api.IEventBus");
        registerUnit.addImport("net.minecraftforge.registries.DeferredRegister");
        registerUnit.addImport("net.minecraftforge.registries.ForgeRegistries");
        registerUnit.addImport("net.minecraftforge.registries.RegistryObject");
        registerUnit.addImport(elementTypeClass);

        if (registerDeclaration.getFieldByName("REGISTER").isEmpty()) {
            registerDeclaration.addFieldWithInitializer("DeferredRegister<" + elementTypeName + ">", "REGISTER",
                    StaticJavaParser.parseExpression(String.format(
                            "DeferredRegister.create(ForgeRegistries.%s, \"%s\")", registryName, MODID)),
                    Modifier.Keyword.PUBLIC, Modifier.Keyword.STATIC, Modifier.Keyword.FINAL);
        }

        if (registerDeclaration.getMethodsByName("register").isEmpty()) {
            registerDeclaration.addMethod("register", Modifier.Keyword.PUBLIC, Modifier.Keyword.STATIC)
                    .setType(new VoidType())
                    .addParameter("IEventBus", "bus")
                    .setBody(StaticJavaParser.parseBlock("""
                            {
                                REGISTER.register(bus);
                            }"""));
        }

        registerUnit.getStorage().orElseThrow(IOException::new).save();

        if (elementHolderClassPath != null) {
            Path elementPath = helper.srcPath().resolve(elementHolderClassPath.replace(".", "/") + ".java");
            elementUnit = getOrCreateUnit(elementPath, elementHolderClassPath, helper);
            elementDeclaration = elementUnit.getPrimaryType()
                    .map(TypeDeclaration::asClassOrInterfaceDeclaration)
                    .orElseThrow(RuntimeException::new);

            elementUnit.addImport("net.minecraftforge.registries.ObjectHolder");
            elementUnit.addImport(elementTypeClass);

            if (elementDeclaration.getAnnotationByName("ObjectHolder").isEmpty()) {
                elementDeclaration.addSingleMemberAnnotation("ObjectHolder", new StringLiteralExpr(MODID));
            }

            elementUnit.getStorage().orElseThrow(IOException::new).save();
        }
    }

    @Override
    public void each(Path file, PluginContext context, PluginHelper helper) throws Exception {
        addElement(file, helper,
                (className, elementName, classPath) -> addElement(file, elementName, className, helper));
    }

    @Override
    public EnumLoopResult finished(PluginContext context, PluginHelper helper) throws Exception {

        context.exceptions().printStackTrace();

        registerUnit.getStorage().orElseThrow(IOException::new).save();
        if (elementUnit != null) {
            elementUnit.getStorage().orElseThrow(IOException::new).save();
        }

        return EnumLoopResult.FINISHED;
    }

    private CompilationUnit getOrCreateUnit(Path path, String classPath, PluginHelper helper) throws IOException {
        CompilationUnit unit;
        if (Files.isRegularFile(path)) {
            unit = helper.buildAST(path);
        } else {
            String packageName = classPath.substring(0, classPath.lastIndexOf("."));
            unit = new CompilationUnit(packageName);
            unit.setStorage(path);
            helper.writeTo(path, false, unit);
        }

        String className = unit.getPrimaryTypeName().orElseThrow(IOException::new);
        if (unit.getPrimaryType().isEmpty()) {
            unit.addClass(className).setPublic(true);
        }
        return unit;
    }

    protected void addElement(Path file, String elementName, String className, PluginHelper helper) {
        String registerName = helper.toLowerName(elementName);
        String fieldName;

        if (elementDeclaration != null) {
            // public static [elementTypeName] [fieldName];
            fieldName = Character.toLowerCase(elementName.charAt(0)) + elementName.substring(1);
            if (elementDeclaration.getFieldByName(fieldName).isEmpty()) {
                elementDeclaration.addPublicField(elementTypeName, fieldName).setStatic(true);
            }
        }

        // public static final RegistryObject<[elementTypeName]> [fieldName] = REGISTER.register("[registerName]", ...);
        fieldName = helper.toUpperName(elementName);
        if (registerDeclaration.getFieldByName(fieldName).isEmpty()) {
            registerDeclaration.addFieldWithInitializer("RegistryObject<" + elementTypeName + ">", fieldName,
                    StaticJavaParser.parseExpression(String.format(
                            "REGISTER.register(\"%s\", %s)",
                            registerName, buildRegisterInitializer(file, className, elementName, helper))),
                    Modifier.Keyword.PUBLIC, Modifier.Keyword.STATIC);
        }
    }

    protected abstract Expression buildRegisterInitializer(Path file, String className, String elementName, PluginHelper helper);

    protected abstract void addElement(Path file, PluginHelper helper, ElementAdder adder) throws Exception;

    public interface ElementAdder {
        void add(String className, String elementName, String classPath);
    }
}
