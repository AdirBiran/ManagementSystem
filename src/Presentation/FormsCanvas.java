package Presentation;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class FormsCanvas extends Canvas {


    public void paintRegistrationForm(){
        GraphicsContext graphicsContext = getGraphicsContext2D();
        graphicsContext.rect(10, 10, 25, 10);
    }


}
