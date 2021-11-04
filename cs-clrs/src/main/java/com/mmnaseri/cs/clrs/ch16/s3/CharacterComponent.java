package com.mmnaseri.cs.clrs.ch16.s3;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/25/15, 12:15 AM)
 */
public class CharacterComponent implements Comparable<CharacterComponent> {

  private final Character character;
  private final int frequency;

  public CharacterComponent(Character character, int frequency) {
    this.character = character;
    this.frequency = frequency;
  }

  public Character getCharacter() {
    return character;
  }

  public int getFrequency() {
    return frequency;
  }

  @Override
  public int compareTo(CharacterComponent that) {
    return Integer.compare(this.getFrequency(), that.getFrequency());
  }

  @Override
  public String toString() {
    return (getCharacter() != null ? getCharacter() + ":" : "") + getFrequency();
  }
}
