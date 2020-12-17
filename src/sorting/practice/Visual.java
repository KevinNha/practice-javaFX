package sorting.practice;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Visual extends Application {
    private static final int MAX_INPUTS = 500;
    private static final int MAX_WIDTH = 2550;
    private static final int MAX_HEIGHT = 1200;
    
    private Rectangle[] rectangles = new Rectangle[MAX_INPUTS];
    private int[] heights = new int[MAX_INPUTS];

    @Override
    public void start(Stage primaryStage) {
        Random rand = new Random();
        double width = 2;
        int yCoord = 600;
        double xCoord = 5;
        
        for (int i = 0; i < MAX_INPUTS; i++) {
            heights[i] = rand.nextInt(800) + 10;
            rectangles[i] = new Rectangle(xCoord, yCoord, width, heights[i]);
            xCoord += 2;
        }
        
        Button submit = new Button("Sort");
        submit.setOnAction(new EventHandler<ActionEvent>() {
           public void handle (ActionEvent e) {
               mergeSort(heights, 0, heights.length - 1);
           }
        });
 
        StackPane layout = new StackPane();
        
        Group root = new Group(rectangles);
        layout.getChildren().add(root);
        layout.getChildren().add(submit);
        
        Scene scene = new Scene(layout, MAX_WIDTH, MAX_HEIGHT);
        
        primaryStage.setTitle("Sorting-Visual");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        

    }
    
    private void merge(int[] array, int left, int middle, int right) {
        int leftSize = middle - left + 1;
        int rightSize = right - middle;
        
        int[] tempL = new int[leftSize];
        int[] tempR = new int[rightSize];
        
        for (int i = 0; i < leftSize; i++) {
            tempL[i] = array[left + i];
        }
        for (int j = 0; j < rightSize; j++) {
            tempR[j] = array[middle + 1 + j];
        }
        
        int leftIndex = 0;
        int rightIndex = 0;
        
        int mergedIndex = left;
        
        while (leftIndex < leftSize && rightIndex < rightSize) {
            if (tempL[leftIndex] < tempR[rightIndex]) {
                array[mergedIndex] = tempL[leftIndex];
                leftIndex++;
            } else {
                array[mergedIndex] = tempR[rightIndex];
                rightIndex++;
            }
            rectangles[mergedIndex].setHeight(heights[mergedIndex]);
            mergedIndex++;
        }
        
        while (leftIndex < leftSize) {
            array[mergedIndex] = tempL[leftIndex];
            leftIndex++;
            rectangles[mergedIndex].setHeight(heights[mergedIndex]);
            mergedIndex++;
        }
        
        while (rightIndex < rightSize) {
            array[mergedIndex] = tempR[rightIndex];
            rightIndex++;
            rectangles[mergedIndex].setHeight(heights[mergedIndex]);
            mergedIndex++;
        }
        
    }
    
    private void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            
            
            merge(array, left, middle, right);
        }
    }
    
    private boolean isMerged(int[] array) {
        for (int i = 1; i < array.length - 1; i++) {
            if (array[0] < array[i] && array[array.length - 1] > array[i]) {
                return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
