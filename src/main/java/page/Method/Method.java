package page.Method;

import com.sun.tracing.dtrace.FunctionAttributes;
import java.util.List;
@FunctionalInterface

public interface Method<T> {
  List<T> queryMethod(long offset, long limit);
}
