
package za.ac.tut;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.time.Year;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class CarFrame extends JFrame
{
    // Creating panels
    private JPanel mainPnl;
    private JPanel detailsAndInfoPnl;
    private JPanel titlePnl;
    private JPanel requestedAndButtonsPnl;

    private JPanel carDetailsPnl;
    private JPanel namePnl;
    private JPanel modelPnl;
    private JPanel yearPnl;
    private JPanel pricePnl;
    private JPanel idPnl;

    private JPanel carInfoPnl;
    private JPanel ifUsedPnl;
    private JPanel noKMPnl;
    private JPanel carTypePnl;

    private JPanel displayPnl;
    private JPanel buttonsPnl;
    private JPanel searchPnl;

    // Creating labels
    private JLabel titleLbl;
    private JLabel idLbl;
    private JLabel nameLbl;
    private JLabel modelLbl;
    private JLabel yearLbl;
    private JLabel priceLbl;

    private JLabel ifUsedLbl;
    private JLabel noKMLbl;
    private JLabel carTypeLbl;

    // Creating fields
    private JTextField idFld;
    private JTextField nameFld;
    private JTextField modelFld;
    private JTextField yearFld;
    private JTextField priceFld;
    private JTextField noKMFld;
    private JTextField searchFld;

    // Creating radio buttons
    private JRadioButton yesBtn;
    private JRadioButton noBtn;

    // Creating button group
    private ButtonGroup yesAndNoBtn;

    // Creating combo boxes
    private JComboBox<String> carTypeBtn;
    private JComboBox<String> searchCriteriaBtn;

    // Creating text area
    private JTextArea displayDetailsArea;

    // Creating buttons
    private JButton addBtn;
    private JButton searchBtn;
    private JButton viewAllBtn;
    private JButton updateBtn;
    private JButton deleteBtn;
    private JButton clearBtn;
    private JButton exitBtn;

    private CarDatabase db;

    public CarFrame()
    {
        db = new CarDatabase();

        setSize(650, 600);
        setTitle("CAR DEALERSHIP");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creating panels
        mainPnl = new JPanel(new BorderLayout());
        titlePnl = new JPanel(new FlowLayout(FlowLayout.CENTER));

        carDetailsPnl = new JPanel(new GridLayout(5, 1));
        idPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        modelPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        yearPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pricePnl = new JPanel(new FlowLayout(FlowLayout.LEFT));

        carDetailsPnl.setBorder(
                new TitledBorder(new LineBorder(Color.black, 1),
                        "CAR DETAILS"));

        carInfoPnl = new JPanel(new GridLayout(3, 1));
        ifUsedPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        noKMPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        carTypePnl = new JPanel(new FlowLayout(FlowLayout.LEFT));

        carInfoPnl.setBorder(
                new TitledBorder(new LineBorder(Color.black, 1),
                        "CAR INFO"));

        displayPnl = new JPanel(new BorderLayout());
        displayPnl.setBorder(
                new TitledBorder(new LineBorder(Color.black, 1),
                        "REQUESTED DETAILS"));

        buttonsPnl = new JPanel(new FlowLayout());
        searchPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));

        requestedAndButtonsPnl = new JPanel(new BorderLayout());
        detailsAndInfoPnl = new JPanel(new BorderLayout());

        // Creating labels
        titleLbl = new JLabel("CAR FORM");

        idLbl = new JLabel("ID      :  ");
        nameLbl = new JLabel("NAME   :  ");
        modelLbl = new JLabel("MODEL :  ");
        yearLbl = new JLabel("YEAR  :  ");
        priceLbl = new JLabel("PRICE :  ");

        ifUsedLbl = new JLabel("PREVIOUSLY USED?:          ");
        noKMLbl = new JLabel("If used enter number of KM:  ");
        carTypeLbl = new JLabel("TYPE OF CAR:              ");

        // Creating text fields
        idFld = new JTextField(30);
        nameFld = new JTextField(30);
        modelFld = new JTextField(30);
        yearFld = new JTextField(30);
        priceFld = new JTextField(30);
        noKMFld = new JTextField(10);
        searchFld = new JTextField(12);

        // Creating display area
        displayDetailsArea = new JTextArea(6, 6);
        displayDetailsArea.setEditable(false);

        // Creating radio buttons
        yesBtn = new JRadioButton("YES");
        noBtn = new JRadioButton("NO");

        yesAndNoBtn = new ButtonGroup();
        yesAndNoBtn.add(yesBtn);
        yesAndNoBtn.add(noBtn);

        // Creating car type combo box
        carTypeBtn = new JComboBox<>();

        carTypeBtn.addItem("OFF ROAD");
        carTypeBtn.addItem("SPORT");
        carTypeBtn.addItem("SEDAN");
        carTypeBtn.addItem("SUV");
        carTypeBtn.addItem("HATCHBACK");
        carTypeBtn.addItem("CONVERTIBLE");
        carTypeBtn.addItem("VAN");
        carTypeBtn.addItem("ELECTRIC");
        carTypeBtn.addItem("LUXURY");

        // Creating search criteria combo box
        searchCriteriaBtn = new JComboBox<>(new String[]{
            "All Vehicles",
            "Brand",
            "Model",
            "Maximum Price",
            "Maximum Mileage",
            "Car Type"
        });

        // Creating buttons
        addBtn = new JButton("ADD CAR");
        searchBtn = new JButton("SEARCH");
        viewAllBtn = new JButton("VIEW ALL");
        updateBtn = new JButton("UPDATE");
        deleteBtn = new JButton("DELETE");
        clearBtn = new JButton("CLEAR");
        exitBtn = new JButton("EXIT");

        // Adding components to title panel
        titlePnl.add(titleLbl);

        // Adding components to car details panels
        idPnl.add(idLbl);
        idPnl.add(idFld);

        namePnl.add(nameLbl);
        namePnl.add(nameFld);

        modelPnl.add(modelLbl);
        modelPnl.add(modelFld);

        yearPnl.add(yearLbl);
        yearPnl.add(yearFld);

        pricePnl.add(priceLbl);
        pricePnl.add(priceFld);

        carDetailsPnl.add(idPnl);
        carDetailsPnl.add(namePnl);
        carDetailsPnl.add(modelPnl);
        carDetailsPnl.add(yearPnl);
        carDetailsPnl.add(pricePnl);

        // Adding components to car info panels
        ifUsedPnl.add(ifUsedLbl);
        ifUsedPnl.add(yesBtn);
        ifUsedPnl.add(noBtn);

        noKMPnl.add(noKMLbl);
        noKMPnl.add(noKMFld);

        carTypePnl.add(carTypeLbl);
        carTypePnl.add(carTypeBtn);

        carInfoPnl.add(ifUsedPnl);
        carInfoPnl.add(noKMPnl);
        carInfoPnl.add(carTypePnl);

        // Combining car details and car info
        detailsAndInfoPnl.add(carDetailsPnl, BorderLayout.NORTH);
        detailsAndInfoPnl.add(carInfoPnl, BorderLayout.CENTER);

        // Search panel
        searchPnl.add(new JLabel("SEARCH BY:"));
        searchPnl.add(searchCriteriaBtn);
        searchPnl.add(searchFld);
        searchPnl.add(searchBtn);
        searchPnl.add(viewAllBtn);

        // Display panel
        displayPnl.add(searchPnl, BorderLayout.NORTH);
        displayPnl.add(new JScrollPane(displayDetailsArea),
                BorderLayout.CENTER);

        // Adding buttons
        buttonsPnl.add(addBtn);
        buttonsPnl.add(updateBtn);
        buttonsPnl.add(deleteBtn);
        buttonsPnl.add(clearBtn);
        buttonsPnl.add(exitBtn);

        // Combining display and buttons
        requestedAndButtonsPnl.add(displayPnl, BorderLayout.CENTER);
        requestedAndButtonsPnl.add(buttonsPnl, BorderLayout.SOUTH);

        // Main panel
        mainPnl.add(titlePnl, BorderLayout.NORTH);
        mainPnl.add(detailsAndInfoPnl, BorderLayout.CENTER);
        mainPnl.add(requestedAndButtonsPnl, BorderLayout.SOUTH);

        // Button actions
        addBtn.addActionListener(e -> addCar());
        searchBtn.addActionListener(e -> searchCars());
        viewAllBtn.addActionListener(e -> viewAllCars());
        updateBtn.addActionListener(e -> updateCar());
        deleteBtn.addActionListener(e -> deleteCar());
        clearBtn.addActionListener(e -> clearFields());
        exitBtn.addActionListener(e -> System.exit(0));

        add(mainPnl);

        setResizable(false);
        setVisible(true);

        // Display existing vehicles
        viewAllCars();
    }

    // Read and validate car details
    private CarData getCarDetails()
    {
        String name = nameFld.getText().trim();
        String model = modelFld.getText().trim();
        String yearText = yearFld.getText().trim();
        String priceText = priceFld.getText().trim();
        String kmText = noKMFld.getText().trim();

        if (name.isEmpty() || model.isEmpty()
                || yearText.isEmpty() || priceText.isEmpty())
        {
            throw new IllegalArgumentException(
                    "Please complete all required car details.");
        }

        if (!yesBtn.isSelected() && !noBtn.isSelected())
        {
            throw new IllegalArgumentException(
                    "Please select YES or NO for previously used.");
        }

        int year = Integer.parseInt(yearText);
        double price = Double.parseDouble(priceText);

        if (year < 1886 || year > Year.now().getValue() + 1)
        {
            throw new IllegalArgumentException("Enter a valid car year.");
        }

        if (!Double.isFinite(price) || price <= 0)
        {
            throw new IllegalArgumentException(
                    "Price must be greater than zero.");
        }

        boolean used = yesBtn.isSelected();
        Integer km = null;

        if (used && !kmText.isEmpty())
        {
            km = Integer.parseInt(kmText);

            if (km < 0)
            {
                throw new IllegalArgumentException(
                        "Mileage cannot be negative.");
            }
        }

        if (used && km == null)
        {
            throw new IllegalArgumentException(
                    "Please enter the mileage for a used car.");
        }

        return new CarData(
                name, model, year, price, used, km,
                (String) carTypeBtn.getSelectedItem());
    }

    // ADD CAR
    private void addCar()
    {
        try
        {
            CarData car = getCarDetails();

            db.addCar(car.name, car.model, car.year,
                    car.price, car.used, car.km, car.type);

            displayDetailsArea.setText("Car added successfully!");

            clearInputFields();
            viewAllCars();
        }
        catch (NumberFormatException e)
        {
            showMessage("Year, price and KM must be valid numbers.");
        }
        catch (IllegalArgumentException e)
        {
            showMessage(e.getMessage());
        }
        catch (SQLException e)
        {
            showMessage("Database error: " + e.getMessage());
        }
    }

    // VIEW ALL CARS
    private void viewAllCars()
    {
        try
        {
            List<String> results = db.getAllCars();
            displayResults(results);
        }
        catch (SQLException e)
        {
            showMessage("Error retrieving cars: " + e.getMessage());
        }
    }

    // SEARCH CARS
    private void searchCars()
    {
        try
        {
            String criteria =
                    (String) searchCriteriaBtn.getSelectedItem();

            String value = searchFld.getText().trim();

            List<String> results;

            if ("All Vehicles".equals(criteria))
            {
                results = db.getAllCars();
            }
            else
            {
                if (value.isEmpty())
                {
                    showMessage("Please enter a search value.");
                    return;
                }

                results = db.search(criteria, value);
            }

            displayResults(results);
        }
        catch (NumberFormatException e)
        {
            showMessage("Price and mileage must be valid numbers.");
        }
        catch (SQLException e)
        {
            showMessage("Search error: " + e.getMessage());
        }
    }

    // UPDATE CAR
    private void updateCar()
    {
        try
        {
            if (idFld.getText().trim().isEmpty())
            {
                showMessage("Enter the ID of the car to update.");
                return;
            }

            int id = Integer.parseInt(idFld.getText().trim());
            CarData car = getCarDetails();

            boolean updated = db.updateCar(
                    id, car.name, car.model, car.year,
                    car.price, car.used, car.km, car.type);

            if (updated)
            {
                displayDetailsArea.setText(
                        "Car updated successfully!");

                clearInputFields();
                viewAllCars();
            }
            else
            {
                showMessage("No car found with ID: " + id);
            }
        }
        catch (NumberFormatException e)
        {
            showMessage("Enter a valid ID, year, price and KM.");
        }
        catch (IllegalArgumentException e)
        {
            showMessage(e.getMessage());
        }
        catch (SQLException e)
        {
            showMessage("Update error: " + e.getMessage());
        }
    }

    // DELETE CAR
    private void deleteCar()
    {
        try
        {
            if (idFld.getText().trim().isEmpty())
            {
                showMessage("Enter the ID of the car to delete.");
                return;
            }

            int id = Integer.parseInt(idFld.getText().trim());

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete car ID " + id + "?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION)
            {
                boolean deleted = db.deleteCar(id);

                if (deleted)
                {
                    displayDetailsArea.setText(
                            "Car deleted successfully!");

                    clearInputFields();
                    viewAllCars();
                }
                else
                {
                    showMessage("No car found with ID: " + id);
                }
            }
        }
        catch (NumberFormatException e)
        {
            showMessage("Car ID must be a number.");
        }
        catch (SQLException e)
        {
            showMessage("Delete error: " + e.getMessage());
        }
    }

    // DISPLAY SEARCH RESULTS
    private void displayResults(List<String> results)
    {
        if (results.isEmpty())
        {
            displayDetailsArea.setText("No cars found.");
            return;
        }

        StringBuilder output = new StringBuilder();

        output.append("TOTAL CARS FOUND: ")
                .append(results.size())
                .append("\n\n");

        for (String car : results)
        {
            output.append(car).append("\n\n");
        }

        displayDetailsArea.setText(output.toString());
    }

    // CLEAR FIELDS
    private void clearFields()
    {
        clearInputFields();

        searchFld.setText("");
        searchCriteriaBtn.setSelectedIndex(0);
        displayDetailsArea.setText("");
    }

    // CLEAR CAR INPUT FIELDS
    private void clearInputFields()
    {
        idFld.setText("");
        nameFld.setText("");
        modelFld.setText("");
        yearFld.setText("");
        priceFld.setText("");
        noKMFld.setText("");

        yesAndNoBtn.clearSelection();
        carTypeBtn.setSelectedIndex(0);
    }

    // DISPLAY MESSAGE
    private void showMessage(String message)
    {
        JOptionPane.showMessageDialog(
                this, message, "CAR DEALERSHIP",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // STORE CAR DETAILS
    private static class CarData
    {
        String name;
        String model;
        int year;
        double price;
        boolean used;
        Integer km;
        String type;

        CarData(String name, String model, int year,
                double price, boolean used, Integer km, String type)
        {
            this.name = name;
            this.model = model;
            this.year = year;
            this.price = price;
            this.used = used;
            this.km = km;
            this.type = type;
        }
    }
}
