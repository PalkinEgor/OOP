module ru.nsu.palkin.task_2_3_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.nsu.palkin.task_2_3_1 to javafx.fxml;
    exports ru.nsu.palkin.task_2_3_1;
}