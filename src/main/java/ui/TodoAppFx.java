package ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import models.Todo;
import services.TodoService;

public class TodoAppFx extends Application {

    private final TodoService todoService = new TodoService();
    private final ObservableList<Todo> todos = FXCollections.observableArrayList();

    private ListView<Todo> listView;
    private TextField titleField;
    private CheckBox completedCheck;
    private Label statusLabel;

    @Override
    public void start(Stage stage) {
        // Input
        titleField = new TextField();
        titleField.setPromptText("Todo title");

        Button addBtn = new Button("Add");
        addBtn.setOnAction(e -> addTodo());

        HBox inputRow = new HBox(10, titleField, addBtn);
        inputRow.setPadding(new Insets(10));

        // List
        listView = new ListView<>(todos);
        listView.setPrefHeight(250);

        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, selected) -> {
            if (selected != null) {
                titleField.setText(selected.getTitle());
                completedCheck.setSelected(selected.getIsCompleted());
            }
        });

        // Editor
        completedCheck = new CheckBox("Completed");

        Button updateBtn = new Button("Update");
        updateBtn.setOnAction(e -> updateTodo());

        Button deleteBtn = new Button("Delete");
        deleteBtn.setOnAction(e -> deleteTodo());

        HBox actions = new HBox(10, updateBtn, deleteBtn);

        VBox editor = new VBox(10, completedCheck, actions);
        editor.setPadding(new Insets(10));

        // Status
        statusLabel = new Label("Ready");

        // Layout
        VBox root = new VBox(10, inputRow, listView, editor, statusLabel);
        root.setPadding(new Insets(10));

        refreshList();

        Scene scene = new Scene(root, 400, 420);
        stage.setTitle("Todo App");
        stage.setScene(scene);
        stage.show();
    }

    private void addTodo() {
        String title = titleField.getText().trim();
        if (title.isEmpty()) {
            statusLabel.setText("Title cannot be empty");
            return;
        }

        todoService.createTodo(title);
        titleField.clear();
        completedCheck.setSelected(false);
        refreshList();
        statusLabel.setText("Todo added");
    }

    private void updateTodo() {
        Todo selected = listView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setText("Select a todo first");
            return;
        }

        todoService.updateTodo(
                selected.getId(),
                titleField.getText(),
                completedCheck.isSelected()
        );

        refreshList();
        statusLabel.setText("Todo updated");
    }

    private void deleteTodo() {
        Todo selected = listView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setText("Select a todo first");
            return;
        }

        todoService.removeTodo(selected.getId());
        refreshList();
        titleField.clear();
        completedCheck.setSelected(false);
        statusLabel.setText("Todo deleted");
    }

    private void refreshList() {
        todos.setAll(todoService.readAllTodo());
    }
}
