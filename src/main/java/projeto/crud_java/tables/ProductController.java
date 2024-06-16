package projeto.crud_java.tables;

import javafx.scene.chart.PieChart;
import projeto.crud_java.beans.Product;
import projeto.crud_java.dbConection.DataBaseUtility;


public class ProductController {
    //TODO: Implementar os métodos de atualizar, consultar, cadastrar e remover produtos do BD

    public void insert(Product product) throws Exception {
        String pName = product.getName();
        String col = "(";
        String val = "(";

        if (!product.getShortDescription().equals("None")){
            col += "shortDescriprion, ";
        }

        String sql = col + ") VALUES " + val + ")";

        DataBaseUtility.insert(sql);

    }
}
