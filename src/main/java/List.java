import java.util.Arrays;

public class List {
    private static int numItems = 0;
    private Task TaskList[] = new Task[100];

    public void addTask(String task) {
        Task myTask = new Task(task);
        TaskList[numItems] = myTask;
        //System.out.println(myTask);
        //System.out.println(Arrays.toString(TaskList));
        addTaskNum();
    }

    public void addTaskNum() {
        numItems++;
    }

    public void printList() {
        if (numItems == 0) {
            System.out.println("whoops nothing to see here");
            return;
        }
        for (int i = 0; i < numItems; i++) {
            System.out.println( i+1 + ". " + TaskList[i] );
        }
    }

    public void markDone(int index) {
        TaskList[index-1].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(TaskList[index-1]);
    }
}