package taskscheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

class Task {
    private String title;
    private Date dueDate;
    private Priority priority;

    public Task(String title, Date dueDate, Priority priority) {
        this.title = title;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Priority getPriority() {
        return priority;
    }
}

enum Priority {
    LOW,
    MEDIUM,
    HIGH
}

class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }
}

class NotificationService {
    public void sendNotification(String message) {
        System.out.println("Notification: " + message);
    }
}

public class TaskSchedulerApp {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        NotificationService notificationService = new NotificationService();

        // Add some sample tasks
        taskManager.addTask(new Task("Task 1", new Date(), Priority.MEDIUM));
        taskManager.addTask(new Task("Task 2", new Date(), Priority.HIGH));
        taskManager.addTask(new Task("Task 3", new Date(), Priority.LOW));

        // Schedule notifications for tasks
        Timer timer = new Timer();
        for (Task task : taskManager.getTasks()) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    notificationService.sendNotification("Due date for task '" + task.getTitle() + "' reached.");
                }
            }, task.getDueDate());
        }

        // Simulate the application running (you can implement a user interface here)
        // For simplicity, we'll just sleep for a while to allow notifications to be sent
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}