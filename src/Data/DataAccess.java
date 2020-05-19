package Data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class DataAccess {

    private Connection con;
    private final String className = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private final String connectURL = "jdbc:sqlserver://localhost;databaseName=ManagementSystemDB;integratedSecurity=true";

    public static void main(String[] args)
    {
        DataAccess dao = new DataAccess();


        // Add Cell
        dao.addCell("Complaints", "123456", "5.01.2001", "true", "text text text", "ididid");

        // Add value to existing cell's value
        dao.addToExistingCellValue("Complaints", "Description", "123456", "GG");

        // Get Cell specific value
        String val = dao.getCellValue("Complaints", "Description", "12345");

        // Get Cell values (whole row)
        List<String> res = dao.getAllCellValues("Complaints", "12345");

        // Get all table
        List<String> res2 = dao.getAllTableValues("Complaints");

        // Update cell to new value
        dao.updateCellValue("Complaints", "Description", "12345", "new Value");
    }

    public DataAccess()
    {
/*
        try
        {
            Class.forName(className);
            con = DriverManager.getConnection(connectURL);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

*/
    }

    public List<String> getAllTableValues(String TableName)
    {
        List<String> res = new LinkedList<>();

        PreparedStatement ps = null;
        String val = "";


        String statement = "SELECT * FROM " + TableName;

        try
        {
            ps = con.prepareStatement(statement);

            ResultSet rs = ps.executeQuery();
            int columns = rs.getMetaData().getColumnCount();

            while (rs.next())
            {
                String row = "";

                for (int i = 1; i < columns + 1; i++)
                    row = row + rs.getString(i).trim() + ",";

                res.add(row.substring(0, row.length() - 1).trim());
            }



            closePS(ps);

        }
        catch (Exception e)
        {
            closePS(ps);
            e.printStackTrace();
        }

        return res;
    }

    public List<String> getAllCellValues(String TableName, String ID)
    {
        List<String> res = new LinkedList<>();

        PreparedStatement ps = null;
        String val = "";


        String statement = "SELECT * FROM " + TableName + " WHERE ID = ?";

        try
        {
            ps = con.prepareStatement(statement);
            ps.setString(1, ID);

            ResultSet rs = ps.executeQuery();
            int columns = rs.getMetaData().getColumnCount();

            while (rs.next())
            {
                for (int i = 1; i < columns + 1; i++)
                    res.add(rs.getString(i).trim());
            }



            closePS(ps);

        }
        catch (Exception e)
        {
            closePS(ps);
            e.printStackTrace();
        }

        return res;

    }

    public String getCellValue(String TableName, String ColumnName, String ID)
    {
        PreparedStatement ps = null;
        String val = "";


        String statement = "SELECT " + ColumnName + " FROM " + TableName + " WHERE ID = ?";

        try
        {
            ps = con.prepareStatement(statement);
            ps.setString(1, ID);

            ResultSet rs = ps.executeQuery();

            rs.next();
            val = rs.getString(1);
            closePS(ps);

        }
        catch (Exception e)
        {
            closePS(ps);
            e.printStackTrace();
        }

        return val.trim();

    }

    public String addToExistingCellValue(String TableName, String ColumnName, String ID, String valueToAdd)
    {

        String oldValue = getCellValue(TableName, ColumnName, ID);

        PreparedStatement ps = null;
        String val = oldValue + valueToAdd;


        String statement = "UPDATE " + TableName + " SET " + ColumnName + " = ? WHERE ID = ?";

        try
        {
            ps = con.prepareStatement(statement);
            ps.setString(1, val);
            ps.setString(2, ID);

            ps.executeUpdate();
            closePS(ps);

        }
        catch (Exception e)
        {
            closePS(ps);
            e.printStackTrace();
        }

        return val.trim();

    }

    public boolean addCell(String TableName, String ... values)
    {
        PreparedStatement ps = null;

        int valuesNumber = values.length;
        String statement = "INSERT INTO " + TableName + " VALUES (";
        for (int i = 0; i < valuesNumber; i++)
            statement = statement + "?,";
        statement = statement.substring(0, statement.length()-1) + ")";

        try
        {
            ps = con.prepareStatement(statement);
            String val;

            for (int i = 0; i < valuesNumber; i++)
            {
                val = values[i];

                if (isBoolean(val))
                    ps.setBoolean(i+1, stringToBoolean(val));

                else if (isDate(val))
                    ps.setDate(i+1, stringToDate(val));

                else if (isDouble(val))
                    ps.setDouble(i+1, Double.parseDouble(val));

                else if (isInt(val))
                    ps.setInt(i+1, Integer.parseInt(val));

                else
                    ps.setString(i+1, val);


            }
            ps.executeUpdate();
            closePS(ps);

        }
        catch (Exception e)
        {
            closePS(ps);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Date stringToDate(String val)
    {
        String[] split = val.split("\\.");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        java.sql.Date date = null;

        try
        {
            date = new Date(sdf.parse(val).getTime());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return date;
    }

    public boolean isDouble(String s)
    {
        try
        {
            Double.parseDouble(s);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }

    }

    public boolean isInt(String s)
    {
        try
        {
            Integer.parseInt(s);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isDate(String s)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);

        try
        {
            dateFormat.parse(s.trim());
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isBoolean(String s)
    {
        return (s.toLowerCase().equals("true") || s.toLowerCase().equals("false"));
    }


    public boolean updateCellValue(String TableName, String ColumnName, String ID, String valueToSet)
    {
        PreparedStatement ps = null;

        try
        {
            ps = con.prepareStatement("UPDATE " + TableName + " SET " + ColumnName + " = ? WHERE ID = ?");
            ps.setString(1, valueToSet);
            ps.setString(2, ID);
            ps.executeUpdate();
            closePS(ps);
            return true;

        }
        catch (Exception e)
        {
            closePS(ps);
            e.printStackTrace();
            return false;
        }

    }

    public Boolean stringToBoolean(String s)
    {
        if (s.toLowerCase().equals("true"))
            return true;

        return false;
    }
    private void closePS(PreparedStatement ps)
    {
        if (ps != null)
            try {
                ps.close();
            } catch (Exception e) {}
    }

    private void closeConnection(Connection con)
    {
        if (con != null)
            try {
                con.close();
            } catch (Exception e) {}
    }

    public void createDB()
    {
        PreparedStatement ps = null;

        try {


            String line = "";
            String query = "";

            try (BufferedReader br = new BufferedReader(new FileReader("./createDB.sql"))) {

                while ((line = br.readLine()) != null) {

                    if (line.contains("GO") || line.contains("Go") || line.contains("GO;") || line.contains("Go;"))
                    {
                        ps = con.prepareStatement(query);
                        ps.execute();
                        query = "";
                        ps.close();
                    }
                    else
                    {
                        query = query + line;
                        query = query + "\n";
                    }
                }

                if (query != "")
                {
                    ps = con.prepareStatement(query);
                    ps.execute();
                    ps.close();
                }

                con.close();

            } catch (Exception e) {
                e.printStackTrace();

                if (ps != null)
                    try {
                        ps.close();
                    } catch (Exception e2) {}

                if (con != null)
                    try {
                        con.close();
                    } catch (Exception e3) {}

            }

        }
        catch (Exception e0) {
            e0.printStackTrace();

            if (ps != null)
                try {
                    ps.close();
                } catch (Exception e4) {}

            if (con != null)
                try {
                    con.close();
                } catch (Exception e5) {}
        }


        System.out.println("done");


    }

}
