package com.example.lab8;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public ComboBox comboBox;
    public TextField textInput;
    public DatePicker dateInput;
    public TextField timeInput;
    ObservableList<Task> tasks = FXCollections.observableArrayList();
    @FXML
    public TableView<Task> taskTable;
    @FXML
     public TableColumn<Task, Long> idColumn;
    public TableColumn<Task, String> idTarget;
    public TableColumn<Task, String> idLocalDateTime;
    String typeOfData;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> typeofdata = FXCollections.observableArrayList(new String[]{TaskFabrica.RAM, TaskFabrica.BD, TaskFabrica.FILE});
        comboBox.setItems(typeofdata);

        comboBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                tasks.clear();
                typeOfData = typeofdata.get((Integer) newValue);
                tasks.addAll(TaskFabrica.createDAOtask(typeOfData).getAllTasks());
                taskTable.setItems(tasks);
            }
        });



        idColumn.setCellValueFactory(new PropertyValueFactory<Task, Long>("id"));
        idTarget.setCellValueFactory(new PropertyValueFactory<Task, String>("target"));
        idLocalDateTime.setCellValueFactory(new PropertyValueFactory<Task, String>("dt"));

        taskTable.setItems(tasks);

        taskTable.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                textInput.setText(taskTable.getItems().get((Integer)newValue).getTarget());
                timeInput.setText(taskTable.getItems().get((Integer)newValue).getDt());
            }
        });
    }

    public void addButton(ActionEvent event) {
        Task task = new Task(
                (long) (Math.random() * 100),
                textInput.getText(),
                timeInput.getText()
                );
        System.out.println(task);
        TaskFabrica.createDAOtask(typeOfData).addTask(task);

        tasks.clear();
        tasks.addAll(TaskFabrica.createDAOtask(typeOfData).getAllTasks());
        taskTable.setItems(tasks);
    }

    public void saveButton(ActionEvent event) {
        Task task = taskTable.getSelectionModel().getSelectedItem();

        Task taskUpd = new Task(
                task.getId(),
                textInput.getText(),
                timeInput.getText()
        );

        TaskFabrica.createDAOtask(typeOfData).updateTask(taskUpd);

        tasks.clear();
        tasks.addAll(TaskFabrica.createDAOtask(typeOfData).getAllTasks());
        taskTable.setItems(tasks);
    }

    public void deleteButton(ActionEvent event) {
        Task task = taskTable.getSelectionModel().getSelectedItem();
        TaskFabrica.createDAOtask(typeOfData).deleteTask((int) task.id);

        tasks.clear();
        tasks.addAll(TaskFabrica.createDAOtask(typeOfData).getAllTasks());
        taskTable.setItems(tasks);
    }
}
