package jo.secondstep.jo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)  
@Target(ElementType.TYPE)  
public @interface DataSet {
 String Handler_Name () default "";
// String Context () default "";
}
