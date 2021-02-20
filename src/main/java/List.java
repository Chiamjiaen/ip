public class List {
    private int numItems;
    private Task[] TaskList;

    List(){
        TaskList = new Task[100];
        numItems = 0;
    }

    public void addTaskFromSave(String inputTask) {
        Task userTask = new Task(inputTask.trim());
        TaskList[numItems] = userTask;
        addTaskNum();
    }

    public void addDeadlineFromSave(String description, String date) {
        Deadline deadline = new Deadline(description.trim(), date.trim());
        TaskList[numItems] = deadline;
        addTaskNum();
    }

    public void addEventFromSave(String desciptiion, String date) {
        Event event = new Event(desciptiion.trim(), date.trim());
        TaskList[numItems] = event;
        addTaskNum();
    }

    public void addTask(String inputTask) {
        Task userTask = new Task(inputTask);
        TaskList[numItems] = userTask;
        addTaskNum();
        taskAddedMessage(userTask);
    }

    public void addDeadline(String inputDeadline) {
        String[] deadlineTokens = inputDeadline.split("/by", 2);
        Deadline deadline = new Deadline(deadlineTokens[0].trim(), deadlineTokens[1].trim());
        TaskList[numItems] = deadline;
        addTaskNum();
        taskAddedMessage(deadline);
    }

    public void addEvent(String inputEvent) {
        String[] eventTokens = inputEvent.split("/at", 2);
        Event event = new Event(eventTokens[0].trim(), eventTokens[1].trim());
        TaskList[numItems] = event;
        addTaskNum();
        taskAddedMessage(event);
    }

    public void addTaskNum() {
        numItems++;
    }

    public void printList() {
        if (numItems == 0) {
            System.out.println("whoops nothing to see here");
            return;
        }
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < numItems; i++) {
            System.out.println( i+1 + ". " + TaskList[i].toString());
        }
    }

    public void markDone(int index) {
        TaskList[index-1].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(TaskList[index-1].toString());
    }

    public void markDoneFromSave(int index) {
        TaskList[index-1].markAsDone();
    }

    private void taskAddedMessage(Task inputTask) {
        System.out.println("Got it. I've added this task: \n" + inputTask.toString());
        System.out.println("Now you have " + numItems + " tasks in the list");
    }

    public String taskType(int index) {
        String[] taskView = TaskList[index].toString().split("]");
        String simpletype = taskView[0];
        String isDone = taskView[1];
        String formattedDesc = taskView[2];
        String[] taskFormat = new String[3];
        if (simpletype.contains("E")) {
            taskFormat[0] = "event";
        } else if (simpletype.contains("D")) {
            taskFormat[0] = "deadline";
        } else if (simpletype.contains("T")) {
            taskFormat[0] = "todo";
        }
        if (isDone.contains("\u2713")) {
            taskFormat[1] = "1";
        } else {
            taskFormat[1] = "0";
        }
        if (taskFormat[0].equals("todo")) {
            taskFormat[2] = formattedDesc;
        } else if (taskFormat[0].equals("deadline") || taskFormat[0].equals("event")) {
            taskFormat[2] = TaskList[index].toFormat();
        }
        return (taskFormat[0] + " | " + taskFormat[1] + " | " + taskFormat[2]);
    }
}
