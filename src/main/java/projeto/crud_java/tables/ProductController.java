package projeto.crud_java.tables;

import javafx.scene.chart.PieChart;
import projeto.crud_java.beans.Product;
import projeto.crud_java.dbConection.DataBaseUtility;


public class ProductController {
    //TODO: Implementar os métodos de atualizar, consultar, cadastrar e remover produtos do BD

    public void insert(Product product) throws Exception {
        String pName = product.getName();
        double price = product.getListPrice();
        StringBuilder col = new StringBuilder("(");
        StringBuilder lin = new StringBuilder("(");

        col.append("productName");
        lin.append("'" + pName + "'");

        col.append(", shorDescription");
        if(!(product.getShortDescription().equals("None") || product.getShortDescription().equals(null)))
            lin.append(", '" + product.getShortDescription() + "'");

        col.append(", brand");
        if(!(product.getBrand().equals("Unknow") || product.getBrand().equals(null)))
            lin.append(", '" + product.getBrand() + "'");

        col.append("category");
        if(!(product.getBrand().equals("Unknow") || product.getBrand().equals(null)))
            lin.append(", '" + product.getBrand() + "'");

        col.append("listPrice");
        lin.append(", '" + price + "'");

        col.append("cost");
        if(!(product.getCost() == -1.0))
            lin.append(", '" + product.getBrand() + "'");

        col.append(")");
        lin.append(")");

        String sql = "Colunas " + col.toString() + "Linhas " + lin.toString();

        DataBaseUtility.insert(sql);
    }
}
