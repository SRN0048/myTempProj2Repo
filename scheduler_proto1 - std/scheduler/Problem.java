package scheduler;

import java.util.*;

public abstract class Problem {
  private ArrayList tasks = new ArrayList();
  private ArrayList resources = new ArrayList();

  public Problem() { }

  public void addTask(Task task) {
    tasks.add(task);
  }

  public void addResource(Resource resource) {
    resources.add(resource);
  }

  public void clear() {
    tasks.removeAll(tasks);
    resources.removeAll(resources);
  }

  public Resource getResource(int id) {
    return (Resource) resources.get(id);
  }
  public Iterator getResources() {
    return resources.iterator();
  }

  public Activity getActivity(int taskID, int activityID) {
    Task task = (Task) tasks.get(taskID);
    if(task == null) return null;
    
    Activity activity = (Activity) task.getActivity(activityID);
    
    return activity;
  }

  public abstract void init();
}
