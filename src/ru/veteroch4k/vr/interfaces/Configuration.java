package ru.veteroch4k.vr.interfaces;

public interface Configuration {
  float sampleRate = 44100;
  int sampleSizeInBits = 16;
  int channels = 2;
  String fileFormat = ".wav";
  boolean signed = true;
  boolean bigEndian = false;

}
