module ru.nsu.palkin.task_2_3_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.nsu.palkin.task_2_3_1 to javafx.fxml;
    exports ru.nsu.palkin.task_2_3_1;
    exports ru.nsu.palkin.task_2_3_1.View;
    opens ru.nsu.palkin.task_2_3_1.View to javafx.fxml;
    exports ru.nsu.palkin.task_2_3_1.Controller;
    opens ru.nsu.palkin.task_2_3_1.Controller to javafx.fxml;
    exports ru.nsu.palkin.task_2_3_1.Model;
    opens ru.nsu.palkin.task_2_3_1.Model to javafx.fxml;
}