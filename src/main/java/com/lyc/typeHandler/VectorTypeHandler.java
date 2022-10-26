package com.lyc.typeHandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.postgresql.util.PGobject;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * pgsql 中向量类型转成float数组
 * 很多向量是double类型的，seetaface6是float类型的
 */
public class VectorTypeHandler extends BaseTypeHandler<float[]> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, float[] parameter, JdbcType jdbcType) throws SQLException {
        PGobject vector = new PGobject();
        vector.setType("vector");
        StringBuilder sb = new StringBuilder();
        //
        sb.append("[");
        for (int j = 0; j < parameter.length; j++) {
            if (j > 0) sb.append(",");
            sb.append(parameter[j]);
        }
        sb.append("]");
        vector.setValue(sb.toString());
        ps.setObject(i, vector);
    }

    @Override
    public float[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String vectorString = rs.getString(columnName);
        if (vectorString == null) {
            return null;
        }
        return parseVectorString(vectorString);
    }

    @Override
    public float[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String vectorString = rs.getString(columnIndex);
        if (vectorString == null) {
            return null;
        }
        return parseVectorString(vectorString);
    }

    @Override
    public float[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String vectorString = cs.getString(columnIndex);
        if (vectorString == null) {
            return null;
        }
        return parseVectorString(vectorString);
    }

    private float[] parseVectorString(String vectorString) {
        vectorString = vectorString.substring(1, vectorString.length() - 1); // Remove the curly braces
        String[] parts = vectorString.split(",");
        float[] vector = new float[parts.length];
        for (int i = 0; i < parts.length; i++) {
            vector[i] = Float.parseFloat(parts[i].trim());
        }
        return vector;
    }

}