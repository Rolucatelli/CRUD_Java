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
import projeto.crud_java.tables.ProductController;

import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML

    public TextField id;
    public TextField prodName;
    public TextField shortDescription;
    public TextField brand;
    public TextField category;
    public TextField listPrice;
    public TextField cost;
    public TextField searchField;


    public TableView<Product> table = new TableView<>();

    public TableColumn<Product, Integer> idCol = new TableColumn<>();
    public TableColumn<Product, String> nameCol = new TableColumn<>();
    public TableColumn<Product, String> shortDescriptionCol = new TableColumn<>();
    public TableColumn<Product, String> brandCol = new TableColumn<>();
    public TableColumn<Product, String> categoryCol = new TableColumn<>();
    public TableColumn<Product, Double> listPriceCol = new TableColumn<>();
    public TableColumn<Product, String> costCol = new TableColumn<>();
    public Label errorMessage;
    public Label nameErrorMessage;
    public Label listPriceValueErrorMessage;
    public Label listPriceNullErrorMessage;
    private final ObservableList<Product> list = FXCollections.observableArrayList(loadTable());

    public void create() {
        try {
            clearErrorMessages();
            if (listPrice.getText().isEmpty()) {
                if (prodName.getText().isEmpty()) throw new NullPointerException("both are empty");
                throw new NullPointerException("listprice is empty");
            }

            double cst = cost.getText().isEmpty() ? -1.0 : Double.parseDouble(cost.getText());

            Product product = new Product(Product.findNextId(), prodName.getText(), shortDescription.getText(), brand.getText(), category.getText(), Double.parseDouble(listPrice.getText()), cst);

            ProductController.insert(product);
            clearTextFields();
            reloadTable();
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
                    errorMessage.setVisible(true);
                    System.out.println(e);
            }
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("Name can't be null")) {
                nameErrorMessage.setVisible(true);
            }
            if (e.getMessage().equals("List Price can't be negative")) {
                listPriceValueErrorMessage.setVisible(true);
            }
            System.out.println(e);
        } catch (Exception e) {
            errorMessage.setVisible(true);
            System.out.println(e);
        }
    }

    public void update() {
        try {
            clearErrorMessages();
            if (listPrice.getText().isEmpty()) {
                if (prodName.getText().isEmpty()) throw new NullPointerException("both are empty");
                throw new NullPointerException("listprice is empty");
            }

            double cst = cost.getText() == null ? -1.0 : Double.parseDouble(cost.getText());

            Product old = ProductController.consult(Integer.parseInt(id.getText()));
            Product product = new Product(Integer.parseInt(id.getText()), prodName.getText(), shortDescription.getText(), brand.getText(), category.getText(), Double.parseDouble(listPrice.getText()), cst);
            ProductController.update(old, product);
            clearTextFields();
            reloadTable();

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
                    errorMessage.setVisible(true);
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
        clearErrorMessages();
        try {
            if (!(searchField.getText().isEmpty())) {
                Product product = ProductController.consult(ProductController.getIdByProductName(searchField.getText()));
                table.getItems().clear();
                table.getItems().add(product);

            } else {
                reloadTable();
            }
        } catch (SQLException e) {
            errorMessage.setVisible(true);
            System.out.println(e);
        }
    }

    public void delete() {
        clearErrorMessages();
        try {
            ProductController.delete(Integer.parseInt(id.getText()));
            Product.removeId(Integer.parseInt(id.getText()));
            clearTextFields();
            reloadTable();
        } catch (Exception e) {
            errorMessage.setVisible(true);
            System.out.println(e);
        }
    }

    public LinkedList<Product> loadTable() {
        try {
            return ProductController.getAllProducts();
        } catch (SQLException e) {
            errorMessage.setVisible(true);
            System.out.println(e);
            return null;
        }
    }

    private void reloadTable() {
        table.getItems().setAll(loadTable());
    }

    public void clearTextFields() {
        id.clear();
        prodName.clear();
        shortDescription.clear();
        brand.clear();
        category.clear();
        listPrice.clear();
        cost.clear();
    }

    private void clearErrorMessages() {
        listPriceValueErrorMessage.setVisible(false);
        listPriceNullErrorMessage.setVisible(false);
        nameErrorMessage.setVisible(false);
        errorMessage.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        idCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        shortDescriptionCol.setCellValueFactory(new PropertyValueFactory<Product, String>("shortDescription"));
        brandCol.setCellValueFactory(new PropertyValueFactory<Product, String>("brand"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
        listPriceCol.setCellValueFactory(new PropertyValueFactory<Product, Double>("listPrice"));
        costCol.setCellValueFactory(new PropertyValueFactory<Product, String>("costString"));
        table.setItems(list);
        try {
            Product.readIds();
        } catch (SQLException e) {
            errorMessage.setVisible(true);
            System.out.println(e);
        }

        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
            @Override
            public void changed(ObservableValue<? extends Product> observableValue, Product product, Product t1) {
                if (t1 != null) {
                    id.setText("" + t1.getId());
                    prodName.setText(t1.getName());
                    shortDescription.setText(t1.getShortDescription());
                    brand.setText(t1.getBrand());
                    category.setText(t1.getCategory());
                    listPrice.setText("" + t1.getListPrice());
                    cost.setText(t1.getCostString());
                }

            }
        });

    }
}
