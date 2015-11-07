package tom.lib.util.jdbc.array;

import java.sql.Types;
import java.util.Arrays;

public class DoubleSqlArray extends ObjectSqlArray{

	public DoubleSqlArray(double[] array) {
		super(Arrays.stream(array).boxed().toArray(), Types.DOUBLE, "float8");
	}
	
	public DoubleSqlArray(Double[] array) {
		super(Arrays.stream(array).toArray(), Types.DOUBLE, "float8");
	}

}
