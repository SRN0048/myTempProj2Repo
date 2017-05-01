package scheduler;

import java.util.*;

public class Scheduler {
  public static int STEPS = 80;
  private Problem problem = null;
  private int bestStep;   // the index of the best performance iteration
  private double bestValue; // the value of the best performance

  public Scheduler() { }

  public void setProblem(Problem problem) {
    this.problem = problem;
    bestStep = 0;
    bestValue = 1e9;
    problem.init();
  }

  public int bestStep() {
    return bestStep;
  }
  
  public double bestValue() {
    return bestValue;
  }
  
  public void schedule(double gain) {
    Iterator iterator = problem.getResources();
    Resource resource;
    while(iterator.hasNext()) {
      resource = (Resource) iterator.next();
      resource.reset();
      resource.setGain(gain);
    }

    // repeats the scheduling step 80 times
    for (int step = 0; step < STEPS; step++) {
      double totPerformance = 0.0;
      // every resource schedules its activities
      iterator = problem.getResources();
      while(iterator.hasNext())
        ((Resource) iterator.next()).schedule();
      // evaluates the total performance
      iterator = problem.getResources();
      while(iterator.hasNext()) 
        totPerformance += ((Resource) iterator.next()).getPerformance();
      // evaluates the performance of this scheduling step
      if (totPerformance < bestValue) {
        bestValue = totPerformance;
        // this is the best performance up to now, thus stores the solution
        iterator = problem.getResources();
        while(iterator.hasNext())
          ((Resource) iterator.next()).store();
        bestStep = step;
      }
    }

    // restores the solution corresponding to the best performance step
    iterator = problem.getResources();
    while(iterator.hasNext())
      ((Resource) iterator.next()).restore();
  }
}
