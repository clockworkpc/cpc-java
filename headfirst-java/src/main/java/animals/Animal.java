public abstract class Animal {

  String animalName;

  // Summary

  public void animalSummary() {
    isAnimalKingdomMember();
    hasCirculatorySystem();
    canBreathe();
  }

  // Attribute
  public String animalKingdomMember() {
    return animalAttributeTemplate("a member of the Animal kingdom");
  }

  public void isAnimalKingdomMember() {
    System.out.println(animalKingdomMember());
  }

  // // Feature


  public String circulatorySystem() {
    return animalFeatureTemplate("a circulatory system");
  }

  public void hasCirculatorySystem() {
    System.out.println(circulatorySystem());
  }

  // // Ability

  public String breathe() {
    return animalAbilityTemplate("breathe");
  }

  public void canBreathe() {
    System.out.println(breathe());
  }

  public String animalAttributeTemplate(String animalAttribute) {
    return indefiniteArticle() + " " + animalName + " " + "is" + " " + animalAttribute;
  }

  public String animalFeatureTemplate(String animalFeature) {
    return indefiniteArticle() + " " + animalName + " " + "has" + " " + animalFeature;
  }

  public String animalAbilityTemplate(String animalAbility) {
    return indefiniteArticle() + " " + animalName + " " + "can" + " " + animalAbility;
  }

  public boolean firstLetterIsAVowel() {
    return animalName.toLowerCase().matches("^[aeiouâãäåæçèéêëìíîïðñòóôõøùúûü].+");
  }

  public String indefiniteArticle() {
    if (firstLetterIsAVowel()) {
      return "An";
    } else {
      return "A";
    }
  }

  public void startsWithAVowel() {
    System.out.println(animalName + " " + "starts with a vowel: " + firstLetterIsAVowel());
  }

  public void setAnimalName(String name) {
    animalName = name;
  }

    public String getAnimalName() {
    return animalName;
  }






  // public String eat() {
  //   return animalAbilityTemplate("eat");
  // }
  //
  // public void eats() {
  //   System.out.println(eat());
  // }
  //
  // public String sleep() {
  //   return animalAbilityTemplate("sleep");
  // }
  //
  // public void sleeps() {
  //   System.out.println(sleep());
  // }


  // public String eat() {
  //   return "This ANIMAL can eat";
  // }
  //
  // public String sleep() {
  //   return "This ANIMAL can sleep";
  // }
  //
  // public String reproduce() {
  //   return "This ANIMAL can reproduce";
  // }
  //
  // public String die() {
  //   return "This ANIMAL can die";
  // }
}

//   Kingdom:	Animalia
// Phylum:	Chordata
// Class:	Mammalia
// Order:	Carnivora
// Family:	Canidae
// Genus:	Canis
// Species:	C. lupus
// Subspecies:	C. l. familiaris
