
package za.ac.tut;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class CarDatabase
{
    private static final String URL = "jdbc:sqlite:cars.db";

    public CarDatabase()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");

            try (Connection c = DriverManager.getConnection(URL);
                 Statement s = c.createStatement())
            {
                s.execute("""
                    CREATE TABLE IF NOT EXISTS cars (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL,
                        model TEXT NOT NULL,
                        year INTEGER NOT NULL,
                        price REAL NOT NULL,
                        used INTEGER NOT NULL DEFAULT 0,
                        km INTEGER,
                        car_type TEXT NOT NULL
                    )
                """);
            }
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException("SQLite JDBC driver not found.", e);
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Could not initialise database.", e);
        }
    }

    // ADD VEHICLE
    public void addCar(String name, String model, int year,
            double price, boolean used, Integer km, String type)
            throws SQLException
    {
        String sql = """
            INSERT INTO cars
            (name, model, year, price, used, km, car_type)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection c = DriverManager.getConnection(URL);
             PreparedStatement ps = c.prepareStatement(sql))
        {
            ps.setString(1, name);
            ps.setString(2, model);
            ps.setInt(3, year);
            ps.setDouble(4, price);
            ps.setInt(5, used ? 1 : 0);

            if (km == null)
                ps.setNull(6, Types.INTEGER);
            else
                ps.setInt(6, km);

            ps.setString(7, type);
            ps.executeUpdate();
        }
    }

    // VIEW ALL VEHICLES
    public List<String> getAllCars() throws SQLException
    {
        List<String> results = new ArrayList<>();

        String sql = "SELECT * FROM cars ORDER BY id";

        try (Connection c = DriverManager.getConnection(URL);
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
            {
                results.add(formatCar(rs));
            }
        }

        return results;
    }

    // SEARCH VEHICLES
    public List<String> search(String criteria, String value)
            throws SQLException
    {
        List<String> results = new ArrayList<>();

        String sql;

        switch (criteria)
        {
            case "Brand":
                sql = "SELECT * FROM cars WHERE LOWER(name) LIKE LOWER(?)";
                break;

            case "Model":
                sql = "SELECT * FROM cars WHERE LOWER(model) LIKE LOWER(?)";
                break;

            case "Maximum Price":
                sql = "SELECT * FROM cars WHERE price <= ?";
                break;

            case "Maximum Mileage":
                sql = "SELECT * FROM cars WHERE used = 1 AND km <= ?";
                break;

            case "Car Type":
                sql = "SELECT * FROM cars WHERE UPPER(car_type) = UPPER(?)";
                break;

            default:
                return getAllCars();
        }

        try (Connection c = DriverManager.getConnection(URL);
             PreparedStatement ps = c.prepareStatement(sql))
        {
            if (criteria.equals("Brand") || criteria.equals("Model"))
            {
                ps.setString(1, "%" + value.trim() + "%");
            }
            else if (criteria.equals("Maximum Price")
                    || criteria.equals("Maximum Mileage"))
            {
                ps.setDouble(1, Double.parseDouble(value.trim()));
            }
            else
            {
                ps.setString(1, value.trim());
            }

            try (ResultSet rs = ps.executeQuery())
            {
                while (rs.next())
                {
                    results.add(formatCar(rs));
                }
            }
        }

        return results;
    }

    // UPDATE VEHICLE
    public boolean updateCar(int id, String name, String model,
            int year, double price, boolean used,
            Integer km, String type) throws SQLException
    {
        String sql = """
            UPDATE cars
            SET name = ?, model = ?, year = ?, price = ?,
                used = ?, km = ?, car_type = ?
            WHERE id = ?
        """;

        try (Connection c = DriverManager.getConnection(URL);
             PreparedStatement ps = c.prepareStatement(sql))
        {
            ps.setString(1, name);
            ps.setString(2, model);
            ps.setInt(3, year);
            ps.setDouble(4, price);
            ps.setInt(5, used ? 1 : 0);

            if (km == null)
                ps.setNull(6, Types.INTEGER);
            else
                ps.setInt(6, km);

            ps.setString(7, type);
            ps.setInt(8, id);

            return ps.executeUpdate() > 0;
        }
    }

    // DELETE VEHICLE
    public boolean deleteCar(int id) throws SQLException
    {
        String sql = "DELETE FROM cars WHERE id = ?";

        try (Connection c = DriverManager.getConnection(URL);
             PreparedStatement ps = c.prepareStatement(sql))
        {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    // FORMAT DATABASE RECORD
    private String formatCar(ResultSet rs) throws SQLException
    {
        int km = rs.getInt("km");
        boolean kmIsNull = rs.wasNull();

        String condition;

        if (rs.getInt("used") == 1)
        {
            condition = "Used, " +
                    (kmIsNull ? "Mileage not recorded" : km + " km");
        }
        else
        {
            condition = "New";
        }

        return "ID: " + rs.getInt("id")
                + " | Brand: " + rs.getString("name")
                + " | Model: " + rs.getString("model")
                + " | Year: " + rs.getInt("year")
                + " | Price: R" + rs.getDouble("price")
                + " | Condition: " + condition
                + " | Type: " + rs.getString("car_type");
    }
}