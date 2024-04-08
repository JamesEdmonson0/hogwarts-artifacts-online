package edu.tcu.cs.hogwartsartifactsonline.wizard;

import edu.tcu.cs.hogwartsartifactsonline.artifact.Artifact;
import edu.tcu.cs.hogwartsartifactsonline.artifact.ArtifactNotFoundException;
import edu.tcu.cs.hogwartsartifactsonline.artifact.ArtifactRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class WizardService {

  private final WizardRepository wizardRepository;

  private final ArtifactRepository artifactRepository;

  public WizardService(WizardRepository wizardRepository, ArtifactRepository artifactRepository) {
    this.wizardRepository = wizardRepository;
    this.artifactRepository = artifactRepository;
  }

  public List<Wizard> findAll() {
    return this.wizardRepository.findAll();
  }

  public Wizard findById(Integer wizardId) {
    return this.wizardRepository.findById(wizardId)
            .orElseThrow(() -> new WizardNotFoundException(wizardId));
  }

  public Wizard save(Wizard newWizard) {
      return this.wizardRepository.save(newWizard);
  }

  public Wizard update(Integer wizardId, Wizard update) {
      return this.wizardRepository.findById(wizardId)
              .map(oldWizard -> {
                  oldWizard.setName(update.getName());
                  return this.wizardRepository.save(oldWizard);
              })
              .orElseThrow(() -> new WizardNotFoundException(wizardId));
  }

  public void delete(Integer wizardId) {
      Wizard wizardToBeDeleted = this.wizardRepository.findById(wizardId)
              .orElseThrow(() -> new WizardNotFoundException(wizardId));

      wizardToBeDeleted.removeAllArtifacts();
      this.wizardRepository.deleteById(wizardId);
  }

  public void assignArtifact(Integer wizardId, String artifactId){
      Artifact artifactToBeAssigned = this.artifactRepository.findById(artifactId)
              .orElseThrow(() -> new ArtifactNotFoundException(artifactId));

      Wizard wizard = this.wizardRepository.findById(wizardId)
              .orElseThrow(() -> new WizardNotFoundException(wizardId));

      if (artifactToBeAssigned.getOwner() != null) {
          artifactToBeAssigned.getOwner().removeArtifact(artifactToBeAssigned);
      }
      wizard.addArtifact(artifactToBeAssigned);
  }

}
