import java.util.Scanner;

class Job {
    String name;
    int priority;
    int duration;

    public Job(String name, int priority, int duration) {
        this.name = name;
        this.priority = priority;
        this.duration = duration;
    }
}

class Heap {
    Job[] elements;
    int size;

    public Heap(int capacity) {
        elements = new Job[capacity];
        size = 0;
    }

    public void add(Job job) {
        elements[size++] = job;
        heapifyUp(size - 1);
    }

    public void heapifyUp(int index) {
        int parent = (index - 1) / 2;
        if (index > 0 && elements[index].priority > elements[parent].priority) {
            swap(index, parent);
            heapifyUp(parent);
        }
    }

    public Job removeMax() {
        Job max = elements[0];
        elements[0] = elements[--size];
        heapifyDown(0);
        return max;
    }

    public void heapifyDown(int index) {
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        if (left < size && elements[left].priority > elements[largest].priority) {
            largest = left;
        }
        if (right < size && elements[right].priority > elements[largest].priority) {
            largest = right;
        }
        if (largest != index) {
            swap(index, largest);
            heapifyDown(largest);
        }
    }

    public void swap(int i, int j) {
        Job temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
}

public class JobScheduler {
    public static void main(String[] args) {
        Heap heap = new Heap(10);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Job Scheduler");
            System.out.println("1. Add Job");
            System.out.println("2. Schedule Jobs");
            System.out.println("3. Run Scheduled Jobs");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline left-over

            switch (option) {
                case 1:
                    System.out.print("Enter job name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter job priority: ");
                    int priority = scanner.nextInt();
                    System.out.print("Enter job duration: ");
                    int duration = scanner.nextInt();
                    scanner.nextLine(); // consume newline left-over
                    heap.add(new Job(name, priority, duration));
                    break;
                case 2:
                    System.out.println("Scheduled Jobs:");
                    while (heap.size > 0) {
                        Job job = heap.removeMax();
                        System.out.println(job.name + " (Priority: " + job.priority + ", Duration: " + job.duration + ")");
                    }
                    break;
                case 3:
                    // Run scheduled jobs logic
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }
}