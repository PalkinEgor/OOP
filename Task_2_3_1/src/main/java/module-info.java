module ru.nsu.palkin.task_2_3_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.nsu.palkin to javafx.fxml;
    exports ru.nsu.palkin;
    exports ru.nsu.palkin.View;
    opens ru.nsu.palkin.View to javafx.fxml;
    exports ru.nsu.palkin.Controller;
    opens ru.nsu.palkin.Controller to javafx.fxml;
    exports ru.nsu.palkin.Model;
    opens ru.nsu.palkin.Model to javafx.fxml;
}