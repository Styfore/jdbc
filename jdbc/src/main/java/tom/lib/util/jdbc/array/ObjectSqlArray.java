package tom.lib.util.jdbc.array;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 
1 data_type_id Data Type Id java.lang.Integer int4 11
2 smallint_type Smallint Type java.lang.Integer int2 6
3 int_type Int Type java.lang.Integer int4 11
4 bigint_type Bigint Type java.lang.Long int8 20
5 decimal_type Decimal Type java.math.BigDecimal numeric 18
6 numeric_type Numeric Type java.math.BigDecimal numeric 12
7 real_type Real Type java.lang.Float float4 14
8 doubleprecision_type Doubleprecision Type java.lang.Double float8 24
9 serial_type Serial Type java.lang.Integer int4 11
10 bigserial_type Bigserial Type java.lang.Long int8 20
11 varchar_type Varchar Type java.lang.String varchar 30
12 char_type Char Type java.lang.String bpchar 30
13 text_type Text Type java.lang.String text 2147483647
14 bytea_type Bytea Type [B bytea 2147483647
15 date_type Date Type java.sql.Date date 13
16 time_type Time Type java.sql.Time time 15
17 timetz_type Timetz Type java.sql.Time timetz 21
18 timestamp_type Timestamp Type java.sql.Timestamp timestamp 29
19 timestamptz_type Timestamptz Type java.sql.Timestamp timestamptz 35
20 interval_type Interval Type org.postgresql.util.PGInterval interval 49
21 boolean_type Boolean Type java.lang.Boolean bool 1

22 point_type Point Type org.postgresql.geometric.PGpoint point 2147483647 23
linesegment_type Linesegment Type org.postgresql.geometric.PGlseg lseg
2147483647

24 box_type Box Type org.postgresql.geometric.PGbox box 2147483647
25 path_type Path Type org.postgresql.geometric.PGpath path 2147483647

26 polygon_type Polygon Type org.postgresql.geometric.PGpolygon polygon
2147483647 27 circle_type Circle Type org.postgresql.geometric.PGcircle
circle 2147483647

28 cidr_type Cidr Type java.lang.Object cidr 2147483647
29 inet_type Inet Type java.lang.Object inet 2147483647
30 macaddr_type Macaddr Type java.lang.Object macaddr 2147483647
31 bit2_type Bit2 Type java.lang.Boolean bit 2
32 bitvarying5_type Bitvarying5 Type java.lang.Object varbit 5
 * @author Thomas
 *
 */
public class ObjectSqlArray implements  java.sql.Array{

	private final Object[] objectArray;
	private final String stringValue;

	private final int baseType;
	private final String baseTypeName;
	
	public ObjectSqlArray(Object[] array, int baseType, String baseTypeName) {
		this.baseType = baseType;
		this.baseTypeName = baseTypeName;
		this.objectArray = array;
		
		this.stringValue = transformToStringValue(array);
	}
	
	private static String transformToStringValue(Object[] array){
		if (array == null){
			return "null";
		}
		else if (array.length == 0){
			return "{}";
		}
		else{
			List<String> stringArray = new ArrayList<>(array.length);
			for (int i = 0; i < array.length; i++) {
				Object el = array[i];
				if (el != null && el.getClass().isArray()){
					stringArray.add(transformToStringValue((Object[]) el));
				}
				else if (el instanceof String){
					stringArray.add(el!=null?String.format("'%s'", el.toString()):"null");
				}
				else {
					stringArray.add(el!=null?el.toString():"null");
				}
			}
			
			return stringArray.stream().collect(Collectors.joining(",", "{", "}"));
		}
	}
		
	public String toString() {
        return stringValue;
    }
	
	@Override
	public void free() throws SQLException {
	}

    @Override
    public Object getArray() throws SQLException {
        return objectArray == null ? null : Arrays.copyOf(objectArray, objectArray.length);
    }
 
    @Override
    public Object getArray(Map<String, Class<?>> map) throws SQLException {
        return getArray();
    }
 
    @Override
    public Object getArray(long index, int count) throws SQLException {
        return objectArray == null ? null : Arrays.copyOfRange(objectArray, (int)index, (int)index + count);
    }
 
    @Override
    public Object getArray(long index, int count, Map<String, Class<?>> map) throws SQLException {
        return getArray(index, count);
    }

	@Override
	public int getBaseType() throws SQLException {
		return this.baseType;
	}

	@Override
	public String getBaseTypeName() throws SQLException {
		return this.baseTypeName;
	}

    public ResultSet getResultSet() throws SQLException {
        throw new UnsupportedOperationException();
    }
 
    public ResultSet getResultSet(Map<String, Class<?>> map) throws SQLException {
        throw new UnsupportedOperationException();
    }
 
    public ResultSet getResultSet(long index, int count) throws SQLException {
        throw new UnsupportedOperationException();
    }
 
    public ResultSet getResultSet(long index, int count, Map<String, Class<?>> map) throws SQLException {
        throw new UnsupportedOperationException();
    }

}
