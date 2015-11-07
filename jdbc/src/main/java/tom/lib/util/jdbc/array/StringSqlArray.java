package tom.lib.util.jdbc.array;

import java.sql.Types;
import java.util.Arrays;

public class StringSqlArray extends ObjectSqlArray{

	public StringSqlArray(double[] array) {
		super(Arrays.stream(array).boxed().toArray(), Types.OTHER, "text");
	}
	
	public StringSqlArray(Double[] array) {
		super(Arrays.stream(array).toArray(), Types.OTHER, "text");
	}

}
