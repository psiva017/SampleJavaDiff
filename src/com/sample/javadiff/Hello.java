package com.sample.javadiff;

import de.danielbechler.diff.ObjectDifferBuilder;
import de.danielbechler.diff.node.DiffNode;
import de.danielbechler.diff.node.Visit;

/**
 * <h1>Sample program</h1>
 * 
 * @author SIVA P
 */

public class Hello {

  private Hello() {}


  /**
   * This is the main method which makes use of Java-Object-diff
   * 
   * @param args Unused
   *
   */
  public static void main(String[] args) {

    // --------- Object Creations ------
    Country country = new Country();
    Country secondCountry = new Country();
    final Revtek firstRevtek = new Revtek();
    final Revtek secondRevtek = new Revtek();

    // ------- Setting values -----------
    country.setCountryCode("IND");
    country.setId(1);
    country.setName("INDIA");

    secondCountry.setCountryCode("US");
    secondCountry.setId(2);
    secondCountry.setName("USA");

    firstRevtek.setFullName("Siva");
    firstRevtek.setAge(22);
    firstRevtek.setId(1);
    firstRevtek.setCountry(country);


    secondRevtek.setFullName("Avinash");
    secondRevtek.setAge(23);
    secondRevtek.setId(1);
    secondRevtek.setCountry(country);

    // --- Comparing Objects----------
    DiffNode diff = ObjectDifferBuilder.buildDefault().compare(firstRevtek, secondRevtek);


    // ------------- Display all changes ---------
    diff.visit(new DiffNode.Visitor() {
      public void node(DiffNode diff, Visit visit) {
        final Object baseValue = diff.canonicalGet(firstRevtek);
        final Object workingValue = diff.canonicalGet(secondRevtek);
        final String message =
            diff.getPath() + " value changed from " + baseValue + " to " + workingValue;
        System.out.println(message);

      }
    });
  }
}

