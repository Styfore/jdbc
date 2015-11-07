package tom.lib.util.jdbc.array;

import java.sql.Types;
import java.util.Arrays;

public class IntegerSqlArray extends ObjectSqlArray{

	public IntegerSqlArray(double[] array) {
		super(Arrays.stream(array).boxed().toArray(), Types.INTEGER, "int4");
	}
	
	public IntegerSqlArray(Double[] array) {
		super(Arrays.stream(array).toArray(), Types.INTEGER, "int4");
	}

}
