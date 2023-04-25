package com.example.lab8;

import com.example.lab8.modules.ListTask;
import com.example.lab8.modules.Task;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class HelloController {

    public TableView table;

    @FXML
    protected void onHelloButtonClick() {
        ListTask listTask = new ListTask(3);

        for (Task element : listTask.getTasks() ) {
            System.out.println(element.toString());
        }

        table.setItems((ObservableList) listTask.getTasks());

    }
}
