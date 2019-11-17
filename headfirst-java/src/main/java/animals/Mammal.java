public abstract class Mammal extends Animal {
  public void mammalSummary() {
    animalSummary();
    isWarmBlooded();
  }

  public String warmBlooded() {
    return animalAttributeTemplate("warm blooded");
  }

  public void isWarmBlooded() {
    System.out.println(warmBlooded());
  }

  // public String warmBlooded() {
  //   return animalAttributeTemplate("warm blooded");
  // }
  //
  // public void isWarmBlooded() {
  //   System.out.println(warmBlooded());
  // }
}
