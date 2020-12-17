module SortingPractice {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires transitive javafx.graphics;
    requires javafx.base;

    opens sorting.practice to javafx.fxml;
    exports sorting.practice;
}