package projeto.crud_java;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import projeto.crud_java.beans.Product;
import projeto.crud_java.dbConection.DataBaseUtility;
import projeto.crud_java.tables.ProductController;

import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    //TODO: Criar a ponte entre os métodos das outras classes para elementos da UI

    public TextField prodName;
    public TextField shortDescription;
    public TextField brand;
    public TextField category;
    public TextField listPrice;
    public TextField cost;
    public TextField searchField;


    public TableView<Product> table = new TableView<>();

    public TableColumn<Product, String> nameCol = new TableColumn<>();
    public TableColumn<Product, String> shortDescriptionCol = new TableColumn<>();
    public TableColumn<Product, String> brandCol = new TableColumn<>();
    public TableColumn<Product, String> categoryCol = new TableColumn<>();
    public TableColumn<Product, Double> listPriceCol = new TableColumn<>();
    public TableColumn<Product, Double> costCol = new TableColumn<>();

    private ObservableList<Product> list = FXCollections.observableArrayList(loadTable());


    public Label errorMessage;
    public Label nameErrorMessage;
    public Label listPriceValueErrorMessage;
    public Label listPriceNullErrorMessage;


    public void create() {
        try {
            clearErrorMessages();
            System.out.println("Botão Create pressionado");

            if (listPrice.getText().isEmpty()) {
                if (prodName.getText().isEmpty())
                    throw new NullPointerException("both are empty");
                throw new NullPointerException("listprice is empty");
            }

            //NumberFormatException
            Double cst = cost.getText().isEmpty() ? -1.0 : Double.parseDouble(cost.getText());

            // IllegalArgumentException
            Product product = new Product(prodName.getText(), shortDescription.getText(), brand.getText(),
                    category.getText(), Double.parseDouble(listPrice.getText()), cst);
            ProductController.insert(product);
            table.getItems().add(product);

        } catch (NullPointerException e) {
            switch (e.getMessage()) {
                case "listprice is empty":
                    listPriceNullErrorMessage.setVisible(true);
                    break;
                case "both are empty":
                    listPriceNullErrorMessage.setVisible(true);
                    nameErrorMessage.setVisible(true);
                    break;
                case null, default:
                    System.out.println(e);
            }
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("Name can't be null")) {
                nameErrorMessage.setVisible(true);
            }
            if (e.getMessage().equals("List Price can't be negative")) {
                listPriceValueErrorMessage.setVisible(true);
            }
        } catch (Exception e) {
            errorMessage.setVisible(true);
            System.out.println(e);
        }
    }

    public void update() {
        try {
            clearErrorMessages();
            System.out.println("Botão Create pressionado");

            if (listPrice.getText().isEmpty()) {
                if (prodName.getText().isEmpty())
                    throw new NullPointerException("both are empty");
                throw new NullPointerException("listprice is empty");
            }

            //NumberFormatException
            Double cst = cost.getText().isEmpty() ? -1.0 : Double.parseDouble(cost.getText());

            // IllegalArgumentException
            Product old = ProductController.consult(prodName.getText());
            Product product = new Product(prodName.getText(), shortDescription.getText(), brand.getText(),
                    category.getText(), Double.parseDouble(listPrice.getText()), cst);
            ProductController.update(product);
            table.getItems().add(product);
            table.getItems().remove(old);

        } catch (NullPointerException e) {
            switch (e.getMessage()) {
                case "listprice is empty":
                    listPriceNullErrorMessage.setVisible(true);
                    break;
                case "both are empty":
                    listPriceNullErrorMessage.setVisible(true);
                    nameErrorMessage.setVisible(true);
                    break;
                case null, default:
                    System.out.println(e);
            }
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("Name can't be null")) {
                nameErrorMessage.setVisible(true);
            }
            if (e.getMessage().equals("List Price can't be negative")) {
                listPriceValueErrorMessage.setVisible(true);
            }
        } catch (Exception e) {
            errorMessage.setVisible(true);
            System.out.println(e);
        }
    }

    public void search() {
        try {
            if (!searchField.getText().isEmpty()) {
                Product product = ProductController.consult(searchField.getText());
                table.getItems().clear();
                table.getItems().add(product);
                System.out.println(product);
            }
        } catch (SQLException e) {
            errorMessage.setVisible(true);
        }
    }

    public LinkedList<Product> loadTable() {
        try {
            return DataBaseUtility.getAllProducts();
        } catch (SQLException e) {
            errorMessage.setVisible(true);
            return null;
        }
    }

    private void clearErrorMessages() {
        listPriceValueErrorMessage.setVisible(false);
        listPriceNullErrorMessage.setVisible(false);
        nameErrorMessage.setVisible(false);
        errorMessage.setVisible(false);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        shortDescriptionCol.setCellValueFactory(new PropertyValueFactory<Product, String>("shortDescription"));
        brandCol.setCellValueFactory(new PropertyValueFactory<Product, String>("brand"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
        listPriceCol.setCellValueFactory(new PropertyValueFactory<Product, Double>("listPrice"));
        costCol.setCellValueFactory(new PropertyValueFactory<Product, Double>("cost"));
        table.setItems(list);
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
            @Override
            public void changed(ObservableValue<? extends Product> observableValue, Product product, Product t1) {

                prodName.setText(t1.getName());
                shortDescription.setText(t1.getShortDescription());
                brand.setText(t1.getBrand());
                category.setText(t1.getCategory());
                listPrice.setText("" + t1.getListPrice());
                cost.setText("" + t1.getCost());

            }
        });

    }
}
