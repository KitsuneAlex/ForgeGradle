package net.minecraftforge.gradle.userdev.dependency;

import groovy.lang.Closure;
import net.minecraftforge.gradle.userdev.util.MutableAttributeContainer;
import org.gradle.api.Action;
import org.gradle.api.artifacts.*;
import org.gradle.api.attributes.Attribute;
import org.gradle.api.attributes.AttributeContainer;
import org.gradle.api.capabilities.Capability;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class RemappedExternalModuleDependency implements ExternalModuleDependency {
    public static final Attribute<Boolean> ATTRIBUTE = Attribute.of("remapped", Boolean.class);

    private final ExternalModuleDependency dependency;
    private final MutableAttributeContainer attributes;

    public RemappedExternalModuleDependency(ExternalModuleDependency dependency) {
        this.dependency = dependency;
        attributes = new MutableAttributeContainer();
        attributes.attributes(dependency.getAttributes()); // Copy base attributes
        attributes.attribute(ATTRIBUTE, true); // Add marker for remapped dependencies
    }

    @Override
    public boolean isChanging() {
        return dependency.isChanging();
    }

    @Override
    public @Nonnull ExternalModuleDependency setChanging(boolean changing) {
        return dependency.setChanging(changing);
    }

    @Override
    public @Nonnull ExternalModuleDependency copy() {
        return new RemappedExternalModuleDependency(dependency);
    }

    @Override
    public boolean isForce() {
        return dependency.isForce();
    }

    @Override
    public void version(@Nonnull Action<? super MutableVersionConstraint> configureAction) {
        dependency.version(configureAction);
    }

    @Override
    public @Nonnull VersionConstraint getVersionConstraint() {
        return dependency.getVersionConstraint();
    }

    @Override
    public @Nonnull ModuleDependency exclude(@Nonnull Map<String, String> excludeProperties) {
        return dependency.exclude(excludeProperties);
    }

    @Override
    public @Nonnull Set<ExcludeRule> getExcludeRules() {
        return dependency.getExcludeRules();
    }

    @Override
    public @Nonnull Set<DependencyArtifact> getArtifacts() {
        return dependency.getArtifacts();
    }

    @Override
    public @Nonnull ModuleDependency addArtifact(@Nonnull DependencyArtifact artifact) {
        return dependency.addArtifact(artifact);
    }

    @Override
    public @Nonnull DependencyArtifact artifact(@Nonnull Closure configureClosure) {
        return dependency.artifact(configureClosure);
    }

    @Override
    public @Nonnull DependencyArtifact artifact(@Nonnull Action<? super DependencyArtifact> configureAction) {
        return dependency.artifact(configureAction);
    }

    @Override
    public boolean isTransitive() {
        return dependency.isTransitive();
    }

    @Override
    public @Nonnull ModuleDependency setTransitive(boolean transitive) {
        return dependency.setTransitive(transitive);
    }

    @Override
    public @Nullable String getTargetConfiguration() {
        return dependency.getTargetConfiguration();
    }

    @Override
    public void setTargetConfiguration(@Nullable String name) {
        dependency.setTargetConfiguration(name);
    }

    @Override
    public @Nonnull AttributeContainer getAttributes() {
        return attributes;
    }

    @Override
    public @Nonnull ModuleDependency attributes(@Nonnull Action<? super AttributeContainer> configureAction) {
        configureAction.execute(attributes);
        return this;
    }

    @Override
    public @Nonnull ModuleDependency capabilities(@Nonnull Action<? super ModuleDependencyCapabilitiesHandler> configureAction) {
        return dependency.capabilities(configureAction);
    }

    @Override
    public @Nonnull List<Capability> getRequestedCapabilities() {
        return dependency.getRequestedCapabilities();
    }

    @Override
    public void endorseStrictVersions() {
        dependency.endorseStrictVersions();
    }

    @Override
    public void doNotEndorseStrictVersions() {
        dependency.doNotEndorseStrictVersions();
    }

    @Override
    public boolean isEndorsingStrictVersions() {
        return dependency.isEndorsingStrictVersions();
    }

    @Override
    public @Nonnull String getGroup() {
        return Objects.requireNonNull(dependency.getGroup());
    }

    @Override
    public @Nonnull String getName() {
        return dependency.getName();
    }

    @Override
    public @Nullable String getVersion() {
        return dependency.getVersion();
    }

    @Override
    public boolean contentEquals(@Nonnull Dependency dependency) {
        return dependency.contentEquals(dependency);
    }

    @Override
    public @Nullable String getReason() {
        return dependency.getReason();
    }

    @Override
    public void because(@Nullable String reason) {
        dependency.because(reason);
    }

    @Override
    public boolean matchesStrictly(@Nonnull ModuleVersionIdentifier identifier) {
        return dependency.matchesStrictly(identifier);
    }

    @Override
    public @Nonnull ModuleIdentifier getModule() {
        return dependency.getModule();
    }
}
