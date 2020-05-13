package edu.berkeley.cs.app;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FuzzySearch_T {
  private FuzzySearch fuzzySearch;

  @Before
  public void setup() {
    fuzzySearch = new FuzzySearch();
  }

  @Test(expected = RuntimeException.class)
  public void testEmptyNeedle() {
    fuzzySearch.indexOf(".", "");
  }

  @Test
  public void testLongNeedle() {
    Assert.assertEquals(-1, fuzzySearch.indexOf("A", "AAA"));
  }

  @Test
  public void testNeedleNotFound() {
    Assert.assertEquals(-1, fuzzySearch.indexOf("ACTGGTCA", "TGAT"));
  }

  @Test
  public void testSingleCharacter() {
    Assert.assertEquals(0, fuzzySearch.indexOf("A", "A"));
  }

  @Test
  public void testSingleWildcard() {
    Assert.assertEquals(0, fuzzySearch.indexOf(".", "A"));
    Assert.assertEquals(0, fuzzySearch.indexOf(".", "T"));
    Assert.assertEquals(0, fuzzySearch.indexOf(".", "C"));
    Assert.assertEquals(0, fuzzySearch.indexOf(".", "G"));
  }

  @Test
  public void testMatchNoWildcard() {
    Assert.assertEquals(3, fuzzySearch.indexOf("ACATGGTC", "TGGT"));
  }

  @Test
  public void testFrontMatch() {
    Assert.assertEquals(0, fuzzySearch.indexOf("ACATGGTC", "ACAT"));
    Assert.assertEquals(0, fuzzySearch.indexOf(".CATGGTC", "ACAT"));
    Assert.assertEquals(0, fuzzySearch.indexOf("..ATGGTC", "ACAT"));
    Assert.assertEquals(0, fuzzySearch.indexOf("...TGGTC", "ACAT"));
    Assert.assertEquals(0, fuzzySearch.indexOf("....GGTC", "ACAT"));
  }

  @Test
  public void testFirstMatch() {
    Assert.assertEquals(5, fuzzySearch.indexOf("GAGCTACATGGACAT", "ACAT"));
  }

  @Test
  public void testFoundPermutation() {
    Assert.assertEquals(-1, fuzzySearch.indexOf("ATCAAGGACACA", "GAGC"));
  }

  @Test
  public void testFuzzySearch() {
    Assert.assertEquals(2, fuzzySearch.indexOf("A.AC.ATG", "ACT"));
    Assert.assertEquals(3, fuzzySearch.indexOf("ACT.G.CA", "GGAC"));
  }
}
