package scheduler;

public class Activity {
  private int id = 0;
  private String name;
  private double releaseTime;
  private double activationTime; 
  private double terminationTime; 
  private double dueTime; 
  private double duration; 
  // Member variables used to store best-performance values
  private double bestActivationTime = 0.0;
  private double bestTerminationTime = 0.0;
  //*******************************************************sss
  private double tempActivationTime = 0.0;
  private double tempTerminationTime = 0.0;

  // The constructor initialises the temporal parameters and computes the
  // activity's duration. During the execution of the scheduling algorithm, only
  // the activation time and the termination time will be updated. The reset()
  // method initialises the activation time and termination time before any new run
  // of the scheduling algorithm. The toString() method returns the string that
  // formats the temporal parameters for their textual visualization on the screen.
  public Activity(String name, double r, double a, double t, double d) {
    this.name = name;
    this.releaseTime = r;
    this.activationTime = a;
    this.terminationTime = t;
    this.dueTime = d;
    this.duration = t - a;
  }

  public int getID() { return id; }
  public String getName() { return name; }
  public double getActivation() { return activationTime; }
  public double getRelease() { return releaseTime; }
  public double getTermination() { return terminationTime; }
  public double getDueTime() { return dueTime; }
  public double getDuration() { return duration; }

  public void setID(int id) {
    this.id = id;
  }

  // The next two methods set the values of release time and due time. If
  // necessary, they change the values of activation time and termination
  // time consistently
  public void setDueTime(double time) {
    if (time < 0.0)
      return;
    if (time > this.dueTime || (time - this.duration) > this.releaseTime)
      this.dueTime = time;

    if (dueTime < terminationTime) {
      terminationTime = dueTime;
      activationTime = terminationTime - duration;
    }
  }
  
  public void setReleaseTime(double time) {
    // YOUR CODE here
  }

  public void reset() {
    activationTime = 0.0;
    terminationTime = activationTime + duration;
  }

  //    The next two methods update the temporal parameters of the activity.
  //    The serialize() method enforces the resources and temporal constraints.
  //    The update() method takes into account the activity preferences; in
  //    particular, it updates the activation time in order to start the
  //    activity after the release time and to complete it as closest as
  //    possible to the due date. The parameter gain represents the strength
  //    of an activity's preferences.
  public void serialize(Activity previous) {
    if (previous.terminationTime > this.activationTime)
      this.activationTime = previous.terminationTime;
    this.terminationTime = this.activationTime + this.duration;
  }

  public void update(double gain) {
    activationTime += gain * (dueTime - terminationTime);

    if (activationTime < releaseTime)
      activationTime += gain * (releaseTime - activationTime);

    terminationTime = activationTime + duration;
  }

  //    The getPerformance() method implements the formula described in
  //    class, where the weights on the earliness and tardiness
  //    performance parameters are set to 1.0.
  public double getPerformance() {
    // YOUR CODE here
    double pe=0.0;
    double pt=0.0;
    
    //calculate pe
    if(this.releaseTime>this.activationTime){
     pe = 1.0*(this.releaseTime-this.activationTime);
    }else if(this.releaseTime<=this.activationTime){
     pe = (double)0; 
    }
    
    //calculate pt
    if(this.terminationTime>this.dueTime){
     pt = 1.0*(this.terminationTime-this.dueTime); 
    }else if(this.terminationTime<=this.dueTime){
     pt = (double)0; 
    }
    return (double)pe+pt;
  }

  // The store() method is used to store an activity's temporal parameters that
  // correspond to the best performance solution found by the scheduling
  // algorithm during a given iteration step.
  public void store() {
    // YOUR CODE here
    
  }

  //    The restore() method is invoked at the end of the scheduling process
  //    to copy in an activity's temporal parameters the values
  //    corresponding to the best performance solution.
  public void restore() {
    activationTime = tempActivationTime;
    terminationTime = tempTerminationTime;
  }
  public String toString() {
    return name
        + " ("
        + releaseTime
        + ", "
        + activationTime
        + ", "
        + terminationTime
        + ", "
        + dueTime
        + ")";
  }
}
