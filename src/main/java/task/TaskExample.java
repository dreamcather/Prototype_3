package task;

public class TaskExample {
    public TaskExample() {
    }

    public String WriteA(String additioanal) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("A "+ additioanal);
        return "";
    }

    public String WriteB() {
        System.out.println("B");
        return "";
    }

}
