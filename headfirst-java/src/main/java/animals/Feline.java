public abstract class Feline extends Mammal implements Quadruped {

  public void felineSummary() {
    mammalSummary();
    hasLegs();
    hasClaws();
  }

  public String legs() {
    String legsNumber = LEGS_NUMBER + " " + "legs";
    return animalFeatureTemplate(legsNumber);
  }

  public void hasLegs() {
    System.out.println(legs());
  }

  public String claws() {
    return animalFeatureTemplate("retractable claws");
  }

  public void hasClaws() {
    System.out.println(claws());
  }


}
