package com.chaozhis;

import com.chaozhis.service.Database;
import com.chaozhis.utils.Field;

import java.io.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO 生成工具
 */

public class CreateDTO {

    public static void main(String[] args) throws IOException {
        new CreateDTO().createDto(TABLENAME, CLASSNAME);
    }

    private final static String FILEPATH = "/Users/airene/";

    private final static String TABLENAME = "cz_web_user";

    private final static String CLASSNAME = "UserDTO";

    private final static String PAGENAME = "com.chaozhis.dto";

    private void createDto(String tableName, String dtoName) throws IOException {
        File f = new File(FILEPATH + dtoName + ".java");
        if (!f.exists()) {
            f.createNewFile();
        }
        FileOutputStream ff;
        BufferedWriter bw = null;

        try {
            ff = new FileOutputStream(f);
            bw = new BufferedWriter(new OutputStreamWriter(ff));
            bw.write(getContent(tableName, dtoName));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getClassType(int type) {
        String typeStr;
        switch (type) {
            case Types.VARCHAR:
                typeStr = "String";
                break;
            case Types.BIGINT:
                typeStr = "Long";
                break;
            case Types.DECIMAL:
                typeStr = "Double";
                break;
            case Types.SMALLINT:
            case Types.INTEGER:
            case Types.TINYINT:
                typeStr = "Integer";
                break;
            case Types.DATE:
                typeStr = "Date";
                break;
            case Types.TIMESTAMP:
                typeStr = "Timestamp";
                break;
            default:
                typeStr = "String";
                break;
        }
        return typeStr;
    }

    /**
     * 获得一张表的所有列名
     *
     * @param tableName 表名
     * @return 列名列表
     */
    private List<Field> getColumns(String tableName) {
        List<Field> columns = new ArrayList<>();
        ResultSet rs;
        Database dbc = null;
        try {
            dbc = new Database();
            dbc.prepareStatement("select * from " + tableName + " limit 1");
            rs = dbc.executeQuery();
            ResultSetMetaData rsm = rs.getMetaData();
            int cols = rsm.getColumnCount();
            Field field;
            for (int i = 1; i <= cols; i++) {
                field = new Field();
                field.setName(rsm.getColumnName(i));
                field.setType(rsm.getColumnType(i));
                columns.add(field);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (dbc != null) {
                dbc.close();
            }
        }
        return columns;
    }

    private String getContent(String tableName, String dtoName) {
        List<Field> list = getColumns(tableName);
        StringBuilder content = new StringBuilder();
        content.append("package ").append(PAGENAME).append(";\n");
        content.append("\n");
        content.append("import java.sql.Timestamp;\n");
        content.append("import java.sql.Date;\n");
        content.append("\n");
        content.append("public class ").append(dtoName).append(" {\n");
        for (Field field : list) {
            content.append(returnGetSetMethod(field.getName(), field.getType()));
        }
        content.append("}");
        return content.toString();
    }

    private String returnGetSetMethod(String name, int type) {
        StringBuilder str = new StringBuilder();
        String typeStr = getClassType(type);
        str.append("    private ").append(typeStr).append(" ").append(name).append(";\n");
        str.append("\n");
        str.append("    public void set").append(name.substring(0, 1).toUpperCase()).append(name.substring(1)).append("(").append(typeStr).append(" ").append(name).append(") {\n");
        str.append("    this.").append(name).append(" = ").append(name).append(";\n");
        str.append("    }\n");
        str.append("    public ").append(typeStr).append(" get").append(name.substring(0, 1).toUpperCase()).append(name.substring(1)).append("() {\n");
        str.append("    return ").append(name).append(";\n");
        str.append("    }\n");
        str.append("\n");
        return str.toString();
    }
}
