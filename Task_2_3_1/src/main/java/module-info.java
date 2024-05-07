module ru.nsu.palkin.task_2_3_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.nsu.palkin to javafx.fxml;
    exports ru.nsu.palkin;
    exports ru.nsu.palkin.view;
    opens ru.nsu.palkin.view to javafx.fxml;
    exports ru.nsu.palkin.controller;
    opens ru.nsu.palkin.controller to javafx.fxml;
    exports ru.nsu.palkin.model;
    opens ru.nsu.palkin.model to javafx.fxml;
}