public abstract class Canine extends Animal implements Mammal, Quadruped {

  public String warmBlooded() {
    return animalAttributeTemplate("warm blooded");
  }

  public void isWarmBlooded() {
    System.out.println(warmBlooded());
  }

  public String fourLegs() {
    String legsNumber = LEGS_NUMBER + " " + "legs";
    return animalFeatureTemplate(legsNumber);
  }

  public void hasFourLegs() {
    System.out.println(fourLegs());
  }




  // public String canineDentition() {
  //   return animalFeatureTemplate("powerful molars and incissors");
  // }
  //
  // public void hasCanineDentition() {
  //   System.out.println(canineDentition());
  // }
}
