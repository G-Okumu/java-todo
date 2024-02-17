package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class TaskTest {
    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
        Task.clearAllTasks(); // clear out all the tasks before each test
    }

    @Test
    public void NewTaskObjectGetsCorrectlyCreated_true() throws Exception {
        Task task = setupNewTask();
        Assertions.assertEquals(true, task instanceof Task);
    }

    @Test
    public void TaskInstantiatesWithDescription_true() throws Exception {
        Task task = setupNewTask();
        Assertions.assertEquals("Mow the lawn", task.getDescription());
    }

    @Test
    public void AllTasksAreCorrectlyReturned_true() throws Exception {
        Task task = setupNewTask();
        Task otherTask = new Task("Brush the cat", "Brushing");
        Assertions.assertEquals(2, Task.getAll().size());
    }

    @Test
    public void AllTasksContainsAllTasks_true() throws Exception {
        Task task = setupNewTask();
        Task otherTask = new Task("Brush the cat", "Brushing");
        Assertions.assertTrue(Task.getAll().contains(task));
        Assertions.assertTrue(Task.getAll().contains(otherTask));
    }

    @Test
    public void isCompletedPropertyIsFalseAfterInstantiation() throws Exception {
        Task task = setupNewTask();
        Assertions.assertEquals(false, task.getCompleted()); //should never start as completed
    }

    @Test
    public void getCreatedAtInstantiatesWithCurrentTimeToday() throws Exception {
    Task task = setupNewTask();
        Assertions.assertEquals(LocalDateTime.now().getDayOfWeek(), task.getCreatedAt().getDayOfWeek());
    }

    @Test
    public void tasksInstantiateWithId() throws Exception {
        Task task = setupNewTask();
        Assertions.assertEquals(1, task.getId());
    }

    @Test
    public void findReturnsCorrectTask() throws Exception {
        Task task = setupNewTask();
        Assertions.assertEquals(1, Task.findById(task.getId()).getId());
    }


    @Test
    public void findReturnsCorrectTaskWhenMoreThanOneTaskExists() throws Exception {
        Task task = setupNewTask();
        Task otherTask = new Task("Brush the cat", "Brushing");
        Assertions.assertEquals(2, Task.findById(otherTask.getId()).getId());
    }

    @Test
    public void updateChangesTaskContent() throws Exception {
        Task task = setupNewTask();
        String formerContent = task.getDescription();
        LocalDateTime formerDate = task.getCreatedAt();
        int formerId = task.getId();

        task.update("Floss the cat");

        Assertions.assertEquals(formerId, task.getId());
        Assertions.assertEquals(formerDate, task.getCreatedAt());
        Assertions.assertNotEquals(formerContent, task.getDescription());
    }

    @Test
    public void deleteDeletesASpecificTask() throws Exception {
        Task task = setupNewTask();
        Task otherTask = new Task("Brush the cat", "Brushing");
        task.deleteTask();
        Assertions.assertEquals(1, Task.getAll().size()); //one is left
        Assertions.assertEquals(Task.getAll().get(0).getId(), 2); //the one that was deleted has the id of 2
    }

    // @Test
    // public void deleteAllTasksDeletesAllTasks() throws Exception {
    //     Task task = setupNewTask();
    //     Task otherTask = setupNewTask();
    //     Task.clearAllTasks();
    //     assertEquals(0, Task.getAll().size());
    // }


    //helper methods
    public Task setupNewTask(){
        return new Task("Mow the lawn", "Brushing");
    }
}