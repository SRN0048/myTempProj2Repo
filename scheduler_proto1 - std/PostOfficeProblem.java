import scheduler.*;
public class PostOfficeProblem extends Problem {

  public PostOfficeProblem() {  }

  public void init() {
    super.clear();
    Task task1 = new Task();
    task1.addActivity(new Activity("Zone11",  5.0, 10.0, 15.0, 20.0));
    task1.addActivity(new Activity("Zone12",  5.0, 10.0, 15.0, 20.0));
    task1.addActivity(new Activity("Zone13", 15.0, 17.0, 22.0, 25.0));
    task1.addActivity(new Activity("Zone14", 20.0, 22.0, 27.0, 35.0));
    task1.addActivity(new Activity("Zone15", 20.0, 30.0, 35.0, 40.0));
    task1.addActivity(new Activity("Zone16", 20.0, 30.0, 35.0, 45.0));
    task1.addActivity(new Activity("Zone17", 25.0, 30.0, 35.0, 50.0));
    this.addTask(task1);

    Resource carrier1 = new Resource("C1");
    carrier1.addTask(task1);
    this.addResource(carrier1);

    Task task2 = new Task();
    task2.addActivity(new Activity("Zone21",  5.0,  7.0, 12.0, 15.0));
    task2.addActivity(new Activity("Zone22", 10.0, 15.0, 20.0, 30.0));
    task2.addActivity(new Activity("Zone23", 15.0, 25.0, 30.0, 40.0));
    task2.addActivity(new Activity("Zone24", 25.0, 30.0, 35.0, 55.0));
    task2.addActivity(new Activity("Zone25", 20.0, 30.0, 35.0, 45.0));
    task2.addActivity(new Activity("Zone26", 15.0, 30.0, 35.0, 45.0));
    task2.addActivity(new Activity("Zone27", 10.0, 30.0, 35.0, 45.0));
    this.addTask(task2);

    Resource carrier2 = new Resource("C2");
    carrier2.addTask(task2);
    this.addResource(carrier2);
  }
}