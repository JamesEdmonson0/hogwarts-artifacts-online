package edu.tcu.cs.hogwartsartifactsonline.artifact;

import java.io.Serializable;

import edu.tcu.cs.hogwartsartifactsonline.wizard.Wizard;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Artifact implements Serializable {

  @Id
  private String id;

  private String name;

  private String description;

  private String imageUrl;
  
  @ManyToOne
  private Wizard owner;

  public Artifact() {
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
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

  public String getImageUrl() {
    return this.imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public Wizard getOwner() {
    return this.owner;
  }

  public void setOwner(Wizard owner) {
    this.owner = owner;
  }

}