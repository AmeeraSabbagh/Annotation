package jo.secondstep.jo;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BuilderProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

		for (Element element : roundEnv.getElementsAnnotatedWith(DataSet.class)) {
			JavaFileObject dataSetBuilder = null;
			PackageElement packageElement = (PackageElement) element.getEnclosingElement();
			BufferedWriter bufferedWriter = null;
			DataSet dataSet = null;
			try {
				dataSet = element.getAnnotation(DataSet.class);
				dataSetBuilder = processingEnv.getFiler()
						.createSourceFile(element.getSimpleName().toString() + "DataSet");
				bufferedWriter = new BufferedWriter(dataSetBuilder.openWriter());
				if (element.getKind().isClass()) {
					for (Element enclosed : element.getEnclosedElements()) {
						if (enclosed.getKind().isField() & (enclosed.getModifiers().contains(Modifier.PUBLIC)
								| enclosed.getModifiers().contains(Modifier.PROTECTED))) {

							bufferedWriter.append("package");
							bufferedWriter.append(packageElement.getQualifiedName().toString());
							bufferedWriter.append(";");
							bufferedWriter.newLine();
							bufferedWriter.append("public class");
							bufferedWriter.append(element.getSimpleName().toString() + "DataSet {");
							bufferedWriter.append("public final static String Handler_Name = " + dataSet.Handler_Name());

						}
					}
				}
				bufferedWriter.newLine();
				bufferedWriter.append("}");
				bufferedWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return false;
	}

}
