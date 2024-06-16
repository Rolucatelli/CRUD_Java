package projeto.crud_java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
            stage.setTitle("Sistema");
            stage.setScene(new Scene(root));
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


//    @Override
//    public void start(Stage stage) throws Exception {

//        Group root = new Group();
//        Scene scene = new Scene(root, 900, 600, Color.LIGHTBLUE);
//        Text text = new Text("Teste do text");
//        text.setX(50);
//        text.setY(50);
//        text.setFont(Font.font(48));
//        text.setFill(Color.rgb(255, 0 ,255)); // Setta a cor do texto
//
//        root.getChildren().add(text);  // Adiciona o texto no grupo de nós
//
//        Line line = new Line();
//        line.setStartX(200);
//        line.setStartY(200);
//        line.setEndX(500);
//        line.setEndY(200);
//        line.setStrokeWidth(5);
//        line.setStroke(Color.rgb(0, 0, 255));
//        line.setOpacity(0.9);
//
//        Rectangle rectangle = new Rectangle();
//        rectangle.setX(300);
//        rectangle.setY(300);
//        rectangle.setWidth(150);
//        rectangle.setHeight(150);
//        rectangle.setFill(Color.RED);
//        line.setStrokeWidth(2);// Adiciona uma borda
//        rectangle.setStroke(Color.BLACK); // Adiciona a cor da borda
//
//        Polygon triangle = new Polygon();
//        triangle.getPoints().setAll(
//                200.0, 200.0,
//                300.0, 300.0,
//                200.0, 300.0 ); // Cordenadas dos pontos do poligono
//        triangle.setFill(Color.GREEN);
//
//        Circle circle = new Circle();
//        circle.setCenterX(500);
//        circle.setCenterY(500);
//        circle.setRadius(50);
//        circle.setFill(Color.BROWN);
//
//        // Adicionando imagens
////        Image image = new Image(imagem.png);  // Se a imagem estiver no src, não precisa adicionar caminho
////        ImageView imageView = new ImageView(image);
////        imageView.setX(400);
////        imageView.setY(400);
//
//
//        root.getChildren().add(line);
//        root.getChildren().add(rectangle);
//        root.getChildren().add(triangle);
//        root.getChildren().add(circle);
////        root.getChildren().add(imageView);
//
//        stage.setTitle("Demonstração Stage");
////        stage.setWidth(900);
////        stage.setHeight(600);
//
////        stage.setX(100);  // Ajusta a posição da janela
////        stage.setY(100);
//
////        stage.setResizable(false);
////        stage.setFullScreen(true);
//
//
//        stage.setScene(scene);
//        stage.show();
//    }
}