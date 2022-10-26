package com.lyc.typeHandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.postgresql.util.PGobject;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * pgsql二维数组转换，点坐标
 */
public class DoubleArray2DTypeHandler extends BaseTypeHandler<double[][]> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, double[][] parameter, JdbcType jdbcType) throws SQLException {
        PGobject pgo = new PGobject();
        pgo.setType("float8[]");
        pgo.setValue(arrayToString(parameter));

        ps.setObject(i, pgo, JdbcType.OTHER.TYPE_CODE);
        //ps.setObject(i, pgo);
    }

    @Override
    public double[][] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        if (value == null) {
            return null;
        }
        return stringToArray(value);
    }

    @Override
    public double[][] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        if (value == null) {
            return null;
        }
        return stringToArray(value);
    }

    @Override
    public double[][] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        if (value == null) {
            return null;
        }
        return stringToArray(value);
    }

    private String arrayToString(double[][] array) {
        StringBuilder sb = new StringBuilder("{");
        for (double[] row : array) {
            sb.append("{");
            for (double value : row) {
                sb.append(value).append(",");
            }
            sb.setLength(sb.length() - 1); // Remove the last comma
            sb.append("},");
        }
        sb.setLength(sb.length() - 1); // Remove the last comma
        sb.append("}");
        return sb.toString();
    }

    private double[][] stringToArray(String value) {
        value = value.substring(1, value.length() - 1); // Remove outer braces
        String[] rows = value.split("\\},\\{");
        double[][] array = new double[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            String row = rows[i];
            row = row.trim(); // Remove leading and trailing whitespace
            if (row.startsWith("{")) {
                row = row.substring(1);
            }
            if (row.endsWith("}")) {
                row = row.substring(0, row.length() - 1);
            }

            String[] values = row.split(",");
            array[i] = new double[values.length];
            for (int j = 0; j < values.length; j++) {
                array[i][j] = Double.parseDouble(values[j].trim());
            }
        }
        return array;
    }
}