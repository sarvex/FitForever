package com.fitforever.ailment;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sarvex Jatasra on 9/16/2015.
 */
public class Ailment implements Parcelable {
  private int image;
  private String name;
  private String description;

  protected Ailment(Parcel in) {
  }

  public Ailment(int image, String name, String description) {
    this.image = image;
    this.name = name;
    this.description = description;
  }

  public static final Creator<Ailment> CREATOR = new Creator<Ailment>() {
    @Override
    public Ailment createFromParcel(Parcel in) {
      return new Ailment(in);
    }

    @Override
    public Ailment[] newArray(int size) {
      return new Ailment[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
  }

  public int getImage() {
    return this.image;
  }

  public void setImage(int image) {
    this.image = image;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
