package processor;

import com.annotation.apt.InstanceFactory;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.processing.FilerException;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic;

import inter.AnnotationProcessor;
import inter.IProcessor;
import util.Utils;

/**
 * -------------------------------------
 * <p>
 * 项目名称： FirstLineCode
 * <p>
 * 版权：locensate.com 版权所有 2016
 * <p>
 * 公司主页：http://www.locensate.com/
 * <p>
 * 描述：
 * <p>
 * 作者： xiaobinghe
 * <p>
 * 时间： 2017/7/14 11:24
 * <p>
 * 修改历史：
 * <p>
 * 修改时间：
 * <p>
 * 修改描述：
 * <p>
 * -------------------------------------
 */

public class InstanceProcessor implements IProcessor {
    private String CLASS_NAME = "InstanceFactory";
    private String METHOD_NAME = "create";

    @Override
    public void process(RoundEnvironment roundEnv, AnnotationProcessor processor) {

        TypeSpec.Builder tb = TypeSpec.classBuilder(CLASS_NAME).addModifiers(Modifier.PUBLIC, Modifier.FINAL).addJavadoc("@实例工厂-APT自动生成");
        MethodSpec.Builder mb = MethodSpec.methodBuilder(METHOD_NAME)
                .addJavadoc("@此方法由apt自动生成")
                .returns(Object.class)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addException(IllegalAccessException.class)
                .addException(InstantiationException.class)
                .addParameter(Class.class, "mClass");

        CodeBlock.Builder cb = CodeBlock.builder();
        cb.beginControlFlow("switch(mClass.getSimpleName)");

        ArrayList<ClassName> mList = new ArrayList<>();

        try {
            for (TypeElement element : ElementFilter.typesIn(roundEnv.getElementsAnnotatedWith(InstanceFactory.class))) {
                processor.mMessager.printMessage(Diagnostic.Kind.NOTE, "正在处理: " + element.toString());
                if (!Utils.isValidClass(processor.mMessager, element)) return;
                ClassName currentType = ClassName.get(element);
                if (mList.contains(currentType)) continue;
                mList.add(currentType);
                //             String className = null;
//                try {
//                    Class<?> clazz = element.getAnnotation(InstanceFactory.class).value();
//                    className = clazz.getCanonicalName();
//                } catch (MirroredTypeException mte) {
//                    DeclaredType classTypeMirror = (DeclaredType) mte.getTypeMirror();
//                    TypeElement classTypeElement = (TypeElement) classTypeMirror.asElement();
//                    className = classTypeElement.getQualifiedName().toString();
//                } catch (Exception e) {
//                }
//                if (className != null && !className.equals(InstanceFactory.class.getName())) {
//                    cb.addStatement("case $S: return  new $T()", currentType.simpleName(), Utils.getType(className));//初始化Repository
//                } else {
                cb.addStatement("case $S: return  new $T()", currentType.simpleName(), currentType);//初始化Presenter
                //               }
            }
            cb.addStatement("default: return mClass.newInstance()");
            cb.endControlFlow();
            mb.addCode(cb.build());
            tb.addMethod(mb.build());
            JavaFile javaFile = JavaFile.builder(Utils.PackageName, tb.build()).build();// 生成源代码
            javaFile.writeTo(processor.mFiler);// 在 app module/build/generated/source/apt 生成一份源代码
        } catch (FilerException e) {
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
