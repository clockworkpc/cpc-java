public class Cat extends Feline {

  String name;

  public Cat(String animalName) {
    this.animalName = animalName;
    setAnimalName(this.animalName);
  }

  public String domesticated() {
    return animalAttributeTemplate("domesticated");
  }

  public void isDomesticated() {
    System.out.println(domesticated());
  }

  public void catSummary() {
    felineSummary();
    isDomesticated();
  }

}
