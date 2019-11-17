public abstract class Canine extends Mammal implements Quadruped {

  public void canineSummary() {
    mammalSummary();
    hasLegs();
    hasDentition();
  }

  public String legs() {
    String legsNumber = LEGS_NUMBER + " " + "legs";
    return animalFeatureTemplate(legsNumber);
  }

  public void hasLegs() {
    System.out.println(legs());
  }

  public String dentition() {
    return animalFeatureTemplate("a strong jaw and a prominent pre-molar");
  }

  public void hasDentition() {
    System.out.println(dentition());
  }

}
