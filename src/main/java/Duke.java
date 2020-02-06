import duke.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) throws IOException, DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greetUser();
        chat();
    }

    private static String readCommand() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        System.out.println("Type something");
        str = br.readLine().toLowerCase();
        return str;
    }

    public static void greetUser() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you");
        System.out.println("____________________________________________________________");

    }

    public static void finishConversation() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

    }


    public static void chat() throws IOException, DukeException {
        System.out.println("____________________________________________________________");
        String str = readCommand();
        System.out.println("____________________________________________________________");

        if (str.equals("bye")) {
            finishConversation();
        } else if (str.equals("add task")) {
            System.out.println("____________________________________________________________");
            System.out.println("Please add no more than 100 tasks");
            System.out.println("____________________________________________________________");
            addTaskScreen();
        } else if (str.equals("list")) {
            listTask();
            chat();
        }else {
            System.out.println(str);
            System.out.println("____________________________________________________________");
            chat();
        }
    }


    public static void addTaskScreen() throws IOException, DukeException {
        System.out.println("____________________________________________________________");
        System.out.println("Please add tasks");
        System.out.println("____________________________________________________________");
        String str = readCommand();
        wrongInputFormatError(str);
        if (str.equals("bye")) {
            finishConversation();
        } else if (!str.equals("done") && str.contains("done")) {
            System.out.println("Mark task done");
            markDone(str);
            addTaskScreen();
        } else if (str.equals("list")) {
            listTask();
            addTaskScreen();
        } else if ((str.contains("todo"))) {
            missingTaskError(str);
            int dividerPosition = str.indexOf(" ");
            String task = str.substring(dividerPosition + 1);
            ToDos newTask = new ToDos(task);
            addTask(newTask);
            addTaskScreen();
        } else if ((str.contains("deadline"))) {
            missingTaskError(str);
            missingDateTimeError(str);
            int dividerPosition = str.indexOf(" ");
            int dividerPositionSlash = str.indexOf("/");
            String task = str.substring(dividerPosition + 1 , dividerPositionSlash);
            String by = str.substring(dividerPositionSlash + 4);
            Deadlines newTask = new Deadlines(task,by);
            addTask(newTask);
            addTaskScreen();
        } else if ((str.contains("event"))) {
            missingTaskError(str);
            missingDateTimeError(str);
            int dividerPosition = str.indexOf(" ");
            int dividerPositionSlash = str.indexOf("/");
            String task = str.substring(dividerPosition + 1 , dividerPositionSlash);
            String calender  = str.substring(dividerPositionSlash+4);
            int dateAndTime = calender.indexOf(" ");
            String date  = calender.substring(0, dateAndTime);
            String time  = calender.substring(dateAndTime+1);
            Events newTask = new Events(task,date,time);
            addTask(newTask);
            addTaskScreen();
        }


    }

    public static void addTask(Task newTask) throws IOException {
        System.out.println("Got it. I've added this task: " + newTask.description);
        taskList.add(newTask);
        System.out.println("added : " + newTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list") ;
    }

    public static void listTask(){
        System.out.println("____________________________________________________________");
        int i = 0;
        while (i < taskList.size()) {
            int j = i + 1;
            System.out.println(j + " ." +  taskList.get(i).toString() );
            i++;
        }

        System.out.println("____________________________________________________________");
    }

    public  static  void markDone(String str) throws IOException {
        int dividerPosition = str.indexOf(" ");
        String index = str.substring(dividerPosition+1);
        int i = Integer.parseInt(index);
        System.out.println(i);
        if(i == 0){
            System.out.println("____________________________________________________________");
            System.out.println("Invalid index");
            System.out.println("____________________________________________________________");
        }else{
            taskList.get(i-1).markDone();
        }
        listTask();
    }


    public static void missingDateTimeError(String input) throws DukeException{
        if(!input.contains("/")){
            throw  new DukeException("OOPS!!!Please specify more details");
        }
    }

    public static void missingTaskError(String input) throws DukeException{
        if(input.equals("todo") || input.equals("deadline") || input.equals("event")){
            throw  new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public static void wrongInputFormatError(String input) throws DukeException{
        if(!input.contains("todo") || !input.contains("deadline") || !input.contains("event")){
            throw  new DukeException("OOPS!!!Wrong input format. Enter todo/deadline/event + task name");
        }
    }
}




