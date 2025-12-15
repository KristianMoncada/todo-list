package models;
// What a todo is
public class Todo {
    private int id;
    private boolean isCompleted;
    private String title;

    public Todo(int id, boolean isCompleted, String title) {
        this.id = id;
        this.isCompleted = isCompleted;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return id + " - " + title + (isCompleted ? " (done)" : " (pending)");
    }

    
}
